package latexEditorController.commands;


import latexEditorView.LatexEditorView;


public class EnableVersionsManagementCommand implements Command{
	
	public LatexEditorView window;

	
	public EnableVersionsManagementCommand(LatexEditorView window) {
		this.window = window;
	}

	
	@Override
	public void execute(){
		window.getChangeVersion().setEnabled(true);
		window.getRollbackToPreviousCommand().setEnabled(true);
		window.getVersionsManager().enable();
		window.getTrackingMechanismLabel().setText("      Tracking Mechanism:  Volatile Strategy");
	}
	
}
