package latexEditorController.commands;


import latexEditorModel.Document;
import latexEditorView.LatexEditorView;
import latexEditorModel.DocumentManager;
import latexEditorModel.strategies.VersionsStrategy;
import latexEditorModel.VersionsManager;
import latexEditorModel.strategies.*;


public class CreateCommand implements Command{
	
	private LatexEditorView window;
	private Document currentDocument;
	private String actionCommand;
	private DocumentManager documentManager;
	private VersionsManager versionsManager;
	private VersionsStrategy volatileStrategy;
	private VersionsStrategyFactory strategyFactory;
	
	
	public CreateCommand(LatexEditorView window){
		this.window = window;
		documentManager = new DocumentManager();
		strategyFactory = new VersionsStrategyFactory(window);
		volatileStrategy = strategyFactory.createStrategy("Volatile");
		versionsManager = new VersionsManager(volatileStrategy, window);
		versionsManager.setStrategy(volatileStrategy);
		versionsManager.disable();	
	}
	
	
	public VersionsStrategyFactory getStrategyFactory(){
		return strategyFactory;
	}
	
	
	public VersionsStrategy getVolatileStrategy(){
		return volatileStrategy;
	}
	
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	

	public VersionsManager getVersionsManager(){
		return versionsManager;
	}

	
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}
	
	
	public DocumentManager getDocumentManager(){
		return documentManager;
	}


	@Override
	public void execute(){
		window.getVersionsMenu().setEnabled(true);
		window.getAddMenu().setEnabled(true);
		window.getEditButton().setEnabled(true);
		window.getRollbackToPreviousCommand().setEnabled(false);
		window.getChangeVersion().setEnabled(false);
		window.getTrackingMechanismLabel().setEnabled(true);
		actionCommand = window.getTemplateChoice();
		if(actionCommand == "New"){	
			currentDocument = documentManager.createDocument("emptyTemplate");
		}else if(actionCommand == "New Article"){
			currentDocument = documentManager.createDocument("articleTemplate");
		}else if(actionCommand == "New Book"){
			currentDocument = documentManager.createDocument("bookTemplate");
		}else if(actionCommand == "New Report"){
			currentDocument = documentManager.createDocument("reportTemplate");
		}else if(actionCommand == "New Letter"){
			currentDocument = documentManager.createDocument("letterTemplate");		
		}else if(actionCommand == " "){
			LoadCommand loadCommand = (LoadCommand)window.getController().getCommands().get("Load Command");
			currentDocument = new Document(" ", " ", " ", " ", loadCommand.getInputDocumentContents());	
		}
		currentDocument.setVersionID(" --- ");
		window.fillDocumentFields(this);
	}

}
