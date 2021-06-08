package latexEditorTests;


import static org.junit.Assert.*;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;

import java.awt.event.ActionEvent;
import org.junit.Test;
import latexEditorController.commands.*;


public class EditCommandTest {
	
	private EditCommand testingEditCommand;
	private static LatexEditorView window = new LatexEditorView();
	private String changedContents;
	private String documentContents;
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();

	
	@Test
	public void testExecute() {
		ActionEvent e = new ActionEvent(0, 0, changedContents);
		newChoiceListener.actionPerformed(e);
		testingEditCommand = new EditCommand(window);
		window.setTextArea("A testing text");
		
		testingEditCommand.execute();
		changedContents = window.getTextArea();
		documentContents = window.getCurrent().getContents();
		
		assertEquals("The changed contents must be the same with the contents of the document", changedContents, documentContents);	
	}

}
