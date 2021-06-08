package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;

import latexEditorController.commands.EditCommand;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;


public class DisableVersionsManagementCommandTest {
	
	private LatexEditorView window = new LatexEditorView();
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();
	private ActionEvent e;
	private EditCommand testingEditCommand;
	
	
	@Test
	public void test() {
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		testingEditCommand = new EditCommand(window);
		window.getController().enact("Enable Versions Management Command");
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		
		int expectedSize = window.getVolatileStrategy().getEntireHistory().size();
		window.getController().enact("Disable Versions Management Command");
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		int testingSize = window.getVolatileStrategy().getEntireHistory().size();
		
		assertEquals("The size of the new list after the edit must be the same with the previous", expectedSize, testingSize);		
	}

}
