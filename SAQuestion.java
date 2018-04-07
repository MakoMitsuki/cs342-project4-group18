// Michael Pedraza
// mpedra5
//package cs342hw3;
import java.util.*;
import java.io.*;

class SAQuestion extends Question{
  
  //
  // constructor
  //
  
  SAQuestion(String text, double maxVal){
  
    super(text,maxVal);
  }
  
  SAQuestion(Scanner s1){
    
    // get the question and it's max value
    //System.out.println("SAQ");
    super(s1);
    System.out.println("Got the question in SAQuestion.");
    System.out.println("The question obtained is:" +text);
    // now get the answer
    rightAnswer = new SAAnswer(s1);
    System.out.println(rightAnswer.getCorrect());
    
  }
  
  public Answer getNewAnswer(Scanner s1){
    SAAnswer y = new SAAnswer(s1.nextLine());
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
    Scanner s1 = new Scanner(System.in);
    String a1 = s1.nextLine();
    //System.out.println("here is the entered one fresh: " +a1);
    
    
    //
    // the problem was here, I had this as MCSA
    // now it's correct.
    
    
    SAAnswer a = new SAAnswer(a1);
    // answer student entered is 
    studentAnswer = a;
    //System.out.println("second one in from student: ");
    //studentAnswer.print();
   
  }
  
  // why does it freeze here?
  // getCredit is never entered despite being invoked
  public double getValue(){
    Answer a = null;
    return (studentAnswer == null) ? 0.0 : (maxValue * studentAnswer.getCredit(a));
    
  }
  
  public void save(PrintWriter pw)
  {
    pw.println(maxValue);
    pw.println(text); 
  }
  
  
}