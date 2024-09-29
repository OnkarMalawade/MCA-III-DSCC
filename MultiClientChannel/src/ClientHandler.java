import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientUserName;
	@SuppressWarnings("unused")
	private String messageToSend;
	public ClientHandler(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			this.socket = socket;
			//  res allocated
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.clientUserName = bufferedReader.readLine();
			clientHandlers.add(this);
			broadcastMessage("Server : " + clientUserName + " has entered the chat guys!");
		}
		catch(Exception e) {
			closeEverything(socket, bufferedReader, bufferedWriter);
		}
	}
	
	@Override
	public void run() {
		String messageFromClient;
		while(socket.isConnected()) {
			try {
				messageFromClient = bufferedReader.readLine();
				broadcastMessage(messageFromClient);
			}
			catch(Exception e) {
				closeEverything(socket, bufferedReader, bufferedWriter);
				break;
			}
		}
	}

	public void broadcastMessage(String messageToSend) {
		this.messageToSend = messageToSend;
		for(ClientHandler clientHandler : clientHandlers) {
			try {
				if(!clientHandler.clientUserName.equals(clientUserName)) {
					clientHandler.bufferedWriter.write(messageToSend);
					clientHandler.bufferedWriter.newLine();
					clientHandler.bufferedWriter.flush();
				}
			}
			catch(Exception e) {
				closeEverything(socket, bufferedReader,bufferedWriter);
			}
		}
	}
	
	public void removeClientHandler() {
		clientHandlers.remove(this);
		broadcastMessage("Server : " + clientUserName + " has left the Chat Guys!");
	}
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		// Lefted 1 member
		removeClientHandler();
		
		try {
			if(bufferedReader != null) {
				bufferedReader.close();
			}
			if(bufferedWriter != null) {
				bufferedWriter.close();
			}
			if(socket != null) {
				socket.close();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
