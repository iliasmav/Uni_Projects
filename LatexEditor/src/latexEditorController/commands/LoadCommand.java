package latexEditorController.commands;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import latexEditorView.LatexEditorView;


public class LoadCommand implements Command {
	private LatexEditorView window;
	private Scanner inputDocument = null;
	private String inputDocumentContents = "";
	private File selectedForLoadingFile;
	
	public LoadCommand(LatexEditorView window){
		this.window = window;
	}
	
	
	public String getInputDocumentContents(){
		return inputDocumentContents;
	}
	
	
	@Override
	public void execute(){
		selectedForLoadingFile = window.getSelectedForLoadingFile();
		try{
			inputDocument = new Scanner(new FileInputStream(selectedForLoadingFile));
			}
			catch(FileNotFoundException e){
				System.out.println("File was not found");
				System.exit(0);
			}
		while (inputDocument.hasNextLine( )){
			 inputDocumentContents = inputDocumentContents + inputDocument.nextLine() + "\n";
		}
	}

}
