import java.util.ArrayList;

public class State {
	
	public enum StateType {
		ACCEPTING,
		START,
		STARTACCEPTING,
		NORMAL
	    };
	
	public  StateType type;
	public String id;
	public ArrayList actions;
	
	public State(StateType type, String id){
		this.type = type;
		this.id = id;
	}

}
