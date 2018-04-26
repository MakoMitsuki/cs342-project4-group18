//Alexander Moreno
//amoren26
package cs342;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.io.PrintWriter;
//gui
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;




public class ExamBuilder {
	private static Exam currentExam;
	private static JFrame ExamBuilderFrame;
	private static JPanel MainPanel;
	private static JTextArea ExamText;
	//private static JOptionPane AlertMessager;
	
	
	

	
	//function definitions
	public static void printMenu(){
		System.out.println("These are the available options:\n"
				+ "\nload <filename>\t-loads an exam from a filename\n"
				+ "save <filename>\t-saves an exam to a filename\n"
				+ "add <Question>\t-adds a new question to current exam\n"
				+ "remove <integer>\t-removes nth question in current exam\n"
				+ "reorder <[OPTIONAL] Answers <integer> | questions> - reorders questions or \n\tanswers with optional integer argument for a specific question\n\tIf no argument is given, then it will reorder all questions and answers.\n"
				+ "print <noarg|filename>\t-prints exam to screen to to a new file.\n"
				+ "Quit\t-Exits the ExamBuilder program\n"
				+ "Help\t-Prints out this menu\n\n");
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
	
	public static void loadExamFromFile(File chosenFile){
		Scanner sc;
		try {
			sc = new Scanner(chosenFile);
			currentExam = new Exam(sc);
			sc.close();//close file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Return reference to exam
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
					else{
						System.out.println("Enter two arguments in this format <double> <Text>");
					}
					
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
					else{
						System.out.println("Enter two arguments in this format <double> <Text>");
					}
					
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
			try{
			int NthQ = Integer.parseInt(strTok.nextToken());
			exam.removeNthQuestion(NthQ-1);
		}
		catch(IndexOutOfBoundsException err){
			System.out.println("ERROR: your number was out of range.");
		}
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
	
	
	public static void printExam(Exam exam){
		exam.print();
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
	
	
	
	private static void clearJTextArea(){
		ExamText.setText("");
		return;
	}
	
	
	public static void initGUI(){
		//AlertMessager = new JOptionPane();
		 ExamBuilderFrame = new JFrame("ExamBuilder");
		ExamBuilderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ExamBuilderFrame.setSize(500, 600);
		ExamBuilderFrame.setLocationRelativeTo(null);
		
		
		//Main panel:
		MainPanel = new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel,BoxLayout.X_AXIS));
		
		//set up exam text zone
		ExamText = new JTextArea(100,100);
		ExamText.setEditable(false);
		PrintStream printStream = new PrintStream(new CustomOutputStream(ExamText));
		System.setOut(printStream);
		System.setErr(printStream);
		
		JScrollPane ExamTextFrame = new JScrollPane(ExamText);
		ExamTextFrame.setSize(500, 500);
		MainPanel.add(ExamTextFrame);
		
		
		//Set up buttons *********************************************************************
		JPanel MenuButtons = new JPanel();
		MenuButtons.setLayout(new BoxLayout(MenuButtons,BoxLayout.Y_AXIS));
		
		//Menu Button
		JButton printMenuButton = new JButton("Show Menu");
		printMenuButton.setToolTipText("Prints the Menu for this program.");
		printMenuButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				clearJTextArea();
				printMenu();
				//Standard 
				return;
			}
		});
		
		
		//LoadExamButton
		JButton loadExamButton = new JButton("Load Exam");
		loadExamButton.setToolTipText("Loads a new Exam from text files");
		loadExamButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				JFileChooser choose = new JFileChooser();
				int choice = choose.showOpenDialog(choose);
				
				if (choice != JFileChooser.APPROVE_OPTION) return;
				
				 choose.getSelectedFile();
				 clearJTextArea();
				 loadExamFromFile(choose.getSelectedFile());
				 printExam(currentExam);
				
			}
		});
		
		//reorderQuestionsButton
		JButton reorderQuestionsButton = new JButton("Reorder Questions");
		reorderQuestionsButton.setToolTipText("Reorders the questions in the current Exam");
		//create action for buttons
		reorderQuestionsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				if( currentExam != null)
				{
					clearJTextArea();
					currentExam.reorderQuestions();
					printExam(currentExam);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please, load a new exam first.");
					return;
				}
				
			}
		});
		
		JButton removeQuestionButton = new JButton("Remove Question");
		removeQuestionButton.setToolTipText("Remove a question by number.");
		removeQuestionButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(currentExam != null){
					
					try{
						
						String userInput = JOptionPane.showInputDialog("Enter a number for a specific question.");
						if(userInput.isEmpty()){
							JOptionPane.showMessageDialog(null, "Bad Input. Please enter an Integer.");
						}
						
						int nthQ = Integer.parseInt(userInput);
						currentExam.removeNthQuestion(nthQ-1);
					}
					catch(NumberFormatException err){
						JOptionPane.showMessageDialog(null, "Bad Input. Please enter an Integer.");
						return;
					}
					catch(IndexOutOfBoundsException err1){
						JOptionPane.showMessageDialog(null, "Bad Input. Your Number was out of range.");
						return;
					}
					clearJTextArea();
					printExam(currentExam);
				}
				else{
					JOptionPane.showMessageDialog(null, "Please, load a new exam first.");
				}
			}
		});
		//Add more buttons....
		
		
		
		
		//Add buttons to MenuButtons
		MenuButtons.add(printMenuButton);
		MenuButtons.add(loadExamButton);
		MenuButtons.add(reorderQuestionsButton);
		MenuButtons.add(removeQuestionButton);
		
		
		
		
		
		MainPanel.add(MenuButtons);
		
		ExamBuilderFrame.add(MainPanel);
		ExamBuilderFrame.setVisible(true);
	}
	
	
	public static void main (String[] args) throws FileNotFoundException {
		currentExam = null;
		initGUI();
		//*********************************************************

		//MainPanel.setVisible(true);
		//MenuButtons.setVisible(true);
		//ExamTextFrame.setVisible(true);
		//ExamText.setText("TEXT HERE");
		//************************************************************
		
		//Exam currentExam = null;

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
					
						removeQuestion(currentExam,strTok);

				}
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
