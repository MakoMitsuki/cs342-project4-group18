// Michael Pedraza
// mpedra5

import java.util.*;
import java.io.*;

class NumQuestion extends Question{
  
  protected double tolerance;
  
  //
  // constructor
  //
  
  
  NumQuestion(Scanner s1){
    
    // get the question and its max value
    //System.out.println("SAQ");
    super(s1);
    // now get the answer
    rightAnswer = new NumAnswer(s1);
    
  }
  
  public Answer getNewAnswer(Scanner s1){
    NumAnswer y = new NumAnswer(s1);
    return y;
  }
 
  public Answer getNewAnswer(){
    
    return new SAAnswer("");
  } 
  
  public Answer getNewAnswer(String text){    
    return new SAAnswer(text);
  }
  
  
  public void getAnswerFromStudent(){
    //print();
    System.out.println("Enter a numerical answer");
    Scanner s1 = new Scanner(System.in);
    double a1 = s1.nextDouble();
    
    
    // set the tolerance of 
    NumAnswer a = new NumAnswer(a1, 0.0);
    // answer student entered is 
    studentAnswer = a;
    
   
  }
  
  // why does it freeze here?
  // getCredit is never entered despite being invoked
  public double getValue(){
    return (studentAnswer == null) ? 0.0 : (maxValue * studentAnswer.getCredit(rightAnswer) );
    
  }
  
  public void save(PrintWriter pw)
  {
    pw.println(maxValue);
    pw.println(text); 
  }
  
  public void restoreStudentAnswers(Scanner Scan)
  {
    double a1 = Scan.nextDouble();
    // set the tolerance of 
    NumAnswer a = new NumAnswer(a1, 0.0);
    // answer student entered is 
    studentAnswer = a;
  }
}