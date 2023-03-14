package phase3.states;
/**
 * An abstract class used to identify the current engine state
 * 
 * Currently there is only one state: ModelingState
 *
 *
 */
public abstract class EngineStateManager {
	private static State currentState = null;
		
	public static void setState(State state) {
		currentState = state;
	}
		
	public static State getState() {
		return currentState;
	}
}
