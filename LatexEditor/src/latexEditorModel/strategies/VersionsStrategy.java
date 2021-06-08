package latexEditorModel.strategies;


import java.util.List;

import latexEditorModel.*;


public interface VersionsStrategy {
	
	public void putVersion(Document document);
	public Document getVersion();
	public void setEntireHistory(List<Document> list);
	public List<Document> getEntireHistory();
	public void removeVersion();
	public void removeListElements();

}
