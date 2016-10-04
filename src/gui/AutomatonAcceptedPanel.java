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
import java.awt.Graphics;

import javax.swing.JPanel;

public class AutomatonAcceptedPanel extends JPanel {
  public Boolean accepted;

  public AutomatonAcceptedPanel() {
    super();
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g.create());
    if (getAccepted() != null) {
      if (getAccepted()) {
        g.setColor(Color.GREEN);
      } else {
        g.setColor(Color.RED);
      }
      g.fillRect(0, 0, getWidth(), getHeight());
    } else {
      g.setColor(Color.ORANGE);
      g.fillRect(0, 0, getWidth(), getHeight());
    }
  }

  public Boolean getAccepted() {
    return accepted;
  }

  public void setAccepted(Boolean accepted) {
    this.accepted = accepted;
  }	
}
