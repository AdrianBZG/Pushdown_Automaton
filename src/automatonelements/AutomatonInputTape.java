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
   * Creates a new input tape for the automaton
   * @param input
   */
  public AutomatonInputTape (String input) {
    setInputString(new ArrayList<String>());
    setInputString(input);
  }

  public String toString() {
    String resultToReturn = new String();
    for(int i = getCurrentIndex(); i < getInputString().size(); i++) {
      resultToReturn += getInputString().get(i) + " ";
    }
    return resultToReturn;
  }

  /**
   * @param input
   */
  public AutomatonInputTape(AutomatonInputTape input) {
    setInputString(input.getInputString());
    setCurrentIndex(input.getCurrentIndex());
  }

  /**
   * Verifies if the whole input string has been read
   */
  public boolean entryEnded() {
    return getCurrentIndex() == getInputString().size();
  }

  /**
   * Reads the next element and moves forward the index
   * @return	Read element or null if the index is at the end
   */
  public String readNextElement() {
    String result = null;
    if (getCurrentIndex() < getInputString().size()) {
      result = getInputString().get(getCurrentIndex());
      setCurrentIndex(getCurrentIndex() + 1);
    }
    return result;
  }

  public String readNextElementWithoutAdvance() {
    String result = null;
    if (getCurrentIndex() < getInputString().size()) {
      result = getInputString().get(getCurrentIndex());
    }
    return result;
  }

  public void setInputString(String input) {
    for (int i = 0; i < input.length(); i++) {
      getInputString().add(input.substring(i, i + 1));
    }
    setCurrentIndex(0);
  }	
}
