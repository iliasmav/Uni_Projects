package latexEditorController.commands;


import latexEditorView.*;


public class CommandsFactory{

	public static Command createCommand(String command, LatexEditorView window){
		if(command.equals("Add Latex Command")){
			return new AddLatexCommand(window);	
		}else if(command.equals("Create Command")){
			return new CreateCommand(window);
		}else if(command.equals("Rollback To Previous Version Command")){
			return new RollbackToPreviousVersionCommand(window);
		}else if(command.equals("Edit Command")){
			return new EditCommand(window);
		}else if(command.equals("Load Command")){
			return new LoadCommand(window);
		}else if(command.equals("Enable Versions Management Command")){
			return new EnableVersionsManagementCommand(window);
		}else if(command.equals("Disable Versions Management Command")){
			return new DisableVersionsManagementCommand(window);
		}else if(command.equals("Save Command")){
			return new SaveCommand(window);
		}else{
			return new ChangeVersionsStrategyCommand(window);
		}
	}
	
}

