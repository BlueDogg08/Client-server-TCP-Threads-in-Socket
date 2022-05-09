// A Java program for a Client
import java.net.*;
import java.io.*;

public class Client
{
	// inizializza il socket e gli input e output stream
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;

	// il costruttore necessita di un indirizzo ip e una porta
	public Client(String address, int port)
	{
		// la connessione viene stabilita
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connesso");

			// aspetta un input dal terminale
			input = new DataInputStream(System.in);

			// invia un output al socket
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}

		// la stringa che prende il messaggio dall'input
		String line = "";

		// continua a leggere fino a che over non è l'input inserito
		while (!line.equals("Over"))
		{
			try
			{
				line = input.readline();
				out.writeUTF(line);
			}
			catch(IOException i)
			{
				System.out.println(i);
			}
		}

		// chiude la connessione
		try
		{
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1", 2000);
	}
}