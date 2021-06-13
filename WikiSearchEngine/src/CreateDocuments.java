import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.lucene.index.*;
import org.apache.lucene.document.*;
import org.apache.lucene.util.*;
import org.apache.lucene.analysis.standard.myAnalyzer;
import org.apache.lucene.store.*;
import java.nio.file.Paths;
import org.apache.lucene.analysis.*;



public class CreateDocuments {
	
	private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private Analyzer analyzer;
    private Directory directory;
    private IndexWriterConfig config;
    private IndexWriter iwriter;
    private String INDEX_DIRECTORY = "C:/Users/user/eclipse-workspace/WikiSearchEngine/index";
    
    
    public CreateDocuments() {
    	analyzer = new myAnalyzer();
    	try {
			directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	config = new IndexWriterConfig(analyzer);
    	try {
			iwriter = new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
    
    
    
    public void createIndex() throws IOException {
    	
    	String csvFile = "C:/Users/user/Desktop/movies_wiki.csv";
    	Scanner scanner = new Scanner(new File(csvFile));
    	
    	
    	
    
    	
        while (scanner.hasNext()) {
        	String scanner_line = scanner.nextLine();
            List<String> line = parseLine(scanner_line);
            System.out.println("Movie [Release Year= " + line.get(0) + ", Title= " + line.get(1) + ", Origin/Ethnicity= " + line.get(2) + ", Director " + line.get(3) + ", Cast= " + line.get(4) + ", Genre= " + line.get(5) + ", Wiki Page= " + line.get(6) + ", Plot= " + line.get(7) + "]");
            Document doc = new Document();
            String s = line.get(0) + " " + line.get(1) + " " + line.get(2) + " "  + line.get(3)  + " " + line.get(4) + " " + line.get(5) + " " + line.get(7);
            System.out.println(s);
            
            doc.add(new Field("contents", s , TextField.TYPE_NOT_STORED));
            doc.add(new SortedDocValuesField ("release_year", new BytesRef(line.get(0))));
            doc.add(new Field("release_year", line.get(0), StringField.TYPE_STORED));
            doc.add(new Field("title", line.get(1), TextField.TYPE_STORED));
            doc.add(new Field("directors", line.get(3), TextField.TYPE_NOT_STORED));
            doc.add(new Field("cast", line.get(4), TextField.TYPE_NOT_STORED));
            doc.add(new Field("genre", line.get(5), TextField.TYPE_NOT_STORED));
            doc.add(new Field("wiki page", line.get(6), StringField.TYPE_STORED));
            doc.add(new Field("plot", line.get(7), TextField.TYPE_STORED));
            
            iwriter.addDocument(doc);    
        }
        
        iwriter.close();
        scanner.close();

    }

    
    public Directory getDirectory() {
		return directory;
	}
    
    public Analyzer getAnalyzer() {
    	return analyzer;
    }

	public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    
    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

	
   

}