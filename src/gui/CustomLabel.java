/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class CustomLabel extends JLabel {
  public CustomLabel(String text, boolean bold) {
    super(text, JLabel.CENTER);
    if(bold) setText("<html><b>" + getText() + "</b></html>");
  }
  
  public CustomLabel(String text, Color textColor, boolean bold) {
    super(text, JLabel.CENTER);
    setForeground(textColor);
    if(bold) setText("<html><b>" + getText() + "</b></html>");
  }
  
  public CustomLabel(String text, Color textColor, Color borderColor, boolean bold) {
    super(text, JLabel.CENTER);
    setForeground(textColor);
    setBorder(BorderFactory.createLineBorder(borderColor));
    if(bold) setText("<html><b>" + getText() + "</b></html>");
  }
}
