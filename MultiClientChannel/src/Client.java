// when user start it
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	
	public Client(Socket socket, String username) {
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.username = username;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void sendMessage() {
		try {
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			Scanner sc = new Scanner(System.in);
			while (socket.isConnected()) {
				String messageToSend = sc.nextLine();
				bufferedWriter.write(username + " : "+ messageToSend);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
			sc.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void listenForMessage() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String msgFromGroupChat;
				while(socket.isConnected()) {
					try {
						msgFromGroupChat = bufferedReader.readLine();
						System.out.println(msgFromGroupChat);
					}
					catch (Exception e) {
						// TODO: handle exception
						closeEverything(socket, bufferedReader, bufferedWriter);
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		// Lefted 1 member
		
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
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method s
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your username for the group chat : ");
		String username = sc.nextLine();
		Socket socket = new Socket("localhost",8867);
		Client client = new Client(socket, username);
		client.listenForMessage();
		client.sendMessage();
		sc.close();
	}

}
