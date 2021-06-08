package latexEditorModel;


import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class Document{
	
	private String author;
	private String date;
	private String copyright;
	private String versionID;
	private String contents;
	private File file;
	
	
	public Document(String author, String date, String copyright, String versionID, String contents){
		this.author = author;
		this.date = date;
		this.copyright = copyright;
		this.versionID = versionID;
		this.contents = contents;
	}
	
	
	public String getContents(){
		return contents;	
	}
	
	
	public void setContents(String contents){
		this.contents = contents;
	}
	
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getCopyright() {
		return copyright;
	}


	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}


	public String getVersionID() {
		return versionID;
	}


	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}
	
	
	public File getFile(){
		return file;
	}
	
	
	public void save(File file){
		this.file = file;
		FileOutputStream outputStream = null;
		PrintWriter outputWriter;
		try{
			outputStream  = new FileOutputStream(file);
			outputWriter = new PrintWriter(outputStream);
			outputWriter.println(author);
			outputWriter.println(date);
			outputWriter.println(copyright);	
			outputWriter.println(versionID);
			outputWriter.println(contents);
			outputWriter.close();
			outputStream.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error opening the file");
			System.exit(0);
		}
		catch(Exception ioe){
			ioe.getMessage();
		}	
	}
	
	
	public Document clone(){	
		return new Document(new String(author), new String(date),
									new String(copyright), new String(versionID), new String(contents));
	}
	
}
