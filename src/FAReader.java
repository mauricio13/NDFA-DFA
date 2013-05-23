
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashSet;


public class FAReader {
	
	public static String[] getTokens(String line){
		String tokens[] = new String[3]; // Change this to accomodate all actions
		return null;
	}

	/*
	 * Input format
	 * StateId state_type (transition if x=0) (transition if x=1) (transition if x=epsilon) \n
	 * if no transition *
	 * e.g.
	 * 0 > 1 2 *
	 * 1 @ 1 1 3
	 * 2 - 0 1 *
	 * ...
	 */
	public static Hashtable<String,State> parseAutomata(String file) throws IOException{
	    // ASCII[tabulador]ASCII[tabulador]ASCII[tabulador][enter]
		BufferedReader br = new BufferedReader(new FileReader(file));
		Hashtable <String,State> automata = new Hashtable <String,State>();
		
	    try {
	        String line = br.readLine();
	        while (line != null) {
	            line = br.readLine();
	            String tokens[] = line.split(" ");
	            String stateId = tokens[0];
	            String stateType = tokens[1];
	            String transitionIfZero = tokens[2];
	            String transitionIfOne = tokens[3];
	            String transitionIfEpsilon = tokens[4];
	            
	            // First check if state exist
	            if (stateType.equals(">")){
	            	// Starting state
	            	insertOrUpdateState(automata, State.StateType.START, stateId, transitionIfZero, transitionIfOne, transitionIfEpsilon);
	            }
	            if (stateType.equalsIgnoreCase("@")){
	            	insertOrUpdateState(automata, State.StateType.ACCEPTING, stateId, transitionIfZero, transitionIfOne, transitionIfEpsilon);
	            }
	            
	            if (stateType.equals(">@") || stateId.equals("@>")){
	            	insertOrUpdateState(automata, State.StateType.STARTACCEPTING, stateId, transitionIfZero, transitionIfOne, transitionIfEpsilon);
	            }
	            
	            if (stateType.equals("-")){
	            	insertOrUpdateState(automata, State.StateType.NORMAL, stateId, transitionIfZero, transitionIfOne, transitionIfEpsilon);
	            }
	            
	        }
	    } finally {
	        br.close();
	    }
		return automata;
	}
	
	public static void insertOrUpdateState(Hashtable<String, State> automata, State.StateType type,
			String id, String transitionForZero, String transitionForOne, String transitionForEpsilon){
		
		State currentState = automata.get(id);
		State sIfZero = (!transitionForZero.equals("*")) ? automata.get(transitionForZero) : null;
		State sIfOne = (!transitionForOne.equals("*")) ? automata.get(transitionForOne) : null;
		State sIfEpsilon = (!transitionForEpsilon.equals("*")) ? automata.get(transitionForEpsilon) : null;
		
		if (!transitionForZero.equals("*"))
			if (sIfZero == null) sIfZero = new State(State.StateType.NORMAL, transitionForZero);
		if (!transitionForOne.equals("*"))
			if (sIfOne == null) sIfOne = new State(State.StateType.NORMAL, transitionForOne);
		if (!transitionForEpsilon.equals("*"))
			if (sIfEpsilon == null) sIfZero = new State(State.StateType.NORMAL, transitionForEpsilon);

		
		if (currentState != null)
			currentState.type = type;
		else
			currentState = new State(type, id);
		
		ArrayList<Action> actions = currentState.actions;
		if (sIfZero != null) actions.add(new Action(transitionForZero, sIfZero));
		if (sIfOne != null) actions.add(new Action(transitionForOne, sIfOne));
		if (sIfEpsilon != null) actions.add(new Action(transitionForEpsilon, sIfEpsilon));	
		
		automata.put(id, currentState);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String comps[] = "1,10,2,".split(",");
		for (String s : comps){
			System.out.println(s);
		}

	}

}
