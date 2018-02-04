package terminalserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * ClientHandler extends the Thread class. Which means that creates a new
 * thread for every instantianin. A new thread is made for every client that
 * connects to the terminal server. The clientHandler class is responsible for
 * servicing the connected clients.
 *
 * @author Golshid Bahadorian
 */
public class ClientHandler extends Thread
{

    private BufferedReader in;
    private PrintStream out;
    private Socket connectionSocket;

    private Calendar date;

    private ArrayList<ServerCommand> commands;

    ClientHandler(Socket conn, ArrayList<ServerCommand> commands)
    {
        this.connectionSocket = conn;
        this.in = null;
        this.out = null;
        date = new GregorianCalendar();
        this.commands = commands;
    }

    @Override
    public void run()
    {
        try
        {
            //get socket writing and reading streams
            openConnection();

            //Send welcome message to client
            out.println("Welcome to localhost. v.1.0.5");
            out.println("Author: Golshid Bahadorian \n");

            play();

        } catch (IOException e)
        {
            System.out.println("Client disconnected: " + e);
        }
    }

    /**
     * This method is responsible for reading and displaying optinos and
     * responses to the client.
     *
     * @throws IOException
     */
    public void play() throws IOException
    {
        boolean quit = false;
        String commandFromClient;

        // Display command options to the client.
        displayCommandOptions();

        out.print("Type here: ");
        
        //Now start reading input from client
        while ((commandFromClient = in.readLine()) != null && !quit)
        {
            switch (commandFromClient.toLowerCase().trim())
            {
                case "help":
                    displayCommandOptions();
                    break;
                case "time":
                    date.getTime().toString();
                    out.println(date.getTime().toString() + "\n");
                    break;
                case "quit":
                    terminateConnection();
                    quit = true;
                    break;
                default:
                    out.println(commandFromClient.toLowerCase().trim() + " is wrong. Please try again. \n");
                    break;
            }

            for (ServerCommand command : commands)
            {
                if (commandFromClient.toLowerCase().trim().equals(command.getName().toLowerCase()))
                {
                    out.println(command.process(commandFromClient, null) + "\n");
                }
            }
            out.print("Type here: ");
        }
    }

    /**
     * Displays the options menu.
     */
    private void displayCommandOptions()
    {
        out.println("\n**** Your Options are ****\n");
        out.println("Option. help " + "   -   " + "Display option menu.");
        out.println("Option. time " + "   -   " + "Display date and time.");
        out.println("Option. quit " + "   -   " + "Terminates connection to the server.");

        for (ServerCommand command : commands)
        {
            out.println("Option. " + command.getName().toLowerCase() + "   -   " + command.getShortDesc());
        }
        out.println();
    }

    private void openConnection() throws IOException
    {
        in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        out = new PrintStream(connectionSocket.getOutputStream());
    }

    /**
     * Terminates connection with socket and IO streams.
     *
     * @throws IOException
     */
    private void terminateConnection() throws IOException
    {
        in.close();
        out.close();
        connectionSocket.close();
    }
}
