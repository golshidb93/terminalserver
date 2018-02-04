package terminalserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TerminalServer represents the actual terminalserver. The TerminalServer listen for connections and assigns a new thread
 * to every connecting clients.
 * 
 * @author Golshid Bahadorian
 */
public class TerminalServer
{
    private ArrayList<ServerCommand> commands;
    
    private ServerSocket serverSocket = null;
    private Socket connecttionSocket = null;

    private String hostName = null;
    static final int PORT_NUMBER = 5000;

    public TerminalServer(ArrayList<ServerCommand> commands)
    {
        this.hostName = "localhost";
        this.commands = commands;
    }

    public void play(int port)
    {
        InetAddress hostAdress = null;
        int portNumber = port;

        if (port != 0)
        {
            try
            {
                hostAdress = InetAddress.getByName(hostName);

                //1. creating a server socket - 1st parameter is port number
                serverSocket = new ServerSocket(portNumber, 10, hostAdress);

                //2. Wait for an incoming connection
                echo("Server socket is created. Waiting for connections.");

                while (true)
                {
                    // get the connection socket
                    connecttionSocket = serverSocket.accept();

                    // Print the host name and the name and port number for the connection
                    echo("Connection received from: " + connecttionSocket.getInetAddress().getHostName() + ":" + connecttionSocket.getPort());

                    // Create a new thread to handle the client
                    new ClientHandler(connecttionSocket, commands).start();
                }

            } catch (IOException ex)
            {
                Logger.getLogger(TerminalServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                try
                {
                    serverSocket.close();
                } catch (IOException ex)
                {
                    Logger.getLogger(TerminalServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else
        {
            try
            {
                hostAdress = InetAddress.getByName(hostName);

                //1. creating a server socket - 1st parameter is port number
                serverSocket = new ServerSocket(TerminalServer.PORT_NUMBER, 10, hostAdress);

                //2. Wait for an incoming connection
                echo("Server socket is created. Waiting for connections.");

                while (true)
                {
                    // get the connection socket
                    connecttionSocket = serverSocket.accept();

                    // Print the host name and the name and port number for the connection
                    echo("Connection received from: " + connecttionSocket.getInetAddress().getHostName() + ":" + connecttionSocket.getPort());

                    // Create a new thread to handle the client
                    new ClientHandler(connecttionSocket, commands).start();
                }

            } catch (IOException ex)
            {
                Logger.getLogger(TerminalServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally
            {
                try
                {
                    serverSocket.close();
                } catch (IOException ex)
                {
                    Logger.getLogger(TerminalServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void echo(String message)
    {
        System.out.println(message);
    }
}
