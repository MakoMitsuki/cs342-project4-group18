// Michael Pedraza
// mpedra5
import java.util.*;
import java.io.*;

abstract class Answer{
  
  private String Answer;
  

  protected Answer(){}  
  public abstract void print();
  public abstract String getCorrect();
  public abstract double getCredit(Answer rightAnswer);
  
  
}