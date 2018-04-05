import java.util.*;

// ScannerFactory.java -- mcabud2

public class ScannerFactory {
	private static Scanner keyboardScanner = new Scanner(System.in);
	
	public ScannerFactory()
	{
		
	}
	
	public static Scanner getKeyboardScanner()
	{
		return keyboardScanner;
	}
}
