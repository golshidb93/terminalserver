package terminalserver;

import java.util.ArrayList;
import static terminalserver.TerminalServer.PORT_NUMBER;

/**
 *
 * @author Golshid Bahadorian
 */
public class Application
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        ArrayList<ServerCommand> commands = new ArrayList<>();

        ServerCommand listFilesCommand = new ListFilesCommand();
        commands.add(listFilesCommand);
        
        ServerCommand searchCommand = new SearchCommand();
        commands.add(searchCommand);
        
        TerminalServer terminal = new TerminalServer(commands);

        if (args.length == 0)
        {
            terminal.play(PORT_NUMBER);
        } else
        {
            terminal.play(Integer.parseInt(args[0]));
        }
    }
}
