import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;

abstract public class MCQuestion extends Question{
  //protected double baseCredit; //Used by child class MCMAQuestion.
  protected  ArrayList<MCAnswer> answerArray;
  private char answerChar;//Character Iterator for question display
  protected int maxAnswers;
  protected MCAnswer rightAnswer;
  protected MCAnswer studentAnswer;
  
  
  MCQuestion(){
    super();
    answerArray = new ArrayList<MCAnswer>();
    answerChar = 'A';
    maxAnswers = 6;
    rightAnswer = null;
    studentAnswer = null;
  }
  
  MCQuestion(Scanner scanner){
    /*moved these lines up to Question.java
     //super.maxValue = Double.parseDouble(scanner.nextLine());
     //super.text = scanner.nextLine();
     */
    super(scanner);
    answerArray = new ArrayList<MCAnswer>();
    //answerChar = 'A';
    //baseCredit = Double.parseDouble(scanner.nextLine());
    //maxAnswers = Integer.parseInt(scanner.nextLine());
    rightAnswer = null;
    studentAnswer = null;
    
  }
  
  MCQuestion(String prompt,double max){
    super(prompt,max);
    answerArray = new ArrayList<MCAnswer>();
    answerChar = 'A';
    maxAnswers = 6;
    rightAnswer = null;
    studentAnswer = null;
    
  }
  
  protected void shuffleArray(ArrayList<MCAnswer> answerArray){
    
    int n = answerArray.size();
    MCAnswer temp;
    
    Random random = new Random();
    
    random.nextInt();
    
    for(int i = 0; i < n; i++){
      int newPos = i + random.nextInt(n-i);
      temp = answerArray.get(i);
      answerArray.set(i,answerArray.get(newPos));
      answerArray.set(newPos, temp);
    }
    
    
  }
  
  //Add an answer to the answer array, will ignore this request if question is at max answers
  
  
  public void addAnswer(MCAnswer ans){
    if(answerArray.size() != maxAnswers){
      answerArray.add( ans);
    }
  }
  
  public void setRightAnswer(MCAnswer ans){
    rightAnswer = ans;
  }
  
  
  public void print(){
    
    super.print();
    
    for(int i = 0; i<answerArray.size(); i++){
      System.out.print("   " +(char)('A' + i)+(". "));
      answerArray.get(i).print();
      System.out.println();
    }  
  }
  
  public void reorderAnswers(){
    shuffleArray(answerArray);
    
  }
  
  
  public double getValue(MCAnswer ans){
    //check answer against all answers
    double creditSum = 0;
    for(int i=0;i<answerArray.size();i++){
      // you took out paramater ans here
      creditSum+= answerArray.get(i).getCredit(ans);
    }
    
    return creditSum;
  }
  
  
  public void save(PrintWriter printWrit){
    super.save(printWrit);
    //printWrit.println(baseCredit);
    printWrit.println(answerArray.size());
    for(int i =0; i<answerArray.size();i++){
      answerArray.get(i).save(printWrit);
      
    }
  }
  
  
// public void saveStudentAnswer(PrintWriter printWrit){
//  studentAnswer.saveStudentAnswers(printWrit);
// }
  
}
