package latexEditorController.commands;


import latexEditorView.LatexEditorView;
import java.io.File;


public class SaveCommand implements Command{
	
	private LatexEditorView window;
	private File selectedForSavingFile;
	
	public SaveCommand(LatexEditorView window){
		this.window = window;
	}
	
	
	@Override
	public void execute(){
		selectedForSavingFile = window.getSelectedForSavingFile();
		window.getCurrent().save(selectedForSavingFile);	
	}
	
}
