package cs342hw3;
import java.util.*;
import java.io.PrintWriter;

public abstract class MCAnswer extends Answer {
	// protected boolean selected = false; removed in Project 3
	protected String text = "";
	protected double creditIfSelected = 0;
	
	protected MCAnswer(String Text, double CreditIfSelected) {
		text = Text;
		creditIfSelected = CreditIfSelected;
	}
	
	/* NEW */
	public MCAnswer(Scanner Scan)
	{
		creditIfSelected = Double.parseDouble(Scan.nextLine());
		text = Scan.nextLine();
	}
	
	public void print()
	{
		System.out.print(text);
	}
	
	public String getText()
	{
		return text;
	}
	
	/* REMOVED FROM PROJECT 3
	 * public double getCreditIfSelected()
	{
		return creditIfSelected;
	}
	
	public void setSelected(boolean Selected)
	{
		selected = Selected;
	}*/
	
	/* NEW */
	public double getCredit()
	{
		return creditIfSelected;
	}
	
	public void save(PrintWriter pw)
	{
		pw.println(creditIfSelected);
		pw.println(text);
		pw.flush();
	}

}
