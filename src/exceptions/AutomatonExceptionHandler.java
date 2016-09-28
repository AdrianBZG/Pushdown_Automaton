/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package exceptions;

/**
 * Clase encargada de definir un error durante el parsing.
 */
public class AutomatonExceptionHandler extends Exception {

	public AutomatonExceptionHandler(String m) {
		super(m);
	}
}
