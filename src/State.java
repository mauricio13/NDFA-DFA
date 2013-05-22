import java.util.ArrayList;

public class State {
	
	public enum StateType {
		ACCEPTING,
		START,
		NORMAL
	    };
	
	public  StateType type;
	public String id;
	
	public State(StateType type, String id){
		this.type = type;
		this.id = id;
	}

}
