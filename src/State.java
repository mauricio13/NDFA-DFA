import java.util.ArrayList;

public class State {
	
	public enum StateType {
		ACCEPTING,
		START,
		STARTACCEPTING,
		NORMAL
	    }
	
	public  StateType type;
	public String id;
	public boolean marked;
	public ArrayList<Action> actions;
	
	public State(StateType type, String id){
		this.type = type;
		this.id = id;
		this.marked = false;
		this.actions = new ArrayList<Action>();
	}
	
	public State(){
		
	}
}
