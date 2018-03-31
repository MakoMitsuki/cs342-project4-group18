package cs342hw3;
import java.io.PrintWriter;
import java.util.*;
/*
 * EXAM CLASS
 * Project 3 - CS342 SPRING 2018
 * Mika Cabudol (mcabud2/672285210)
*/

// !!!!!!!!! REPORT THE VALUE CONTRIBUTED FOR EACH QUESTION

public class Exam {
	private String text = "";
	private List<Question> questions = new ArrayList<Question>();
	
	public Exam (String Text)
	{
		text = Text;
	}
	
	/* NEW */
	public Exam(Scanner Scan)
	{
		text = Scan.nextLine();
	}

	public void addQuestion(Question q)
	{
		questions.add(q);
	}
	
	public int getNumQ()
	{
		return questions.size();
	}

	public void print()
	{
		System.out.println("EXAM: " + text);
		for (int i = 0; i < questions.size(); i++)
		{
			System.out.println("QUESTION #" + (i+1) + ":");
			questions.get(i).print();
			System.out.println();
		}
	}
	
	public void reorderQuestions()
	{
		Collections.shuffle(questions);
	}
	
	public void reorderMCAnswers(int position)
	{
		if (position > 0 && position <= questions.size())
		{
			if (questions.get(position) instanceof MCQuestion) {
				MCQuestion cq = (MCQuestion) questions.get(position);
				cq.reorderAnswers();
			}
			else
			{
				System.out.println("That question is not a multiple choice question.");
			}
		}
		else
		{
			// reorder all questions if invalid question
			System.out.println("Invalid index. Reordering all multiple choice questions by default.");
			for (int i = 0; i < questions.size(); i++)
			{
				if (questions.get(i) instanceof MCQuestion) {
					MCQuestion cq = (MCQuestion) questions.get(i);
					cq.reorderAnswers();
				}
			}
		}
		
	}
	
	public void getAnswerFromStudent(int position)
	{
		System.out.println("QUESTION #" + (position + 1) + ":");
		questions.get(position).print();
		questions.get(position).getAnswerFromStudent();
	}

	public float getValue()
	{
		float eval = 0.0f;
		for (int i = 0; i < questions.size(); i++)
		{
			eval += questions.get(i).getValue();
		}
		
		return eval;
	}
	
	public void reportQuestionValues()
	{
		System.out.println("[[SCORE REPORT]]");
		double totalMax = 0.0;
		for (int i = 0; i < questions.size(); i++)
		{
			System.out.println((i + 1) + ": " + questions.get(i).getValue() + " / " + questions.get(i).getMaxVal());
			System.out.println("----");
			totalMax += questions.get(i).getMaxVal();
		}
		float pc = 0.0f;
		if (this.getValue() != 0)
			pc = (float)((this.getValue()/totalMax) * 100);
		else
			pc = 0.0f;
		System.out.println("TOTAL SCORE: " + this.getValue() + " / " + totalMax + " (" + pc + "%)");
	}
	
	/* ============== NEW FUNCTIONS ============= */
	public void save(PrintWriter pw)
	{
		pw.println(text);
		pw.println();
		pw.flush();
		for (int i = 0; i < questions.size(); i++)
		{
			questions.get(i).save(pw);
			pw.println();
			pw.flush();
		}
	}
	
	public void saveStudentAnswers(PrintWriter pw)
	{
		for (int i = 0; i < questions.size(); i++)
		{
			pw.println();
			questions.get(i).saveStudentAnswer(pw);
		}
		pw.flush();
	}
	
	public void restoreStudentAnswers (Scanner Scan)
	{
		System.out.println("Scanning student report of " + Scan.nextLine());
		for (int i=0; i < questions.size(); i++)
		{
			Scan.nextLine();
			String type = Scan.nextLine();
			if (type.equals("MCSAAnswer"))
			{
				MCSAQuestion q = (MCSAQuestion) questions.get(i);
				q.restoreStudentAnswers(Scan);
			}
			if (type.equals("MCMAAnswer"))
			{
				MCMAQuestion q = (MCMAQuestion) questions.get(i);
				q.restoreStudentAnswers(Scan);
			}
			if (type.equals("SAAnswer"))
			{
				SAQuestion q = (SAQuestion) questions.get(i);
				q.restoreStudentAnswers(Scan);
			}
		}
	}
}
