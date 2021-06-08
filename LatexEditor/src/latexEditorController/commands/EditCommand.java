package latexEditorController.commands;


import latexEditorView.LatexEditorView;


public class EditCommand implements Command {
	private LatexEditorView window;
	private String editedContents;
	 
	
	public EditCommand(LatexEditorView window){
		this.window = window;
	}
	
	
	@Override
	public void execute(){
		window.getVersionsMenu().setEnabled(true);			
		editedContents = window.getTextArea();
		window.getCurrent().setContents(editedContents);
	
		if(window.getVersionsManager().isEnabled()){
			window.getVersionsManager().setCurrentVersion(window.getCurrent());
		}
	}
	
}
