package robotView;

import javax.swing.*;

import robotCommander.IRobotController;
import robotModel.ICommandModel;
import robotModel.IMouseTracker;

public class RobotView extends JFrame implements IRobotView {
	//our model and command interfaces(mvc pattern)
	private IRobotController controller;
	private ICommandModel model;
	private IMouseTracker mouseTracker;
	
	private RobotView_EditPanel panel_edit;
	private RobotView_MenuBar menuBar;
	
	public RobotView(IRobotController c, ICommandModel m, IMouseTracker mt){
		//init controller and model
		controller = c;
		model = m;
		mouseTracker = mt;
		
		//add self as an observer of mouseTracker - will update the title bar
		mt.addObserver(this);
		
		//build the menuBar
		menuBar = new RobotView_MenuBar(controller, model, mouseTracker);
		setJMenuBar(menuBar);
		
		//init our gui objects and layout
		panel_edit = new RobotView_EditPanel(controller, model);
		
		//add our edit panel to the UI
		add(panel_edit);
		
		//set basic gui properties for the window
		setTitle("RobotCommander");
		setSize(600,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void update() {
		if(mouseTracker.isStarted()){
			setTitle("RobotCommander " + mouseTracker.getMousePos());
		}
		else if(!mouseTracker.isStarted()){
			setTitle("RobotCommander");
		}
	}
	
}
