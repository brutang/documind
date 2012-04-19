import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DocumindGUI {

	class ButtonListener implements ActionListener {
		  ButtonListener() {
		  }

		  public void actionPerformed(ActionEvent e) {
		    if (e.getActionCommand().equals("Get Highlighted Text")) {
		      System.out.println(documentArea.getSelectedText());
		      highlightedTextArea.setText(documentArea.getSelectedText());
		    }
		  }
		}
	
	private JFrame frame;
	private Container container;
	private JTextArea documentArea;
	private JScrollPane scrollPane;
	private JButton getHighlightedButton;
	private JTextArea highlightedTextArea;
	private JScrollPane scrollPaneHighlight;
	
	private static final int FRAME_HEIGHT = 500;
	private static final int FRAME_WIDTH = 500;

	/**
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
		
		// Place a component at cell location (1,1)
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		
		// Associate the gridbag constraints with the component
		documentArea = new JTextArea();
		documentArea.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		documentArea.setLineWrap(true);
		scrollPane = new JScrollPane(documentArea);
		gbl.setConstraints(scrollPane, gbc);
		
		gbc.gridx = 600;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		getHighlightedButton = new JButton("Get Highlighted Text");
		getHighlightedButton.addActionListener(new ButtonListener());
		gbl.setConstraints(getHighlightedButton, gbc);
		
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		highlightedTextArea = new JTextArea();
		highlightedTextArea.setSize(100, 100);
		highlightedTextArea.setLineWrap(true);
		highlightedTextArea.setEditable(false);
		scrollPaneHighlight = new JScrollPane(highlightedTextArea);
		gbl.setConstraints(scrollPaneHighlight, gbc);
		
		
		//add components to container
		container.add(scrollPane);
		container.add(getHighlightedButton);
		container.add(scrollPaneHighlight);
		
		
		/*documentArea = new JTextArea();
		documentArea.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		documentArea.setLineWrap(true);
		scrollPane = new JScrollPane(documentArea);
		frame.add(scrollPane);
		frame.add(getHighlightedButton);*/
	}
	
	public void openFile() throws IOException{
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(frame, "Select File");
		if(ret == JFileChooser.APPROVE_OPTION){
			File file = fileopen.getSelectedFile();
			FileReader reader = new FileReader(file.getPath());
			BufferedReader br = new BufferedReader(reader);
			documentArea.read(br, null);
			br.close();
		}
		else{
			System.out.println("Invalid file");
		}
	}
	
	public void viewGUI(){
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		DocumindGUI docMind = new DocumindGUI();
		try {
			docMind.openFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		docMind.viewGUI();
		while(true){}
	}

}
