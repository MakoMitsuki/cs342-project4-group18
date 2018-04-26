package cs342;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class createMCSAQuestion {
	private  ArrayList<String> returnStrings = null;
	//private ArrayList<String> returnData = null;
	private JTextField QuestionText;
	private JFrame MCSAQuestionGUI;
	
	
	
	createMCSAQuestion(ArrayList<String> dataString){
		double QuestionValue = 0.0;
		
		MCSAQuestionGUI = new JFrame("New MCSAQuestion");
		MCSAQuestionGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel QuestionSurvey = new JPanel();
		QuestionSurvey.setLayout(new BoxLayout(QuestionSurvey,BoxLayout.Y_AXIS));
		MCSAQuestionGUI.add(QuestionSurvey);
		
		
		//prompt
		NumberFormat valueFormat = NumberFormat.getNumberInstance();
		QuestionText = new JTextField("Prompt: ");
		JFormattedTextField Value = new JFormattedTextField(valueFormat);
		
		Value.setColumns(10);
		//Answers
		JTextField AnswerField1 = new JTextField("1st Answer: ");
		JTextField AnswerField2 = new JTextField("2nd Answer: ");
		JTextField AnswerField3 = new JTextField("3rd Answer: ");
		JTextField AnswerField4 = new JTextField("4th Answer: ");
		JTextField AnswerField5 = new JTextField("5th Answer: ");
		JTextField AnswerField6 = new JTextField("6th Answer: ");
		
		JButton DoneButton = new JButton("Done");
		DoneButton.setToolTipText("Add question to your Exam.");
		DoneButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				returnStrings.add(QuestionText.getText());
				
				returnStrings.add(Value.getText().toString());
				
				//Don't care about contents, just throw everything in there.
				returnList(dataString,AnswerField1.getText());
				returnList(dataString,AnswerField2.getText());
				returnList(dataString,AnswerField3.getText());
				returnList(dataString,AnswerField4.getText());
				returnList(dataString,AnswerField5.getText());
				returnList(dataString,AnswerField6.getText());
				
				//Call killer
				MCSAQuestionGUI.dispatchEvent(new WindowEvent(MCSAQuestionGUI, WindowEvent.WINDOW_CLOSING));
				
			}
			
		});
		 
		//Ans1 = AnswerField1.getSelectedText()
	}
	
	
	public void returnList(ArrayList<String> list,String data){
		list.add(data);
	}
	
	final void End(){
		
	}
		
}

