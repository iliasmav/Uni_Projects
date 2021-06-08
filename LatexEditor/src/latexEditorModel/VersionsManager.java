package latexEditorModel;


import latexEditorView.LatexEditorView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import latexEditorModel.strategies.*;


public class VersionsManager {
	
	private LatexEditorView window;
	private VersionsStrategy strategy;
	private boolean enabled;
	private int id = 1;

	
	public VersionsManager(VersionsStrategy strategy, LatexEditorView window) {
		this.strategy = strategy;
		this.window = window;
	}

	
	public boolean isEnabled(){
		return enabled;
	}
	
	
	public void enable(){
		enabled = true;
	}
	
	
	public void disable(){
		enabled = false;
	}
	
	
	public void setStrategy(VersionsStrategy strategy){
		this.strategy = strategy;
	}
	
	
	public VersionsStrategy getStrategy(){
		return strategy;
	}
	
	
	public void setCurrentVersion(Document newDocumentVersion){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	Date date = new Date();
		newDocumentVersion.setVersionID("Version" + id);
		strategy.putVersion(newDocumentVersion);
		window.setCurrent(newDocumentVersion);
		window.getCurrent().setVersionID("Version" + id);
		id++;
		window.getFieldsLabel().setText("Author:  " + window.getAuthorInput() + "     " + "Date:  " + dateFormat.format(date) + "     " + "Copyright:  " + window.getCopyrightInput() + "     " + "VersionID:  " + window.getCurrent().getVersionID());
	}
	
	
	public Document getPreviousVersion(){
		return strategy.getVersion();
	}
	
	
	public void rollbackToPreviousVersion(){
		strategy.removeVersion();
		window.setTextArea(getPreviousVersion().getContents());
		window.setCurrent(getPreviousVersion());
		window.getFieldsLabel().setText("Author:  " + window.getAuthorInput() + "     " + "Date:  " + window.getDateFormat().format(window.getDate()) + "     " + "Copyright:  " + window.getCopyrightInput() + "     " + "VersionID:  " + getPreviousVersion().getVersionID());
		id--;
	}
	
}
