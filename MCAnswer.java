import java.util.*;
import java.io.PrintWriter;

public abstract class MCAnswer extends Answer {
 // protected boolean selected = false; removed in Project 3
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
   //System.out.println(creditIfSelected);
   // skip space
   s1.skip(" ");
   // get answer
   text = s1.nextLine();
   //System.out.println(text);
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
 
 /* REMOVED FROM PROJECT 3
  * public double getCreditIfSelected()
 {
  return creditIfSelected;
 }
 
 */
 
 public void setSelected(boolean Selected)
 {
  selected = Selected;
 }
 
 /* NEW */
 public double getCredit(Answer a)
 {
   //System.out.println("this is the credit if selected: " +creditIfSelected);
  return selected ? creditIfSelected : 0.0;
 }
 
 public void save(PrintWriter pw)
 {
  pw.print(creditIfSelected);
  pw.print(" ");
  pw.println(text);
  pw.flush();
 }

}
