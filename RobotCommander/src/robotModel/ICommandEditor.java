package robotModel;

import java.util.Map;

public interface ICommandEditor {
	//Adds a command to the commandMap
	public boolean addCommand(Map<String,String> commandMap, String commandName);
	//Deletes specified command from the commandMap
	public void deleteCommand(Map<String,String> commandMap, String commandName);
	//Adds a new command to the command string of a specified commandName in the commandMap
	public void addToCommandString(Map<String,String> commandMap, String commandName, String newCommand);
	//Deletes the last command from a command string.
	public void deleteFromCommandString(Map<String,String> commandMap, String commandName);
}
