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
import automatonelements.AutomatonStack;
import automatonelements.AutomatonStateSet;
import automatonelements.AutomatonTransition;
import automatonelements.AutomatonTransitionTable;

public class PushDownAutomaton extends Automaton {

	private AutomatonAlphabet stackAlphabet;									// Alfabeto de la pila (tau).
	private String startingStackSymbol;								        // Estado inicial de la pila.
	private AutomatonStack stack;									            // Pila del automata.
	private String[] lastSymbolsPushedToStack;
	private PushDownAutomaton stepByStepAutomaton;
	
	/**
	 * Crea un automata vacio.
	 */
	public PushDownAutomaton() {
		setAutomaton(new AutomatonTransitionTable());
		setFinalStates(new AutomatonStateSet());
		setInputStringAlphabet(new AutomatonAlphabet());
		setStackAlphabet(new AutomatonAlphabet());
		setStack(new AutomatonStack());
		startingStackSymbol = (null);
		setStartingState(null);
		setStepByStepAutomaton(this);
		
	}
	/**
	 * Crea un automata copiando las referencias del que se le pasa por parametro
	 * y aplicandose una transicion.
	 * @param other
	 * @param transitionToApply
	 */
	public PushDownAutomaton(PushDownAutomaton other, AutomatonTransition transitionToApply) {
		String readSymbol = new String();
		String symbolsToPush[];
		
		this.setAutomaton(other.getAutomaton());
		this.setFinalStates(other.getFinalStates());
		this.setInputString(new AutomatonInputTape(other.getInputString()));
		this.setStackAlphabet(other.getStackAlphabet());
		this.startingStackSymbol = (other.getStartingStackSymbol());
		this.startingState = (other.getStartingState());
		this.setStack((AutomatonStack)other.getStack().clone());					/// Verificar si el clone est� realmente implementado.
		this.setInputStringAlphabet(other.getInputStringAlphabet());
		
		if (!transitionToApply.getCharacterToRead().equals(EPSYLON)) {
			readSymbol = this.getInputString().readNextElement();
		}
		this.getStack().pop();
		symbolsToPush = transitionToApply.getStackCharsToPush();
		setLastSymbolsPushedToStack(symbolsToPush);
		
		this.pushSymbolsToStack(symbolsToPush);
		
		this.setActualState(transitionToApply.getDestinyState());
		setStepByStepAutomaton(other);
	}
	
	/**
	 * Evalua la entrada actual.
	 * @return True si es aceptada.
	 */
	public boolean evaluateEntry() {
		ArrayList<String> actualStates = new ArrayList<String>();
		ArrayList<AutomatonTransition> possibleTransitions = new ArrayList<AutomatonTransition>();
		
		actualStates.add(actualState);
		if (entryAccepted(actualStates))
			return true;
		
		for (int i = 0; i < actualStates.size(); i++) {
			possibleTransitions = possibleTransitions(actualStates.get(i));
			for (int j = 0; j < possibleTransitions.size(); j++){
				PushDownAutomaton newAutomaton = new PushDownAutomaton(this, possibleTransitions.get(j));
			
				if (newAutomaton.evaluateEntry()) {
				  setStepByStepAutomaton(newAutomaton);
				  return true;
				}
			}
		}
			
		return false;
	}
	
	 /**
   * Evalua la entrada actual paso por paso.
   * @return True si es aceptada.
   */
  public boolean performOneStep() {
    ArrayList<String> actualStates = new ArrayList<String>();
    ArrayList<AutomatonTransition> possibleTransitions = new ArrayList<AutomatonTransition>();
    PushDownAutomaton newAutomaton = getStepByStepAutomaton();
    
    actualStates.add(actualState);
    
    for (int i = 0; i < actualStates.size(); i++) {
      possibleTransitions = possibleTransitions(actualStates.get(i));
      for (int j = 0; j < possibleTransitions.size(); j++) {
        newAutomaton = new PushDownAutomaton(this, possibleTransitions.get(j));
        
        if (newAutomaton.evaluateEntry()) {
          return true;
        }
      
      }
    }
    
    return false;
  }
	
	/**
	 * Empuja todos los simbolos a la pila.
	 * Hay que tener cuidado con la semantica del orden en que se empujan.
	 * @param symbols
	 */
	private void pushSymbolsToStack(String symbols[]) {
		for(int i = symbols.length - 1; i >= 0; i--) {
			if (!symbols[i].equals(EPSYLON))
				this.getStack().push(symbols[i]);		
		}
	}
	/**
	 * Devuelve true si en el estado en el que esta
	 * se puede dar por aceptada la entrada.
	 * @param actualStates
	 * @return
	 */
	private boolean entryAccepted(ArrayList<String> actualStates) {
		if (getInputString().entryEnded()) {
			if (getFinalStates().isEmpty())
				return getStack().isEmpty();
			else {
				for (int i = 0; i < actualStates.size(); i++)
					if (getFinalStates().contains(actualStates.get(i)))
						return true;
			}
		}
		return false;
	}
	/**
	 * Devuelve la lista de posibles transiciones
	 * para un determinado estado.
	 * @param state
	 * @return
	 */
	private ArrayList<AutomatonTransition> possibleTransitions(String state) {
		ArrayList<AutomatonTransition> result = new ArrayList<AutomatonTransition>();
		
		if (!getStack().isEmpty()) {
			for (int i = 0; i < getAutomaton().get(state).size(); i++) {
				if (getAutomaton().get(state).get(i).getCharacterToRead().equals(EPSYLON) || 
						getAutomaton().get(state).get(i).getCharacterToRead().equals(getInputString().readNextElementWithoutAdvance()))
						if (getAutomaton().get(state).get(i).getStackCharToConsume().equals(getStack().peek()))
							result.add(getAutomaton().get(state).get(i));
			}
		}
		return result;
	}
	
