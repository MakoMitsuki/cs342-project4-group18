<<<<<<< HEAD
// QUESTION: Alexander Moreno

import java.util.Scanner;
import java.io.PrintWriter;



 public abstract class Question {
 
 protected String text;
 protected Answer rightAnswer;
 protected Answer studentAnswer;
 protected double maxValue;
 
 //private Answer AnswerSelected;
 
 
 Question(){
  text = "null";
  rightAnswer = null;
  studentAnswer = null;
  maxValue =  0;
 }
 
 //Scanner constructor
 Question(Scanner scan){
  text = scan.nextLine();
  
 }
 
 Question(String prompt,double max){
  
  text = prompt;
  rightAnswer = null;
  studentAnswer = null;
  maxValue = max;
  
 }
 
 //Basic print out of function text
 public void print(){
  System.out.println(text);
 }
 
 
 public void setRightAnswer(Answer ans){
   rightAnswer = ans;
 }
 
 public Answer getNewAnswer(){
  return null;
 }
 
 // changed from Answer to void
 public abstract void getAnswerFromStudent();
 
 public double getValue(){
  return 0;
  
 }
 
 public double getMaxVal(){
  return maxValue;
 }
 
 public void save(PrintWriter printWrit){
  printWrit.println(maxValue);
  printWrit.println(text);
 }
 
 public void saveStudentAnswers(PrintWriter printWrit){
  
 }
 
 public void restoreStudentAnswers(Scanner scnr){
  //restore student answers
  
 }
}
=======
//remove
//package cs342;

/*
 * Alexander Moreno
 * CS 342 Homework 1
 * NETID: amoren26
 */

import java.util.Scanner;
import java.io.PrintWriter;



 public abstract class Question {
 
 protected String text;
 protected Answer rightAnswer;
 protected Answer studentAnswer;
 protected double maxValue;
 
 //private Answer AnswerSelected;
 
 
 Question(){
  text = "null";
  rightAnswer = null;
  studentAnswer = null;
  maxValue =  0;
 }
 
 //Scanner constructor
 Question(Scanner scan){
   System.out.println("in Question");
   maxValue = scan.nextDouble();
   System.out.println(maxValue);
   // clear the line
   scan.nextLine(); 
   text = scan.nextLine();
   System.out.println(text);
   
 }
 
 Question(String prompt,double max){
  
  text = prompt;
  rightAnswer = null;
  studentAnswer = null;
  maxValue = max;
  
 }
 
 //Basic print out of function text
 public void print(){
  System.out.println(text);
 }
 
 
 public void setRightAnswer(Answer ans){
  
 }
 
 public Answer getNewAnswer(){
  return null;
 }
 
 // changed from Answer to void
 public abstract void getAnswerFromStudent();
 
 public double getValue(){
  return 0;
  
 }
 
 public double getMaxVal(){
  return maxValue;
 }
 
 public void save(PrintWriter printWrit){
  printWrit.println(maxValue);
  printWrit.println(text);
 }
 
 public void saveStudentAnswers(PrintWriter printWrit){
  
 }
 
 public void restoreStudentAnswers(Scanner scnr){
  //restore student answers
  
 }
}
>>>>>>> 745872b6fb95ee1828835d68b2208ddba2d0f158
