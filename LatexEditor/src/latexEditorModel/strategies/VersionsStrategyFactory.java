package latexEditorModel.strategies;


import latexEditorView.LatexEditorView;


public class VersionsStrategyFactory{
	
	private LatexEditorView window;
	
	
	public VersionsStrategyFactory(LatexEditorView window) {
		this.window = window;
	}
	
	
	public VersionsStrategy createStrategy(String strategy){
		if (strategy == "Volatile"){
			return (new VolatileVersionsStrategy(window));
		}else{
			return (new StableVersionsStrategy(window));
		}
	}

}
