/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package automaton;

import java.io.IOException;
import java.util.ArrayList;

import automatonelements.AutomatonAlphabet;
import automatonelements.AutomatonInputTape;
import automatonelements.AutomatonStack;
import automatonelements.AutomatonStateSet;
import automatonelements.AutomatonTransition;
import automatonelements.AutomatonTransitionTable;
import common.AutomatonCommonData;
import common.AutomatonCommonText;
import gui.AutomatonWindow;

public class PushDownAutomaton extends Automaton {

	private AutomatonAlphabet stackAlphabet;					// Tau alphabet (the stack alphabet)
	private String initialStackSymbol;							// Stack initial symbol
	private AutomatonStack stack;								// The automaton stack

	/**
	 * Creates an empty automaton
	 */
	public PushDownAutomaton() {
		setAutomaton(new AutomatonTransitionTable());
		setFinalStates(new AutomatonStateSet());
		setInputStringAlphabet(new AutomatonAlphabet());
		setStackAlphabet(new AutomatonAlphabet());
		setStack(new AutomatonStack());
		setInitialStackSymbol(null);
		setStartingState(null);
	}

	/**
	 * Creates an empty automaton copying the references from the one that is passed through parameter
	 * and applying one transition
	 * @param other
	 * @param transitionToApply
	 * @throws IOException 
	 */
	public PushDownAutomaton(PushDownAutomaton other, AutomatonTransition transitionToApply) throws IOException {
		String symbolsToPush[];

		setAutomaton(other.getAutomaton());
		setFinalStates(other.getFinalStates());
		setInputString(new AutomatonInputTape(other.getInputString()));
		setStackAlphabet(other.getStackAlphabet());
		setInitialStackSymbol(other.getStartingStackSymbol());
		setStartingState(other.getStartingState());
		setStack((AutomatonStack)other.getStack().clone());
		setInputStringAlphabet(other.getInputStringAlphabet());

		if (!transitionToApply.getCharacterToRead().equals(AutomatonCommonText.EPSYLON)) {
			getInputString().readNextElement();
		}
		getStack().pop();
		symbolsToPush = transitionToApply.getStackCharsToPush();

		pushSymbolsToStack(symbolsToPush);

		setCurrentState(transitionToApply.getDestinyState());

		updateTransitionInformation(transitionToApply);
	}

	private void updateTransitionInformation(AutomatonTransition transitionToApply) throws IOException {
		AutomatonCommonData.setTransitionNumber(AutomatonCommonData.getTransitionNumber() + 1);
		if(AutomatonCommonData.getTransitionNumber() > 1) {
			showTransitionInfo("<br><b><u>" + AutomatonCommonData.getTransitionNumber() + ".</u> CURRENT STATE:</b> " + getCurrentState() + "<b><br> INPUT TAPE STATUS: </b>" + getInputString() + "<b><br> STACK STATUS: </b>" + printStackElements() + "<b><br> ACTION(\u03B4): </b>" + transitionToApply);
		} else {
			showTransitionInfo("<b><u>" + AutomatonCommonData.getTransitionNumber() + ".</u> CURRENT STATE:</b> " + getCurrentState() + "<b><br> INPUT TAPE STATUS: </b>" + getInputString() + "<b><br> STACK STATUS: </b>" + printStackElements() + "<b><br> ACTION(\u03B4): </b>" + transitionToApply);
		}
	}

