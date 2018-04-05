import java.util.*;

// MCSAAnswer -- mcabud2

public class MCSAAnswer extends MCAnswer{
	
	public MCSAAnswer(String Text, double CreditIfSelected) {
		super(Text, CreditIfSelected);
	}
	
	public MCSAAnswer(Scanner scan)
	{
		super(scan);
	}
	
	public double getCredit()
	{
		return super.getCredit();
	}
	/* !! REMOVED FROM MCSAAnswer
	public double getCredit(Answer rightAnswer)
	{
		if (selected)
			return creditIfSelected;
		else
			return 0.0;
	} */

}
