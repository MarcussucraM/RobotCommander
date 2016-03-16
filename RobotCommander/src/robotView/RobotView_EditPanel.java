package robotView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import robotCommander.IRobotController;
import robotModel.ICommandModel;
import robotCommanderConstants.GUIConstants;
import robotCommanderConstants.CommandConstants;

public class RobotView_EditPanel extends JPanel implements ActionListener, IRobotView {
	// Our model and controller
	private IRobotController controller;
	private ICommandModel model;

	// Our panel title and textarea/scrollpane where the command string will be
	// contained.
	private JLabel commandLabel;
	private JTextArea commandText;
	private JScrollPane commandTextScroller;

	// Container that holds our edit Panel controls.
	private JPanel selectionPanel;

	// Editing tools
	private JComboBox<String> commandSelectBox;
	private JTextField xMousePosField;
	private JTextField yMousePosField;
	private JTextField typeField;
	private JTextField timeField;
	private JButton addCommandButton;
	private JButton deleteCommandButton;

	public RobotView_EditPanel(IRobotController c, ICommandModel m) {
		// init controller and model, add self as observer of the model
		controller = c;
		model = m;
		m.addObserver(this);

		commandLabel = new JLabel("Edit Command", SwingConstants.CENTER);
		initTextArea();
		// create panel for the bottom
		initSelectionPanel();

		// add components to our container
		setLayout(new BorderLayout());
		add(commandLabel, BorderLayout.NORTH);
		add(commandTextScroller, BorderLayout.CENTER);
		add(selectionPanel, BorderLayout.SOUTH);

	}

	// creates a text area within a scrollPane
	private void initTextArea() {
		commandText = new JTextArea();
		commandText.setEditable(false);
		commandText.setLineWrap(true);
		commandTextScroller = new JScrollPane(commandText);
	}

	// creates a JPanel for editing the command string.
	// contains a combobox for selecting commands, buttons for adding
	// and deleting commands
	// made up of 3 panels, a container for 2 and then the control panels
	private void initSelectionPanel() {
		selectionPanel = new JPanel(new BorderLayout());
		JPanel controlPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());

		// first create the control panel - lots of stuff in here
		commandSelectBox = new JComboBox<String>(GUIConstants.commandList);
		JLabel xPosLabel = new JLabel("X:");
		xMousePosField = new JTextField(3);
		JLabel yPosLabel = new JLabel("Y:");
		yMousePosField = new JTextField(3);
		JLabel typeLabel = new JLabel("Type:");
		typeField = new JTextField(15);
		JLabel timeLabel = new JLabel("Time(ms):");
		timeField = new JTextField(5);

		// add a listener to the selection box
		commandSelectBox.addActionListener(this);

		// add components to the controlPanel
		controlPanel.add(commandSelectBox);
		controlPanel.add(xPosLabel);
		controlPanel.add(xMousePosField);
		controlPanel.add(yPosLabel);
		controlPanel.add(yMousePosField);
		controlPanel.add(typeLabel);
		controlPanel.add(typeField);
		controlPanel.add(timeLabel);
		controlPanel.add(timeField);

		// create buttonpanel components
		addCommandButton = new JButton(GUIConstants.edit_addNew_string);
		deleteCommandButton = new JButton(GUIConstants.edit_delete_string);

		// add listeners to them
		addCommandButton.addActionListener(this);
		deleteCommandButton.addActionListener(this);

		// add to the buttonPanel
		buttonPanel.add(addCommandButton);
		buttonPanel.add(deleteCommandButton);

