import java.util.*;

// MCMAAnswer.java -- mcabud2

public class MCMAAnswer extends MCAnswer{

	public MCMAAnswer(String Text, double CreditIfSelected) {
		super(Text, CreditIfSelected);
	}
	
	public MCMAAnswer(Scanner scan)
	{
		super(scan);
	}
	
	public double getCredit()
	{
		return super.getCredit();
	}
}
