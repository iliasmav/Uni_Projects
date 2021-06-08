package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.LoadListener;


public class LoadCommandTest {
	
	private LatexEditorView window = new LatexEditorView();
	private ActionEvent e;
	private Scanner inputDocument;
	private String testingContents;
	private LoadListener loadListener = window.new LoadListener();
	private String expectedContents;

	
	@Test
	public void testExecute() {
		e = new ActionEvent(0, 0, "dd");
		loadListener.actionPerformed(e);
		expectedContents = "";
		testingContents = window.getTextArea();
		
		try{
			inputDocument = new Scanner(new FileInputStream(window.getSelectedForLoadingFile()));
			}
			catch(FileNotFoundException e){
				System.out.println("File was not found");
				System.exit(0);
			}

		while (inputDocument.hasNextLine( )){
			 expectedContents = expectedContents + inputDocument.nextLine() + "\n";
			 }
		
		assertEquals("The contents of the current document must match with the contents of the file that has loaded from the disk", expectedContents, testingContents);	
	}

}
