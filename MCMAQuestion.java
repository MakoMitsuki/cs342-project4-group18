//package cs342hw3;
import java.util.*;
import java.io.PrintWriter;

/* !!!!!!!!! NOTE
 * CHECK THE FUNCTIONALITY OF THESE FUNCTIONS
 * */

public class MCMAQuestion extends MCQuestion{
 protected ArrayList<MCAnswer> studentAnswer = new ArrayList<MCAnswer>();
 public double baseCredit = 0.0;
 
 public MCMAQuestion(String Text, double MaxValue, double BaseCredit)
 {
  super(Text, MaxValue);
  baseCredit = BaseCredit;
 }
 
 public MCMAQuestion(Scanner Scan)
 {
  super(Scan);
  baseCredit = Scan.nextDouble();
  
  // clear the line
    Scan.nextLine();
    
    // get number of answers
    int numAnswers = Scan.nextInt();
    
    // now get the answers
    for(int i = 0; i < numAnswers; i++){
      MCMAAnswer a = new MCMAAnswer(Scan);
      answerArray.add(a);
    }
 }
 
 public Answer getNewAnswer(String t, double creditIfSelected)
 {
  Answer a = new MCMAAnswer(t, creditIfSelected);
  return a;
 }
 
 public Answer getNewAnswer()
 {
  Answer a = new MCMAAnswer("", 0.0);
  return a;
 }
 
 public void getAnswerFromStudent()
 {
  System.out.println("How many answers will be entered for this question?");
  int nA = 1;
  try {
   nA = Integer.parseInt(ScannerFactory.getKS().nextLine());
  }
  catch (Exception e)
  {
   
  }
  while(nA != 0) {
   System.out.println("Enter the text of the answer (Not the letter or index):");
   String sA = "";
   boolean isAnswered = false;
   sA = ScannerFactory.getKS().nextLine();
   while (!isAnswered) {
    for (int i = 0; i < answerArray.size(); i++)
    {
     MCAnswer thisans = answerArray.get(i);
     if (thisans.getCorrect().equalsIgnoreCase(sA))
     {
      studentAnswer.add(thisans);
      isAnswered = true;
      break;
     }
    }
    if(!isAnswered)
    {
     System.out.println("Invalid answer. Try again.");
     sA = ScannerFactory.getKS().nextLine();
    }
   }
   nA--;
  }
  System.out.println(">> STUDENT ANSWERS:");
  for (int j = 0; j < studentAnswer.size(); j++)
  {
   studentAnswer.get(j).print();
   System.out.println();
  }
  System.out.println();
 }
 
 public double getValue()
 {
  Answer a = null;
  double val = baseCredit;
  for (int i = 0; i < studentAnswer.size(); i++)
  {
   val += studentAnswer.get(i).getCredit(a);
  }
  return val;
 }
 
 public void save(PrintWriter pw)
 {
  pw.println("MCMAQuestion");
  super.save(pw);
  pw.println(baseCredit);
  pw.println(answerArray.size());
  for (int i=0; i < answerArray.size(); i++)
  {
   answerArray.get(i).save(pw);
  }
  pw.flush();
 }
 
 public void saveStudentAnswer(PrintWriter pw)
 {
  pw.println("MCMAAnswer");
  pw.println(studentAnswer.size());
  for (int i = 0; i < studentAnswer.size(); i++)
  {
   pw.println(studentAnswer.get(i).getCorrect());
  }
  pw.flush();
 }
 
 public void restoreStudentAnswers(Scanner Scan)
 {
  int nA = Integer.parseInt(Scan.nextLine());
  while(nA != 0) {
   String sA = Scan.nextLine();
   for (int i = 0; i < answerArray.size(); i++)
   {
    MCAnswer thisans = answerArray.get(i);
    if (thisans.getCorrect().equalsIgnoreCase(sA))
    {
     studentAnswer.add(thisans);
     break;
    }
   }
   nA--;
  }
 }
}