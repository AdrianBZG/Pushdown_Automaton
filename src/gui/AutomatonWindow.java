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
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import automaton.PushDownAutomaton;
import exceptions.AutomatonExceptionHandler;
import handlers.AutomatonFileHandler;

public class AutomatonWindow extends JFrame {
  public final static String INFO_ICON_PATH = "res/info-image-16.png";
  public final static ImageIcon infoIcon = new ImageIcon(INFO_ICON_PATH);
  public final static String AUTOMATA_ICON_PATH = "res/automata-icon.png";
  public final static ImageIcon icon = new ImageIcon(AUTOMATA_ICON_PATH);

  PushDownAutomaton automaton;
  JTextField textField;
  JButton button;
  JButton checkButton = new JButton("Check");
  JButton resetButton = new JButton("Reset");
  JButton initializeButton = new JButton("Initialize");
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
  String[] possibleModes = { "Normal", "Step by step" };
  JComboBox<String> modeComboBox = new JComboBox<String>(possibleModes);
  JLabel inputStringLabel = new JLabel("Input String:");
  JLabel stackText = new CustomLabel("AUTOMATON STACK", true);
  JLabel stateText = new CustomLabel("State: ", true);
  JLabel stateValue = new JLabel("?");
  JLabel previousStateText = new CustomLabel("| Previous State: ", true);
  JLabel previousStateValue = new JLabel("?");
  JLabel statusText = new CustomLabel("| Status: ", true);
  JLabel inputTapeText = new CustomLabel("Input tape:", true);
  JLabel inputTapeValue = new JLabel("?");
  JPanel topPanel = new JPanel(new GridLayout(1,3));
  JPanel topInputPanel = new JPanel(new GridLayout(2,1));
  JPanel topInputString = new JPanel(new FlowLayout());
  JPanel topModePanel = new JPanel(new FlowLayout());
  JPanel topChooseFilePanel = new JPanel(new FlowLayout());
  // Bottom part (State + Previous state + Next state + Input tape)
  JPanel bottomPanel = new JPanel(new GridLayout(2,1));
  JPanel bottomStatePanel = new JPanel(new FlowLayout());
  JPanel bottomInputTapePanel = new JPanel(new FlowLayout());
  //
  // Center part (Transitions (center) + Stack (right)
  JPanel centerPanel = new JPanel(new BorderLayout());
  JPanel transitionsPanel = new JPanel(new GridLayout(0,1));
  JPanel stackPanel = new JPanel(new BorderLayout());
  JPanel stackPanelElements = new JPanel(new GridLayout(0,1));
  //
  AutomatonAcceptedPanel automatonAcceptedPanel;
  Boolean accepted;

  public AutomatonWindow(PushDownAutomaton automaton) {
    setAutomaton(automaton);
    setLayout(new BorderLayout());
    setPanel(new JPanel(new GridLayout(2, 1)));
    setTextPanel(new JPanel(new GridLayout(2, 1)));
    setTextField(new JTextField());
    getTextField().setPreferredSize( new Dimension( 60, 24 ) );
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
        final String INFORMATION_TEXT = "Subject: Complejidad Computacional\nAssignment: Pushdown Automaton (1)\nDescription: App that simulates a Pushdown Automaton\nVersion: 0.0.1\nAuthor: Adrian Rodriguez Bazaga\nEmail: arodriba@ull.es\n\nSTATUS COLORS:\nGreen: Accepted\nRed: Rejected\nOrange: Unknown";
        JOptionPane.showMessageDialog(null, INFORMATION_TEXT);
      }
    });
    //
    topChooseFilePanel.add(infoLabel);
    topChooseFilePanel.add(chooseFileButton);
    topPanel.add(topChooseFilePanel);
    topModePanel.add(modeLabel);
    topModePanel.add(modeComboBox);
    topPanel.add(topModePanel);
    topInputString.add(inputStringLabel);
    topInputString.add(getTextField());
    topInputPanel.add(topInputString);
    topInputPanel.add(getButton());
    topPanel.add(topInputPanel);
    //

    // Bottom part
    bottomInputTapePanel.add(inputTapeText);
    bottomInputTapePanel.add(inputTapeValue);
    bottomStatePanel.add(resetButton);
    bottomStatePanel.add(initializeButton);
    bottomStatePanel.add(getButton());
    bottomStatePanel.add(stateText);
    bottomStatePanel.add(stateValue);
    bottomStatePanel.add(previousStateText);
    bottomStatePanel.add(previousStateValue);
    bottomStatePanel.add(statusText);
    bottomStatePanel.add(getAcceptedPanel());
    bottomPanel.add(bottomInputTapePanel);
    bottomPanel.add(bottomStatePanel);
    //

    // Center part
    stackText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    stackPanelElements.add(stackText);
    stackPanel.add(stackPanelElements, BorderLayout.NORTH);
    stackPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    transitionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    centerPanel.add(transitionsPanel, BorderLayout.CENTER);
    centerPanel.add(stackPanel, BorderLayout.EAST);
    //

    chooseFileButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        //FileChooserWindow fileChooserWindow  = new FileChooserWindow();
        //fileChooserWindow.setVisible(true);
        int result = jFileChooser.showOpenDialog(new JFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = jFileChooser.getSelectedFile();
          System.out.println("Selected file: " + selectedFile.getAbsolutePath());
          try {
            setAutomaton(AutomatonFileHandler.parseFromFile(selectedFile.getAbsolutePath()));
          } catch (IOException | AutomatonExceptionHandler exception) {
            // TODO Auto-generated catch block
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
        if(automataMode) {
          String text = getTextField().getText();
          getAutomaton().setInputString(text);
          accepted = getAutomaton().evaluateEntry();
          getAcceptedPanel().setAccepted(accepted);
          repaint();
        } else {
          JOptionPane.showMessageDialog(null, "ERROR: This option is only available in step-by-step mode.");
        }
      }
    });

    initializeButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(automataMode) {
          String text = getTextField().getText();
          if(text.length() > 0) { 
            getAutomaton().setInputString(text);
            updateInputTapeValue(text);
            stackPanelElements.add(new CustomLabel(getAutomaton().getStartingStackSymbol(), Color.RED, false));
            stateValue.setText(getAutomaton().getStartingState());
          } else {
            JOptionPane.showMessageDialog(null, "ERROR: The Input String can't be empty!");
          }
        } else {
          JOptionPane.showMessageDialog(null, "ERROR: This option is only available in step-by-step mode.");
        }
      }
    });

    getButton().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String text = getTextField().getText();

        getAutomaton().setInputString(text);
        if(automataMode) {
          if(getAutomaton().performOneStep()) {
            System.out.println("Test: " + getAutomaton().getStepByStepAutomaton().getActualState());
          }
          System.out.println("Test2: " + getAutomaton().getStepByStepAutomaton().getActualState());
        } else {
          accepted = getAutomaton().evaluateEntry();
          getAcceptedPanel().setAccepted(accepted);
          repaint();
        }
      }
    });

    this.add(topPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(bottomPanel, BorderLayout.SOUTH);
    setIconImage(icon.getImage());
  }

  private void updateInputTapeValue(String inputTapeValueText) {
    String formattedText = new String("");
    char[] charArray = inputTapeValueText.toCharArray();
    formattedText += "<html><font color='red'>" + charArray[0] + "</font>";
    for(int i = 1; i < charArray.length; i++) {
      formattedText += charArray[i];
    }
    formattedText += "</html>";
    inputTapeValue.setText(formattedText);
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
