package latexEditorController.commands;


import latexEditorView.LatexEditorView;


public class AddLatexCommand implements Command{
	
	private LatexEditorView window;
	private String actionCommand;

	
	public AddLatexCommand(LatexEditorView window) {
		this.window = window;
	}

	
	@Override
	public void execute(){
		actionCommand = window.getAddCommandChoice();
		if (actionCommand == "Chapter"){
			window.insertTextArea("\\chapter{...}" + "\n");
		}else if(actionCommand == "Section"){
			window.insertTextArea("\\section{}" + "\n");
		}else if (actionCommand == "Subsection"){
			window.insertTextArea("\\subsection{}" + "\n");
		}else if (actionCommand == "Subsubsection"){
			window.insertTextArea("\\subsubsection{}" + "\n");
		}else if (actionCommand == "Enumeration List(Unordered)"){
			String enumerationList = "\\begin{itemize}" + "\n" + "\\item ..."
									 + "\n" + "\\item ..." + "\n" + "\\end{itemize}" + "\n";
			window.insertTextArea(enumerationList);
		}else if (actionCommand == "Enumeration List(Ordered)"){
			String enumerationList = "\\begin{enumerate}" + "\n" + "\\item ..."
									 + "\n" + "\\item ..." + "\n" + "\\end{enumerate}" + "\n";
			window.insertTextArea(enumerationList);	
		}else if(actionCommand == "Table"){
			String table = "\\begin{table}" + "\n" + "\\caption{....}\\label{...}" + "\n"
							+ "\\begin{tabular}{|c|c|c|}" + "\n"  + "  " + "\\hline" + "\n"
							+ "... &...&...\\" + "\n" + "... &...&...\\" + "\n" 
							+ "... &...&...\\" + "\n"
							+ "  " +  "\\hline" + "\n"
							+ "\\end{tabular}" + "\n" + "\\end{table}" + "\n";
			window.insertTextArea(table);
		}else{
			String figure = "\\begin{figure}" + "\n" + "\\includegraphics[width=...,height=...]{...}"
							 + "\n" + "\\caption{....}\\label{...}" + "\n" + "\\end{figure}" + "\n";
			window.insertTextArea(figure);
		}
	}	
	
}

		

