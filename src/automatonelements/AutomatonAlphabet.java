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

import common.AutomatonCommonText;

public class AutomatonAlphabet {
  private ArrayList<String> alphabet;				// List of symbols that compose the alphabet

  /**
   * Creates a empty alphabet
   */
  public AutomatonAlphabet() {
    setAlphabet(new ArrayList<String>());
  }
  
  public String toString() {
    String resultToReturn = new String();
    
    for(String symbol : alphabet) {
      resultToReturn += symbol + " ";
    }
    
    resultToReturn = resultToReturn.substring(0, resultToReturn.length() - 1);
    
    return resultToReturn;
  }

  /**
   * Adds an element to the alphabet
   * @param element
   */
  public void addElementToAlphabet(String element) {
    if (!getAlphabet().contains(element) && !element.equals(AutomatonCommonText.EPSYLON)) {
      getAlphabet().add(element);
    }
  }

  /**
   * Verifies if an element belongs to the alphabet
   * @param element	Element to check
   * @return			true if the element belongs to the alphabet
   */
  public boolean elementBelongsToAlphabet(String element) {
    return getAlphabet().contains(element) || element.equals(AutomatonCommonText.EPSYLON);
  }

  private ArrayList<String> getAlphabet() {
    return alphabet;
  }

  private void setAlphabet(ArrayList<String> alphabet) {
    this.alphabet = alphabet;
  }	
}