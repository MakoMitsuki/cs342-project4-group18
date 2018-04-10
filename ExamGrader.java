import java.util.*;
import java.io.*;

/* EXAM GRADER
 * Mika Cabudol (mcabud2)
 * Project 4 - CS342 */

public class ExamGrader{
  public static void main (String[] args) throws IOException{
    System.out.println("[[EXAM GRADER]]");
    System.out.println("CS342 Project 4 Part 3 by Mika Cabudol (mcabud2)");
    System.out.println("=================================================");

    String ef = "";
    File efile = new File("");
    while(ef.equals(""))
    {
      System.out.println("Enter the directory of the Exam");
      ef = ScannerFactory.getKS().nextLine();
      if (ef.equals(""))
        System.out.println("Unable to find file. Please try again");
      else{
        efile = new File(ef);
        break;
      }
   }

   String s = "";
   File sfile = new File("");
   while(s.equals(""))
   {
    System.out.println("Enter the directory of the Student Answer");
     s = ScannerFactory.getKS().nextLine();
     if (s.equals(""))
     {
      System.out.println("Unable to find file. Please try again");
     }
     else
     {
      sfile = new File(s);
      break;
     }
   }

   // confirm that they are the same file here
   
   System.out.println("Scanning exam file...");
   // get exam and answers
   Scanner escan = new Scanner(efile);
   Exam e = new Exam(escan);
   escan.close();
   System.out.println("Exam loaded.");
   System.out.println("Scanning student answer file...");
   Scanner ascan = new Scanner(sfile);
   e.restoreStudentAnswers(ascan);
   ascan.close();
   System.out.println("Answer file loaded.");
   System.out.println("Reporting score...");
   
   e.reportQuestionValues();

   System.out.println();
   System.out.println("Storing the score report in CSV file 'cs342g18.csv;...");
   PrintWriter csvwriter = null;
    try {
     csvwriter = new PrintWriter(new File("cs342g18.csv"));
    }
    catch (FileNotFoundException na) {
     na.printStackTrace();
    }
    e.toCSV(csvwriter);
    csvwriter.close();

   System.out.println("=====================[END]======================");
  }
}