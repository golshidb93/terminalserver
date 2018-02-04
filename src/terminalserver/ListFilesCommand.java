package terminalserver;

import java.io.File;

/**
 * A command that list files in a particular directory
 *
 * If your program is correct, then you should be able to easily add this
 * command to your server
 *
 * @author Girts Strazdins, girts.strazdins@gmail.com, 2015-10-01
 */
public class ListFilesCommand implements ServerCommand
{

    /**
     * Command name, should be lowercase
     *
     * @return
     */
    @Override
    public String getName()
    {
        return "listFiles";
    }

    /**
     * Process a command with specified arguments, return response
     *
     * @param cmd command received from the user/client
     * @param arguments optional arguments supplied with the command
     * @return Output of the command
     */
    @Override
    public String process(String cmd, String[] arguments)
    {
        String dir;
        if (arguments != null && arguments.length > 0)
        {
            dir = arguments[0]; // Use specified directory
        } else
        {
            dir = System.getProperty("user.dir"); // Get current directory
        }

        String response = "Files in directory " + dir + ": \r\n";

        File folder = new File(dir);
        if (folder.exists() && folder.isDirectory())
        {
            File[] listOfFiles = folder.listFiles();
            for (File fname : listOfFiles)
            {
                if (fname.isFile())
                {
                    response += "        " + fname.getName() + "\r\n";
                } else if (fname.isDirectory())
                {
                    response += " <Dir>  " + fname.getName() + "\r\n";
                }
            }
        } else
        {
            response += "        Path does not exist!";
        }

        return response;
    }

    /**
     * Short description of the command (max one line)
     *
     * @return
     */
    @Override
    public String getShortDesc()
    {
        return "Lists files in a specific directory";
    }

    /**
     * Long description of the command
     *
     * @return
     */
    @Override
    public String getLongDesc()
    {
        return getShortDesc() + ". If directory name specified as the "
                + "first argument, use it. Otherwise use current directory.";
    }

}
