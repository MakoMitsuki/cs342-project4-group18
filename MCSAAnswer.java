import java.util.*;

public class MCSAAnswer extends MCAnswer{
 
 public MCSAAnswer(String Text, double CreditIfSelected) {
  super(Text, CreditIfSelected);
 }
 
 public MCSAAnswer(Scanner scan)
 {
  super(scan);
 }
 
 public double getCredit(Answer a)
 {
  return super.getCredit(a);
 }
 /* !! REMOVED FROM MCSAAnswer
 public double getCredit(Answer rightAnswer)
 {
  if (selected)
   return creditIfSelected;
  else
   return 0.0;
 } */

}
