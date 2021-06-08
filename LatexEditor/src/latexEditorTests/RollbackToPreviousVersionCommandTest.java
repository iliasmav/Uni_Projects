package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;

import latexEditorController.commands.EditCommand;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;


public class RollbackToPreviousVersionCommandTest {
	
	private LatexEditorView window = new LatexEditorView();
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();
	private ActionEvent e;
	private EditCommand testingEditCommand;
	
	
	@Test
	public void test() {
		newChoiceListener.actionPerformed(e);
		testingEditCommand = new EditCommand(window);
		window.getController().enact("Enable Versions Management Command");
		testingEditCommand.execute();
		
		String expectedPreviousVersionContents = window.getCurrent().getContents();
		window.setTextArea("A testing text2");
		testingEditCommand.execute();
		window.getController().enact("Rollback To Previous Version Command");
		
		String testingPreviousVersionContents = window.getCurrent().getContents();
		
		assertEquals("The contents of the current document must match with the contents of the previous version", expectedPreviousVersionContents, testingPreviousVersionContents);	
	}

}
