/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package common;

public final class AutomatonCommonData {
	public static int transitionNumber = 0;

	public static int getTransitionNumber() {
		return transitionNumber;
	}

	public static void setTransitionNumber(int transitionNumber) {
		AutomatonCommonData.transitionNumber = transitionNumber;
	}
	
	public static void resetTransitionNumber() {
	  setTransitionNumber(0);
	}
}
