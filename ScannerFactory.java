//package cs342hw3;
import java.util.*;

public class ScannerFactory {
 private static Scanner keyboardScanner = new Scanner(System.in);
 
 public ScannerFactory()
 {
  
 }
 
 public static Scanner getKS()
 {
  return keyboardScanner;
 }
}
