package latexEditorModel;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.io.FileInputStream;
import java.util.Scanner;


public  class DocumentManager {
	
		private static final int ID = 0;
		private static final int STATE = 1;
		private Scanner inputArticle = null;
		private Scanner inputBook = null;
		private Scanner inputReport = null;
		private Scanner inputLetter = null;
		private String articleTemplateState = "";
		private String reportTemplateState = "";
		private String letterTemplateState = "";
		private String bookTemplateState = "";
		private  BufferedReader prototypeSpecsReader;
		private HashMap <String, Document> mapOfPrototypes = new HashMap <String, Document>();
		
	
		public DocumentManager(){
			dynamicallyLoadPrototypes("C:/Users/H/Desktop/tex-templates/prototype-specs.txt");	
		}
		
		
		public void loadDataFromTemplateFiles(){
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
		}
		
		
		public void dynamicallyLoadPrototypes(String prototypeSpecsFileName){	
			loadDataFromTemplateFiles();
			
			try {
				prototypeSpecsReader = new BufferedReader(
						new FileReader (prototypeSpecsFileName)
					);
				
				String currentSpec;
				while ((currentSpec = prototypeSpecsReader.readLine())!= null) {
					
					String keyStateConfigPair[] = currentSpec.split(" ");
					
					if(keyStateConfigPair[STATE].equals("articleTemplateState")){
						mapOfPrototypes.put(
								keyStateConfigPair[ID],
								new Document ("", "", "", "", articleTemplateState));
					}else if(keyStateConfigPair[STATE].equals("bookTemplateState")){
						mapOfPrototypes.put(
								keyStateConfigPair[ID],
								new Document ("", "", "", "", bookTemplateState));
					}else if(keyStateConfigPair[STATE].equals("letterTemplateState")){
						mapOfPrototypes.put(
								keyStateConfigPair[ID],
								new Document ("", "", "", "", letterTemplateState));
					}else if(keyStateConfigPair[STATE].equals("reportTemplateState")){
						mapOfPrototypes.put(
								keyStateConfigPair[ID],
								new Document ("", "", "", "", reportTemplateState));
					}else{
						mapOfPrototypes.put("emptyTemplate", new Document(" ", " ", " ", " ", " "));
					}	
				}
				prototypeSpecsReader.close();
				
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		
		
		public Document createDocument(String prototypeID){	
			Document prototype = mapOfPrototypes.get(prototypeID);
			return prototype.clone();
		}

}
