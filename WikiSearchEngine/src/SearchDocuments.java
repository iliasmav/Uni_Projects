
import java.io.IOException;
import java.util.ArrayList;
import java.io.StreamTokenizer;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.Sort;
import org.apache.lucene.queryparser.classic.*;
import org.apache.lucene.document.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.WeightedSpanTermExtractor;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.search.highlight.GradientFormatter;


public class SearchDocuments {
	private CreateDocuments createDocuments;
	private DirectoryReader ireader;
	private IndexSearcher isearcher;
	private QueryParser parser;
	private ScoreDoc[] hits;
	private Query query;
	private SimpleHTMLFormatter htmlFormatter;
	private Highlighter highlighter;
	private TextFragment[] frag;
	private Sort sort;
	
	public SearchDocuments() throws IOException {
		createDocuments = new CreateDocuments();
		//createDocuments.createIndex();
		ireader = DirectoryReader.open(createDocuments.getDirectory());
		isearcher = new IndexSearcher(ireader);
	}
	
	
	public ArrayList<Document> search(String text, Boolean sortIsSelected) throws ParseException, IOException {
		
		ArrayList<Document> hitDocs = new ArrayList<Document>();
		parser = new QueryParser("contents", createDocuments.getAnalyzer());
		query = parser.parse(text);
		
		
		if(sortIsSelected==true) {
			sort = new Sort(SortField.FIELD_DOC, new SortField("release_year", SortField.Type.STRING));
			hits = isearcher.search(query,5,sort).scoreDocs;
			for (int i = 0; i<hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println(hitDoc.getValues("title"));
				hitDocs.add(hitDoc);
				
			}
		}else {
			hits = isearcher.search(query, 5).scoreDocs;
			for (int i = 0; i<hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				System.out.println(hitDoc.getValues("title"));
				hitDocs.add(hitDoc);
			}
		}
		return hitDocs;
		
	}

	public ArrayList<Document> search(String text, int page, boolean sortIsSelected) throws ParseException, IOException {
		
		ArrayList<Document> hitDocs = new ArrayList<Document>();
		parser = new QueryParser("contents", createDocuments.getAnalyzer());
		query = parser.parse(text);
		
		if(sortIsSelected ==true) {
			sort = new Sort(SortField.FIELD_DOC, new SortField("release_year", SortField.Type.STRING));
			hits = isearcher.search(query,page,sort).scoreDocs;
			for (int i = 0; i<hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				hitDocs.add(hitDoc);	
			}
			
		}else{
			hits = isearcher.search(query, page).scoreDocs;
			for (int i = 0; i<hits.length; i++) {
				Document hitDoc = isearcher.doc(hits[i].doc);
				hitDocs.add(hitDoc);
			}	
		}
		
		return hitDocs;	
		
	}
	
	
	public TextFragment[] highlight(int i) throws ParseException, IOException, InvalidTokenOffsetsException {
	
		htmlFormatter = new SimpleHTMLFormatter();
		highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
	
		Document hitDoc = isearcher.doc(hits[i].doc);
		String txt = hitDoc.get("plot");
		TokenStream tokenStream = TokenSources.getAnyTokenStream(isearcher.getIndexReader(), hits[i].doc, "plot", createDocuments.getAnalyzer());
		frag = highlighter.getBestTextFragments(tokenStream, txt, false, 10);
			
		return frag;
	}

}
