package cs342;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.io.PrintWriter;

public class ExamBuilder {
	
	//function definitions
	public static void printMenu(){
		System.out.println("These are the available options:\n");
		System.out.println("load <filename>\t-loads an exam from a filename");
		System.out.println("save <filename>\t-saves an exam to a filename");
		System.out.println("add <Question>\t-adds a new question to current exam");
		System.out.println("remove <integer>\t-removes nth question in current exam");
		System.out.println("reorder <[OPTIONAL] Answers <integer> | questions> - reorders questions or \n\tanswers with optional integer argument for a specific question\n\tIf no argument is given, then it will reorder all questions and answers.");
		System.out.println("print <noarg|filename>\t-prints exam to screen to to a new file.");
		System.out.println("Quit\t-Exits the ExamBuilder program");
		System.out.println("Help\t-Prints out this menu\n\n");
	}
	
	public static  Exam loadExamFromFile(Exam exam,StringTokenizer strTok) throws FileNotFoundException {
		//Get filename
		
		try{
		if(strTok.hasMoreTokens()){
		String filename = strTok.nextToken();
		File loadFile = new File(filename);
		
		
		//load into a scanner, then create a new Exam instance
		Scanner sc = new Scanner(loadFile);
		exam = new Exam(sc);

		sc.close();//close file
		return exam;//Return reference to exam
		}
		else{
			System.out.println("Error : No file name provided\n");
		}
		return null;
		}
		catch(FileNotFoundException e){
			System.out.println("Error: No such file found\n");
			return null;
		}
	}
	//adds a new question based off of input
	public  static void addNewQuestion(Exam exam,Scanner KBScanner,StringTokenizer strTok){
		//
		String Text;
		double MaxVal = 0.0;
		String userInput = "";
		String[] userArr;
		int numAnswers = 0;
		double AnswerVal;
		
		
		if(strTok.hasMoreTokens()){
			String QuestionToken = strTok.nextToken();
			QuestionToken = QuestionToken.toLowerCase();
			
			
			//MCSAQuestion-start----------------------------------------------
			if(QuestionToken.equals("mcsaquestion")){
				System.out.print("Enter Text for Question: ");
				Text = KBScanner.nextLine();
				
				System.out.print("Enter max value for Question as a double: ");
				
				while(true){
				try{
				MaxVal = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}}
				
				MCSAQuestion q = new MCSAQuestion(Text,MaxVal);
				
				System.out.println("Add up to 6 answers to your question in format: <value(double)> <Text>.\ntype Done to exit.");
				numAnswers = 0;
				while(!(userInput.toLowerCase().equals("done")) && (numAnswers <= 5)){
					System.out.print("Answer #"+(numAnswers+1)+":");
					userInput = KBScanner.nextLine();
					userArr = userInput.split(" ",2);
					if(userArr.length == 2){
						try{
							MCSAAnswer newAns = new MCSAAnswer(userArr[1],Double.parseDouble(userArr[0]));
							numAnswers++;
							q.addAnswer(newAns);
						}
						catch(NumberFormatException e){
							System.out.println("Error: Your first argument is not a valid double value.");
						}
					}//yes
					
				}
				exam.addQuestion(q);
				return;
				
				//MCSAQuestion--end---------------------------------------------------------
			}
			else if (QuestionToken.equals("mcmaquestion")){
				//MCMAQuestion--start------------------------------------------------
				System.out.print("Enter Text for Question: ");
				Text = KBScanner.nextLine();
				
				while(true){
				System.out.print("Enter max value for Question as a double: ");
				
				try{
				MaxVal = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}}
				
				//get basecredit
				double BaseCredit = 0.0;
				while(true){
				System.out.print("Enter base credit value(double): ");
				try{
				BaseCredit = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}}
				
				MCMAQuestion q = new MCMAQuestion(Text,MaxVal,BaseCredit);
				
				System.out.println("Add up to 6 answers to your question in format: <value(double)> <Text>.\ntype Done to exit.");
				numAnswers = 0;
				while(!(userInput.toLowerCase().equals("done")) && (numAnswers <= 5)){
					System.out.print("Answer #"+(numAnswers+1)+":");
					userInput = KBScanner.nextLine();
					userArr = userInput.split(" ",2);
					if(userArr.length == 2){
						try{
							MCMAAnswer newAns = new MCMAAnswer(userArr[1],Double.parseDouble(userArr[0]));
							numAnswers++;
							q.addAnswer(newAns);
						}
						catch(NumberFormatException e){
							System.out.println("Error: Your first argument is not a valid double value.");
						}
					}//yes
					
				}
				exam.addQuestion(q);
				return;
		}
				
				
				
				
				
				
				
			else if (QuestionToken.equals("saquestion")){
				
				//SAQuestion--start--------------------------------------------------
				System.out.print("Enter Text for Question: ");
				Text = KBScanner.nextLine();
				
				
				while(true){
					System.out.print("Enter max value for Question as a double: ");
				try{
				MaxVal = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}
				}
				
				SAQuestion q = new SAQuestion(Text,MaxVal);
				
				System.out.print("Enter Text for correct Answer: ");
				
				String rightAnswer = KBScanner.nextLine();
				
				SAAnswer SAAns = new SAAnswer(rightAnswer);
				
				q.setRightAnswer(SAAns);
				
				exam.addQuestion(q);
				return;
				//SAQuestion--END------------------------------------------------------------
				
			}
			else if (QuestionToken.equals("numquestion")){
				//NumQuestion --start---------------------------------------------------
				double tolerance = 0.0;
				System.out.print("Enter Text for Question: ");
				Text = KBScanner.nextLine();
				
				
				while(true){
					System.out.print("Enter max value for Question as a double: ");
				try{
				MaxVal = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}
				}
				
				while(true){
					System.out.print("Enter tolerance for question: ");
				try{
				tolerance = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}
				}
				
				double correctVal = 0.0;
				while(true){
					System.out.print("Enter correct double value for question: ");
				try{
				correctVal = Double.parseDouble(KBScanner.nextLine());
				break;
				}
				catch(NumberFormatException e){
					System.out.println("Error: Input was NOT a double.");
				}
				}
				
				NumQuestion q = new NumQuestion(Text,MaxVal,tolerance);
				
				NumAnswer numAns = new NumAnswer(correctVal,tolerance);
				
				q.setRightAnswer(numAns);
				exam.addQuestion(q);
				
				
				
				
				//NumQuestion---END--------------------------------------------------------
			}
			else{
				System.out.println("Error: No such question type as \"" + QuestionToken + "\"");
			}
		}
	}
	
	
	//should add new answers to MCAnswers, up to their max. If question is a single answer question
	//It will overwrite the old answer.
	public static void addNewAnswers(){
		//Might not even used
		
	}
	
	
	//remove question at qNum-1
	public static  void removeQuestion(Exam exam, StringTokenizer strTok) {
		if(strTok.hasMoreTokens()){
			int NthQ = Integer.parseInt(strTok.nextToken());
			exam.removeNthQuestion(NthQ-1);
		}
	}
	//Reorder Answers or exams
	public static void reorderLists(Exam exam,StringTokenizer strTok){
		if(strTok.hasMoreTokens())
		{
			String arg = strTok.nextToken();
			arg = arg.toLowerCase();
			if(arg.equals("questions")){
				exam.reorderQuestions();
			}
			else if(arg.equals("answers")){
				if(strTok.hasMoreTokens()){
					exam.reorderMCAnswers(Integer.parseInt(strTok.nextToken())-1);
				}
				else {
					exam.reorderMCAnswers(-1);
				}
				
			}
			else
			{
				System.out.println("Error: Exact command should be : reorder <Questions|Answers> <Integer for specific question>");
			}
		}
		else{
			exam.reorderMCAnswers(-1);
			exam.reorderQuestions();
		}
	}
	//Print Exam to screen or file
	//If given a filename, works exactly like saveExam().
	public  static void printExam(Exam exam,StringTokenizer strTok){
		if(strTok.hasMoreTokens()){
			try {
				//get new filename
				String filename = strTok.nextToken();
				File newFile = new File(filename);
				PrintWriter writer;
				writer = new PrintWriter(newFile);
				exam.save(writer);
				writer.close();
			} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Error: No such file");
				
			}
		}
		else{
			exam.print();
		}
		
	}
	
	//save exam to a file, filename given by user, if file not found, makes a new one.
	public  static void saveExam(Exam exam, StringTokenizer strTok){
		try {
			if(strTok.hasMoreTokens()){
			//get new filename
			String filename = strTok.nextToken();
			File newFile = new File(filename);
			PrintWriter writer;
			writer = new PrintWriter(newFile);
			exam.save(writer);
			writer.close();
			}
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		}
	}
	
	//Quit Program
	public  static void Quit(){
		System.exit(0);
	}
	
	
	
	
	public static void main (String[] args) throws FileNotFoundException {
		
		Exam currentExam = null;

		StringTokenizer strTok;
		Scanner InputScanner = ScannerFactory.getKS();
		InputScanner.useDelimiter(" \\t\\n");
		System.out.println("Alexander Moreno\nNETID : amoren26\n Group #: 18\nProgram: ExamBuilder.\n");
		System.out.println("Classmates: Michael Pedraza (ExamTaker), Mika Cabudol(ExamGrader)");
		String userInput;
		String userTok;
		//Print intro
		//Enter infinite loop
		
		printMenu();
		while(true){
			System.out.print("Enter command: ");
			userInput = InputScanner.nextLine();
			strTok = new StringTokenizer(userInput);
			userTok = strTok.nextToken();//Get command token
			userTok= userTok.toLowerCase();
			//System.out.print(userTok);
			if(userTok.equals("quit")){
				System.out.println("Thank you for using this program. Have a good day.");
				Quit();
			}
			else if(userTok.equals("load"))
			{
				currentExam = loadExamFromFile(currentExam,strTok);
			}
			else if(userTok.equals("print")){
				
				try{
					printExam(currentExam, strTok);
				}
				catch(NullPointerException e){
					System.out.println("No exam loaded. Load an exam from a file.");
				}
			}
			else if(userTok.equals("add"))
			{
				if(currentExam != null){
				addNewQuestion(currentExam,InputScanner,strTok);}
				else{
					System.out.println("No exam loaded. Load an exam from a file.");
				}
			}
			else if(userTok.equals("remove")){
				if(currentExam != null){
				removeQuestion(currentExam,strTok);}
				else{
					System.out.println("No exam loaded. Load an exam from a file.");
				}
				
				
			}
			else if(userTok.equals("save"))
			{
				if(currentExam != null){
				saveExam(currentExam,strTok);}
				else{
					System.out.println("No exam loaded. Load an exam from a file.");
				}
			}
			else if(userTok.equals("reorder")){
				if(currentExam != null){
				reorderLists(currentExam,strTok);}
				else{
					System.out.println("No exam loaded. Load an exam from a file.");
				}
			}
			else if(userTok.equals("help")){
				printMenu();
			}
		}
		
		
		
		//ending
	}

}
