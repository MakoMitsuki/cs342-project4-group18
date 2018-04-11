import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.io.PrintWriter;

/* Alexander Moreno (amoren26) */

public class ExamBuilder {
 
 //function definitions
 public void printMenu(){
  System.out.println("These are the available options:\n");
  
  //System.out.println
 }
 
 public static  Exam loadExamFromFile(Exam exam,StringTokenizer strTok) throws FileNotFoundException {
  //Get filename
  
  try{
  if(strTok.hasMoreTokens()){
  String filename = strTok.nextToken();
  File loadFile = new File(filename);
  
  
  //load into a scanner, then create a new Exam instance
  Scanner sc = new Scanner(loadFile);
  exam = new Exam(sc);

  sc.close();//close file
  return exam;//Return reference to exam
  }
  else{
   System.out.println("Error : No file name provided\n");
  }
  return null;
  }
  catch(FileNotFoundException e){
   System.out.println("Error: No such file found\n");
   return null;
  }
 }
 //adds a new question based off of input
 public  static void addNewQuestion(Exam exam,Scanner KBScanner,StringTokenizer strTok){
  
 }
 
 //remove question at qNum-1
 public static  void removeQuestion(Exam exam, StringTokenizer strTok) {
  if(strTok.hasMoreTokens()){
   int NthQ = Integer.parseInt(strTok.nextToken());
   exam.removeNthQuestion(NthQ-1);
  }
 }
 //Reorder Answers or exams
 public static void reorderLists(Exam exam,StringTokenizer strTok){
  if(strTok.hasMoreTokens())
  {
   String arg = strTok.nextToken();
   arg = arg.toLowerCase();
   if(arg.equals("questions")){
    exam.reorderQuestions();
   }
   else if(arg.equals("answers")){
    if(strTok.hasMoreTokens()){
     exam.reorderMCAnswers(Integer.parseInt(strTok.nextToken()));
    }
    else {
     exam.reorderMCAnswers(-1);
    }
    
   }
  }
  else{
   exam.reorderMCAnswers(-1);
   exam.reorderQuestions();
  }
 }
 //Print Exam to screen or file
 public  static void printExam(Exam exam){
  exam.print();
 }
 
 //save exam to a file, filename given by user, if file not found, makes a new one.
 public  static void saveExam(Exam exam, StringTokenizer strTok){
  try {
   if(strTok.hasMoreTokens()){
   //get new filename
   String filename = strTok.nextToken();
   File newFile = new File(filename);
   PrintWriter writer;
   writer = new PrintWriter(newFile);
   exam.save(writer);
   writer.close();
   }
  } catch (FileNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   
  }
 }
 
 //Quit Program
 public  static void Quit(){
  System.exit(0);
 }
 
 
 
 
 public static void main (String[] args) throws FileNotFoundException {
  
  Exam currentExam = null;

  StringTokenizer strTok;
  Scanner InputScanner = ScannerFactory.getKS();
  InputScanner.useDelimiter(" \\t\\n");
  System.out.println("Alexander Moreno\nNETID : amoren26\n Group #: 18\nProgram: ExamBuilder.\n");
  String userInput;
  String userTok;
  //Print intro
  //Enter infinite loop
  
  
  while(true){
   System.out.print("Enter command: ");
   userInput = InputScanner.nextLine();
   strTok = new StringTokenizer(userInput);
   userTok = strTok.nextToken();//Get command token
   userTok= userTok.toLowerCase();
   //System.out.print(userTok);
   if(userTok.equals("quit")){
    Quit();
   }
   else if(userTok.equals("load"))
   {
    currentExam = loadExamFromFile(currentExam,strTok);
   }
   else if(userTok.equals("print")){
    if(currentExam == null){
     System.out.println("Exam is NULL!!!!\n");
    }
    printExam(currentExam);
   }
   else if(userTok.equals("add"))
   {
    
   }
   else if(userTok.equals("remove")){
    removeQuestion(currentExam,strTok);
    
   }
   else if(userTok.equals("save"))
   {
    saveExam(currentExam,strTok);
   }
   else if(userTok.equals("reorder")){
    reorderLists(currentExam,strTok);
   }
  }
  
  
  
  //ending
 }

}
