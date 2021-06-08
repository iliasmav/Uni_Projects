//Konstantina Vatavali 2649
//Ilias Mavrianos 2753
///Les Logiciels///

package latexEditorView;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.File;
import latexEditorModel.*;
import latexEditorController.*;
import latexEditorController.commands.*;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import latexEditorModel.strategies.*;
import javax.swing.JScrollPane;



public class LatexEditorView {
	
	private NewChoiceListener ncl;
	private JFrame frame;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu addFromTemplateMenu;
	private JMenu addMenu;
	private JMenu versionsMenu;
	private JMenuItem newChoice;
	private JMenuItem articleChoice;
	private JMenuItem letterChoice;
	private JMenuItem bookChoice;
	private JMenuItem reportChoice;
	private JMenuItem saveCommand;
	private JMenuItem loadCommand;
	private JMenuItem chapterCommand;
	private JMenuItem sectionCommand;
	private JMenuItem subsectionCommand;
	private JMenuItem subsubsectionCommand;
	private JMenuItem enumerationListUnordered;
	private JMenuItem enumerationListOrdered;
	private JMenuItem figureCommand;
	private JMenuItem tableCommand;
	private JMenuItem rollbackToPreviousCommand;
	private JMenuItem changeVersion;
	private JMenuItem alterTrackingMechanism;
	private JButton editButton;
	private CreateCommand createCommand;
	private String templateChoice = " ";
	private String addCommandChoice;
	private String authorInput;
	private String copyrightInput;
	private LatexEditorController controller;
	private File selectedForSavingFile;
	private File selectedForLoadingFile;
	private JLabel fieldsLabel;
	private DateFormat dateFormat;
	private Date date;
	private JLabel trackingMechanismLabel;
	private JScrollPane scrolledText;

	
	public JButton getEditButton(){
		return editButton;
	}
	
	
	public NewChoiceListener getNewChoiceListener(){
		return ncl;
	}
	
	
	public JMenu getAddMenu(){
		return addMenu;
	}
	
	
	public VersionsStrategyFactory getStrategyFactory(){
		return createCommand.getStrategyFactory();
	}
	
	
	public JMenuItem getChangeVersion() {
		return changeVersion;
	}

	
	public DateFormat getDateFormat(){
		return dateFormat;
	}
	
	
	public Date getDate(){
		return date;
	}
	
	
	public JMenu getVersionsMenu(){
		return versionsMenu;
	}

	
	public JLabel getTrackingMechanismLabel(){
		return trackingMechanismLabel;
	}
	
	
	public DocumentManager getDocumentManager(){
		return createCommand.getDocumentManager();
	}
	
	
	public VersionsStrategy getVolatileStrategy(){
		return createCommand.getVolatileStrategy();
	}
	
	
	public VersionsManager getVersionsManager(){
		return createCommand.getVersionsManager();
	}
	
	
	public JMenuItem getRollbackToPreviousCommand(){
		return rollbackToPreviousCommand;
	}
	
	
	public JLabel getFieldsLabel(){
		return fieldsLabel;
	}
	
	
	public String getAuthorInput(){
		return authorInput;
	}
	
	
	public String getCopyrightInput(){
		return copyrightInput;
	}
	
