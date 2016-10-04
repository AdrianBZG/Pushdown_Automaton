/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import automaton.PushDownAutomaton;
import common.AutomatonCommonText;
import exceptions.AutomatonExceptionHandler;

public class AutomatonFileHandler {
	private static PushDownAutomaton automaton;						        // The automaton

	/**
	 * Creates an automaton from a file
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws AutomatonExceptionHandler
	 */
	public static PushDownAutomaton parseFromFile(String fileName) throws IOException, AutomatonExceptionHandler {
		setAutomaton(new PushDownAutomaton());

		Scanner scanner = null;
		String tempString = new String();
		try {
			scanner = new Scanner(new File(fileName));		

			tempString = skipLineComments(scanner);
			tempString = readStates(scanner, tempString);
			tempString = readSigma(scanner, tempString);
			tempString = readTau(scanner, tempString);
			tempString = readStartingSymbol(scanner, tempString);
			tempString = readStartingStackSymbol(scanner, tempString);
			tempString = readFinalStates(scanner, tempString);
			while(tempString != null) {
				tempString = readTransitions(scanner, tempString);
			}
		} catch(FileNotFoundException e) {
			throw new FileNotFoundException(fileName + AutomatonCommonText.NOT_FOUND_ERROR);
		} catch(AutomatonExceptionHandler e) {
			System.err.println(e.getMessage());
			throw new AutomatonExceptionHandler(e.getMessage());
		} finally {
			scanner.close();
		}
		return getAutomaton();
	}

	/**
	 * Skips commentaries and empty lines and returns the next line 
	 * that is not a comment or null, if there's not anything more to read
	 * @param scanner
	 * @return
	 */
	private static String skipLineComments(Scanner scanner) {
		String aux = null;
		if(scanner.hasNextLine())
			do {  								
				scanner = scanner.skip("[\t\r \n]*"); 
				aux = scanner.nextLine();
			} while (scanner.hasNextLine() && aux.charAt(0) == AutomatonCommonText.COMMENT);		// Skipping all comments on the beginning	
		return aux;
	}

	/**
	 * Parses the states line
	 * @param scanner
	 * @param line
	 * @return
	 */
	private static String readStates(Scanner scanner, String line) {
		String states[] = line.split("[\t ]+");

		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == AutomatonCommonText.COMMENT) {
				break;
			}
			getAutomaton().addState(states[i]);
		}

		return skipLineComments(scanner);
	}

	/**
	 * Parses the Sigma alphabet line
	 * @param scanner
	 * @param line
	 * @return
	 */
	private static String readSigma(Scanner scanner, String line) {
		String states[] = line.split("[\t ]+");

		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == AutomatonCommonText.COMMENT) {
				break;
			}
			getAutomaton().addElementToInputAlphabet(states[i]);
		}
		return skipLineComments(scanner);
	}

	/**
	 * Parses the Tau alphabet line
	 * @param scanner
	 * @param line
	 * @return
	 */
	private static String readTau(Scanner scanner, String line) {		
		String states[] = line.split("[\t ]+");

		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == AutomatonCommonText.COMMENT) {
				break;
			}
			getAutomaton().addElementToStackAlphabet(states[i]);
		}
		return skipLineComments(scanner);
	}

	/**
	 * Parses the starting symbol line
	 * @param scanner
	 * @param line
	 * @return
	 * @throws AutomatonExceptionHandler
	 */
	private static String readStartingSymbol(Scanner scanner, String line) throws AutomatonExceptionHandler {	
		String states[] = line.split("[\t ]+");

		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == AutomatonCommonText.COMMENT) {
				break;
			}
			if (getAutomaton().getStartingState() != null) {
				throw new AutomatonExceptionHandler(AutomatonCommonText.ONE_STARTING_SYMBOL_ERROR);
			}
			getAutomaton().setStartingState(states[i]);
		}
		return skipLineComments(scanner);

	}

	/**
	 * Parses the stack starting symbol
	 * @param scanner
	 * @param line
	 * @return
	 * @throws AutomatonExceptionHandler
	 */
	private static String readStartingStackSymbol(Scanner scanner, String line) throws AutomatonExceptionHandler {
		String states[];
		states = line.split("[\t ]+");

		for (int i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == AutomatonCommonText.COMMENT) {
				break;
			}
			if (getAutomaton().getStartingStackSymbol() != null) {
				throw new AutomatonExceptionHandler(AutomatonCommonText.ONE_STARTING_SYMBOL_STACK_ERROR);
			}
			getAutomaton().setStartingStackSymbol(states[i]);
		}
		return skipLineComments(scanner);


	}

	/**
	 * Parses the final states list, adding nothing if it's EMPTY
	 * @param scanner
	 * @param line
	 * @return
	 * @throws AutomatonExceptionHandler
	 */
	private static String readFinalStates(Scanner scanner, String line) throws AutomatonExceptionHandler {
		String states[];
		states = line.split("[\t ]+");
		int i = 0;
		int j = 0;
		boolean emptyFound = false;

		j = getNextCommentIndex(states);

		for (i = 0; i < j; i++) {		
			if (states[i].equals(AutomatonCommonText.EMPTY_FINAL_STATES)) {
				if (!getAutomaton().getFinalStates().isEmpty()) {
					throw new AutomatonExceptionHandler(AutomatonCommonText.FINAL_STATES_ERROR);
				}
				emptyFound = true;
			} else if (!getAutomaton().stateExist(states[i])) {
				throw new AutomatonExceptionHandler(AutomatonCommonText.STATE_NOT_FOUND_ERROR_1 + states[i] + AutomatonCommonText.STATE_NOT_FOUND_ERROR_2);
			} else if (emptyFound) {
				throw new AutomatonExceptionHandler(AutomatonCommonText.ERROR_ADD_STATE_ON_EMPTY);
			} if (!states[i].equals(AutomatonCommonText.EMPTY_FINAL_STATES)) {
				getAutomaton().addFinalState(states[i]);
			}
		}

		return skipLineComments(scanner);
	}

	/**
	 * Parses the next transition line
	 * @param scanner
	 * @param line
	 * @return
	 * @throws AutomatonExceptionHandler
	 */
	private static String readTransitions(Scanner scanner, String line) throws AutomatonExceptionHandler {

		String states[] = line.split("[\t ]+");
		int j = getNextCommentIndex(states);

		if (states.length < 5 || j != 5) {
			throw new AutomatonExceptionHandler(AutomatonCommonText.TRANSITIONS_ERROR);
		}

		try {
			getAutomaton().addTransition(states[0], states[1], states[2], states[3], states[4]);
		} catch (IllegalArgumentException e) {
			throw new AutomatonExceptionHandler(e.getMessage());
		}

		return skipLineComments(scanner);

	}

	/**
	 * Obtains from a list of symbols, the next one or the index where is the comment symbol or the index of
	 * the last element if it hasn't any
	 * @param states
	 * @return
	 */
	private static int getNextCommentIndex(String[] states) {
		int i;
		for (i = 0; i < states.length; i++) {
			if (states[i].charAt(0) == AutomatonCommonText.COMMENT) {
				break;
			}
		}
		return i;
	}

	private static PushDownAutomaton getAutomaton() {
		return automaton;
	}

	private static void setAutomaton(PushDownAutomaton automaton) {
		AutomatonFileHandler.automaton = automaton;
	}
}
