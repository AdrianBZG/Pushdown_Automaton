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

public abstract class Automaton {

  public final static String EPSYLON = "$";           // Simbolo que representa la cadena vacia.
  
  protected AutomatonTransitionTable automaton;   // Asociacion de estados a transiciones.
  protected String actualState;                   // Estado actual del automata.
  protected AutomatonStateSet finalStates;              // Conjunto de estados vacios.
  protected AutomatonAlphabet inputStringAlphabet;              // Alfabeto de la cadena de entrada (sigma). 
  protected String startingState;                 // Estado inicial.
  protected AutomatonInputTape automatonInputTape;                // Cadena de entrada.
  
  /**
   * Verifica si el estado existe en el automata.
   * @param state
   * @return True si existe.
   */
  public boolean stateExist(String state) {
    return getAutomaton().containsKey(state);
  }
  /**
   * Añade un nuevo estado.
   * @param newState
   */
  public void addState(String newState){
    if (getAutomaton().containsKey(newState))
      throw new IllegalArgumentException("El estado " + newState + " ya existe.");
    else
      getAutomaton().put(newState, new ArrayList<AutomatonTransition>());   
  }
  /**
   * Añade un nuevo estado final.
   * @param finalState
   */
  public void addFinalState(String finalState) {
    if (getFinalStates().contains(finalState))
      throw new IllegalArgumentException("El estado " + finalState + " ya es un estado final.");
    else
      getFinalStates().add(finalState);
  }
  /**
   * Añade un nuevo elemento al alfabeto sigma.
   * @param newElement
   */
  public void addElementToInputAlphabet(String newElement) {
    if (getInputStringAlphabet().elementBelongsToAlphabet(newElement))
      throw new IllegalArgumentException("El elemento " + newElement + " ya forma parte del alfabeto de entrada.");
    else
      getInputStringAlphabet().addElementToAlphabet(newElement);
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

  public String getActualState() {
    return actualState;
  }

  protected void setActualState(String actualState) {
    this.actualState = actualState;
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
    setActualState(startingState);
  }  
}
