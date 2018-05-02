//package cs342;

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
	
	
	private JTextField AnswerField1;
	private JTextField AnswerField2;
	private JTextField AnswerField3;
	private JTextField AnswerField4;
	private JTextField AnswerField5;
	private JTextField AnswerField6;
	private JFormattedTextField ans1Points;
	private JFormattedTextField ans2Points;
	private JFormattedTextField ans3Points;
	private JFormattedTextField ans4Points;
	private JFormattedTextField ans5Points;
	private JFormattedTextField ans6Points;
	
	
	private JFormattedTextField Value;
	private double QuestionValue;
	private Callable<Integer> func;
	 createMCSAQuestion(Callable<Integer> newFunc){
		func = newFunc;
		returnStrings = null;
		QuestionValue = 0.0;
		MCSAQuestionGUI = new JFrame("New MCSAQuestion");
		MCSAQuestionGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel QuestionSurvey = new JPanel();
		QuestionSurvey.setLayout(new BoxLayout(QuestionSurvey,BoxLayout.Y_AXIS));
		
		
		
		//prompt
		NumberFormat valueFormat = NumberFormat.getNumberInstance();
		QuestionText = new JTextField("Prompt: ");
		Value = new JFormattedTextField(valueFormat);
		Value.setValue(QuestionValue);
		
		
		//Answers
		AnswerField1 = new JTextField("1st");
		ans1Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans1Points.setValue(QuestionValue);
		AnswerField2 = new JTextField("2nd");
		ans2Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans2Points.setValue(QuestionValue);
		AnswerField3 = new JTextField("3rd");
		ans3Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans3Points.setValue(QuestionValue);
		AnswerField4 = new JTextField("4th");
		ans4Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans4Points.setValue(QuestionValue);
		AnswerField5 = new JTextField("5th");
		ans5Points = new JFormattedTextField(new DecimalFormat("#0.00"));
		ans5Points.setValue(QuestionValue);
		AnswerField6 = new JTextField("6th");
		ans6Points = new JFormattedTextField(new DecimalFormat("#0.00"));
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
					func.call();
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
		
	}
	
	public void showDialogBox(createMCSAQuestion q) {
		
		AnswerField1.setText("1st");
		AnswerField2.setText("2nd");
		AnswerField3.setText("3rd");
		AnswerField4.setText("4th");
		AnswerField5.setText("5th");
		AnswerField6.setText("6th");
		ans1Points.setValue(QuestionValue);
		ans2Points.setValue(QuestionValue);
		ans3Points.setValue(QuestionValue);
		ans4Points.setValue(QuestionValue);
		ans5Points.setValue(QuestionValue);
		ans6Points.setValue(QuestionValue);
		Value.setValue(QuestionValue);
		QuestionText.setText("Prompt: ");
		
		
		MCSAQuestionGUI.setVisible(true);
	}
	
	
	public  ArrayList<String> returnData(){
		
		 return returnStrings;
	}



		
}

