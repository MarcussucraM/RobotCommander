package robotModel;

import java.util.Map;

public interface ICommandEditor {
	public boolean addCommand(Map<String,String> commandMap, String commandName);
	public void deleteCommand(Map<String,String> commandMap, String commandName);
	public void addToCommandString(Map<String,String> commandMap, String commandName, String newCommand);
	public void deleteFromCommandString(Map<String,String> commandMap, String commandName);
}
