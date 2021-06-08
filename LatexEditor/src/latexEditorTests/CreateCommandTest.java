package latexEditorTests;


import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.*;
import latexEditorModel.*;
import latexEditorController.commands.*;
import latexEditorView.LatexEditorView;


public class CreateCommandTest {
	private Document testingDocument;
	private DocumentManager documentManagerTesting;
	private String testingBookContents;
	private String testingLetterContents;
	private String testingArticleContents;
	private String testingReportContents;
	private String testingEmptyContents;
	private String expectedBookContents;
	private String expectedLetterContents;
	private String expectedEmptyContents;
	private String expectedReportContents;
	private String expectedArticleContents;
	private LatexEditorView window = new LatexEditorView();
	private CreateCommand createCommandTester;
	private Scanner inputArticle = null;
	private Scanner inputBook = null;
	private Scanner inputReport = null;
	private Scanner inputLetter = null;
	private String articleTemplateState = "";
	private String reportTemplateState = "";
	private String letterTemplateState = "";
	private String bookTemplateState = "";
	
	
	@Test
	public void testExecute() {
		try{
			 inputArticle =
			 new Scanner(new FileInputStream("C:/Users/H/Desktop/tex-templates/article-template.tex"));
			 
			 inputBook =
			 new Scanner(new FileInputStream("C:/Users/H/Desktop/tex-templates/book-template.tex"));
			 
			 inputReport =
			 new Scanner(new FileInputStream("C:/Users/H/Desktop/tex-templates/report-template.tex"));
			 
			 inputLetter =
			 new Scanner(new FileInputStream("C:/Users/H/Desktop/tex-templates/letter-template.tex"));
			 }
			 catch(FileNotFoundException e)
			 {
				 System.out.println("File morestuff.txt was not found");
				 System.out.println("or could not be opened.");
				 System.exit(0);
			 }
	
			while (inputArticle.hasNextLine( )){
				 articleTemplateState = articleTemplateState + inputArticle.nextLine() + "\n";
				 }
			
			while (inputLetter.hasNextLine( )){
				 letterTemplateState = letterTemplateState + inputLetter.nextLine() + "\n";
				 }
	
			while (inputBook.hasNextLine( )){
				 bookTemplateState = bookTemplateState + inputBook.nextLine() + "\n";
				 }
			
			while (inputReport.hasNextLine( )){
				 reportTemplateState = reportTemplateState + inputReport.nextLine() + "\n";
				 }
		createCommandTester = new CreateCommand(window);
		
		expectedBookContents = bookTemplateState;
		expectedLetterContents = letterTemplateState;
		expectedEmptyContents = " ";
		expectedReportContents = reportTemplateState;
		expectedArticleContents = articleTemplateState;
		
		assertEquals("The Expected Report Contents must be the same with the testing", expectedReportContents, testReportTemplate());
		assertEquals("The Expected Article Contents must be the same with the testing", expectedArticleContents, testArticleTemplate());
		assertEquals("The Expected Empty Contents must be the same with the testing", expectedEmptyContents, testEmptyTemplate());
		assertEquals("The Expected Letter Contents must be the same with the testing", expectedLetterContents, testLetterTemplate());
		assertEquals("The Expected Book Contents must be the same with the testing", expectedBookContents, testBookTemplate());	
	}

	
	public String testArticleTemplate(){
		documentManagerTesting = createCommandTester.getDocumentManager();
		testingDocument = documentManagerTesting.createDocument("articleTemplate");
		testingArticleContents = testingDocument.getContents();
		
		return testingArticleContents;	
	}
	
	
	public String testReportTemplate(){
		documentManagerTesting = createCommandTester.getDocumentManager();
		testingDocument = documentManagerTesting.createDocument("reportTemplate");
		testingReportContents = testingDocument.getContents();

		return testingReportContents;
	}
	
	
	public String testBookTemplate(){
		documentManagerTesting = createCommandTester.getDocumentManager();
		testingDocument = documentManagerTesting.createDocument("bookTemplate");
		testingBookContents = testingDocument.getContents();
		
		return testingBookContents;
	}
		

	public String testLetterTemplate(){
		documentManagerTesting = createCommandTester.getDocumentManager();
		testingDocument = documentManagerTesting.createDocument("letterTemplate");
		testingLetterContents = testingDocument.getContents();
			
		return testingLetterContents;		
	}
	
	
	public String testEmptyTemplate(){	
			documentManagerTesting = createCommandTester.getDocumentManager();
			testingDocument = documentManagerTesting.createDocument("emptyTemplate");
			testingEmptyContents = testingDocument.getContents();
			
			return testingEmptyContents;		
	}	
	
}
