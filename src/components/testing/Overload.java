package components.testing;

import components.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * DESCRIPTION
 *
 * @author YOUR NAME HERE
 */
public class Overload {

    public static final int BAD_ARGS = 1;
    public static final int FILE_NOT_FOUND = 2;
    public static final int BAD_FILE_FORMAT = 3;
    public static final int UNKNOWN_COMPONENT = 4;
    public static final int REPEAT_NAME = 5;
    public static final int UNKNOWN_COMPONENT_TYPE = 6;
    public static final int UNKNOWN_USER_COMMAND = 7;
    public static final int UNSWITCHABLE_COMPONENT = 8;

    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String[] NO_STRINGS = new String[ 0 ];

    private static final String PROMPT = "? ";
    private static Map<String, Component> componentMap = new HashMap<String, Component>();

    static {
        Reporter.addError(
                BAD_ARGS, "Usage: java components.Overload <configFile>" );
        Reporter.addError( FILE_NOT_FOUND, "Config file not found" );
        Reporter.addError( BAD_FILE_FORMAT, "Error in config file" );
        Reporter.addError(
                UNKNOWN_COMPONENT,
                "Reference to unknown component in config file"
        );
        Reporter.addError(
                REPEAT_NAME,
                "Component name repeated in config file"
        );
        Reporter.addError(
                UNKNOWN_COMPONENT_TYPE,
                "Reference to unknown type of component in config file"
        );
        Reporter.addError(
                UNKNOWN_USER_COMMAND,
                "Unknown user command"
        );
    }

    public static void readFile(String file) throws FileNotFoundException {
        File realFile = new File(file);
        if(realFile.exists()) {
            Scanner in = new Scanner(new File(file));
            while (in.hasNextLine()) {
                String[] tokens = in.nextLine().split(WHITESPACE_REGEX);
                if (!componentMap.containsKey(tokens[1])) {
                    if (tokens[0].equals("PowerSource")) {
                        componentMap.put(tokens[1], new PowerSource(tokens[1]));
                    } else if (tokens[0].equals("CircuitBreaker")) {
                        componentMap.put(tokens[1], new CircuitBreaker(tokens[1], componentMap.get(tokens[2]), Integer.parseInt(tokens[3])));
                    } else if (tokens[0].equals("Outlet")) {
                        componentMap.put(tokens[1], new Outlet(tokens[1], componentMap.get(tokens[2])));
                    } else if (tokens[0].equals("Appliance")) {
                        componentMap.put(tokens[1], new Appliance(tokens[1], componentMap.get(tokens[2]), Integer.parseInt(tokens[3])));
                    } else {
                        Reporter.usageError(6);
                    }
                }
                else{
                    Reporter.usageError(5);
                }

            }
            System.out.println(componentMap.size() + " components created. \nStarting up the main circuit(s).");
            for (Component each : componentMap.values()) {
                if (each instanceof PowerSource) {
                    each.engage();
                }
            }
        }
        else{
            Reporter.usageError(2);
        }
    }

    public static void userInput(){
        Scanner in = new Scanner(System.in);
        String input = "";
        while (!input.equals("quit")) {
            System.out.print("? ->");
            input = in.nextLine();
            String[] tokens = input.split(WHITESPACE_REGEX);
            if (tokens[0].equals("toggle")) {
                if (componentMap.containsKey(tokens[1])) {
                    Component comp = componentMap.get(tokens[1]);
                    if (comp.isSwitchable()) {
                        if (comp.isSwitchOn()) {
                            comp.turnOff();
                        } else {
                            comp.turnOn();
                        }
                    } else {
                        Reporter.usageError(8);
                    }
                } else {
                    Reporter.usageError(4);
                }
            }
            else if (tokens[0].equals("connect")){
                if (componentMap.containsKey(tokens[2])) {
                    if (tokens[1].equals("CircuitBreaker")) {
                        componentMap.put(tokens[2], new CircuitBreaker(tokens[2], componentMap.get(tokens[3]), Integer.parseInt(tokens[4])));
                    } else if (tokens[1].equals("Outlet")) {
                        componentMap.put(tokens[2], new Outlet(tokens[2], componentMap.get(tokens[3])));
                    } else if (tokens[1].equals("Appliance")) {
                        componentMap.put(tokens[2], new Appliance(tokens[2], componentMap.get(tokens[3]), Integer.parseInt(tokens[4])));
                    } else {
                        Reporter.usageError(6);
                    }
                }
                else{
                    Reporter.usageError(4);
                }

            }
            else if (tokens[0].equals("display")){
                for (Component each : componentMap.values()){
                    if (each instanceof PowerSource){
                        each.display();
                    }
                }
            }
            else{
                Reporter.usageError(7);
            }
        }
    }

    public static void main( String[] args ) throws FileNotFoundException {
        System.out.println( "Overload Project, CS2" );
        if (args.length == 1) {
            readFile(args[0]);
            userInput();
        }
        else{
            Reporter.usageError(1);
        }

    }

}
