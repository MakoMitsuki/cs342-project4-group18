//package cs342;
import java.util.*;
import java.io.*;

class NumAnswer extends Answer{
  
  protected double number;
  protected double tolerance;
  
  public NumAnswer(double val, double tol)
  {
    number = val;
    tolerance = tol;
  
  }
  
  NumAnswer (Scanner s1){
    //System.out.println("enter an answer");
    number = s1.nextDouble();
    //System.out.println(number);
    // clear the line
    //s1.nextLine();
    // get tolerance level
    tolerance = s1.nextDouble();
    
  }
  
  public void print(){
    System.out.println(number);
  }
  
  //
  // getCredit
  //
  
  public double getCredit(Answer rightAnswer){
    
    
    // cast first
    NumAnswer theRightAnswer = (NumAnswer)rightAnswer;    
    double upperBound=0, lowerBound =0;
    double num = theRightAnswer.number;
    double tol = theRightAnswer.tolerance;
    
    upperBound = num + tol;
    lowerBound = num - tol;
    
    if(number <= upperBound && number >= lowerBound  ){
      // correct
      return 1.0;
    }
    return 0.0;
  }
  
  public String getCorrect(){ 
   return "";
  }
  
  public void save(PrintWriter pw)
 {
  pw.println(number);
  pw.println(tolerance);
 }
  
  
  
}