//package cs342hw3;
import java.util.*;

public class MCMAAnswer extends MCAnswer{

 public MCMAAnswer(String Text, double CreditIfSelected) {
  super(Text, CreditIfSelected);
 }
 
 public MCMAAnswer(Scanner scan)
 {
  super(scan);
 }
 
 public double getCredit(Answer a)
 {
  return super.getCredit(a);
 }
}
