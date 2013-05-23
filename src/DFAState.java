import java.util.ArrayList;
import java.util.HashSet;


public class DFAState extends State{
	public HashSet<State> states;
	public boolean marked;
	public boolean checkedStates;
	public boolean isFinal;
	
	public DFAState(){
		marked = false;
		checkedStates = false;
		isFinal = false;
		this.actions = new ArrayList<Action>();
	}
	
	public ArrayList<Action> actions()
	{
		ArrayList<Action> actions = new ArrayList<Action>(); 
		for (State s : states){
			for(Action a : s.actions){
				actions.add(a);
			}
		}
		return actions;
	}
	
	public String name(){
		String name = "";
		for (State s : states){
			name += s.id + ",";
		}
		return name;
	}
	
	public boolean isFinal(){
		if(checkedStates) return isFinal;
		for (State s : states){
			if (s.type == State.StateType.STARTACCEPTING || s.type == State.StateType.ACCEPTING)
				isFinal = true;
				checkedStates = true;
				return isFinal;
		}
		checkedStates = true;
		return isFinal;
	}
}
