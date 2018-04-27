import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* EXAM GRADER GUI
 * Mika Cabudol (mcabud2)
 * Project 5 - CS342 */

public class ExamGrader{
	private static JTextArea ExamText;
	private static JTextField studentfield;
	private static JTextField examfield;
	private static Exam openexam;
	
	// MAIN GRADE BUTTON
	public static Exam grade (String examfile, String studentfile) throws IOException
	{	
		Exam e = null;
	    File efile = new File("");
	    if (examfile.equals(""))
	    {
	       System.out.println("Unable to find exam file. Please try again");
	    	return e;
	    }
	    else{
	       efile = new File(examfile);
		   // get exam and answers
		   Scanner escan = new Scanner(efile);
		   e = new Exam(escan);
		   escan.close();
	    }

	   File sfile = new File("");
	   boolean isPair = false;
	   if (studentfile.equals(""))
	   {
		   System.out.println("Unable to find student file. Please try again");
		   return null;
		}
	   else
	   {
	      sfile = new File(studentfile);
	      Scanner ascan = new Scanner(sfile);
	      isPair = e.restoreStudentAnswers(ascan);
	      ascan.close();
	      if (!isPair)
	      {
	        System.out.println("Exam file and student file are incompatible. Please try again.");
	        return null;
	      }
	   }
	   e.reportQuestionValues();
	   System.out.println();
	   return e;
	}
	
	public static void createCSV(Exam e)
	{
		if (e != null)
		{
			PrintWriter csvwriter = null;
			try {
			     csvwriter = new PrintWriter(new File("cs342g18.csv"));
			 }
			catch (FileNotFoundException na) {
			     na.printStackTrace();
			 }
			 e.toCSV(csvwriter);
			 csvwriter.close();
			System.out.println("Stored the score report in CSV file 'cs342g18.csv'");
		}
		else
		{
			System.out.println("No exam report can be fetched. Please grade an exam.");
		}
		
	}
	
  public static void main (String[] args){
		JFrame ExamGraderFrame = new JFrame("Exam Grader");
		ExamGraderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ExamGraderFrame.setSize(800, 400);
		ExamGraderFrame.setLocationRelativeTo(null);
		Font Arial = new Font("Arial", 0, 12);
		ExamGraderFrame.setFont(Arial);
		ExamGraderFrame.setBackground(Color.white);
		
      
		// ================= MAIN TEXTBOX ===========================
		ExamText = new JTextArea(50,50);
		ExamText.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(ExamText));
		System.setOut(printStream);
		System.setErr(printStream);
		
		JScrollPane ExamTextFrame = new JScrollPane(ExamText);
		ExamTextFrame.setSize(200, 300);
		
		// ================= FILE TEXTBOXES ========================
		examfield = new JTextField();
		examfield.setMaximumSize( 
			     new Dimension(Integer.MAX_VALUE, examfield.getPreferredSize().height) );
		examfield.setColumns(45);
		examfield.setEditable(true);
		
		studentfield = new JTextField();
		studentfield.setMaximumSize( 
			     new Dimension(Integer.MAX_VALUE, studentfield.getPreferredSize().height) );
		studentfield.setColumns(45);
		studentfield.setEditable(true);
		
		// ==================== BUTTONS ==============================
		// GRADE BUTTON
		JButton gradeButton = new JButton("Grade");
		gradeButton.setToolTipText("Grade the exam");
		gradeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				ExamText.setText("");
				try {
					openexam = grade(examfield.getText(), studentfield.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return;
			}
		});
		gradeButton.setPreferredSize(new Dimension(200, 70));
		
		// BROWSE EXAM BUTTON
		JButton browseExam = new JButton("Browse Exam");
		browseExam.setToolTipText("Browse directory for Exam file.");
		browseExam.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser choose = new JFileChooser();
				choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int choice = choose.showOpenDialog(choose);
				
				if (choice != JFileChooser.APPROVE_OPTION) return;
				examfield.setText(choose.getSelectedFile().getAbsolutePath());
				return;
			}
		});
		browseExam.setPreferredSize(new Dimension(180, 10));
		
		// BROWSE STUDENT BUTTON
		JButton browseStudent = new JButton("Browse Student File");
		browseStudent.setToolTipText("Browse directory for Exam file.");
		browseStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser choose = new JFileChooser();
				choose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int choice = choose.showOpenDialog(choose);
				
				if (choice != JFileChooser.APPROVE_OPTION) return;
				studentfield.setText(choose.getSelectedFile().getAbsolutePath());
				return;
			}
		});
		browseStudent.setPreferredSize(new Dimension(150, 10));
		
		// CREATE CSV BUTTON
		JButton newCSV = new JButton("Create CSV Report");
		newCSV.setToolTipText("Create a CSV Report of this exam grade.");
		newCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				createCSV(openexam);
			}
		});
		
	// ============= LABELS =======================
	JLabel edl = new JLabel("Enter Exam Directory:");
	edl.setHorizontalTextPosition(SwingConstants.LEFT);
	edl.setLabelFor(examfield);
	JLabel sdl = new JLabel("Enter Student Directory:");
	sdl.setLabelFor(studentfield);
	sdl.setHorizontalTextPosition(SwingConstants.LEFT);
		
	//====================== MAIN LAYOUT ======================
	JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
    listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	
	JPanel lPanel = new JPanel();
	lPanel.setLayout(new BoxLayout(lPanel,BoxLayout.X_AXIS));
    lPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	lPanel.add(examfield);
	lPanel.add(Box.createRigidArea(new Dimension(5, 0)));
	lPanel.add(browseExam);
	
	JPanel lPanel2 = new JPanel();
	lPanel2.setLayout(new BoxLayout(lPanel2,BoxLayout.X_AXIS));
    lPanel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	lPanel2.add(studentfield);
	lPanel2.add(Box.createRigidArea(new Dimension(5,0)));
	lPanel2.add(browseStudent);
	
	JPanel lPanel3 = new JPanel();
	lPanel3.setLayout(new BoxLayout(lPanel3,BoxLayout.LINE_AXIS));
    lPanel3.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    lPanel3.add(Box.createHorizontalGlue());
    lPanel3.add(gradeButton);
	
	JPanel viewPanel = new JPanel();
	viewPanel.setLayout(new BoxLayout(viewPanel,BoxLayout.Y_AXIS));
    viewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	viewPanel.add(ExamText);
	viewPanel.add(Box.createRigidArea(new Dimension(0, 5)));
	viewPanel.add(newCSV);
    
    //listPane.add(Box.createHorizontalGlue());
	listPane.add(edl);
	listPane.add(Box.createRigidArea(new Dimension(5, 0)));
	listPane.add(lPanel);
    //listPane.add(Box.createHorizontalGlue());
	listPane.add(sdl);
	listPane.add(Box.createRigidArea(new Dimension(5, 0)));
	listPane.add(lPanel2);
	listPane.add(Box.createRigidArea(new Dimension(5, 0)));
	listPane.add(lPanel3);
	
	JPanel MainPanel = new JPanel();
	MainPanel.setLayout(new BoxLayout(MainPanel,BoxLayout.X_AXIS));
	
    MainPanel.add(listPane, BorderLayout.CENTER);
	MainPanel.add(viewPanel);
	ExamGraderFrame.add(MainPanel);
	ExamGraderFrame.setVisible(true);
	
  }
}