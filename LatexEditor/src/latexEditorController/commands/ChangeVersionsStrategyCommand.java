package latexEditorController.commands;


import latexEditorView.LatexEditorView;
import latexEditorModel.strategies.*;


public class ChangeVersionsStrategyCommand implements Command {
	private LatexEditorView window;
	private VersionsStrategy stableStrategy;
	private boolean firstTimeStable = true;
	
	
	public ChangeVersionsStrategyCommand(LatexEditorView window) {
		this.window = window;
	}
	
	
	@Override
	public void execute(){
		if (firstTimeStable){
			stableStrategy = window.getStrategyFactory().createStrategy("Stable Strategy");	
		}
		if(window.getChangeVersion().getActionCommand() == "Change To Stable"){	
			window.getVersionsManager().setStrategy(stableStrategy);
			stableStrategy.setEntireHistory(window.getVolatileStrategy().getEntireHistory());
			window.getChangeVersion().setText("Change To Volatile");
			window.getChangeVersion().setActionCommand("Change To Volatile");
			window.getVolatileStrategy().removeListElements();
			window.getTrackingMechanismLabel().setText("      Tracking Mechanism:  Stable Strategy");
			firstTimeStable = false;
		}else{
			window.getVersionsManager().setStrategy(window.getVolatileStrategy());
			window.getVolatileStrategy().setEntireHistory(stableStrategy.getEntireHistory());
			stableStrategy.removeListElements();
			window.getChangeVersion().setText("Change To Stable");		
			window.getChangeVersion().setActionCommand("Change To Stable");
			window.getTrackingMechanismLabel().setText("      Tracking Mechanism:  Volatile Strategy");
		}	
	}

}
