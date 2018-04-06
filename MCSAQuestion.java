//package cs342hw3;
import java.util.Scanner;
import java.io.PrintWriter;

/* !!!!!!!!!!!!!! NOTE
 * BEWARE OF .setSelected() BEING USED AT 
 * GET ANSWER FROM STUDENT
 */

public class MCSAQuestion extends MCQuestion{
 
 public MCSAQuestion(String Text, double MaxValue)
 {
  super(Text, MaxValue);
  studentAnswer = new MCSAAnswer("", 0.0);
  rightAnswer = new MCSAAnswer("", 0.0);
 }
 
 /* NEW */
 public MCSAQuestion(Scanner Scan)
 {
  super(Scan);
  studentAnswer = new MCSAAnswer("", 0.0);
  rightAnswer = new MCSAAnswer("", 0.0);
 }
 
 // MCSA Answer
 public MCSAAnswer getNewAnswer(String text, double creditifSelected)
 {
  MCSAAnswer a = new MCSAAnswer(text, creditifSelected);
  return a;
 }
 
 public Answer getNewAnswer()
 {
  return null;
 }
 
 /* !!!!!!!!!!! beware of setSelected */
 public void getAnswerFromStudent()
 {
  // get student input here
  System.out.println("Enter the text of the answer (Not the letter or index):");
  String sA = "";
  boolean isAnswered = false;
  sA = ScannerFactory.getKS().nextLine();
  while(!isAnswered) {
   for (int i = 0; i < answerArray.size(); i++)
   {
    MCAnswer thisans = answerArray.get(i);
    if (thisans.getCorrect().equalsIgnoreCase(sA))
    {
     studentAnswer = thisans;
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
  System.out.println(">> STUDENT ANSWER:");
  studentAnswer.print();
  System.out.println();
 }
 
 public double getValue()
 {
   Answer a = null;
  return studentAnswer.getCredit(a);
 }
 
 /* NEW */
 public void save(PrintWriter pw)
 {
  pw.println("MCSAQuestion");
  super.save(pw);
  pw.println(answerArray.size());
  for (int i=0; i < answerArray.size(); i++)
  {
   answerArray.get(i).save(pw);
  }
  pw.flush();
 }
 
 public void saveStudentAnswer(PrintWriter pw)
 {
  pw.println("MCSAAnswer");
  pw.println(studentAnswer.getCorrect());
  pw.flush();
 }
 
 public void restoreStudentAnswers(Scanner Scan)
 {
  String sA = Scan.nextLine();
  for (int i = 0; i < answerArray.size(); i++)
  {
   MCAnswer thisans = answerArray.get(i);
   if (thisans.getCorrect().equalsIgnoreCase(sA))
   {
    studentAnswer = thisans;
    break;
   }
  }
 }
}
