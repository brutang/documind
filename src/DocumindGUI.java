import java.awt.TextArea;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DocumindGUI {

	
	private JFrame jFrame;
	private JTextArea documentArea;
	private JScrollPane scrollPane;
	
	private static final int FRAME_HEIGHT = 800;
	private static final int FRAME_WIDTH = 800;

	/**
	 * @param args
	 */
	
	public DocumindGUI() {
		jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		documentArea = new JTextArea();
		documentArea.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		documentArea.setLineWrap(true);
		scrollPane = new JScrollPane(documentArea);
		jFrame.add(scrollPane);
	}
	
	public void openFile() throws IOException{
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(jFrame, "Select File");
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
		//jFrame.pack();
		jFrame.setVisible(true);
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
