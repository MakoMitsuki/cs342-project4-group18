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
    
    Font Arial = new Font("Arial", 0, 14);
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
        
        if (choice != JFileChooser.APPROVE_OPTION){ 
          return;
        }
        
        File a = choose.getSelectedFile();
        //clearJTextArea();
        loadExamFromFile(a);
        currentExam.getAnswerFromStudent(-1);
        
        System.out.println("You have answered every question.  If you would like to"
                             +" change your answer to one of the questions click the CHANGE ANSWER "
                             + "button.");
        
        
        double grade = currentExam.getValue();
        
        System.out.println("You earned a score of: " +grade +" / " +currentExam.size()*5);
        //printExam(currentExam);
        
      }
    });
    
    JButton changeAnswerButton = new JButton("Change Answer");
    changeAnswerButton.setToolTipText("Allows an answer to be changed");
    changeAnswerButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        
        String response = JOptionPane.showInputDialog("Enter the number " 
                          +"of the question you would like to change.", null);
        
        int i = Integer.parseInt(response);
        currentExam.getAnswerFromStudent(i-1);        
      }
    });
    
    MenuButtons.add(loadExamButton);
    MenuButtons.add(changeAnswerButton);

    
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
    initGUI();
    
    String name;

        name= JOptionPane.showInputDialog("Enter your name: ");
        System.out.println("Name: "+name +"\n");
    
        System.out.println("Welcome to the ExamTaker application!\n\n"
                      +"Click the Load Exam Button to select an "
                      +"exam to complete.  Read each question's\n "
                      +"instructions carefully as some answers "
                      +"require your approval to be answered.\n");
    

    
    //
    // added boolean to allow student
    // to let it be known when they
    // are done with the exam.
    //
    
    //boolean done = false;
    //obtain the singleton scanner
    // Scanner s1 = ScannerFactory.getKS();
    
    //System.out.println("Enter the file data path: ");
    // String a2 = s1.nextLine();
    // File fileA = new File(a2);
    // s1 = new Scanner(fileA);  
    
    
    // enter destination portons
//    System.out.println("Enter destination for writing");
//    Scanner s2 = new Scanner(System.in);
//    String a1 = s2.nextLine();
//    File file = new File(a1);
//    PrintWriter pw = new PrintWriter(a1);
    
//    System.out.println("Please enter your name.");
//    String studentName = s2.nextLine();
//    pw.println(studentName);
    
    
//    // create a new instance of the exam
//    Exam e1 = new Exam(s1);
//    
//    // print exam
//    e1.print();
    
    // re order
    // e1.reorderQuestions();
    // e1.print();    
    // save re-ordered exam
    // e1.save(pw);
    // e1.saveStudentAnswers(pw);
    
    // take exam
    // release 4 version
//    System.out.println();
//    System.out.println("Here is the exam.");
//    System.out.println("You are allowed to skip questions but will");
//    System.out.println("not be able to return to them until you have");
//    System.out.println("completed all other questions.");
//    System.out.println("You will also be allowed to change your answer");
//    System.out.println("to a question if needed to once you have completed");
//    System.out.println("the exam.  When you are done enter DONE");
//    System.out.println();
//    e1.getAnswerFromStudent(-1);
    
//    while(!done){
//      if(e1.isComplete()){
//        System.out.println("You completed the exam.  If you would");
//        System.out.println("like submit your exam now enter DONE.  If you");
//        System.out.println("want to change one of your answers enter CHANGE.");
//        Scanner sc = new Scanner(System.in);
//        String option = sc.nextLine();
//        
//        if(option.equalsIgnoreCase("change")){
//          System.out.println("Please enter the number of the question");
//          System.out.println("you would like to change your answer to.");
//          int individualQuestion = sc.nextInt();
//          e1.getAnswerFromStudent(individualQuestion - 1);
//        }
//        
//        else if(option.equalsIgnoreCase("Done")){
//          done = true;
//        }
//        
//        else{
//          System.out.println("invalid response. please try again");
//        } 
//      }  
//    }
//    
//    double grade = e1.getValue();
//    System.out.println("your grade is a score of: " +grade);
//    //System.out.println(e1.getMaxPossible());
//    
//    // close and save the file
//    pw.close();
    
  }
}