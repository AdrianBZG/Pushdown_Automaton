package common;

import javax.swing.JLabel;

public final class AutomatonCommonText {
  public final static char COMMENT = '#';                                         // Comment starting symbol
  public final static String EMPTY_FINAL_STATES = "EMPTY";                        // No final states symbol
  public final static String EPSYLON = "$";                                       // Symbol that represents the empty string
  public final static String NOT_FOUND_ERROR = " not found.";
  public final static String ONE_STARTING_SYMBOL_ERROR = "Solo puede haber un unico simbolo de arranque.";
  public final static String ONE_STARTING_SYMBOL_STACK_ERROR = "Solo puede haber un unico simbolo de inicio de pila.";
  public final static String FINAL_STATES_ERROR = "Error en los estados finales";
  public final static String STATE_NOT_FOUND_ERROR_1 = "El estado ";
  public final static String STATE_NOT_FOUND_ERROR_2 = " no existe.";
  public final static String ERROR_ADD_STATE_ON_EMPTY = "No se puede indicar EMPTY si se a�aden estados finales.";
  public final static String TRANSITIONS_ERROR = "Error en las transiciones";
  public final static String TRANSITION_PANEL_HEADER_TEXT = "<html><br><b><u>TRANSITION LOG</u></b><br><br></html>";
  public final static String THE_STATE_TEXT = "The state ";
  public final static String THE_ELEMENT_TEXT = "The element ";
  public final static String ALREADY_EXISTS = " already exists.";
  public final static String ALREADY_FINAL_STATE = " is already a final state.";
  public final static String ALREADY_BELONGS_TO_SIGMA = " already belongs to the Sigma alphabet.";
  public final static String ALREADY_BELONGS_TO_TAU = " already belongs to the Tau alphabet.";
  public final static String NOT_BELONGS_TO_STATE_SET = " no forma parte del conjunto de estados.";
  public final static String NOT_BELONGS_TO_SIGMA_ALPHABET = " not belongs to Sigma alphabet.";
  public final static String NOT_BELONGS_TO_TAU_ALPHABET = " not belongs to Tau alphabet.";
  public final static String INFORMATION_TEXT = "Subject: Complejidad Computacional\nAssignment: Pushdown Automaton (1)\nDescription: App that simulates a Pushdown Automaton\nVersion: 0.0.1\nAuthor: Adrian Rodriguez Bazaga\nEmail: arodriba@ull.es\n\nSTATUS COLORS:\nGreen: Accepted\nRed: Rejected\nOrange: Unknown";
  public final static JLabel EPSILON_TEXT = new JLabel("<html><b>\u03A3:</b></html>");
  public final static JLabel TAU_TEXT = new JLabel("<html><b>\u03A4:</b></html>");
  public final static JLabel AUTOMATON_TYPE_TEXT = new JLabel("<html><b>Automaton Type:</b></html>");
  public final static JLabel AUTOMATON_INITIAL_STATE_TEXT = new JLabel("<html><b>q0:</b></html>");
  public final static JLabel AUTOMATON_FINAL_STATE_TEXT = new JLabel("<html><b>F:</b></html>");
  public final static String ACCEPTED_TEXT = "<br><font color=\"green\">ACCEPTED</font>";
  public final static String REJECTED_TEXT = "<br><font color=\"red\">REJECTED</font>";
  public final static String WINDOW_TITLE = "Pushdown Automaton";
  public final static int WINDOW_SIZE_X = 950;
  public final static int WINDOW_SIZE_Y = 650;
  public final static String ACCEPTANCE_BY_EMPTY_STACK_TEXT = "Acceptance by empty stack";
  public final static String ACCEPTANCE_BY_FINAL_STATE_TEXT = "Acceptance by final state";
}
