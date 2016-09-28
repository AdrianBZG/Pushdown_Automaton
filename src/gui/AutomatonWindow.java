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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import automaton.PushDownAutomaton;

public class AutomatonWindow extends JFrame {
	PushDownAutomaton automaton;
	JTextField textField;
	JButton button;
	JButton chooseFileButton = new JButton("Choose file...");
	JPanel panel;
	JPanel textPanel;
	JPanel filePanel;
	JPanel modePanel;
	JPanel inputPanel;
	JLabel loadFileLabel = new JLabel("Load File...");
	JLabel modeLabel = new JLabel("Mode:");
	JLabel inputStringLabel = new JLabel("Input String:");
	JLabel stackText = new JLabel("Stack:");
	JLabel stateText = new JLabel("State:");
	JLabel previousStateText = new JLabel("Previous State:");
	JLabel nextStateText = new JLabel("Next State:");
	JLabel inputTapeText = new JLabel("Input tape:");
	JPanel topPanel = new JPanel(new GridLayout(1,3));
	JPanel topInputPanel = new JPanel(new GridLayout(2,1));
	JPanel topInputString = new JPanel(new FlowLayout());
	AutomatonAcceptedPanel automatonAcceptedPanel;
	Boolean accepted;

	public AutomatonWindow(PushDownAutomaton automaton) {
		setAutomaton(automaton);
		setLayout(new BorderLayout());
		setPanel(new JPanel(new GridLayout(2, 1)));
		setTextPanel(new JPanel(new GridLayout(2, 1)));
		setTextField(new JTextField());
		getTextField().setPreferredSize( new Dimension( 100, 24 ) );
		setAcceptedPanel(new AutomatonAcceptedPanel());
		setButton(new JButton("Comprobar"));

		//getPanel().add(getTextField());
		//getPanel().add(getButton());
		//getTextPanel().add(getPanel());

		getTextPanel().add(getAcceptedPanel());
		
		//
		topPanel.add(chooseFileButton);
		topPanel.add(modeLabel);
		topInputString.add(inputStringLabel);
		topInputString.add(getTextField());
		topInputPanel.add(topInputString);
		topInputPanel.add(getButton());
		topPanel.add(topInputPanel);
		//

		chooseFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooserWindow fileChooserWindow  = new FileChooserWindow();
				fileChooserWindow.setVisible(true);
			}
		});
		
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
		this.add(topPanel, BorderLayout.NORTH);
		this.add(getAcceptedPanel(), BorderLayout.CENTER);
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
