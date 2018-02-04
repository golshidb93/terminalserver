package terminalserver;

/**
 * Interface for a single command supported by the server
 * @author Girts Strazdins, girts.strazdins@gmail.com, 2015-06-16
 */
public interface ServerCommand {
    /**
     * Command name, should be lowercase
     * @return 
     */
    String getName();

    /**
     * Process a command with specified arguments, return response
     * @param command command received from the user/client
     * @param arguments optional arguments supplied with the command
     * @return Output of the command
     */
    String process(String command, String[] arguments);
    
    /**
     * Short description of the command (max one line)
     * @return
     */
    String getShortDesc();

    /**
     * Long description of the command
     * @return 
     */
    String getLongDesc();
}