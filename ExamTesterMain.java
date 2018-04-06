// Michael Pedraza
// mpedra5


import java.util.*;
import java.io.*;

class ExamTesterMain{
  
  public static void main (String[] args)throws IOException{
    
    System.out.println("Start");
    
    // obtain the singleton scanner
    Scanner s1 = ScannerFactory.getKS();
    
    // enter destination portons
    System.out.println("Enter destination for writing");
    Scanner s2 = new Scanner(System.in);
    String a1 = s2.nextLine();
    File file = new File(a1);
    PrintWriter pw = new PrintWriter(a1);
    
    
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
    e1.getAnswerFromStudent(-1);
    
     double grade = e1.getValue();
     System.out.println("your grade is a score of: " +grade);
     //System.out.println(e1.getMaxPossible());
    
    // close and save the file
    pw.close();

    
  }
}