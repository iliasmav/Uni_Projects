package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;


import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;
import latexEditorView.LatexEditorView.ChangeVersionsStrategyCommandListener;
import latexEditorModel.strategies.*;


public class ChangeVersionsStratetyCommandTest {
	
	private LatexEditorView window = new LatexEditorView();
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();
	private ChangeVersionsStrategyCommandListener changeVersionsStrategyListener = window.new ChangeVersionsStrategyCommandListener();
	private ActionEvent e;
	private boolean strategyChanged;
	
	
	@Test
	public void testExecute(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		testChangeToStable();
		testChangeToVolatile();
	}

	
	public void testChangeToStable(){
		e = new ActionEvent(0, 0, "Change To Stable");
		changeVersionsStrategyListener.actionPerformed(e);
		VersionsStrategy testingStableStrategy = window.getVersionsManager().getStrategy();
		
		if(testingStableStrategy instanceof StableVersionsStrategy){
			strategyChanged = true;
		}
		assertTrue("The changed Strategy must be a StableVersionsStrategy object)", strategyChanged);	
	}
	
	
	public void testChangeToVolatile(){
		e = new ActionEvent(0, 0, "Change To Volatile");
		changeVersionsStrategyListener.actionPerformed(e);
		VersionsStrategy testingVolatileStrategy = window.getVersionsManager().getStrategy();
		
		if(testingVolatileStrategy instanceof VolatileVersionsStrategy){
			strategyChanged = true;
		}
		assertTrue("The changed Strategy must be a VolatileVersionsStrategy object)", strategyChanged);	
	}
	
}
