// Michael Pedraza
// mpedra5

import java.util.*;
import java.io.*;

class SAAnswer extends Answer{
  protected String text;
  
  public SAAnswer(String t){
    text = t;
  }
  
  SAAnswer (Scanner s1){
    // got it here
    System.out.println("made it to SAAnswer");
    text = s1.nextLine();
    System.out.println("the answer1:  " +text);
  }
  
  public void print(){
    System.out.println(text);
  }
  
  
  //
  // I am never getting here and I don't know why.
  // I was able to get here in the previous release.
  //
  
  public double getCredit(Answer rightAnswer){
    
    boolean x = false;  // do string comparison
    System.out.println("before!!!!!!!!!!!!!!!!!!!!!!  " );
    
    String temp = rightAnswer.getCorrect();
    System.out.println("after!!!!!!!!!!!!!!!!!!!!!!  " +temp);
    x = text.equalsIgnoreCase(temp);
    
    if(x){
      // answers match
      return 1.0;
    }
    return 0.0;
  }
  
  public String getCorrect()
  {    
    String temp = text; 
    return temp;
  }
  
  public void save(PrintWriter pw)
 {
  pw.println(text);
 }
  
}