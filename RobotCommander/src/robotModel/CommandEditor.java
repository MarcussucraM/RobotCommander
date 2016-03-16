package robotModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import robotCommanderConstants.CommandConstants;

public class CommandEditor implements ICommandEditor{
	
	public CommandEditor(){
	}

	@Override
	public boolean addCommand(Map<String, String> commandMap, String commandName) {
		if(commandName != null && !commandName.equals("") && !commandExists(commandMap, commandName)){
			commandMap.put(commandName, "");
			return true;
		}
		return false;
	}

	@Override
	//deletes the current command
	public void deleteCommand(Map<String, String> commandMap, String commandName) {
		// TODO Auto-generated method stub
		
	}


	private boolean commandExists(Map<String, String> commandMap, String commandName) {
		if(commandMap.containsKey(commandName)) return true;
		return false;
	}
	

	//Updates the currentCommands commandString by adding a newCommand to the end of it.
	public void addToCommandString(Map<String, String> commandMap, String commandName, String newCommand) {
		String oldCommandString = commandMap.get(commandName);
		String newCommandString = oldCommandString + newCommand;
		commandMap.put(commandName, newCommandString);
	}

	
	public void deleteFromCommandString(Map<String, String> commandMap, String commandName) {
		String oldCommandString = commandMap.get(commandName);
		
		//first split up the string into individual pieces and put them in an array
		//afterwards we are going to delete the last element of the array
		String[] splitUp = oldCommandString.split(CommandConstants.delimiter);
		List<String> commands = new ArrayList<String>(Arrays.asList(splitUp));
		if(!commands.isEmpty()) commands.remove(commands.size()-1);
		
		//build the commandString again now that we've removed the last element
		String newCommandString = "";
		for(int i = 0; i < commands.size(); i++){
			commands.set(i, commands.get(i) + CommandConstants.delimiter);
			newCommandString += commands.get(i);
		}
		
		commandMap.put(commandName, newCommandString);
	}
	

}
