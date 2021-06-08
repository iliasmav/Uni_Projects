package latexEditorTests;


import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import org.junit.Test;
import latexEditorView.LatexEditorView;
import latexEditorView.LatexEditorView.NewChoiceListener;
import latexEditorView.LatexEditorView.ChapterCommandListener;
import latexEditorView.LatexEditorView.SectionCommandListener;
import latexEditorView.LatexEditorView.SubsectionCommandListener;
import latexEditorView.LatexEditorView.SubsubsectionCommandListener;
import latexEditorView.LatexEditorView.TableCommandListener;
import latexEditorView.LatexEditorView.FigureCommandListener;
import latexEditorView.LatexEditorView.EnumerationListOrderedListener;
import latexEditorView.LatexEditorView.EnumerationListUnorderedListener;


public class AddLatexCommandTest {
	private LatexEditorView window = new LatexEditorView();
	private NewChoiceListener newChoiceListener = window.new NewChoiceListener();
	private String testingChapterAddedContents;
	private String testingSectionAddedContents;
	private String testingSubsectionAddedContents;
	private String testingSubsubsectionAddedContents;
	private String testingEnumerationListOrderedAddedContents;
	private String testingEnumerationListUnorderedAddedContents;
	private String testingTableAddedContents;
	private String testingFigureAddedContents;
	private ActionEvent e;
	
	
	@Test
	public void testExecute() {
		testingChapterAddedContents = ChapterCommand();
		testingSectionAddedContents = SectionCommand();
		testingSubsectionAddedContents = SubsectionCommand();
		testingSubsubsectionAddedContents = SubsubsectionCommand();
		testingEnumerationListOrderedAddedContents = EnumerationListOrderedCommand();
		testingEnumerationListUnorderedAddedContents = EnumerationListUnorderedCommand();
		testingTableAddedContents = TableCommand();
		testingFigureAddedContents = FigureCommand();
		
		String expectedChapterAddedContents = "\\chapter{...}" + "\n" + " " ;
		String expectedSectionAddedContents = "\\section{}" + "\n" + " ";
		String expectedSubsectionAddedContents = "\\subsection{}" + "\n" + " ";
		String expectedSubsubsectionAddedContents = "\\subsubsection{}" + "\n" + " ";
		String expectedEnumerationListOrderedAddedContents = "\\begin{enumerate}" + "\n" + "\\item ..."
															+ "\n" + "\\item ..." + "\n" + "\\end{enumerate}" + "\n" + " ";
		String expectedEnumerationListUnorderedAddedContents = "\\begin{itemize}" + "\n" + "\\item ..."
															+ "\n" + "\\item ..." + "\n" + "\\end{itemize}" + "\n" + " ";
		String expectedTableAddedContents = "\\begin{table}" + "\n" + "\\caption{....}\\label{...}" + "\n"
											+ "\\begin{tabular}{|c|c|c|}" + "\n"  + "  " + "\\hline" + "\n"
											+ "... &...&...\\" + "\n" + "... &...&...\\" + "\n" 
											+ "... &...&...\\" + "\n"
											+ "  " +  "\\hline" + "\n"
											+ "\\end{tabular}" + "\n" + "\\end{table}" + "\n" + " ";
		String expectedFigureAddedContents = "\\begin{figure}" + "\n" + "\\includegraphics[width=...,height=...]{...}"
				 							+ "\n" + "\\caption{....}\\label{...}" + "\n" + "\\end{figure}" + "\n" + " "
				 									+ "";
				 							
				 							
		assertEquals("The Expected Chapter Added Contents must be the same with the testing", expectedChapterAddedContents, testingChapterAddedContents);
		assertEquals("The Expected Section Added Contents must be the same with the testing", expectedSectionAddedContents, testingSectionAddedContents);
		assertEquals("The Expected Subsection Added Contents must be the same with the testing", expectedSubsectionAddedContents, testingSubsectionAddedContents);
		assertEquals("The Expected Subsubsection Contents must be the same with the testing", expectedSubsubsectionAddedContents, testingSubsubsectionAddedContents);
		assertEquals("The Expected Enumeration List(Unordered) Contents must be the same with the testing", expectedEnumerationListUnorderedAddedContents, testingEnumerationListUnorderedAddedContents);
		assertEquals("The Expected Enumeration List(Ordered) Contents must be the same with the testing", expectedEnumerationListOrderedAddedContents, testingEnumerationListOrderedAddedContents);
		assertEquals("The Expected Table Added Contents must be the same with the testing", expectedTableAddedContents, testingTableAddedContents);
		assertEquals("The Expected Figure Contents must be the same with the testing", expectedFigureAddedContents, testingFigureAddedContents);						
	}

	
	public String ChapterCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		ChapterCommandListener chapterCommandListener = window.new ChapterCommandListener();
		e = new ActionEvent(0, 0, "Chapter");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
	
	public String SectionCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		SectionCommandListener chapterCommandListener = window.new SectionCommandListener();
		e = new ActionEvent(0, 0, "Section");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
	
	public String SubsectionCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		SubsectionCommandListener chapterCommandListener = window.new SubsectionCommandListener();
		e = new ActionEvent(0, 0, "Subsection");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
	
	public String SubsubsectionCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		SubsubsectionCommandListener chapterCommandListener = window.new SubsubsectionCommandListener();
		e = new ActionEvent(0, 0, "Subsubsection");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}

	
	public String EnumerationListUnorderedCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		EnumerationListUnorderedListener chapterCommandListener = window.new EnumerationListUnorderedListener();
		e = new ActionEvent(0, 0, "Enumeration List(Unordered)");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
	
	public String EnumerationListOrderedCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		EnumerationListOrderedListener chapterCommandListener = window.new EnumerationListOrderedListener();
		e = new ActionEvent(0, 0, "Enumeration List(Ordered)");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
	
	public String TableCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		TableCommandListener chapterCommandListener = window.new TableCommandListener();
		e = new ActionEvent(0, 0, "Table");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
	
	public String FigureCommand(){
		e = new ActionEvent(0, 0, "dd");
		newChoiceListener.actionPerformed(e);
		
		FigureCommandListener chapterCommandListener = window.new FigureCommandListener();
		e = new ActionEvent(0, 0, "Figure");
		chapterCommandListener.actionPerformed(e);
		return window.getTextArea();
	}
	
}	