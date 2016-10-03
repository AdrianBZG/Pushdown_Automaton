/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package logic;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import automaton.PushDownAutomaton;
import gui.AutomatonWindow;

public class Main {

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    SwingUtilities.invokeAndWait(new Runnable() {
      public void run() {
        try {
          PushDownAutomaton automaton = null;
          AutomatonWindow frame = new AutomatonWindow(automaton);
          frame.toggleWindow(true);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
