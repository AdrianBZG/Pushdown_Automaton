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

public abstract class AutomatonTape {
  private ArrayList<String> inputString;      // The input string for the automaton
  private int currentIndex;                   // Current index to read from the input

  /**
   * @return the inputString
   */
  public ArrayList<String> getInputString() {
    return inputString;
  }

  /**
   * @param inputString the inputString to set
   */
  public void setInputString(ArrayList<String> inputString) {
    this.inputString = inputString;
  }
  
  /**
   * @return the actualIndex
   */
  public int getCurrentIndex() {
    return currentIndex;
  }
  
  /**
   * @param currentIndex the currentIndex to set
   */
  public void setCurrentIndex(int currentIndex) {
    this.currentIndex = currentIndex;
  }
}
