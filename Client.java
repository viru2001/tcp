import java.net.*;
import java.io.*;

public class Client
{
	// initialize socket and input output streams
	private Socket socket		 = null;
	private DataInputStream input = null;
	private DataOutputStream out	 = null;

	// constructor to put ip address and port
	public Client(String address, int port)
	{
		// establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");

			// takes input from terminal
			input = new DataInputStream(System.in);

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException exc)
		{
			System.out.println(exc);
		}

		// string to read message from input
		String line = "";

		// keep reading until "stop" is input
		while (!line.equals("stop"))
		{
			try
			{
				line = input.readLine();
				out.writeUTF(line); // send encoded string
			}
			catch(IOException exc)
			{
				System.out.println(exc);
			}
		}

		// close the connection
		try
		{
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException exc)
		{
			System.out.println(exc);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}
