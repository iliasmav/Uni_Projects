package latexEditorController.commands;


import latexEditorView.LatexEditorView;


public class DisableVersionsManagementCommand implements Command{

	private LatexEditorView window;
	
	
	public DisableVersionsManagementCommand(LatexEditorView window) {
		this.window = window;
	}

	
	@Override
	public void execute(){
		window.getChangeVersion().setEnabled(false);
		window.getRollbackToPreviousCommand().setEnabled(false);
		window.getVersionsManager().disable();	
		window.getTrackingMechanismLabel().setText("      Tracking Mechanism:  Disabled");
	}
	
}
