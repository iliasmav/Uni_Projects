package latexEditorController;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import latexEditorController.commands.*;
import latexEditorView.*;


public class LatexEditorController {
	
	private HashMap<String, Command> commands = new HashMap<String, Command>();
	private BufferedReader listOfCommands = null;	
	private LatexEditorView window;
	
	
	public LatexEditorController(LatexEditorView window){
		this.window = window;
		dynamicallyLoadCommands("C:/Users/H/Desktop/tex-templates/listOfCommands.txt");	
	}
		
	
	public HashMap<String, Command> getCommands() {
		return commands;
	}


	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}
	
	
	public void addCommandToMap(String commandKey, Command command){
		commands.put(commandKey, command);
	}
	

	public void dynamicallyLoadCommands(String listOfCommandsFileName){
		try {
			listOfCommands = new BufferedReader(new FileReader (listOfCommandsFileName));
			String currentCommandString;
			Command currentCommandObject;
			
			while ((currentCommandString = listOfCommands.readLine()) != null) {
				currentCommandObject = CommandsFactory.createCommand(currentCommandString, window);
				addCommandToMap(currentCommandString, currentCommandObject);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void enact(String userCommand){
		 commands.get(userCommand).execute();
	}

}
