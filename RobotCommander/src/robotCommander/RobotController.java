package robotCommander;

import java.util.Set;

import robotModel.ICommandModel;
import robotModel.IMouseTracker;
import robotModel.IMyRobot;
import robotView.*;

public class RobotController implements IRobotController {
	private IRobotView view;
	private ICommandModel commandModel;
	private IMouseTracker mouseTracker;
	private IMyRobot myRobot;
	
	public RobotController(ICommandModel model, IMouseTracker mouseTracker, IMyRobot myRobot){
		this.commandModel = model;
		this.mouseTracker = mouseTracker;
		this.myRobot = myRobot;
		this.view = new RobotView(this, commandModel, mouseTracker);
	}

	//gets the list of commands from the model.
	//might not need this.
	public Set<String> getCommands() {
		return commandModel.getCommands();
	}

	//should input string as argument - so that we can change model state
	public void openCommand(String selectedCommand) {
		commandModel.setCurrentCommand(selectedCommand);
	}

	@Override
	public void saveCommands(){
		commandModel.save();
	}

	@Override
	public boolean createNewCommand(String newCommand) {
		return commandModel.addNewCommand(newCommand);
	}

	@Override
	public void addToCommandString(String newCommand) {
		commandModel.addToCommandString(newCommand);
	}
	
	@Override
	public void deleteFromCommandString(){
		commandModel.deleteFromCommandString();
	}
	
	@Override
	public boolean startRobot() {
		if(commandModel.getCurrentCommand() != null){
			myRobot.setCommandString(commandModel.getCurrentCommandString());
			myRobot.start();
			return true;
		}
		return false;
	}

	@Override
	public void stopRobot() {
		myRobot.stop();
	}

	@Override
	public void startMouseTracker() {
		mouseTracker.startMouseTracker();
	}
	
	@Override
	public void stopMouseTracker(){
		mouseTracker.stopMouseTracker();
	}

}
