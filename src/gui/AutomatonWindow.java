/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automaton.PushDownAutomaton;

public class AutomatonWindow extends JFrame{
  PushDownAutomaton automaton;
	JPanel panel;
	JPanel textPanel;
	AutomatonAcceptedPanel automatonAcceptedPanel;
	JTextField textField;
	JButton button;
	Boolean accepted;
	
	public AutomatonWindow(PushDownAutomaton automaton) {
		setAutomaton(automaton);
		setPanel(new JPanel(new GridLayout(2, 1)));
		setTextPanel(new JPanel(new GridLayout(2, 1)));
		setTextField(new JTextField());
		setAcceptedPanel(new AutomatonAcceptedPanel());
		setButton(new JButton("Comprobar"));
		
		getPanel().add(getTextField());
		getPanel().add(getButton());
		getTextPanel().add(getPanel());
		
		getTextPanel().add(getAcceptedPanel());
		
		getButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = getTextField().getText();
				
				getAutomaton().setInputString(text);
				accepted = getAutomaton().evaluateEntry();
				getAcceptedPanel().setAccepted(accepted);
				repaint();
			}
		});
		this.add(getTextPanel());
	}

	public PushDownAutomaton getAutomaton() {
		return automaton;
	}

	public void setAutomaton(PushDownAutomaton automaton) {
		this.automaton = automaton;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public JPanel getTextPanel() {
		return textPanel;
	}

	public void setTextPanel(JPanel textPanel) {
		this.textPanel = textPanel;
	}

	public AutomatonAcceptedPanel getAcceptedPanel() {
		return automatonAcceptedPanel;
	}

	public void setAcceptedPanel(AutomatonAcceptedPanel automatonAcceptedPanel) {
		this.automatonAcceptedPanel = automatonAcceptedPanel;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	
	
}
