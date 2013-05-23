
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

	//state:symbol
	public static HashSet<State> parseAutomata(String file) throws IOException{
	    // ASCII[tabulador]ASCII[tabulador]ASCII[tabulador][enter]
		BufferedReader br = new BufferedReader(new FileReader(file));
	    try {
	        String line = br.readLine();
	        while (line != null) {
	        	State state;
	            line = br.readLine();
	            char typeOfState = line.charAt(0);
	            switch (typeOfState){
	            	case '>':
	            		state = new State(State.StateType.START, "");
	            		break;
	            	case '@':
	            		break;
	            	case ' ':
	            		break;
	            	default:
	            		// Raise error condition
	            		break;
	            }
	        }
	    } finally {
	        br.close();
	    }
		return null;
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
