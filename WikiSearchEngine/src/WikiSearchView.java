import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.html.*;
import java.awt.Color;
import org.apache.lucene.search.highlight.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import java.awt.SystemColor;


public class WikiSearchView {

	private JFrame frame;
	private JTextField textField;
	private SearchDocuments searchDocuments;
	private JMenu menu;
	private JMenuItem titleSearch;
	private JMenuItem castSearch;
	private JMenuItem genreSearch;
	private JMenuItem releaseYearSearch;
	private JMenuItem directorSearch;
	private javax.swing.text.html.HTMLDocument textArea;
	private JButton nextPageButton;
	private JMenuBar menuBar;
	private JTextPane textPane;
	private JScrollPane scrolledText;
	private JLabel lblNewLabel;
	private JButton searchButton;
	private JRadioButton sort;
	private int page;
	private int counter;
	
	/**
	 * @wbp.nonvisual location=73,-11
	 */
	
	public class NextPageListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				textPane.setText("");
				ArrayList<Document> hitDocs = searchDocuments.search(textField.getText(), page, sort.isSelected());
				System.out.println(hitDocs.size());
				for (int i=page-5; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					
					TextFragment[] frag = searchDocuments.highlight(i);
			
					for (int j = 0; j < frag.length; j++) {
						int k = 0;
						if ((frag[j] != null) && (frag[j].getScore() > 0)) {
							if (k==0) {
								textArea.insertString(textArea.getLength(), "\n", null);
								textArea.insertString(textArea.getLength(), "...", null);	
							}
							textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), frag[j].toString() + " ");
							k=k+1;
						} 
					}
					
					textArea.insertString(textArea.getLength(), "\n", null);	
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}
				
				if (hitDocs.size() < page){
					textArea.insertString(textArea.getLength(),"-----------------------------------", null);
					textArea.insertString(textArea.getLength(), "\n", null);	
					textArea.insertString(textArea.getLength(), "| No More Results Found |", null);
					textArea.insertString(textArea.getLength(), "\n", null);	
					textArea.insertString(textArea.getLength(),"-----------------------------------", null);
				}
				page=page+5;
				
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
	
	
	
	public class SearchListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				page=10;
				textPane.setText("");
				ArrayList<Document> hitDocs = searchDocuments.search(textField.getText(), sort.isSelected());
				counter = 1;
				for (int i=0; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					textArea.insertString(textArea.getLength(), "\n", null);
					
					TextFragment[] frag = searchDocuments.highlight(i);
					
					textArea.insertString(textArea.getLength(), "...", null);
					for (int j = 0; j < frag.length; j++) {
						if ((frag[j] != null) && (frag[j].getScore() > 0)) {
							textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), frag[j].toString() + " ");		
						} 
					}
					
					textArea.insertString(textArea.getLength(), "...", null);
					textArea.insertString(textArea.getLength(), "\n", null);	
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}	       
				
			} catch (ParseException | IOException | InvalidTokenOffsetsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	
	public class TitleSearchListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				counter = 1;
				page=10;
				textPane.setText("");
				ArrayList<Document> hitDocs = searchDocuments.search("title:" + textField.getText(), sort.isSelected());
				for (int i=0; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}
				
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	}
	
	
	public class ReleaseYearListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				counter = 1;
				page=10;
				textPane.setText("");
				ArrayList<Document> hitDocs = searchDocuments.search("release_year:" + textField.getText(), sort.isSelected());
				for (int i=0; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}
				
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	

	
	public class CastSearchListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				page=10;
				counter = 1;
				textPane.setText("");
				ArrayList<Document> hitDocs = searchDocuments.search("cast:" + textField.getText(),sort.isSelected());
				for (int i=0; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}
				
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	}
	
	
	public class DirectorSearchListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				page=10;
				counter = 1;
				textPane.setText("");
				ArrayList<Document> hitDocs = searchDocuments.search("directors:" + textField.getText(), sort.isSelected());
				for (int i=0; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}
				
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	}
	
	
	public class GenreSearchListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			try {
				page=10;
				textPane.setText("");
				counter = 1;
				ArrayList<Document> hitDocs = searchDocuments.search("genre:" + textField.getText(), sort.isSelected());
				for (int i=0; i<hitDocs.size(); i++) {
					textArea.insertString(textArea.getLength(), counter + "." + " ", null);
					textArea.insertString(textArea.getLength(), hitDocs.get(i).get("title") + " " + "-" + " " + hitDocs.get(i).get("release_year"), null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertAfterEnd(textArea.getCharacterElement(textArea.getLength()), "<a href=\\>" + hitDocs.get(i).get("wiki page") + "</a>");
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					textArea.insertString(textArea.getLength(), "\n", null);
					counter = counter + 1;
				}
				
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	}
	
		
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WikiSearchView window = new WikiSearchView();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws BadLocationException 
	 */
	public WikiSearchView() throws IOException, BadLocationException {
		searchDocuments = new SearchDocuments();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws BadLocationException 
	 */
	private void initialize() throws BadLocationException {
		frame = new JFrame();
		frame.setTitle("myWikiSearch");
		frame.setBounds(700, 700, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setBounds(138, 29, 397, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("Wiki Movies Searching");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(204, 51, 0));
		lblNewLabel.setBounds(292, 11, 243, 20);
		frame.getContentPane().add(lblNewLabel);
		
		searchButton = new JButton("Search");
		searchButton.setBackground(UIManager.getColor("TextField.light"));
		searchButton.setForeground(SystemColor.textHighlight);
		searchButton.addActionListener(new SearchListener());
		searchButton.setBounds(447, 55, 89, 33);
		frame.getContentPane().add(searchButton);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("TextField.light"));
		menuBar.setForeground(UIManager.getColor("TextField.selectionBackground"));
		menuBar.setBounds(322, 60, 97, 21);
		frame.getContentPane().add(menuBar);
		
		menu = new JMenu("Search By");
		menu.setForeground(SystemColor.textHighlight);
		menu.setBackground(SystemColor.menu);
		menu.setBounds(322, 60, 107, 22);
		menuBar.add(menu);
		
		titleSearch = new JMenuItem("title");
		titleSearch.setBounds(304, 60, 129, 22);
		titleSearch.addActionListener(new TitleSearchListener());
		menu.add(titleSearch);
		
		releaseYearSearch = new JMenuItem("release year");
		releaseYearSearch.setBounds(304, 60, 129, 22);
		releaseYearSearch.addActionListener(new ReleaseYearListener());
		menu.add(releaseYearSearch);
		
		
		castSearch = new JMenuItem("cast");
		castSearch.setBounds(304, 60, 129, 22);
		castSearch.addActionListener(new CastSearchListener());
		menu.add(castSearch);
		
		genreSearch = new JMenuItem("genre");
		genreSearch.setBounds(304, 60, 129, 22);
		genreSearch.addActionListener(new GenreSearchListener());
		menu.add(genreSearch);
		
		directorSearch = new JMenuItem("director");
		directorSearch.setBounds(304, 60, 129, 22);
		directorSearch.addActionListener(new DirectorSearchListener());
		menu.add(directorSearch);
		
		nextPageButton = new JButton("Go to Next Page");
		nextPageButton.setBounds(552, 627, 122, 23);
		frame.getContentPane().add(nextPageButton);
		nextPageButton.addActionListener(new NextPageListener());
		
		textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setEnabled(true);
		
		scrolledText = new JScrollPane(textPane);
		scrolledText.setBounds(10, 129, 664, 493);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrolledText);
		
		textArea = (HTMLDocument) textPane.getStyledDocument();
		textPane.setBounds(10, 129, 664, 493);
		
		sort = new JRadioButton("Show Older Movies First");
		sort.setBackground(SystemColor.menu);
		sort.setForeground(SystemColor.textHighlight);
		sort.setBounds(138, 61, 178, 20);
		frame.getContentPane().add(sort);
		
		scrolledText.getHorizontalScrollBar().setEnabled(true);
		scrolledText.getVerticalScrollBar().setEnabled(true);
		scrolledText.isVisible();	
	}	
}










