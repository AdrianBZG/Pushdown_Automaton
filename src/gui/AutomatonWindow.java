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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import automaton.PushDownAutomaton;
import common.AutomatonCommonData;
import common.AutomatonCommonText;
import exceptions.AutomatonExceptionHandler;
import handlers.AutomatonFileHandler;

public class AutomatonWindow extends JFrame {
  public final static String INFO_ICON_PATH = "res/info-image-16.png";
  public final static ImageIcon infoIcon = new ImageIcon(INFO_ICON_PATH);
  public final static String AUTOMATA_ICON_PATH = "res/automata-icon.png";
  public final static ImageIcon icon = new ImageIcon(AUTOMATA_ICON_PATH);
  public static JTextArea TRANSITIONS_TEXT_AREA = new JTextArea("");

  public static PushDownAutomaton automaton;
  JTextField textField;
  static JTextPane scrollPane;
  JScrollPane jsp;
  JButton button;
  JButton checkButton = new JButton("Check");
  JButton resetButton = new JButton("Reset");
  JButton chooseFileButton = new JButton("Load automata from file...");
  JPanel panel;
  JPanel textPanel;
  JPanel filePanel;
  JPanel modePanel;
  JPanel inputPanel;
  boolean automataMode = false;                           // False: Normal, True: Step by step
  private JLabel infoLabel;                               // The label for the information icon
  JLabel loadFileLabel = new JLabel("Load File...");
  JFileChooser jFileChooser = new JFileChooser();
  JLabel modeLabel = new JLabel("Mode:");
  String[] possibleModes = { "Normal" };
  JComboBox<String> modeComboBox = new JComboBox<String>(possibleModes);
  JLabel inputStringLabel = new JLabel("Input String:");
  JLabel stackText = new CustomLabel("AUTOMATON STACK", true);
  JLabel statusText = new CustomLabel("Status: ", true);
  JPanel firstTopPanel = new JPanel(new GridLayout(1,4));
  JPanel secondTopPanel = new JPanel(new GridLayout(1,0));
  JPanel topPanel = new JPanel(new BorderLayout());
  JPanel topInputPanel = new JPanel(new GridLayout(2,1));
  JPanel topInputString = new JPanel(new FlowLayout());
  JPanel topModePanel = new JPanel(new FlowLayout());
  JPanel topChooseFilePanel = new JPanel(new FlowLayout());
  JPanel automatonInfoPanel = new JPanel(new FlowLayout());
  public JLabel epsilonTextValue = new JLabel("?");
  public JLabel tauTextValue = new JLabel("?");
  public JLabel automatonInitialStateTextValue = new JLabel("?");
  public JLabel automatonFinalStateTextValue = new JLabel("?");
  public JLabel automatonTypeTextValue = new JLabel("?");
  
  // Bottom part (State + Previous state + Next state + Input tape)
  JPanel bottomPanel = new JPanel(new GridLayout(2,1));
  JPanel bottomStatePanel = new JPanel(new FlowLayout());
  JPanel bottomStatusPanel = new JPanel(new FlowLayout());
  JPanel bottomInputTapePanel = new JPanel(new FlowLayout());
  //
  // Center part (Transitions (center) + Stack (right)
  JPanel centerPanel = new JPanel(new BorderLayout());
  JPanel transitionsPanel = new JPanel(new GridLayout(0,1));
  //
  AutomatonAcceptedPanel automatonAcceptedPanel;
  Boolean accepted;

