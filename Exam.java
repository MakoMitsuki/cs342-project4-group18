
//package cs342;

/*
 * Alexander Moreno
 * CS 342 Homework 1
 * NETID: amoren26
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;


public class Exam {
  
  private String name;
  private ArrayList<Question> questionArray;
  private double totalPossibleScore;
  private double totalScore;
  private Scanner scanner;
  
  
  Exam(String testName, Scanner scnr){
    name =  testName;
    questionArray = new ArrayList<Question>();
    totalScore = 0;
    scanner = scnr;
  }
  
  Exam(Scanner Nscanner){
    questionArray = new ArrayList<Question>();
    scanner = ScannerFactory.getKS();
    
    name = Nscanner.nextLine();//first line of file SHOULD be title of Exam
    
    String type = Nscanner.nextLine();
    if(type.equals("")){
      type = Nscanner.nextLine();
    }
    //System.out.println(type);
    while (Nscanner.hasNextLine()){
      
      if(type.equals("MCSAQuestion")){
        questionArray.add(new MCSAQuestion(Nscanner));
      }else if(type.equals("MCMAQuestion")){
        questionArray.add(new MCMAQuestion(Nscanner));
      }
      else if(type.equals("SAQuestion")){
        questionArray.add(new SAQuestion(Nscanner));
      }
   else if (type.equals("NumQuestion")){
    questionArray.add(new NumQuestion(Nscanner));
   }
   
   
   // check if there is a next line
   // this was one of the issues.
   
   if(Nscanner.hasNextLine()){
        type = Nscanner.nextLine();
      }
      
      
      
    }
    
  }
  
  
  
  private void shuffleArray(ArrayList<Question> arr){
    
    int n = arr.size();
    Question temp;
    
    Random random = new Random();
    
    random.nextInt();
    
    for(int i = 0; i < n; i++){
      int newPos = i + random.nextInt(n-i);
      temp = arr.get(i);
      arr.set(i,arr.get(newPos));
      arr.set(newPos, temp);
    }
    
    
  }
  
  public int size(){
    return questionArray.size();
  }
  
  
  public void addQuestion(Question newQuest){
    questionArray.add(newQuest);
  }
  
  public void print(){
    //Print out all Questions with their answers
    System.out.println("Exam " + name + " \n");
    for(int i  = 0;i<questionArray.size();i++){
      System.out.print((i+1) + ". " );
      questionArray.get(i).print();
      System.out.println();
      //questionArray.get(i).getAnswerFromStudent();
    }
  }
  
  
  public void reorderQuestions(){
    shuffleArray(questionArray);
    //Also reorder the answers to all the questions
  }
  
  public void reorderMCAnswers(int pos){
    //Reorder answers of the posth question
    if(questionArray.get(pos) instanceof MCQuestion){
      ((MCQuestion)questionArray.get(pos)).reorderAnswers();
      
    }
    if (pos < 0){
      for(int i = 0;i<questionArray.size();i++){
        if(questionArray.get(i) instanceof MCQuestion){
          ((MCQuestion)questionArray.get(pos)).reorderAnswers();
          
        }
      }
    }
    
  }
  
  //1. if pos is a number greater 0 and less than the index of the questionArray,
  //It should start the getAnswerFromStudent(), which prints out the question and prompts for the 
  //question to be answered
  //2. if pos is less than 0, then all answers are iterated through, each printing the question, then 
  // a text input prompt for each.
  public void getAnswerFromStudent(int pos){
    //Prompt user to enter answer for posth question
    if(pos >= 0 && pos < questionArray.size()){
      questionArray.get(pos).getAnswerFromStudent();
    }else{
      System.out.println("Error: index out of range questions index.");
    }
    
    if (pos < 0){
      for(int i =0; i < questionArray.size();i++){
        System.out.print((i+1) + ". " );
        questionArray.get(i).print();
        questionArray.get(i).getAnswerFromStudent();
      }
    }
    
    
  }
  
  public double getValue(){
    totalPossibleScore = 0;//Reset score before counting score from all questions
    
    //iterate through all questions, retrieve value, then add to total
    for(int i = 0;i<questionArray.size();i++){
      totalPossibleScore += questionArray.get(i).getMaxVal();
      
    }
    
    return totalPossibleScore;
  }
  
  //go through all questions and report their individual values
  public void reportQuestionValues(){
    this.getValue();
    
    for(int i =0;i<questionArray.size();i++){
      totalScore += questionArray.get(i).getValue();
    }
    
    System.out.println("Question results:");
    for (int i  = 0;i<questionArray.size();i++){
      System.out.println("\tScore #" + (i+1) + ": "+ questionArray.get(i).getValue() + "/" + (questionArray.get(i).getMaxVal()));
    }
    
  }
  
  void save(PrintWriter printWrit){
    printWrit.println(name);
    printWrit.println();
    for(int i =0;i<questionArray.size();i++){
      questionArray.get(i).save(printWrit);
      printWrit.println();
    }
    printWrit.println("EOF");
  }
  
  void saveStudentAnswers(PrintWriter printWrit){
    //write student Answers to a file
    for(int i =0;i<questionArray.size();i++){
      questionArray.get(i).saveStudentAnswers(printWrit);
      printWrit.println();
    }
    printWrit.println("EOF");
    
  }
  
  void restoreStudentAnswers(Scanner scanner){
    scanner.nextLine();//skip first line, has name //alt, this line is student name. So add a new student var to exam?
    
    
    for(int i =0; i <questionArray.size();i++){
      questionArray.get(i).restoreStudentAnswers(scanner);
      scanner.nextLine();//skip blank line
    }
    //System.out.print("Answers have been restored.");
    
  }
  
}
