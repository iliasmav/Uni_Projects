package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;
import latexEditorController.commands.EditCommand;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;
import latexEditorView.LatexEditorView.SaveListener;


public class SaveCommandTest {
	
	private LatexEditorView window = new LatexEditorView();
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();
	private ActionEvent e;
	private EditCommand testingEditCommand;
	private Scanner inputDocument;
	private String testingContents = "";
	private SaveListener saveListener = window.new SaveListener();
	
	
	@Test
	public void testExecute() {
		newChoiceListener.actionPerformed(e);
		testingEditCommand = new EditCommand(window);
		window.setTextArea("A testing text");
		testingEditCommand.execute();
		e = new ActionEvent(0, 0, "dd");
		saveListener.actionPerformed(e);
		
		String expectedContents = window.getCurrent().getContents() + '\n';	
		try{
			inputDocument = new Scanner(new FileInputStream(window.getCurrent().getFile()));
			}
			catch(FileNotFoundException e){
				System.out.println("File was not found");
				System.exit(0);
			}
		inputDocument.nextLine();
		inputDocument.nextLine();
		inputDocument.nextLine();
		inputDocument.nextLine();
		while (inputDocument.hasNextLine( )){
			 testingContents = testingContents + inputDocument.nextLine() + "\n";
			 }
		System.out.println(testingContents);
		
		assertEquals("The contents of the current document must match with the contents of the file that has saved to disk", expectedContents, testingContents);
	}

}
