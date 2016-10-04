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

public class AutomatonStateSet extends ArrayList<String> {
  public String toString() {
    String resultToReturn = new String();

    for(String symbol : this) {
      resultToReturn += symbol + " ";
    }

    if(resultToReturn.length() > 1) {
      resultToReturn = resultToReturn.substring(0, resultToReturn.length() - 1);
    } else {
      resultToReturn = AutomatonCommonText.EMPTY_FINAL_STATES;
    }

    return resultToReturn;
  }
}
