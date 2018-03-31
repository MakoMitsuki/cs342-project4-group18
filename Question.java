package cs342hw3;
import java.io.PrintWriter;
import java.util.*;

/*
 * QUESTION CLASS
 * Project 3 - CS342 SPRING 2018
 * Mika Cabudol (mcabud2/672285210)
*/

public abstract class Question {
	protected String text = "";
	protected double maxVal = 0.0;
	protected Answer rightAnswer = null;
	protected Answer studentAnswer = null;
	
	protected Question(String Text, double MaxValue) {
		text = Text;
		MaxValue = maxVal;
	}
	
	/* NEW */
	public Question(Scanner Scan)
	{
		try
		{
			maxVal = Double.parseDouble(Scan.nextLine());
		}
		catch(Exception e)
		{
			//Scan.nextLine();
		}
		text = Scan.nextLine();
	}
	
	public double getMaxVal()
	{
		return maxVal;
	}
	
	public void print()
	{
		System.out.println();
		System.out.println(text);
	}
	
	public void setRightAnswer(Answer a)
	{
		rightAnswer = a;
	}
	
	public abstract Answer getNewAnswer();
	
	public abstract void getAnswerFromStudent();
	
	public abstract double getValue();
	
	/* ============== NEW FUNCTIONS ============= */
	public abstract void save(PrintWriter pw);
	
	public abstract void saveStudentAnswer(PrintWriter pw);
	
	public abstract void restoreStudentAnswers(Scanner Scan);

}