		// finally add everything to the selectionPanel
		selectionPanel.add(controlPanel, BorderLayout.NORTH);
		selectionPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//set all fields to disabled to start
		setFieldsEnabled(false, false, false, false);
	}

	public JLabel getCommandLabel() {
		return commandLabel;
	}

	// model will update this when current command changes
	public void setCommandLabel(String currentCommand) {
		commandLabel.setText(currentCommand);
	}

	// after user presses save button - all of this text will be retrieved
	// and put into command map
	public JTextArea getCommandText() {
		return commandText;
	}

	@Override
	//called when the model updates state
	public void update() {
		commandLabel.setText(model.getCurrentCommand());
		commandText.setText(model.getCurrentCommandString());
	}
	
	//shows an error message incase the user inputs nothing, or something not intended
	private void showErrorMessage(String msg){
		JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
	}
	
	//Checks User input for x/y fields
	private boolean checkXYFieldInput(){
		//check to make sure user isn't inputting spaces
		if(xMousePosField.getText().trim().isEmpty() || yMousePosField.getText().trim().isEmpty()){
			showErrorMessage("Incorrect input for x/y fields");
		}
		try{
			//not actually using these values - just checking to make sure we can do this for later
			Integer.parseInt(xMousePosField.getText().trim());
			Integer.parseInt(yMousePosField.getText().trim());
		} catch(NumberFormatException e){ 
			showErrorMessage("Incorrect input for x/y fields");
			return false;
		}
		
		return true;
	}
	
	//checks user input for time field
	private boolean checkTimeFieldInput(){
		if(timeField.getText().trim().isEmpty()){
			showErrorMessage("Incorrect input for Time field");
			return false;
		}
		try{
			int time = Integer.parseInt(timeField.getText().trim());
			if(time <= 1000){
				showErrorMessage("For safety purposes a time of less than 1 second is not allowed.");
				return false;
			}
		} catch(NumberFormatException e){
			showErrorMessage("Incorrect input for Time field");
			return false;
		}
		return true;
	}
	
	//When the user selects a field in the combo box we don't all of the input fields to be open
	//So we enable/disable depending on which field in the combo box is chosen.
	private void setFieldsEnabled(boolean x, boolean y, boolean type, boolean wait){
		xMousePosField.setEnabled(x);
		yMousePosField.setEnabled(y);
		typeField.setEnabled(type);
		timeField.setEnabled(wait);
	}
	
	//clears all fields
	private void clearFields(){
		xMousePosField.setText("");
		yMousePosField.setText("");
		typeField.setText("");
		timeField.setText("");
	}

	// takes care of any events that take place in this panel.
	public void actionPerformed(ActionEvent e) {
		// if we're clicking the commandSelect box we need to make sure that the
		// user
		// isn't able to for example, enter something into the x,y fields if
		// they're
		// doing a click command.
		if (e.getSource() == commandSelectBox) {
			if (commandSelectBox.getSelectedItem().equals("Click")) {
				System.out.println("Click pressed");
				setFieldsEnabled(false, false, false, false);
			}
			if (commandSelectBox.getSelectedItem().equals("DoubleClick")) {
				System.out.println("DoubleClick pressed");
				setFieldsEnabled(false, false, false, false);
			}
			if (commandSelectBox.getSelectedItem().equals("Type")) {
				System.out.println("Type pressed");
				setFieldsEnabled(false, false, true, false);
			}
			if (commandSelectBox.getSelectedItem().equals("MoveMouseTo")) {
				System.out.println("MoveMouseTo pressed");
				setFieldsEnabled(true, true, false, false);
			}
			if (commandSelectBox.getSelectedItem().equals("Wait")) {
				System.out.println("Wait pressed");
				setFieldsEnabled(false, false, false, true);
			}
			if(commandSelectBox.getSelectedItem().equals("Quit")){
				System.out.println("Quit pressed");
				setFieldsEnabled(false, false, false, false);
			}
		}
		if (e.getSource() == addCommandButton) {
			System.out.println("Add button pressed");
			String newCommand = "";
			if (commandSelectBox.getSelectedItem().equals("Click")) {
				newCommand = "Click" + CommandConstants.delimiter;
			}
			if (commandSelectBox.getSelectedItem().equals("DoubleClick")) {
				newCommand = "DoubleClick" + CommandConstants.delimiter;
			}
			if (commandSelectBox.getSelectedItem().equals("Type")) {
				newCommand = "Type" + CommandConstants.fieldTag + typeField.getText()
				+ CommandConstants.endFieldTag + CommandConstants.delimiter;				
			}
			if (commandSelectBox.getSelectedItem().equals("MoveMouseTo")) {
				//handle user input
				if(checkXYFieldInput()){
					newCommand = "MoveMouseTo" + CommandConstants.fieldTag
							+ xMousePosField.getText().trim() + ","
							+ yMousePosField.getText().trim() + CommandConstants.endFieldTag
							+ CommandConstants.delimiter;
				}
			}
			if (commandSelectBox.getSelectedItem().equals("Wait")) {
				if(checkTimeFieldInput()){
					newCommand = "Wait" + CommandConstants.fieldTag
							+ timeField.getText().trim() + CommandConstants.endFieldTag
							+ CommandConstants.delimiter;
				}
			}
			if(commandSelectBox.getSelectedItem().equals("Quit")){
				newCommand = "Quit" + CommandConstants.delimiter;
			}
			//clear all of the fields for next input
			controller.addToCommandString(newCommand);
			clearFields();
		}
		if (e.getSource() == deleteCommandButton) {
			System.out.println("Delete button pressed");
			controller.deleteFromCommandString();
			clearFields();
		}

	}

}
