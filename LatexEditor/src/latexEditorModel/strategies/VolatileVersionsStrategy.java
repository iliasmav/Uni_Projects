package latexEditorModel.strategies;


import java.util.ArrayList;
import java.util.List;
import latexEditorModel.Document;
import latexEditorView.LatexEditorView;


public class VolatileVersionsStrategy implements VersionsStrategy {
	
	private List<Document> volatileList = new ArrayList<Document>();

	
	public VolatileVersionsStrategy(LatexEditorView window){
	}

	
	@Override
	public void putVersion(Document document) {
		volatileList.add(new Document(document.getAuthor(), document.getDate(), document.getCopyright(),
						 document.getVersionID(), document.getContents()));
	}

	
	@Override
	public Document getVersion() {
		return volatileList.get(volatileList.size()-1);
	}

	
	@Override
	public void setEntireHistory(List<Document> list){
		for (int i = 0; i < list.size(); i++){
			volatileList.add(new Document(list.get(i).getAuthor(), list.get(i).getDate(), list.get(i).getCopyright(),
					 list.get(i).getVersionID(), list.get(i).getContents()));
		}	
	}
	

	@Override
	public List<Document> getEntireHistory() {
		return volatileList;	
	}

	
	@Override
	public void removeVersion(){
		volatileList.remove(volatileList.size()-1);
	}
	
	
	@Override
	public void removeListElements(){
		for (int i = 0; i < volatileList.size(); i++){
			volatileList.remove(volatileList.get(i));	
		}
	}	

}
