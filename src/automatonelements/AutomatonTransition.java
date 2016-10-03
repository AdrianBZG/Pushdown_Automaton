/**
 * @author Adrian Rodriguez Bazaga 
 * @version 1.0.0
 * @date 26 September 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Complejidad Computacional
 * @title Pushdown Automaton
 */

package automatonelements;

public class AutomatonTransition {

	private String characterToRead;
	private String stackCharToConsume;
	private String[] stackSymbolsToPush;
	private String originState;
	private String destinyState;
	
	public AutomatonTransition (String origin, String destiny, String charToRead, String stackCharToConsume, String[] stackCharsToPush) {
		
		setOriginState(origin);
		setDestinyState(destiny);
		setCharacterToRead(charToRead);
		setStackCharToConsume(stackCharToConsume);
		setStackCharsToPush(stackCharsToPush);
	}
	
	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public String getDestinyState() {
		return destinyState;
	}

	public void setDestinyState(String destinyState) {
		this.destinyState = destinyState;
	}

	public String getCharacterToRead() {
		return characterToRead;
	}
	
	public void setCharacterToRead(String characterToRead) {
		this.characterToRead = characterToRead;
	}
	
	public String getStackCharToConsume() {
		return stackCharToConsume;
	}
	
	public void setStackCharToConsume(String stackCharToConsume) {
		this.stackCharToConsume = stackCharToConsume;
	}
	
	public String[] getStackCharsToPush() {
		return stackSymbolsToPush;
	}
	
	public void setStackCharsToPush(String[] stackCharsToPush) {
		this.stackSymbolsToPush = stackCharsToPush;
	}
	
	public String toString() {
		return new String("(" + getOriginState() + "," + getCharacterToRead() + "," + getStackCharToConsume() +
						          ") -> (" + getDestinyState() + "," + getStackCharsToPushAsString() + ")");
	}
	
	public String getStackCharsToPushAsString() {
		String resultToReturn = new String();
		for(String s : getStackCharsToPush()) {
			resultToReturn += s;
		}
		return resultToReturn;
	}	
}
