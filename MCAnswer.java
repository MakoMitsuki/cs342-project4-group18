// MCAnswer: Mika Cabudol

import java.util.*;
import java.io.PrintWriter;

public abstract class MCAnswer extends Answer {
 protected String text = "";
 protected double creditIfSelected = 0;
 protected boolean selected;
 
 protected MCAnswer(String Text, double CreditIfSelected) {
  text = Text;
  creditIfSelected = CreditIfSelected;
 }
 
 /* NEW */
 public MCAnswer(Scanner s1)
 {
 
   // get points
   creditIfSelected = s1.nextDouble();
   System.out.println(creditIfSelected);
   // skip space
   s1.skip(" ");
   // get answer
   text = s1.nextLine();
   System.out.println(text);
   selected = false;
  
 }
 
 public void print()
 {
  System.out.print(text);
 }
 
 public String getCorrect()
 {
  return text;
 }
 
 /* NEW */
 public double getCredit()
 {
  return creditIfSelected;
 }
 
 public void save(PrintWriter pw)
 {
  pw.println(creditIfSelected);
  pw.println(text);
  pw.flush();
 }

}
