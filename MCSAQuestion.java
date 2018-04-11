package cs342;
import java.util.Scanner;
import java.io.PrintWriter;

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
    // get number of answers
    int numAnswers = Scan.nextInt();
    
    // now get the answers
    for(int i = 0; i < numAnswers; i++){
      MCSAAnswer a = new MCSAAnswer(Scan);
      answerArray.add(a);
    }
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
  
  public void getAnswerFromStudent()
  {
    //
    boolean validResponse = false;
    
    // print the question
    // print();
    
    // get scanner input
    System.out.println("Enter your answer here: ");
    Scanner s1 = new Scanner(System.in);
    String a1 = s1.nextLine();
    char c = Character.toUpperCase(a1.charAt(0));
    int choice = c - 'A';
    
    while(validResponse == false){
      
      if(choice < 0 || choice >= answerArray.size()){
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
        System.out.println("choice: " +choice);
        studentAnswer = answerArray.get(choice);
        System.out.println("the answer selected is " +studentAnswer);
      }
    }
    
    for(int i = 0; i < answerArray.size(); i++){
      answerArray.get(i).setSelected(false);
    }
    
    // now set the student's choice
    answerArray.get(choice).setSelected(true); 
  }
  
  public double getValue()
  {
    double sum = 0.0;
    for(int i = 0; i < answerArray.size(); i++){
      sum += answerArray.get(i).getCredit(rightAnswer);
    }
    return Math.min(1.0,sum) * maxValue;
  }
  
  /* NEW */
  public void save(PrintWriter pw)
  {
    pw.println("MCSAQuestion");
    super.save(pw);
    /*
    pw.println(answerArray.size());
    for (int i=0; i < answerArray.size(); i++)
    {
      answerArray.get(i).save(pw);
    }*/
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