	/**
	 * Evaluates the current input
	 * @return True if the input is accepted
	 * @throws IOException 
	 */
	public boolean evaluateEntry() throws IOException {
		ArrayList<String> actualStates = new ArrayList<String>();
		ArrayList<AutomatonTransition> possibleTransitions = new ArrayList<AutomatonTransition>();
		actualStates.add(getCurrentState());

		if (entryAccepted(actualStates)) {
			return true;
		}

		for (int i = 0; i < actualStates.size(); i++) {
			possibleTransitions = possibleTransitions(actualStates.get(i));
			for (int j = 0; j < possibleTransitions.size(); j++) {
				PushDownAutomaton newAutomaton = new PushDownAutomaton(this, possibleTransitions.get(j));
				if (newAutomaton.evaluateEntry()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Pushes all symbols to the stack
	 * @param symbols
	 */
	private void pushSymbolsToStack(String symbols[]) {
		for(int i = symbols.length - 1; i >= 0; i--) {
			if (!symbols[i].equals(AutomatonCommonText.EPSYLON)) {
				this.getStack().push(symbols[i]);
			}
		}
	}

	/**
	 * Checks if the input can be accepted in the current automaton state
	 * @param actualStates
	 * @return
	 */
	private boolean entryAccepted(ArrayList<String> actualStates) {
		if (getInputString().entryEnded()) {
			if (getFinalStates().isEmpty()) {
				return getStack().isEmpty();
			} else {
				for (int i = 0; i < actualStates.size(); i++) {
					if (getFinalStates().contains(actualStates.get(i))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Retrieves the list of possible transitions from a certain state
	 * @param state
	 * @return
	 */
	private ArrayList<AutomatonTransition> possibleTransitions(String state) {
		ArrayList<AutomatonTransition> result = new ArrayList<AutomatonTransition>();

		if (!getStack().isEmpty()) {
			for (int i = 0; i < getAutomaton().get(state).size(); i++) {
				if (getAutomaton().get(state).get(i).getCharacterToRead().equals(AutomatonCommonText.EPSYLON) || 
						getAutomaton().get(state).get(i).getCharacterToRead().equals(getInputString().readNextElementWithoutAdvance())) {
					if (getAutomaton().get(state).get(i).getStackCharToConsume().equals(getStack().peek())) {
						result.add(getAutomaton().get(state).get(i));
					}
				}
			}
		}
		return result;
	}

	/**
	 * Adds a new element to the Tau alphabet
	 * @param newElement
	 */
	public void addElementToStackAlphabet(String newElement) {
		if (getStackAlphabet().elementBelongsToAlphabet(newElement)) {
			throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + newElement + AutomatonCommonText.ALREADY_BELONGS_TO_TAU);
		} else {
			getStackAlphabet().addElementToAlphabet(newElement);
		}
	}

	/**
	 * Adds a new transition to the automaton
	 * @param origin
	 * @param entryToConsume
	 * @param stackSymbolToConsume
	 * @param destiny
	 * @param symbolsToPush
	 * @throws IllegalArgumentException
	 */
	public void addTransition(String origin, String entryToConsume, String stackSymbolToConsume, String destiny, String symbolsToPush) throws IllegalArgumentException {
		checkForTransitionErrors(origin, destiny, entryToConsume, stackSymbolToConsume, symbolsToPush);
		getAutomaton().get(origin).add(new AutomatonTransition(origin, destiny, entryToConsume, stackSymbolToConsume, symbolsToPush.split("")));
	}

	private void checkForTransitionErrors(String origin, String destiny, String entryToConsume, String stackSymbolToConsume, String symbolsToPush) {
		if (!stateExist(origin)) {
			throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + origin + AutomatonCommonText.NOT_BELONGS_TO_STATE_SET);
		}
		
		if (!stateExist(destiny)) {
			throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + destiny + AutomatonCommonText.NOT_BELONGS_TO_STATE_SET);
		}
		
		if (!getInputStringAlphabet().elementBelongsToAlphabet(entryToConsume)) {
			throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + entryToConsume + AutomatonCommonText.NOT_BELONGS_TO_SIGMA_ALPHABET);
		}
		
		if (!getStackAlphabet().elementBelongsToAlphabet(stackSymbolToConsume)) {
			throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + stackSymbolToConsume + AutomatonCommonText.NOT_BELONGS_TO_TAU_ALPHABET);
		}

		for (int i = 0; i < symbolsToPush.length(); i++) {
			if(!getStackAlphabet().elementBelongsToAlphabet(symbolsToPush.substring(i, i + 1))) {
				throw new IllegalArgumentException(AutomatonCommonText.THE_ELEMENT_TEXT + symbolsToPush.substring(i, i + 1) + AutomatonCommonText.NOT_BELONGS_TO_TAU_ALPHABET);	
			}
		}
	}

	public AutomatonAlphabet getStackAlphabet() {
		return stackAlphabet;
	}

	private void setStackAlphabet(AutomatonAlphabet stackAlphabet) {
		this.stackAlphabet = stackAlphabet;
	}

	public String getStartingStackSymbol() {
		return initialStackSymbol;
	}

	public void setStartingStackSymbol(String startingStackSymbol) {
		this.initialStackSymbol = startingStackSymbol;
		getStack().push(startingStackSymbol);
	}

	public String printStackElements() {
		String resultToReturn = new String();
		AutomatonStack newStack = (AutomatonStack)getStack().clone();

		while(!newStack.empty()) {
			resultToReturn += newStack.pop() + " ";
		}

		return resultToReturn;
	}

	private AutomatonStack getStack() {
		return stack;
	}

	private void setStack(AutomatonStack stack) {
		this.stack = stack;
	}

	private void showTransitionInfo(String info) throws IOException {
		AutomatonWindow.appendTextToTransitionsPanel(info);
	}

	public String getInitialStackSymbol() {
		return initialStackSymbol;
	}

	public void setInitialStackSymbol(String initialStackSymbol) {
		this.initialStackSymbol = initialStackSymbol;
	}
}