import java.awt.TextArea;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DocumindGUI {

	
	private JFrame jFrame;
	private JTextArea documentArea;

	/**
	 * @param args
	 */
	
	public DocumindGUI() {
		jFrame = new JFrame();
		documentArea = new JTextArea();
		jFrame.add(documentArea);
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
		jFrame.pack();
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
