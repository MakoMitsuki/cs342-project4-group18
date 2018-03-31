package cs342hw3;
import java.util.Scanner;
import java.io.PrintWriter;

public class SAQuestion extends Question{
	private Scanner scan = new Scanner(System.in);

	public SAQuestion(String Text, double MaxValue) {
		super(Text, MaxValue);
		// initialize Student Answer
		studentAnswer = new SAAnswer("");
		rightAnswer = new SAAnswer("");
	}
	
	/* NEW */
	public SAQuestion(Scanner Scan)
	{
		super(Scan);
	}
	
	public SAAnswer getNewAnswer()
	{
		SAAnswer a = new SAAnswer("");
		return a;
	}
	
	public SAAnswer getNewAnswer(String t)
	{
		SAAnswer a = new SAAnswer(t);
		return a;
	}
	
	public void setRightAnswer(Answer a)
	{
		super.setRightAnswer(a);
	}
	
	public void getAnswerFromStudent()
	{
		// get student input here
		System.out.println("Type in the text of your answer:");
		String sA = "";
		sA = scan.nextLine();
		Answer a = new SAAnswer(sA);
		studentAnswer = a;
		System.out.println(">> STUDENT ANSWER:");
		studentAnswer.print();
		System.out.println();
	}
	
	public double getValue()
	{
		if (studentAnswer.getText().equals(rightAnswer.getText()))
		{
			return maxVal;
		}
		else
		{
			return 0.0;
		}
	}
	
	/* NEW */
	public void save(PrintWriter pw)
	{
		pw.println("SAQuestion");
		pw.println(maxVal);
		pw.println(text);
		rightAnswer.save(pw);
		pw.flush();
	}
	
	public void saveStudentAnswer(PrintWriter pw)
	{
		pw.println("SAAnswer");
		pw.println(studentAnswer.getText());
		pw.flush();
	}
	
	public void restoreStudentAnswers(Scanner Scan)
	{
		String sA = Scan.nextLine();
		studentAnswer = new SAAnswer(sA);
	}
}
