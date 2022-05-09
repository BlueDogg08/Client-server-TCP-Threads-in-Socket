// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
	//inizializza il socket e input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;

	// il costruttore necessita della porta
	public Server(int port)
	{
		// avvia il server e aspetta una connessione
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server avvaito");

			System.out.println("Aspettando il client ...");

			socket = server.accept();
			System.out.println("Client accettato");

			// Prende un input dal socket del client
			in = new DataInputStream(
				new BufferedInputStream(socket.getInputStream()));

			String line = "";

			// legge i messaggi dal client fino a che non viene inviato "Over"
			while (!line.equals("Over"))
			{
				try
				{
					line = in.readUTF();
					System.out.println(line);

				}
				catch(IOException i)
				{
					System.out.println(i);
				}
			}
			System.out.println("Chiudendo connessione");

			// la connessione viene chiusa
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(2000);
	}
}