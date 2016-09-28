package gui;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserWindow extends JFrame {
	JPanel container = new JPanel();
	JFileChooser fileChooser = new JFileChooser();
	
	public FileChooserWindow() {
		setLayout(new BorderLayout());
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("PA Pushdown Automata File", "pa");
	    fileChooser.setFileFilter(filter);
	    container.add(fileChooser);
	    add(container, BorderLayout.CENTER);
	}
}
