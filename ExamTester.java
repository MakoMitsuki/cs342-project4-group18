package cs342hw3;
import java.util.*;
import java.io.*;

/*
 * EXAM TESTER CLASS
 * Project 3 - CS342 SPRING 2018
 * Mika Cabudol (mcabud2/672285210)
*/

public class ExamTester {
	public static Question mcsa(Scanner scan)
	{
		//int numQ = 0;
		MCSAQuestion qmcsa = new MCSAQuestion(scan);
		int numQ = Integer.parseInt(scan.nextLine());
		while(numQ != 0)
		{
			MCSAAnswer amcsa = new MCSAAnswer(scan);
			qmcsa.addAnswer(amcsa);
			//next = scan.nextLine();
			numQ--;
		}
		return qmcsa;
	}
	
	public static Question mcma(Scanner scan)
	{
		MCMAQuestion qmcma = new MCMAQuestion(scan);
		int numQ = Integer.parseInt(scan.nextLine());
		while(numQ != 0)
		{
			MCMAAnswer amcma = new MCMAAnswer(scan);
			qmcma.addAnswer(amcma);
			numQ--;
		}
		return qmcma;
	}
	
	public static Question sa(Scanner scan)
	{
		SAQuestion qsa = new SAQuestion(scan);
		SAAnswer asa = new SAAnswer(scan);
		qsa.setRightAnswer(asa);
		return qsa;
	}
	
	public static Exam createExam(Scanner scan)
	{
		Exam e = new Exam(scan);
		scan.nextLine();
		while(scan.hasNextLine())
		{
			String type = scan.nextLine();
			if (type.equals("MCSAQuestion"))
			{
				e.addQuestion(mcsa(scan));
			}
			else if (type.equals("MCMAQuestion"))
			{
				e.addQuestion(mcma(scan));
			}
			else if (type.equals("SAQuestion"))
			{
				e.addQuestion(sa(scan));
			}
		}
		return e;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("[[EXAM CLASSES APPLICATION V3]]");
		System.out.println("CS342 Project 3 by Mika Cabudol (mcabud2)");
		System.out.println("=================================================");
		
		// INSTANTIATE FILE FROM USER INPUT
		// debugging - C:\Users\Mika\Documents\cs342\cs342hw3\cs342hw3\examsample.txt

		System.out.println("Enter the directory of the file where the EXAM will come from:");
		File file = new File(ScannerFactory.getKeyboardScanner().nextLine());
		Scanner fscan = new Scanner(file);
		
		// SCAN AND CREATE THE EXAM
		Exam e = createExam(fscan);
		fscan.close();
		// e.print(); debugging
		
		// REORDER EXAM
		System.out.println("--------------------");
		System.out.println("REORDERING EXAM...");
		e.reorderQuestions();

		
		// CREATE REORDERED EXAM FILE
		System.out.println("--------------------");
		System.out.println("Enter the file/directory to which you want to save this new reordered exam:");
		String fnew = ScannerFactory.getKeyboardScanner().nextLine();

		FileOutputStream reorderedExam = new FileOutputStream(fnew);
		PrintWriter examwriter = new PrintWriter(reorderedExam);
		
		// SAVE EXAM
		e.save(examwriter);
		examwriter.flush();
		examwriter.close();
		System.out.println("New reordered exam succesfully saved in directory.");
		System.out.println("=====================================================");

		
		// CREATE STUDENT FILE
		System.out.println("[[STARTING EXAM]]");
		System.out.println("Enter the file/directory to which you want to save the student answer records:");
		String snew = ScannerFactory.getKeyboardScanner().nextLine();
		
		FileOutputStream sAnsFile = new FileOutputStream(snew);
		PrintWriter answerwriter = new PrintWriter(sAnsFile);
		
		// INPUT STUDENT ANSWERS
		System.out.println("Enter the name of the student.");
		System.out.println("====================");
		answerwriter.println(ScannerFactory.getKeyboardScanner().nextLine());
		answerwriter.flush();
		
		for (int i = 0; i < e.getNumQ(); i++)
		{
			e.getAnswerFromStudent(i);
		}
		
		// SAVE STUDENT ANSWERS
		System.out.println("====================");
		System.out.println("Saving student answers to given directory...");
		e.saveStudentAnswers(answerwriter);
		answerwriter.close();
		
		// e.reportQuestionValues(); // debugging
		
		// CLEAR OLD EXAM DATA (QUESTIONS + ANSWERS)
		System.out.println("Clearing exam data and loading the new exam from given directory.");
		e = null;
		
		// LOAD REVISED EXAM
		Scanner nscan = new Scanner(new File(fnew));
		Exam re = createExam(nscan);
		nscan.close();
		
		// RESTORE STUDENT ANSWERS
		System.out.println("===================");
		Scanner ascan = new Scanner(new File(snew));
		re.restoreStudentAnswers(ascan);
		ascan.close();
		
		// GRADE EXAM
		// Total value of questions
		// report overall score, along with value for each question
		System.out.println();
		re.reportQuestionValues();
		
		
		System.out.println("=====================[END]======================");
	}

}
