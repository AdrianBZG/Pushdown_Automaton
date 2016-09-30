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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import automaton.PushDownAutomaton;
import exceptions.AutomatonExceptionHandler;
import gui.AutomatonWindow;
import handlers.AutomatonFileHandler;

public class Main {

  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    SwingUtilities.invokeAndWait(new Runnable() {
      public void run() {
        PushDownAutomaton automaton = null;
        AutomatonWindow frame; 
        /*if (args.length < 1)
          System.err.println("Se debe pasar por parametro el archivo a leer");
		*/
        //try {
          //automaton = AutomatonFileHandler.parseFromFile("automaton_examples/" + args[0]);
          try {
			frame  = new AutomatonWindow(automaton);
	        frame.setTitle("Pushdown Automaton");
	        frame.setSize(950, 650);
	        frame.setLocationRelativeTo(null); // Center the frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        frame.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //} /*catch (IOException | AutomatonExceptionHandler e) {
          //e.printStackTrace();
          //return;
        //}
      }
    });
  }
}
