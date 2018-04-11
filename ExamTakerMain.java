// Michael Pedraza
// mpedra5
// release 4


import java.util.*;
import java.io.*;

class ExamTakerMain{
  
  public static void main (String[] args)throws IOException{
    
    //
    // added boolean to allow student
    // to let it be known when they
    // are done with the exam.
    //
    
    boolean done = false;
    
    
    
    // obtain the singleton scanner
    Scanner s1 = ScannerFactory.getKS();
    
    System.out.println("Enter the file data path: ");
    String a2 = s1.nextLine();
    File fileA = new File(a2);
    s1 = new Scanner(fileA);  
      
    
    // enter destination portons
    System.out.println("Enter destination for writing");
    Scanner s2 = new Scanner(System.in);
    String a1 = s2.nextLine();
    File file = new File(a1);
    PrintWriter pw = new PrintWriter(a1);
    
    System.out.println("Please enter your name.");
    String studentName = s2.nextLine();
    pw.println(studentName);
    
    
    // create a new instance of the exam
    Exam e1 = new Exam(s1);
    
    // print exam
    e1.print();
    
    // re order
    // e1.reorderQuestions();
    // e1.print();
    
    // save re-ordered exam
    e1.save(pw);
    
    // take exam
    System.out.println();
    System.out.println("Here is the exam.");
    System.out.println("You are allowed to skip questions but will");
    System.out.println("not be able to return to them until you have");
    System.out.println("completed all other questions.");
    System.out.println("You will also be allowed to change your answer");
    System.out.println("to a question if needed to once you have completed");
    System.out.println("the exam.  When you are done enter DONE");
    System.out.println();
    e1.getAnswerFromStudent(-1);
    
    while(!done){
      
      
      
      
      if(e1.isComplete()){
        System.out.println("You completed the exam.  If you would");
        System.out.println("like submit your exam now enter DONE.  If you");
        System.out.println("want to change one of your answers enter CHANGE.");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        
        if(option.equalsIgnoreCase("change")){
          System.out.println("Please enter the number of the question");
          System.out.println("you would like to change your answer to.");
          int individualQuestion = sc.nextInt();
          e1.getAnswerFromStudent(individualQuestion - 1);
        }
        
        else if(option.equalsIgnoreCase("Done")){
          done = true;
        }
        
        else{
          System.out.println("invalid response. please try again");
        }
        
        
      }
      
      
    }
              
              
   
      
    
     double grade = e1.getValue();
     System.out.println("your grade is a score of: " +grade);
     //System.out.println(e1.getMaxPossible());
    
    // close and save the file
    pw.close();

    
  }
}