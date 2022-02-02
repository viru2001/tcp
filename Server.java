import java.net.*;
import java.io.*;

public class Server
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;

	// constructor with port
	public Server(int port)
	{
		// starts server and wait for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started \n Waiting for a client ... ");

			socket = server.accept(); 
			System.out.println("Client connected");

			// takes input from the client socket
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// reads message from client until "stop" is sent
			while (!line.equals("stop"))
			{
				try
				{
					line = in.readUTF(); // convert string from UTF format to string in java
					System.out.println(line);

				}
				catch(IOException exc)
				{
					System.out.println(exc);
				}
			}
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		}
		catch(IOException exc)
		{
			System.out.println(exc);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
