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

import automaton.Automaton;

public class AutomatonAlphabet {
	private ArrayList<String> alphabet;				// Lista de simbolos que componen el alfabeto.
	
	/**
	 * Crea un alfabeto vacio.
	 */
	public AutomatonAlphabet(){
		setAlphabet(new ArrayList<String>());
	}
	/**
	 * Añade el elemento recibido al alfabeto.
	 * @param element
	 */
	public void addElementToAlphabet(String element) {
		if (!getAlphabet().contains(element) && !element.equals(Automaton.EPSYLON))
			getAlphabet().add(element);
	}
	/**
	 * Verifica si el elemento pertenece al alfabeto.
	 * @param element	elemento a analizar.
	 * @return			true si element pertenece al alfabeto.
	 */
	public boolean elementBelongsToAlphabet(String element){
		return getAlphabet().contains(element) || element.equals(Automaton.EPSYLON);
	}
	/**
	 * Getters y Setters.
	 */
	private ArrayList<String> getAlphabet() {
		return alphabet;
	}

	private void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	
}