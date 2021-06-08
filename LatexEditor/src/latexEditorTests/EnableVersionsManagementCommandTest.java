package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;

import latexEditorController.commands.EditCommand;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;


public class EnableVersionsManagementCommandTest {
	
	private LatexEditorView window = new LatexEditorView();
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();
	private ActionEvent e;
	private EditCommand testingEditCommand;
	
	
	@Test
	public void testExecute(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		testingEditCommand = new EditCommand(window);
		volatileTest();
		stableTest();	
	}

	
	public void volatileTest(){
		window.getController().enact("Enable Versions Management Command");
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		String expectedContents = window.getCurrent().getContents();
		window.getController().enact("Rollback To Previous Version Command");
		String testingContents = window.getCurrent().getContents();
		
		assertEquals("The Contents of the document before the edit must be the same with the testing(previous version contents)", expectedContents, testingContents);	
	}
	
	
	public void stableTest(){
		window.getController().enact("Change Versions Strategy Command");
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		String expectedContents = window.getCurrent().getContents()+ "\n";
		window.getController().enact("Rollback To Previous Version Command");
		String testingContents = window.getCurrent().getContents();
		
		assertEquals("The Contents of the document before the edit must be the same with the testing(previous version contents)", expectedContents, testingContents);
	}
	
}
