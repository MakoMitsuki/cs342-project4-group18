package cs342hw3;
import java.util.*;
import java.io.PrintWriter;

public class SAAnswer extends Answer{
	protected String text = "";
	
	public SAAnswer(String Text) {
		text = Text;
	}
	
	/* NEW */
	public SAAnswer(Scanner Scan)
	{
		text = Scan.nextLine();
	}
	
	public void print()
	{
		System.out.println(text);
	}
	
	public String getText()
	{
		return text;
	}
	
	// has to be exact characters, case insensitive
	public double getCredit()
	{
		return 1;
	}
	
	/* NEW */
	public void save(PrintWriter pw)
	{
		pw.println(text);
		pw.flush();
	}
}
