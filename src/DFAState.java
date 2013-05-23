import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


public class DFAState extends State{
	public Hashtable<String,State> states;
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
		
		Iterator<State> it= states.values().iterator();
		while(it.hasNext()){
			State s = it.next(); // Check for duplicates
			for(Action a : s.actions){
				actions.add(a);
			}
		}
		return actions;
	}
	
	public String name(){
		String name = "";
		Iterator<State> it= states.values().iterator();
		while(it.hasNext()){
			State s = it.next();
			name += s.id + ",";
		}
		return name;
	}
	
	public boolean isFinal(){
		if(checkedStates) return isFinal;
		Iterator<State> it= states.values().iterator();
		while(it.hasNext()){
			State s = it.next();
			if (s.type == State.StateType.STARTACCEPTING || s.type == State.StateType.ACCEPTING)
				isFinal = true;
				checkedStates = true;
				return isFinal;
		}
		checkedStates = true;
		return isFinal;
	}
}
