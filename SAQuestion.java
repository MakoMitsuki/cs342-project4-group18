//package cs342;
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
    //System.out.println("Got the question in SAQuestion.");
    //System.out.println("The question obtained is:" +text);
    // now get the answer
    rightAnswer = new SAAnswer(s1);
    //System.out.println(rightAnswer.getCorrect());
    
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
    
    System.out.println("If you want to skip this question");
    System.out.println("and come back to it later enter: skip");
    
    Scanner s1 = new Scanner(System.in);
    String a1 = s1.nextLine();
    
    if(a1.equalsIgnoreCase("skip")){
      System.out.println("You have decided to skip this question.");
      System.out.println("You can return to it once you complete");
      System.out.println("the rest of the exam.");
      answered = false;
    }
    
    else{
    
    
    //
    // the problem was here, I had this as MCSA
    // now it's correct.
    
    
    SAAnswer a = new SAAnswer(a1);
    // answer student entered is 
    studentAnswer = a;
    answered = true;
    //System.out.println("second one in from student: ");
    //studentAnswer.print();
    }
   
   
  }
  
  // why does it freeze here?
  // getCredit is never entered despite being invoked
  public double getValue(){
    // an answer object set to null was here
    // and was sent as the paramter.  
    // the correct parameter is now in place.
    return (studentAnswer == null) ? 0.0 : (maxValue * studentAnswer.getCredit(rightAnswer));
    
  }
  
  public void save(PrintWriter pw)
  {
 pw.println("SAQuestion");
 super.save(pw);
 rightAnswer.save(pw);
  }
  
   public void restoreStudentAnswers(Scanner Scan)
   {
     String sA = Scan.nextLine();
     studentAnswer = new SAAnswer(sA);
   }
  
  
}