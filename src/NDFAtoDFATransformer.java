import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class NDFAtoDFATransformer {

	public Hashtable<String, Action> ndfa;
	public Hashtable<String, Action> dfa;
	
	public static State moveDFA(State state, String input){
		for (Action a : state.actions)
			if(a.symbol.equals("input")) return a.toState;
		return null;
	}
	
	public static HashSet<State> moveNFA(DFAState state, String input){
		//HashSet<State> states = state.states;
		HashSet <State> result = new HashSet<State>();
		for (State s : state.states){
			for (Action a : s.actions){
				if (a.symbol.equals(input)) result.add(a.toState);
			}
		}
		return result;
	}
	
	public static HashSet<State> epsilonClosure(HashSet <State> states){
		Stack<State> remainingStates = new Stack<State>();
		HashSet<State> result = new HashSet<State>();
		// Push all states to the stack
		for (State s : states){
			remainingStates.add(s);
		}
		
		while (!remainingStates.isEmpty()){
			State s = remainingStates.pop();
			ArrayList<Action> actions = s.actions;
			for (Action action : actions){
				if (action.symbol.equals("epsilon")){
					State actionState = action.toState;
					if (!result.contains(actionState)){
						result.add(actionState);
						remainingStates.push(actionState);
					}
				}
			}
		}
		
		return result;
	}
	
	public static HashSet<State> acceptingStates(HashSet<State> states){
		HashSet<State> acceptingStates = new HashSet<State>();
		for (State s : states){
			if(s.type == State.StateType.ACCEPTING ||s.type == State.StateType.STARTACCEPTING ){
				acceptingStates.add(s);
			}
		}
		return acceptingStates;
	}
	
	public static HashSet<State> startingStates(HashSet<State> states){
		HashSet<State> acceptingStates = new HashSet<State>();
		for (State s : states){
			if(s.type == State.StateType.START ||s.type == State.StateType.STARTACCEPTING ){
				acceptingStates.add(s);
			}
		}
		return acceptingStates;
	}
	
	public static DFAState nextUnmarkedState(HashSet<DFAState> states){
		for (DFAState s : states) if (!s.marked) return s;
		return null;
	}
	
	// Change this to use dictionaries instead of sets
	public static String toDFA(String inputFile) throws IOException{
		HashSet<State> nfaStates = FAReader.parseAutomata(inputFile);
		HashSet<DFAState> dfaStates = new HashSet<DFAState>();
		
		HashSet<State> startingStates = acceptingStates(nfaStates); // Change this to use all the states
		DFAState startingState = new DFAState();
		startingState.states = epsilonClosure(startingStates);
		dfaStates.add(startingState);
		
		DFAState nextUnmarkedState = nextUnmarkedState(dfaStates);
		
		while (nextUnmarkedState != null){
			nextUnmarkedState.marked = true;
			ArrayList <Action> actions = nextUnmarkedState.actions();
			for (Action a : actions){
				HashSet <State> epsStates = epsilonClosure(moveNFA(nextUnmarkedState, a.symbol));
				DFAState S = new DFAState();
				S.states = epsStates;
				if (!dfaStates.contains(S)) System.out.print("");
				Action action = new Action(a.symbol, S);
				nextUnmarkedState.actions.add(action);
			}
				 //Set MoveDFA(T,a) to S T=nextUnmarkedState
			nextUnmarkedState = nextUnmarkedState(dfaStates);
		}
		
		
		/*	
			switch (type){
				case ACCEPTING:
					acceptingStates.add(state);
					break;
				case START:
					startingStates.add(state);
					break;
				case STARTACCEPTING:
					startingStates.add(state);
					acceptingStates.add(state);
					break;
				case NORMAL:
					break;
				
			}
			*/
		return "";
	}
	
}
