//package cs342;
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
  
  public NumQuestion(String text, double maxVal, double tol) {
	  super(text,maxVal);
	  tolerance = tol;
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
    
    // get scanner input
    System.out.println("If you want to skip this question");
    System.out.println("and come back to it later enter: skip");
    System.out.println("otherwise press enter to proceed");
    
    Scanner s1 = new Scanner(System.in);
    String a1 = s1.nextLine();
    
    if(a1.equalsIgnoreCase("skip")){
      System.out.println("You have decided to skip this question.");
      System.out.println("You can return to it once you complete");
      System.out.println("the rest of the exam.");
      answered = false;
    }
    
    else{
      // changes here////////////////////////////////////////////////////
    System.out.println("Enter a numerical answer");
    double a2 = s1.nextDouble();
    
    
    // set the tolerance of 
    NumAnswer a = new NumAnswer(a2, 0.0);
    // answer student entered is 
    studentAnswer = a;
    answered = true;
    }
    
   s1.close();
  }
  
  // why does it freeze here?
  // getCredit is never entered despite being invoked
  public double getValue(){
    return (studentAnswer == null) ? 0.0 : (maxValue * studentAnswer.getCredit(rightAnswer) );
    
  }
  
  public void save(PrintWriter pw)
  {
	pw.println("NumQuestion");
    pw.println(maxValue);
    pw.println(text);
    rightAnswer.save(pw);
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
