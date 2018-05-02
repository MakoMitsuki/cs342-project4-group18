// Michael Pedraza
// mpedra5
// release 4


import java.util.*;
import java.io.*;

//gui
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ExamTakerMain{
  
  
  private static Exam currentExam;
  private static JFrame ExamTakerFrame;
  private static JPanel MainPanel;
  private static JTextArea ExamText;
  private static JTextField studentfield;
  private static boolean turnIn;
  private static boolean examInUse;
  
  public static  Exam loadExamFromFile(Exam exam,StringTokenizer strTok) throws FileNotFoundException {
    //Get filename
    
    try{
      if(strTok.hasMoreTokens()){
        String filename = strTok.nextToken();
        File loadFile = new File(filename);
        
        
        //load into a scanner, then create a new Exam instance
        Scanner sc = new Scanner(loadFile);
        exam = new Exam(sc);
        
        sc.close();//close file
        return exam;//Return reference to exam
      }
      else{
        System.out.println("Error : No file name provided\n");
      }
      return null;
    }
    catch(FileNotFoundException e){
      System.out.println("Error: No such file found\n");
      return null;
    }
  }
  
  public static void loadExamFromFile(File chosenFile){
  Scanner sc;
  try {
   sc = new Scanner(chosenFile);
   currentExam = new Exam(sc);
   sc.close();//close file
  } catch (FileNotFoundException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

  //Return reference to exam
 }
  
  public static void clearJTextArea(){
    ExamText.setText("");
    return;
  }
  
  public static void printExam(Exam exam){
    exam.print();
  }
  
  
  
  public static void initGUI(){
    
    //
    // frame set up
    //
    
    ExamTakerFrame = new JFrame("ExamTaker");
    ExamTakerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ExamTakerFrame.setSize(500, 600);
    ExamTakerFrame.setLocationRelativeTo(null);
    
    Font Arial = new Font("Arial", 0, 12);
    ExamTakerFrame.setFont(Arial);
    ExamTakerFrame.setBackground(Color.white);
    
    //Main panel:
    MainPanel = new JPanel();
    MainPanel.setLayout(new BoxLayout(MainPanel,BoxLayout.Y_AXIS));
    
    // set up exam text zone
    // this is how/where exam will be displayed
    ExamText = new JTextArea(100,100);
    ExamText.setEditable(false);
    PrintStream printStream = new PrintStream(new CustomOutputStream(ExamText));
    System.setOut(printStream);
    System.setErr(printStream);
    
    
    JScrollPane ExamTextFrame = new JScrollPane(ExamText);
    ExamTextFrame.setSize(20, 20);
    MainPanel.add(ExamTextFrame);
    
    
    // following line is useless
    ExamText.setSize(10,10);
    //MainPanel.add(ExamText);    
    
    //
    //Set up buttons
    //
    
    JPanel MenuButtons = new JPanel();
    MenuButtons.setLayout(new BoxLayout(MenuButtons,BoxLayout.X_AXIS));
    
    //
    //LoadExamButton
    //
    
    JButton loadExamButton = new JButton("Load Exam");
    loadExamButton.setToolTipText("Loads a new Exam");
    loadExamButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        
        JFileChooser choose = new JFileChooser();
        int choice = choose.showOpenDialog(choose);
        
//        if(currentExam.isComplete()){
//          System.out.println("You have already loaded and completed an exam.");
//          return;
//                               
//        }
        
        if (choice != JFileChooser.APPROVE_OPTION){ 
          return;
        }
        
        if(examInUse){
          System.out.println("You have already loaded an exam");
        }
        
        else{
               
        
        File a = choose.getSelectedFile();
        //clearJTextArea();
        loadExamFromFile(a);
        examInUse = true;
        currentExam.getAnswerFromStudent(-1);
        
        System.out.println("You have answered every question.  If you would like to"
                             +" change your \nanswer to one of the questions click the CHANGE ANSWER "
                             + "button."); 
        }
      }
    });
    
    JButton changeAnswerButton = new JButton("Change Answer");
    changeAnswerButton.setToolTipText("Allows an answer to be changed");
    changeAnswerButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        
        
        if(examInUse == true){  
        String response = JOptionPane.showInputDialog("Enter the number " 
                          +"of the question you would like to change.", null);
        
        int i = Integer.parseInt(response);
        currentExam.getAnswerFromStudent(i-1);
        }
        
        else{
          System.out.println("You have not yet loaded an exam.  Please click "
                               +"the LOAD EXAM button and select\nan exam file. "
                               +"Once you have completed every question you may change your "
                               +"answers.\n");
        
        }
      }
    });
    
    
    JButton turnInButton = new JButton("Turn In");
    turnInButton.setToolTipText("Turn in your completed exam.");
    turnInButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        
        if(examInUse == true && currentExam.isComplete()){
        double grade = currentExam.getValue();
        // currentExam.saveStudentAnswers();
        System.out.println("You earned a score of: " +grade +" / " +currentExam.size()*5);
        //printExam(currentExam);
        }
        
        else{
          System.out.println("You have not yet loaded an exam.  Please click "
                               +"the LOAD EXAM button and select\nan exam file. "
                               +"Once you have completed every question you may turn in "
                               +"your exam by\nclicking the TURN IN button.");
        }
        
      }
    });
    
    MenuButtons.add(loadExamButton);
    MenuButtons.add(changeAnswerButton);
    MenuButtons.add(turnInButton);

    
    //
    // end buttons
    //    
    
    MainPanel.add(MenuButtons);

    ExamTakerFrame.add(MainPanel);
    // now I make it visible
    ExamTakerFrame.setVisible(true);
    
  }
  
  //
  // main
  //
  
  public static void main (String[] args)throws IOException{
    currentExam = null;
    turnIn = false;
    examInUse = false;
    initGUI();
    
    String name;

        name= JOptionPane.showInputDialog("Enter your name: ");
        System.out.println("Name: "+name +"\n");
    
        System.out.println("Welcome to the ExamTaker application!\n\n"
                      +"Click the Load Exam Button to select an "
                      +"exam to complete.  Read each question's\n "
                      +"instructions carefully as some answers "
                      +"require your approval to be answered.\n");
    
  }
}