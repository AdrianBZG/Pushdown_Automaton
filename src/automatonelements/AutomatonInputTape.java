/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package automatonelements;

import java.util.ArrayList;

public class AutomatonInputTape extends AutomatonTape {	
	/**
	 * Crea una cadena de entrada nueva.
	 * @param input
	 */
	public AutomatonInputTape (String input) {
		setInputString(new ArrayList<String>());

		setInputString(input);

	}

	public String toString() {
		String resultToReturn = new String();
		for(int i = getActualIndex(); i < getInputString().size(); i++) {
			resultToReturn += getInputString().get(i) + " ";
		}
		return resultToReturn;
	}

	/**
	 * 
	 * @param input
	 */
	public AutomatonInputTape(AutomatonInputTape input) {
		setInputString(input.getInputString());
		setActualIndex(input.getActualIndex());
	}
	/**
	 * Verifica si se ha leido toda la cadena de entrada.
	 * @return
	 */
	public boolean entryEnded() {
		return getActualIndex() == getInputString().size();
	}
	/**
	 * Lee el siguiente elemento y avanza en el �ndice.
	 * @return	elemento le�do, null si se ha llegado al final.
	 */
	public String readNextElement() {
		String result = null;
		if (getActualIndex() < getInputString().size()) {
			result = getInputString().get(getActualIndex());
			setActualIndex(getActualIndex() + 1);
		}
		return result;
	}

	public String readNextElementWithoutAdvance() {
		String result = null;
		if (getActualIndex() < getInputString().size()) {
			result = getInputString().get(getActualIndex());
		}
		return result;
	}
	/**
	 * Getters y Setters.
	 * @param input
	 */
	public void setInputString(String input) {
		for (int i = 0; i < input.length(); i++) 
			getInputString().add(input.substring(i, i + 1));
		setActualIndex(0);
	}	
}
