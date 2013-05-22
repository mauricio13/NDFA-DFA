
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class FAReader {
	
	public static String[] getTokens(String line){
		String tokens[] = new String[3]; // Change this to accomodate all actions
		return null;
	}

	public static Hashtable<String, ArrayList<String>> parseAutomata(String file) throws IOException{
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
		// TODO Auto-generated method stub

	}

}
