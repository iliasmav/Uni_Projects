package latexEditorController.commands;


import latexEditorView.LatexEditorView;


public class RollbackToPreviousVersionCommand implements Command {
	
	private LatexEditorView window;
	
	
	public RollbackToPreviousVersionCommand(LatexEditorView window) {
		this.window = window;
	}
	
	
	@Override
	public void execute(){
		window.getVersionsManager().rollbackToPreviousVersion();	
	}

}
