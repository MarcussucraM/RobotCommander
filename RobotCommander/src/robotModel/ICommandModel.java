package robotModel;

import java.util.Map;
import java.util.Set;

public interface ICommandModel {
	//observer pattern - keep track of observers
	public void addObserver(robotView.IRobotView o);
	public void removeObserver(robotView.IRobotView o);
	public void notifyObservers();
	
	//getter/setter methods for view to update display
	public void setCurrentCommand(String currentCommand);
	public void setCommandMap(Map<String,String> commandMap);
	public String getCurrentCommand();
	public String getCurrentCommandString();
	public Set<String> getCommands();
	
	//setter methods for controller to change state
	public boolean addNewCommand(String newCommand);
	public void addToCommandString(String newCommand);
	public void deleteFromCommandString();
	
	//save the commandMap to file
	public void save();
}
