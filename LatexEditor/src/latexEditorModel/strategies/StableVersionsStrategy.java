package latexEditorModel.strategies;


import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import latexEditorView.LatexEditorView;
import latexEditorModel.Document;


public class StableVersionsStrategy implements VersionsStrategy {
	private LatexEditorView window;
	private List<File> stableList = new ArrayList<File>();
	private Scanner inputFile = null;
	private JFileChooser fileChooser;
	private boolean firstTimeStable = true;
	
	
	public StableVersionsStrategy(LatexEditorView window) {
		this.window = window;
	}
	

	@Override
	public void putVersion(Document document) {
		File file = new File(fileChooser.getSelectedFile() + "/document" + document.getVersionID() + ".tex");
		document.save(file);		
		stableList.add(document.getFile());
		
		
	}

	
	@Override
	public Document getVersion(){
		Document documentFromFile;
		
		File file = stableList.get(stableList.size()-1);
		try{
			 inputFile = new Scanner(new FileInputStream(file));
			 }catch(FileNotFoundException e)
			 {
				 System.out.println("File morestuff.txt was not found");
				 System.out.println("or could not be opened.");
				 System.exit(0);
			 }
		
		String author = inputFile.nextLine();
		String date = inputFile.nextLine();
		String copyright = inputFile.nextLine();
		String versionID = inputFile.nextLine();
		
		String contents = "";
		while (inputFile.hasNextLine( )){
			contents = contents + inputFile.nextLine() + "\n";
		}
		
		String actionCommand = window.getTemplateChoice();
		if(actionCommand == "New"){	
			documentFromFile = window.getDocumentManager().createDocument("emptyTemplate");
		}else if(actionCommand == "New Article"){
			documentFromFile = window.getDocumentManager().createDocument("articleTemplate");
		}else if(actionCommand == "New Book"){
			documentFromFile = window.getDocumentManager().createDocument("bookTemplate");
		}else if(actionCommand == "New Report"){
			documentFromFile = window.getDocumentManager().createDocument("reportTemplate");
		}else{
			documentFromFile= window.getDocumentManager().createDocument("letterTemplate");		
		}
		documentFromFile.setAuthor(author);
		documentFromFile.setDate(date);
		documentFromFile.setCopyright(copyright);
		documentFromFile.setVersionID(versionID);
		documentFromFile.setContents(contents);
		
		return documentFromFile;
	}

	
	@Override
	public void setEntireHistory(List<Document> list) {
		File file;
		
		if (firstTimeStable){
			String choosertitle = "Choose a directory";
			fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setDialogTitle(choosertitle);
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChooser.setAcceptAllFileFilterUsed(false);
			if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				
			}else {
				System.out.println("No Selection ");
			}
		     
			for(int i = 0; i < list.size(); i++){
				file = new File(fileChooser.getSelectedFile() + "/document" + list.get(i).getVersionID() + ".tex");
				list.get(i).save(file);
				stableList.add(file);	
			}
			firstTimeStable = false;
		}else{
		
			for(int i = 0; i < list.size(); i++){
				file = new File(fileChooser.getSelectedFile() + "/document" + list.get(i).getVersionID() + ".tex");
				list.get(i).save(file);
				stableList.add(file);  	
			}
		}
		
	}

	
	@Override
	public List<Document> getEntireHistory() {
		Document documentFromStableList = null;
		List<Document> tempList = new ArrayList<Document>();
		
		for(int i = 0; i < stableList.size(); i++){
			try{
				 inputFile = new Scanner(new FileInputStream(stableList.get(i)));
				 }catch(FileNotFoundException e)
				 {
					 System.out.println("File morestuff.txt was not found");
					 System.out.println("or could not be opened.");
					 System.exit(0);
				 }
			
			String author = inputFile.nextLine();
			String date = inputFile.nextLine();
			String copyright = inputFile.nextLine();
			String versionID = inputFile.nextLine();
			String contents = "";
			
			while (inputFile.hasNextLine( )){
				contents = contents + inputFile.nextLine() + "\n";
			}
			
			String actionCommand = window.getTemplateChoice();
			
			if(actionCommand == "New"){	
				documentFromStableList = window.getDocumentManager().createDocument("emptyTemplate");
			}else if(actionCommand == "New Article"){
				documentFromStableList = window.getDocumentManager().createDocument("articleTemplate");
			}else if(actionCommand == "New Book"){
				documentFromStableList = window.getDocumentManager().createDocument("bookTemplate");
			}else if(actionCommand == "New Report"){
				documentFromStableList = window.getDocumentManager().createDocument("reportTemplate");
			}else{
				documentFromStableList= window.getDocumentManager().createDocument("letterTemplate");		
			}
			
			documentFromStableList.setAuthor(author);
			documentFromStableList.setDate(date);
			documentFromStableList.setCopyright(copyright);
			documentFromStableList.setVersionID(versionID);
			documentFromStableList.setContents(contents);
			tempList.add(documentFromStableList);
		
		}
	    return tempList;	
	}
	
	
	@Override
	public void removeVersion(){
		stableList.remove(stableList.size()-1);
	}
	
	
	@Override
	public void removeListElements(){
		for(int i=0; i < stableList.size(); i++){
			stableList.get(i).delete();
			stableList.remove(stableList.get(i));
		}
	}

}