	public LatexEditorController getController() {
		return controller;
	}

	
	public void setSelectedForSavingFile(File selectedForSavingFile) {
		this.selectedForSavingFile = selectedForSavingFile;
	}
	
	
	public File getSelectedForLoadingFile(){
		return selectedForLoadingFile;
	}
	
	
	public File getSelectedForSavingFile() {
		return selectedForSavingFile;
	}

	
	public  String getTextArea(){
		return textArea.getText();
	}
	
	
	public void setTextArea(String text){
		textArea.setText(text);
	}
	
	
	public void insertTextArea(String text){
		textArea.insert(text, textArea.getCaretPosition());
	}
	
	
	public  String getTemplateChoice() {
		return templateChoice;
	}
	
	
	public String getAddCommandChoice(){
		return addCommandChoice;
	}
	
	
	public void setCurrent(Document document){
		createCommand.setCurrentDocument(document);
	}

	
	public Document getCurrent(){
		return createCommand.getCurrentDocument();
	}
	
	
	public class NewChoiceListener implements ActionListener{
		private String contents;
		public void actionPerformed(ActionEvent e){
			templateChoice = newChoice.getActionCommand();
			controller.enact("Create Command");
			createCommand = (CreateCommand)controller.getCommands().get("Create Command");
			contents = createCommand.getCurrentDocument().getContents();
			textArea.setText(contents);
			addMenu.setEnabled(true);
			chapterCommand.setEnabled(true);
		}
	}
	
	
	public class ArticleChoiceListener implements ActionListener{
		private String contents;
		public void actionPerformed(ActionEvent e){
			templateChoice = articleChoice.getActionCommand();
			controller.enact("Create Command");
			createCommand = (CreateCommand)controller.getCommands().get("Create Command");
			contents = createCommand.getCurrentDocument().getContents();
			textArea.setText(contents);
			addMenu.setEnabled(true);
			chapterCommand.setEnabled(false);	
		}
	}
	
	
	public class LetterChoiceListener implements ActionListener{
		private String contents;
		public void actionPerformed(ActionEvent e){
			templateChoice = letterChoice.getActionCommand();
			controller.enact("Create Command");
			createCommand = (CreateCommand)controller.getCommands().get("Create Command");
			contents = createCommand.getCurrentDocument().getContents();
			textArea.setText(contents);
			addMenu.setEnabled(false);
		}
	}
	
	
	public class BookChoiceListener implements ActionListener{
		private String contents;
		public void actionPerformed(ActionEvent e){
			templateChoice = bookChoice.getActionCommand();
			controller.enact("Create Command");
			createCommand = (CreateCommand)controller.getCommands().get("Create Command");
			contents = createCommand.getCurrentDocument().getContents();
			textArea.setText(contents);
			addMenu.setEnabled(true);
			chapterCommand.setEnabled(true);
		}
	}
	
	
	public class ReportChoiceListener implements ActionListener{
		private String contents;
		public void actionPerformed(ActionEvent e){
			templateChoice = reportChoice.getActionCommand();
			controller.enact("Create Command");
			createCommand = (CreateCommand)controller.getCommands().get("Create Command");
			contents = createCommand.getCurrentDocument().getContents();
			textArea.setText(contents);
			addMenu.setEnabled(true);
			chapterCommand.setEnabled(true);
		}
	}

	
	public class EditListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controller.enact("Edit Command");
		}
	}
	
	
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = fileChooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION){
				selectedForSavingFile = fileChooser.getSelectedFile();	
			}
			controller.enact("Save Command");
		}
	}
	
	
	public class LoadListener implements ActionListener{
		private String contents;
		public void actionPerformed(ActionEvent e){
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION){
				selectedForLoadingFile = fileChooser.getSelectedFile();	
			}
			controller.enact("Load Command");
			controller.enact("Create Command");
			createCommand = (CreateCommand)controller.getCommands().get("Create Command");
			contents = createCommand.getCurrentDocument().getContents();
			textArea.setText(contents);
			addMenu.setEnabled(true);
			chapterCommand.setEnabled(true);
		}
	}
	
	
	public class ChapterCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			addCommandChoice = chapterCommand.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class SectionCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = sectionCommand.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class SubsectionCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = subsectionCommand.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class SubsubsectionCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = subsubsectionCommand.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class EnumerationListUnorderedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = enumerationListUnordered.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class EnumerationListOrderedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = enumerationListOrdered.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class TableCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = tableCommand.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class FigureCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			addCommandChoice = figureCommand.getActionCommand();
			controller.enact("Add Latex Command");
		}
	}
	
	
	public class ChangeVersionsStrategyCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controller.enact("Change Versions Strategy Command");
		}
	}
	
	
	public class AlterVersionsManagementListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (alterTrackingMechanism.getActionCommand() == "Disable Tracking Mechanism"){
				controller.enact("Disable Versions Management Command");
				alterTrackingMechanism.setActionCommand("Enable Tracking Mechanism");
				alterTrackingMechanism.setText("Enable Tracking Mechanism");
			}else{
				controller.enact("Enable Versions Management Command");
				alterTrackingMechanism.setActionCommand("Disable Tracking Mechanism");
				alterTrackingMechanism.setText("Disable Tracking Mechanism");
			}		
		}
	}
	
	
	public class RollbackToPreviousVersionCommandListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controller.enact("Rollback To Previous Version Command");	
		}
	}
	
	
	public void createMenusAndMenuBars(){
		//The main menu bar
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		//The File Menu
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		//User choice for creating a empty document
		newChoice = new JMenuItem("New");
		ncl = new NewChoiceListener();
		newChoice.addActionListener(ncl);
		fileMenu.add(newChoice);
		//The Add From Template Menu
		addFromTemplateMenu = new JMenu("Add From Template");
		fileMenu.add(addFromTemplateMenu);	
		//The Add Latex Command Menu
		addMenu = new JMenu("Add");
		menuBar.add(addMenu);
		addMenu.setEnabled(false);	
	}
	
	
	public void fillAddFromTemplateMenu(){
		//User choice for loading an Article Template
		articleChoice = new JMenuItem("New Article");
		articleChoice.addActionListener(new ArticleChoiceListener());
		addFromTemplateMenu.add(articleChoice);
		//User choice for loading a Letter Template
		letterChoice = new JMenuItem("New Letter");
		letterChoice.addActionListener(new LetterChoiceListener());
		addFromTemplateMenu.add(letterChoice);
		//User choice for loading a Book Template
		bookChoice = new JMenuItem("New Book");
		bookChoice.addActionListener(new BookChoiceListener());
		addFromTemplateMenu.add(bookChoice);
		//User choice for loading a Report Template
		reportChoice = new JMenuItem("New Report");
		reportChoice.addActionListener(new ReportChoiceListener());
		addFromTemplateMenu.add(reportChoice);	
	}
	
	
	public void fillFileMenu(){
		//User choice for saving the document to storage
		saveCommand = new JMenuItem("Save");
		saveCommand.addActionListener(new SaveListener());
		fileMenu.add(saveCommand);
		//User choice for loading the document from storage
		loadCommand = new JMenuItem("Load");
		loadCommand.addActionListener(new LoadListener());
		fileMenu.add(loadCommand);
	
		fillAddFromTemplateMenu();
	}
	
	
	public void createEditButton(){
		editButton = new JButton("Edit");
		editButton.setSelected(true);
		editButton.setFocusTraversalKeysEnabled(false);
		editButton.setBorderPainted(false);
		editButton.setPreferredSize(new Dimension(67, 22));
		editButton.setContentAreaFilled(false);
		editButton.setForeground(Color.DARK_GRAY);
		editButton.addActionListener(new EditListener());
		menuBar.add(editButton);
		editButton.setEnabled(false);	
	}
	
	
	public void fillAddMenu(){
		//User choice for adding a Chapter.
		chapterCommand = new JMenuItem("Chapter");
		chapterCommand.addActionListener(new ChapterCommandListener());
		addMenu.add(chapterCommand);
		//User choice for adding a Section
		sectionCommand = new JMenuItem("Section");
		sectionCommand.addActionListener(new SectionCommandListener());
		addMenu.add(sectionCommand);
		//User choice for adding a Subsection
		subsectionCommand = new JMenuItem("Subsection");
		subsectionCommand.addActionListener(new SubsectionCommandListener());
		addMenu.add(subsectionCommand);
		//User choice for adding a Subsubsection
		subsubsectionCommand = new JMenuItem("Subsubsection");
		subsubsectionCommand.addActionListener(new SubsubsectionCommandListener());
		addMenu.add(subsubsectionCommand);
		//User choice for adding an Unordered Enumeration List
		enumerationListUnordered = new JMenuItem("Enumeration List(Unordered)");
		enumerationListUnordered.addActionListener(new EnumerationListUnorderedListener());
		addMenu.add(enumerationListUnordered);
		//User choice for adding an Ordered Enumeration List
		enumerationListOrdered = new JMenuItem("Enumeration List(Ordered)");
		enumerationListOrdered.addActionListener(new EnumerationListOrderedListener());
		addMenu.add(enumerationListOrdered);
		//User choice for adding a Table
		tableCommand = new JMenuItem("Table");
		tableCommand.addActionListener(new TableCommandListener());
		addMenu.add(tableCommand);
		//User choice for adding a Figure
		figureCommand = new JMenuItem("Figure");
		figureCommand.addActionListener(new FigureCommandListener());
		addMenu.add(figureCommand);
	}
	
	
	public void fillVersionsMenu(){
		//The Versions Menu
		versionsMenu = new JMenu("Versions");
		menuBar.add(versionsMenu);
		versionsMenu.setEnabled(false);
		//User choice for change tracking mechanism type
		changeVersion = new JMenuItem("Change To Stable");
		changeVersion.addActionListener(new ChangeVersionsStrategyCommandListener());
		versionsMenu.add(changeVersion);
		//User choice for roll back to previous version of document
		rollbackToPreviousCommand = new JMenuItem("Rollback To Previous");
		rollbackToPreviousCommand.addActionListener(new RollbackToPreviousVersionCommandListener());
		versionsMenu.add(rollbackToPreviousCommand);
		//User choice to enable/disable the tracking mechanism
		alterTrackingMechanism = new JMenuItem("Enable Tracking Mechanism");
		alterTrackingMechanism.addActionListener(new AlterVersionsManagementListener());
		versionsMenu.add(alterTrackingMechanism);
		
		menuBar.add(trackingMechanismLabel);
	}
	
	
	public void fillDocumentFields(CreateCommand createCommand){
		JFrame frame = new JFrame("");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 2, 2));
		
		JTextField authorField = new JTextField(5);
		JTextField copyrightField = new JTextField(5);
		
		panel.add(new JLabel("Please, fill an author"));    
		panel.add(authorField);
		
		panel.add(new JLabel("Please, fill the copyright"));
        panel.add(copyrightField);
        
        int option = JOptionPane.showConfirmDialog(frame, panel, "Please "
        		             + "fill the fields of your Documents", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		
        
        if(option == JOptionPane.OK_OPTION){
        	authorInput = authorField.getText();
        	copyrightInput = copyrightField.getText();
        	createCommand.getCurrentDocument().setAuthor(authorInput);
        	createCommand.getCurrentDocument().setCopyright(copyrightInput);
        		
        	panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            panel.add(new JLabel("Author choosed: " + authorInput));
            panel.add(new JLabel("Copyright choosed: " + copyrightInput));
            JOptionPane.showMessageDialog(frame, panel);
        }
        
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	date = new Date();
    	createCommand.getCurrentDocument().setDate(dateFormat.format(date));
    	
        fieldsLabel.setText("Author: " + authorInput + "     " + "Date: " + dateFormat.format(date) + "     " + "Copyright: " + copyrightInput + "     " + "VersionID: " + createCommand.getCurrentDocument().getVersionID());
        fieldsLabel.setEnabled(true);
        
        textArea.setEditable(true);
        scrolledText.getHorizontalScrollBar().setEnabled(true);
		scrolledText.getVerticalScrollBar().setEnabled(true);
	} 
	
	/**
	 * Launch the application.
	 */

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LatexEditorView window = new LatexEditorView();
					window.frame.setVisible(true);
						
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}	
		});
	}

	
	/**
	 * Create the application.
	 */
	public LatexEditorView() {
		controller = new LatexEditorController(this);
		initialize();
		createMenusAndMenuBars();
		createEditButton();
		fillFileMenu();
		fillAddMenu();
		fillVersionsMenu();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		//Initialization of JFrame and JTextArea
		frame = new JFrame();
		frame.setTitle("my Latex Editor");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setName("");	
		textArea = new JTextArea(80,80);
		textArea.setDisabledTextColor(Color.GRAY);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		textArea.setEditable(false);
		
		fieldsLabel = new JLabel("Author:               Date:               Copyright:               VersionID:             ");
		fieldsLabel.setForeground(new Color(0, 102, 102));
		fieldsLabel.setBackground(Color.WHITE);
		fieldsLabel.setPreferredSize(new Dimension(33, 14));
		frame.getContentPane().add(fieldsLabel, BorderLayout.NORTH);
		frame.setBounds(100, 100, 627, 567);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fieldsLabel.setEnabled(false);
		
		scrolledText = new JScrollPane(textArea);
		scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrolledText);
		scrolledText.getHorizontalScrollBar().setEnabled(false);
		scrolledText.getVerticalScrollBar().setEnabled(false);
		
		trackingMechanismLabel = new JLabel("      Tracking Mechanism: Disabled");
		trackingMechanismLabel.setForeground(new Color(51, 102, 51));
		trackingMechanismLabel.setBackground(Color.WHITE);
		trackingMechanismLabel.setEnabled(false);	
	}
}

