/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package automaton;

import java.util.ArrayList;

import automatonelements.AutomatonAlphabet;
import automatonelements.AutomatonInputTape;
import automatonelements.AutomatonStateSet;
import automatonelements.AutomatonTransition;
import automatonelements.AutomatonTransitionTable;
import common.AutomatonCommonText;

public abstract class Automaton {  
  protected AutomatonTransitionTable automaton;         // Automaton transition table
  protected String currentState;                        // Current automaton state
  protected AutomatonStateSet finalStates;              // Automaton final states set
  protected AutomatonAlphabet inputStringAlphabet;      // Input tape alphabet (Sigma). 
  protected String startingState;                       // Initial state
  protected AutomatonInputTape automatonInputTape;      // Automaton input tape

  /**
   * Verifies if the state belongs to the automaton
   * @param state
   * @return True if the state exists
   */
  public boolean stateExist(String state) {
    return getAutomaton().containsKey(state);
  }

  /**
   * Adds a new state
   * @param newState
   */
  public void addState(String newState){
    if (getAutomaton().containsKey(newState)) {
      throw new IllegalArgumentException(AutomatonCommonText.THE_STATE_TEXT + newState + AutomatonCommonText.ALREADY_EXISTS);
    } else {
      getAutomaton().put(newState, new ArrayList<AutomatonTransition>());
    }
  }

  /**
   * Adds a new final state
   * @param finalState
   */
  public void addFinalState(String finalState) {
    if (getFinalStates().contains(finalState)) {
      throw new IllegalArgumentException(AutomatonCommonText.THE_STATE_TEXT + finalState + AutomatonCommonText.ALREADY_FINAL_STATE);
    } else {
      getFinalStates().add(finalState);
    }
  }

  /**
   * Adds a new element to the Sigma alphabet
   * @param newElement
   */
  public void addElementToInputAlphabet(String newElement) {
    if (getInputStringAlphabet().elementBelongsToAlphabet(newElement)) {
      throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + newElement + AutomatonCommonText.ALREADY_BELONGS_TO_SIGMA);
    } else {
      getInputStringAlphabet().addElementToAlphabet(newElement);
    }
  }

  public AutomatonInputTape getInputString() {
    return automatonInputTape;
  }

  public void setInputString(String input) {
    setInputString(new AutomatonInputTape(input));
  }

  protected void setInputString(AutomatonInputTape automatonInputTape) {
    this.automatonInputTape = automatonInputTape;
  }

  public AutomatonTransitionTable getAutomaton() {
    return automaton;
  }

  protected void setAutomaton(AutomatonTransitionTable automaton) {
    this.automaton = automaton;
  }

  public String getCurrentState() {
    return currentState;
  }

  protected void setCurrentState(String currentState) {
    this.currentState = currentState;
  }

  public AutomatonStateSet getFinalStates() {
    return finalStates;
  }

  protected void setFinalStates(AutomatonStateSet finalStates) {
    this.finalStates = finalStates;
  }

  public AutomatonAlphabet getInputStringAlphabet() {
    return inputStringAlphabet;
  }

  protected void setInputStringAlphabet(AutomatonAlphabet inputStringAlphabet) {
    this.inputStringAlphabet = inputStringAlphabet;
  }

  public String getStartingState() {
    return startingState;
  }

  public void setStartingState(String startingState) {
    this.startingState = startingState;
    setCurrentState(startingState);
  }  
}
