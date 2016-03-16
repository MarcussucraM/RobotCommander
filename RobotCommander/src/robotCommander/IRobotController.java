package robotCommander;

import java.util.Set;

public interface IRobotController {
	//allows user to see a list of Commands to choose from.
	public Set<String> getCommands();
	
	//initiated after user selects a command - updates model currentCommand
	//which in turn notifies the view that state has updated and that it should change.
	public void openCommand(String selectedCommand);
	
	//saves the commandMap to a file
	public void saveCommands();
	
	//creates a new command for our command map
	public boolean createNewCommand(String newCommand);
	
	//called when user clicks either add or delete on edit page
	//updates the string.
	public void addToCommandString(String newCommand);
	public void deleteFromCommandString();
	
	//starts up the robot
	//returns false if this fails
	public boolean startRobot();
	
	//stops the robot loop
	public void stopRobot();
	
	//starts tracking where the mouse is located
	public void startMouseTracker();
	
	//stops tracking where the mouse is located
	public void stopMouseTracker();
}
