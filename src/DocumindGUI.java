import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DocumindGUI {

	class ButtonListener implements ActionListener {
		  ButtonListener() {
		  }

		  public void actionPerformed(ActionEvent e) {
		    if (e.getActionCommand().equals("Get Highlighted Text")) {
		      System.out.println(documentArea.getSelectedText());
		    }
		    else if (e.getActionCommand().equals("Open File")) {
		    	openFile();
		    }
		    else if (e.getActionCommand().equals("Summarize Highlighted Text")) {
		    	summaryArea.setText(Summarizer.summarizeText(curFile));
		    }
		  }
		}
	
	private JFrame frame;
	private Container container;
	private JTextArea documentArea;
	private JScrollPane scrollPane;
	private JButton getHighlightedButton;
	private JTextArea summaryArea;
	private JScrollPane scrollPaneSummary;
	private JButton openFileButton;
	private File curFile;
	private JButton summarizeButton;
	
	private static final int FRAME_HEIGHT = 500;
	private static final int FRAME_WIDTH = 500;

	/**
	 * @param summarizer 
	 * @param args
	 */
	
	public DocumindGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		container = frame.getContentPane();
		
		// Create the layout
		GridBagLayout gbl = new GridBagLayout();
		
		// Set layout on container
		container.setLayout(gbl);
		
		// Place a component at cell location (0,0)
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 3;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		
		// Associate the gridbag constraints with the component
		documentArea = new JTextArea();
		documentArea.setLineWrap(true);
		scrollPane = new JScrollPane(documentArea);
		gbl.setConstraints(scrollPane, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.PAGE_START;
		getHighlightedButton = new JButton("Get Highlighted Text");
		getHighlightedButton.addActionListener(new ButtonListener());
		gbl.setConstraints(getHighlightedButton, gbc);
		
		gbc.gridy = GridBagConstraints.RELATIVE;
		openFileButton = new JButton("Open File");
		openFileButton.addActionListener(new ButtonListener());
		gbl.setConstraints(openFileButton, gbc);
		
		summarizeButton = new JButton("Summarize Highlighted Text");
		summarizeButton.addActionListener(new ButtonListener());
		gbl.setConstraints(summarizeButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		//gbc.gridheight = 200;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		summaryArea = new JTextArea();
		summaryArea.setLineWrap(true);
		summaryArea.setEditable(false);
		scrollPaneSummary = new JScrollPane(summaryArea);
		gbl.setConstraints(scrollPaneSummary, gbc);
		
		
		//add components to container
		container.add(scrollPane);
		container.add(getHighlightedButton);
		container.add(openFileButton);
		container.add(summarizeButton);
		container.add(scrollPaneSummary);
	}
	
	public void openFile(){
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(frame, "Select File");
		if(ret == JFileChooser.APPROVE_OPTION){
			try {
				documentArea.setText("");
				curFile = fileopen.getSelectedFile();
				FileReader reader = new FileReader(curFile.getPath());
				BufferedReader br = new BufferedReader(reader);
				documentArea.read(br, null);
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Invalid file");
		}
	}
	
	public void viewGUI(){
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		DocumindGUI docMind = new DocumindGUI();
		docMind.viewGUI();
		while(true){}
	}

}