	/**
	 * Calcula la epsylon clausura de un estado.
	 * @param Actualstate
	 * @return
	 */
	private ArrayList<String> epsylonClausure(String Actualstate) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> aux = new ArrayList<String>();
		String state = null;
		
		aux.add(Actualstate);
		
		while (!aux.isEmpty()) {
			state = aux.get(0);
			aux.remove(0);
		
			for (int i = 0; i < getAutomaton().get(state).size(); i++) 
				if (getAutomaton().get(state).get(i).getCharacterToRead().equals(EPSYLON) && 
						getAutomaton().get(state).get(i).getStackCharToConsume().equals(getStack().peek())) {
					if (!result.contains(getAutomaton().get(state).get(i).getDestinyState()))
						aux.add(getAutomaton().get(state).get(i).getDestinyState());
					
				}
			
			result.add(state);
		}
		
		return result;
	}
	
	/**
	 * A�ade un nuevo elemento al alfabeto tau.
	 * @param newElement
	 */
	public void addElementToStackAlphabet(String newElement) {
		if (getStackAlphabet().elementBelongsToAlphabet(newElement))
			throw new IllegalArgumentException("El elemento " + newElement + " ya forma parte del alfabeto de la pila.");
		else
			getStackAlphabet().addElementToAlphabet(newElement);
	}
	
	/**
	 * A�ade una nueva transicion.
	 * @param origin
	 * @param entryToConsume
	 * @param stackSymbolToConsume
	 * @param destiny
	 * @param symbolsToPush
	 * @throws IllegalArgumentException
	 */
	public void addTransition(String origin, String entryToConsume, String stackSymbolToConsume, String destiny, String symbolsToPush)throws IllegalArgumentException {
		if (!stateExist(origin))
			throw new IllegalArgumentException("El elemento " + origin + " no forma parte del conjunto de estados.");
		if (!stateExist(destiny))
			throw new IllegalArgumentException("El elemento " + destiny + " no forma parte del conjunto de estados.");
		if (!getInputStringAlphabet().elementBelongsToAlphabet(entryToConsume))
			throw new IllegalArgumentException("El elemento " + entryToConsume + " no forma parte del alfabeto de entrada.");
		if (!getStackAlphabet().elementBelongsToAlphabet(stackSymbolToConsume))
			throw new IllegalArgumentException("El elemento " + stackSymbolToConsume + " no forma parte del alfabeto de la pila.");
		
		for (int i = 0; i < symbolsToPush.length(); i++) {
			if(!getStackAlphabet().elementBelongsToAlphabet(symbolsToPush.substring(i, i + 1)))
				throw new IllegalArgumentException("El elemento " + symbolsToPush.substring(i, i + 1) + " no forma parte del alfabeto de la pila.");	
		}
		
		getAutomaton().get(origin).add(new AutomatonTransition(origin, destiny, entryToConsume, stackSymbolToConsume, symbolsToPush.split("")));
	}
	

	private AutomatonAlphabet getStackAlphabet() {
		return stackAlphabet;
	}

	private void setStackAlphabet(AutomatonAlphabet stackAlphabet) {
		this.stackAlphabet = stackAlphabet;
	}

	public String getStartingStackSymbol() {
		return startingStackSymbol;
	}

	public void setStartingStackSymbol(String startingStackSymbol) {
		this.startingStackSymbol = startingStackSymbol;
		getStack().push(startingStackSymbol);
	}

	private AutomatonStack getStack() {
		return stack;
	}

	private void setStack(AutomatonStack stack) {
		this.stack = stack;
	}
  public String[] getLastSymbolsPushedToStack() {
    return lastSymbolsPushedToStack;
  }
  public void setLastSymbolsPushedToStack(String[] lastSymbolsPushedToStack) {
    this.lastSymbolsPushedToStack = lastSymbolsPushedToStack;
  }
  public PushDownAutomaton getStepByStepAutomaton() {
    return stepByStepAutomaton;
  }
  public void setStepByStepAutomaton(PushDownAutomaton stepByStepAutomaton) {
    this.stepByStepAutomaton = stepByStepAutomaton;
  }
	
}