  public AutomatonWindow(PushDownAutomaton automaton) throws IOException {
    setAutomaton(automaton);
    setLayout(new BorderLayout());
    setPanel(new JPanel(new GridLayout(2, 1)));
    setTextPanel(new JPanel(new GridLayout(2, 1)));
    setTextField(new JTextField());
    getTextField().setPreferredSize( new Dimension( 120, 24 ) );
    setAcceptedPanel(new AutomatonAcceptedPanel());
    getAcceptedPanel().setPreferredSize( new Dimension( 60, 24 ) );
    setButton(checkButton);
    jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PA Pushdown Automata File", "pa");
    jFileChooser.setFileFilter(filter);

    // Top part
    // The info icon
    infoLabel = new JLabel(infoIcon);
    infoLabel.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, AutomatonCommonText.INFORMATION_TEXT);
      }
    });
    //
    topChooseFilePanel.add(infoLabel);
    topChooseFilePanel.add(chooseFileButton);
    firstTopPanel.add(topChooseFilePanel);
    topModePanel.add(modeLabel);
    topModePanel.add(modeComboBox);
    firstTopPanel.add(topModePanel);
    topInputString.add(inputStringLabel);
    topInputString.add(getTextField());
    topInputPanel.add(topInputString);
    topInputPanel.add(getButton());
    firstTopPanel.add(topInputPanel);
    automatonInfoPanel.add(AutomatonCommonText.EPSILON_TEXT);
    automatonInfoPanel.add(epsilonTextValue);
    automatonInfoPanel.add(AutomatonCommonText.TAU_TEXT);
    automatonInfoPanel.add(tauTextValue);
    automatonInfoPanel.add(AutomatonCommonText.AUTOMATON_TYPE_TEXT);
    automatonInfoPanel.add(automatonTypeTextValue);
    automatonInfoPanel.add(AutomatonCommonText.AUTOMATON_INITIAL_STATE_TEXT);
    automatonInfoPanel.add(automatonInitialStateTextValue);
    automatonInfoPanel.add(AutomatonCommonText.AUTOMATON_FINAL_STATE_TEXT);
    automatonInfoPanel.add(automatonFinalStateTextValue);
    automatonInfoPanel.setVisible(false);
    secondTopPanel.add(automatonInfoPanel);
    topPanel.add(firstTopPanel, BorderLayout.CENTER);
    topPanel.add(secondTopPanel, BorderLayout.SOUTH);
    //

    // Bottom part
    bottomStatePanel.add(resetButton);
    bottomStatePanel.add(getButton());
    bottomStatusPanel.add(statusText);
    bottomStatusPanel.add(getAcceptedPanel());
    bottomPanel.add(bottomStatePanel);
    bottomPanel.add(bottomStatusPanel);
    //

    // Center part
    centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    scrollPane = new JTextPane();
    scrollPane.setEditable(false);
    scrollPane.setContentType("text/html");
    jsp = new JScrollPane(scrollPane);
    centerPanel.add(new JLabel(AutomatonCommonText.TRANSITION_PANEL_HEADER_TEXT, JLabel.CENTER), BorderLayout.NORTH);
    centerPanel.add(jsp, BorderLayout.CENTER);
    //

    chooseFileButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int result = jFileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = jFileChooser.getSelectedFile();
          System.out.println("Selected file: " + selectedFile.getAbsolutePath());
          try {
            setAutomaton(AutomatonFileHandler.parseFromFile(selectedFile.getAbsolutePath()));
            updateAutomatonInfoPanel();
          } catch (IOException | AutomatonExceptionHandler exception) {
            exception.printStackTrace();
            return;
          }
        }

      }
    });

    modeComboBox.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String chosenOption = (String)cb.getSelectedItem();
        if(chosenOption.equals("Normal")) {
          checkButton.setText("Check");
          automataMode = false;
        } else {
          checkButton.setText("Next step");
          automataMode = true;
        }
      }
    });

    resetButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        clearTransitionsPanel();
        getAcceptedPanel().setAccepted(null);
        repaint();
      }
    });

    getButton().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String text = getTextField().getText();

        getAutomaton().setInputString(text);
        try {
          clearTransitionsPanel();
          AutomatonCommonData.resetTransitionNumber();
          accepted = getAutomaton().evaluateEntry();
          getAcceptedPanel().setAccepted(accepted);
          if(accepted) {
            appendTextToTransitionsPanel("<br><font color=\"green\">ACCEPTED</font>");
          } else {
            appendTextToTransitionsPanel("<br><font color=\"red\">REJECTED</font>");
          }
          repaint();
        } catch (IOException e1) {
          e1.printStackTrace();
        }

      }
    });

    this.add(topPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(bottomPanel, BorderLayout.SOUTH);
    setWindowSettings();
    setIconImage(icon.getImage());
  }

  private void setWindowSettings() {
    setTitle("Pushdown Automaton");
    setSize(950, 650);
    setLocationRelativeTo(null); // Center the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(false);
  }

  public void toggleWindow(boolean show) {
    setVisible(show);
  }
  
  private void updateAutomatonInfoPanel() {
    epsilonTextValue.setText(getAutomaton().getInputStringAlphabet().toString());
    //tauTextValue.setText(getAutomaton().getStackAlphabet().toString());
    automatonInitialStateTextValue.setText(getAutomaton().getStartingState());
    automatonFinalStateTextValue.setText(getAutomaton().getFinalStates().toString());
    if(automatonFinalStateTextValue.getText() == "EMPTY") {
      automatonTypeTextValue.setText("Acceptance by empty stack");
    } else {
      automatonTypeTextValue.setText("Acceptance by final state");
    }
    automatonInfoPanel.setVisible(true);
  }

  public static PushDownAutomaton getAutomaton() {
    return automaton;
  }

  public static void setAutomaton(PushDownAutomaton newAutomaton) {
    automaton = newAutomaton;
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

  public static void appendTextToTransitionsPanel(String s) throws IOException {
    try {
      if(AutomatonCommonData.getTransitionNumber() > 1) {
        HTMLDocument doc = (HTMLDocument)scrollPane.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit)scrollPane.getEditorKit();
        editorKit.insertHTML(doc, doc.getLength(), s, 0, 0, null);
      } else {	// Prevent the empty new line
        scrollPane.setText(s);
      }
    } catch(BadLocationException exc) {
      exc.printStackTrace();
    }
  }

  private void clearTransitionsPanel() {
    scrollPane.setText("");
  }
}
