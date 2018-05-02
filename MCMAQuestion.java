//package cs342;

import java.util.*;
import java.io.PrintWriter;

/* !!!!!!!!! NOTE
 * CHECK THE FUNCTIONALITY OF THESE FUNCTIONS
 * */

public class MCMAQuestion extends MCQuestion{
  protected ArrayList<MCAnswer> studentAnswer = new ArrayList<MCAnswer>();
  public double baseCredit;
  
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
    // clear the line
    Scan.nextLine();
    
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
  
  
  /////////////////////////////////////////////////////////////////////////////////////
  
  public void getAnswerFromStudent()

  {
    //
    boolean validResponse = false;
    
    // print the question
    print();
    
    // get scanner input
    System.out.println("If you want to skip this question");
    System.out.println("and come back to it later enter: skip");
    System.out.println("otherwise press enter to proceed");
    
    Scanner s1 = new Scanner(System.in);
    String a1 = s1.nextLine();
    
    if(a1.equalsIgnoreCase("skip")){
      System.out.println("You have decided to skip this question.");
      System.out.println("You can return to it once you complete");
      System.out.println("the rest of the exam.");
      answered = false;
    }
    
    
    
    else{
    System.out.println("Enter one selection at a time.");
    System.out.println("When finished making selection(s) enter DONE");
    a1 = s1.nextLine();
    char c = Character.toUpperCase(a1.charAt(0));
    int choice = c - 'A';
    
    while(!a1.equalsIgnoreCase("DONE")){
      
      while(validResponse == false){
        
        if(choice < 0 || choice > answerArray.size()){
          System.out.println("Your entry is invalid.  Please retry.");
          System.out.println("Enter your answer here: ");
          a1 = s1.nextLine();
          c = Character.toUpperCase(a1.charAt(0));
          choice = c - 'A';
        }
        
        
        // save a reference to the chosen 
        // answer in studentAnswer
        else{
          validResponse = true;
          c = Character.toUpperCase(a1.charAt(0));
          choice = c - 'A';        
        }
      }
            
      // now set the student's choice
      answerArray.get(choice).setSelected(true); 
      
      // reset
      validResponse = false;
      // prompt for next line
      a1 = s1.nextLine();
    }
    
    answered = true;
  }
    s1.close();
 }
  
  
  ////////////////////////////////////////////////////////////////////////////////////
  
  public double getValue()
  {
  
    double sum = 0.0;
    for(int i = 0; i < answerArray.size(); i++){
      sum += answerArray.get(i).getCredit(rightAnswer);
    }
    //System.out.println("stats: " +sum+  +maxValue+  +baseCredit); 
    return (Math.min(1.0,sum) + baseCredit) * maxValue;
  }
  
  public void save(PrintWriter pw)
  {
    pw.println("MCMAQuestion");
    pw.println(maxValue);
    pw.println(text);
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
