package cs342;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class createMCSAQuestion extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  static ArrayList<String> returnStrings = null;
	private JTextField QuestionText;
	private JFrame MCSAQuestionGUI;
	
	
	
	 createMCSAQuestion(Callable<Integer> newFunc){
		returnStrings = null;
		double QuestionValue = 0.0;
		MCSAQuestionGUI = new JFrame("New MCSAQuestion");
		MCSAQuestionGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel QuestionSurvey = new JPanel();
		QuestionSurvey.setLayout(new BoxLayout(QuestionSurvey,BoxLayout.Y_AXIS));
		
		
		
		//prompt
		NumberFormat valueFormat = NumberFormat.getNumberInstance();
		QuestionText = new JTextField("Prompt: ");
		JFormattedTextField Value = new JFormattedTextField(valueFormat);
		Value.setValue(QuestionValue);
		
		
		//Answers
		JTextField AnswerField1 = new JTextField("1st");
		JFormattedTextField ans1Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans1Points.setValue(QuestionValue);
		JTextField AnswerField2 = new JTextField("2nd");
		JFormattedTextField ans2Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans2Points.setValue(QuestionValue);
		JTextField AnswerField3 = new JTextField("3rd");
		JFormattedTextField ans3Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans3Points.setValue(QuestionValue);
		JTextField AnswerField4 = new JTextField("4th");
		JFormattedTextField ans4Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans4Points.setValue(QuestionValue);
		JTextField AnswerField5 = new JTextField("5th");
		JFormattedTextField ans5Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans5Points.setValue(QuestionValue);
		JTextField AnswerField6 = new JTextField("6th");
		JFormattedTextField ans6Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans6Points.setValue(QuestionValue);
		
		JButton DoneButton = new JButton("Done");
		DoneButton.setToolTipText("Add question to your Exam.");
		DoneButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				returnStrings = new ArrayList<String>();
				
				returnStrings.add(QuestionText.getText());
				returnStrings.add(Value.getText().toString());
				
				returnStrings.add(AnswerField1.getText());
				returnStrings.add(ans1Points.getText());
				returnStrings.add(AnswerField2.getText());
				returnStrings.add(ans2Points.getText());
				returnStrings.add(AnswerField3.getText());
				returnStrings.add(ans3Points.getText());
				returnStrings.add(AnswerField4.getText());
				returnStrings.add(ans4Points.getText());
				returnStrings.add(AnswerField5.getText());
				returnStrings.add(ans5Points.getText());
				returnStrings.add(AnswerField6.getText());
				returnStrings.add(ans6Points.getText());
				
				//Call killer
				try {
					newFunc.call();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//MCSAQuestionGUI.dispatchEvent(new WindowEvent(MCSAQuestionGUI, WindowEvent.WINDOW_CLOSING));
				MCSAQuestionGUI.setVisible(false);
				
			}
			
		});
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setToolTipText("Cancel this operation.");
		cancelButton.addActionListener(new ActionListener(){
			
			@Override 
			public void actionPerformed(ActionEvent e){
				MCSAQuestionGUI.setVisible(false);
			}
		});
		 
		
		JPanel Options = new JPanel();
		Options.setLayout(new BoxLayout(Options,BoxLayout.X_AXIS));
		Options.add(DoneButton);
		Options.add(cancelButton);
		
		
		QuestionSurvey.add(QuestionText);
		QuestionSurvey.add(Value);
		QuestionSurvey.add(AnswerField1);
		QuestionSurvey.add(ans1Points);
		QuestionSurvey.add(AnswerField2);
		QuestionSurvey.add(ans2Points);
		QuestionSurvey.add(AnswerField3);
		QuestionSurvey.add(ans3Points);
		QuestionSurvey.add(AnswerField4);
		QuestionSurvey.add(ans4Points);
		QuestionSurvey.add(AnswerField5);
		QuestionSurvey.add(ans5Points);
		QuestionSurvey.add(AnswerField6);
		QuestionSurvey.add(ans6Points);
		QuestionSurvey.add(Options);
		QuestionSurvey.setSize(new Dimension(150,150));
		MCSAQuestionGUI.add(QuestionSurvey);
		MCSAQuestionGUI.setSize(400, 400);
		MCSAQuestionGUI.setVisible(false);
		//NOTIFY();
		
	}
	
	public void showDialogBox(createMCSAQuestion q) {
		MCSAQuestionGUI.setVisible(true);
	}
	
	
	public  ArrayList<String> returnData(){
		
		 return returnStrings;
	}



		
}

