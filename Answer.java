package cs342hw3;
import java.io.PrintWriter;
import java.util.*;

/*
 * ANSWER CLASS
 * Project 3 - CS342 SPRING 2018
 * Mika Cabudol (mcabud2/672285210)
*/

public abstract class Answer {
	// protected String text = ""; moved to children classes
	
	protected Answer()
	{
		
	}
	
	/* NEW */
	public Answer(Scanner Scan)
	{

	}
	
	public abstract void print();
	
	// additional helper function for getCredit functions
	public abstract String getText();
	
	public abstract double getCredit();
	
	/* NEW */
	public abstract void save(PrintWriter pw);

}
