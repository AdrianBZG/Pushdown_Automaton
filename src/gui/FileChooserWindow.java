/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserWindow extends JFrame {
  private JPanel container = new JPanel();
  private JFileChooser fileChooser = new JFileChooser();

  public FileChooserWindow() {
    addElementsToWindow();
    setWindowSettings();
  }
  
  private void addElementsToWindow() {
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PA Pushdown Automata File", "pa");
    getFileChooser().setFileFilter(filter);
    getFileChooser().setCurrentDirectory(new File(System.getProperty("user.home")));
    getContainer().add(getFileChooser());
    add(getContainer(), BorderLayout.CENTER);
  }
  
  private void setWindowSettings() {
    setLayout(new BorderLayout());
    pack();
  }

  /**
   * @return the container
   */
  public JPanel getContainer() {
    return container;
  }

  /**
   * @param container the container to set
   */
  public void setContainer(JPanel container) {
    this.container = container;
  }

  /**
   * @return the fileChooser
   */
  public JFileChooser getFileChooser() {
    return fileChooser;
  }

  /**
   * @param fileChooser the fileChooser to set
   */
  public void setFileChooser(JFileChooser fileChooser) {
    this.fileChooser = fileChooser;
  }
}
