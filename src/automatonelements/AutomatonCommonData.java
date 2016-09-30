package automatonelements;

public final class AutomatonCommonData {
	public static int transitionNumber = 0;

	public static int getTransitionNumber() {
		return transitionNumber;
	}

	public static void setTransitionNumber(int transitionNumber) {
		AutomatonCommonData.transitionNumber = transitionNumber;
	}
	
	public static void resetTransitionNumber() {
		transitionNumber = 0;
	}
}
