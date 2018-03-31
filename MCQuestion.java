package cs342hw3;
import java.util.*;
import java.io.PrintWriter;

/*
 * Question for all types of multiple choice
 * contains addAnswer() and reorderAnswers()
 */

public abstract class MCQuestion extends Question{
	protected List<MCAnswer> answers = new ArrayList<MCAnswer>();
	protected char[] letterAns = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	
	public MCQuestion(String Text, double MaxValue)
	{ 
		super(Text, MaxValue);
	}
	
	/* NEW */
	
	public MCQuestion(Scanner Scan)
	{
		super(Scan);
	}
	
	public void print()
	{
		super.print();
		for (int i = 0; i < answers.size(); i++)
		{
			System.out.print(letterAns[i] + ") " );
			answers.get(i).print();
			System.out.println();
		}
	}
	
	public void addAnswer(MCAnswer a)
	{
		answers.add(a);
	}
	
	public void reorderAnswers()
	{
		Collections.shuffle(answers);
	}
	
	/* ============== NEW FUNCTIONS ============= */
	/* The method checks this answer against each of the stored answer choices, 
	 * and if any of them earns credit, then the return value
	 * is this credit times the maxValue for the Question.
	 */
	public abstract double getValue();
	
	public void save (PrintWriter pw)
	{
		pw.println(maxVal);
		pw.println(text);
		pw.flush();
	}
	
	public abstract void saveStudentAnswer(PrintWriter pw);
	
	public abstract void restoreStudentAnswers(Scanner Scan);
}
