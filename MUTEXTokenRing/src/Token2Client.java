import java.io.*;
import java.net.*;

public class Token2Client {
    public static void main(String[] args) throws Exception {
        InetAddress localhost = InetAddress.getLocalHost();
        BufferedReader br;
        String input;
        TokenClient21 client;
        TokenClient21 server;

        // Initialize both client and server components
        client = new TokenClient21(localhost);
        server = new TokenClient21(localhost);

        client.setRecPort(8004); // Receive on port 8004
        client.setSendPort(9002); // Send to port 9002
        server.setSendPort(9000); // Send to the central server on port 9000

        while (true) {
            System.out.println("Checking if client has token...");

            if (client.hasToken) {
                System.out.println("Do you want to enter the Data –> YES/NO");
                br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();

                if (input.equalsIgnoreCase("yes")) {
                    System.out.println("Sending data...");
                    server.setSendData = true;
                    server.sendData();
                } else {
                    // If not sending data, pass the token
                    client.sendData();
                    client.hasToken = false;
                    client.recData(); // Await for next token pass
                }
            } else {
                System.out.println("Entering receiving mode...");
                client.recData();
                client.hasToken = true;
            }
        }
    }
}

class TokenClient21 {
    InetAddress localhost;
    int sendPort, recPort;
    boolean hasToken = false;
    boolean setSendData = false;

    // Constructor
    public TokenClient21(InetAddress localhost) {
        this.localhost = localhost;
    }

    // Set sending port
    void setSendPort(int sendPort) {
        this.sendPort = sendPort;
    }

    // Set receiving port
    void setRecPort(int recPort) {
        this.recPort = recPort;
    }

    // Method to send data
    void sendData() throws Exception {
        BufferedReader br;
        String str = "Token";
        DatagramSocket socket;
        DatagramPacket packet;

        // If setSendData is true, prompt user for data to send
        if (setSendData) {
            System.out.println("Enter the Data:");
            br = new BufferedReader(new InputStreamReader(System.in));
            str = "ClientTwo: " + br.readLine();
        }

        // Send packet
        socket = new DatagramSocket();
        packet = new DatagramPacket(str.getBytes(), str.length(), localhost, sendPort);
        socket.send(packet);
        socket.close();

        System.out.println("Data Sent");
        setSendData = false;
        hasToken = false;
    }

    // Method to receive data
    void recData() throws Exception {
        byte[] buffer = new byte[256];
        DatagramPacket packet;
        DatagramSocket socket = new DatagramSocket(recPort);

        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        socket.close();

        String receivedData = new String(packet.getData(), 0, packet.getLength());
        System.out.println("The data is: " + receivedData);

        // If the received message is "Token," set hasToken to true
        if (receivedData.equals("Token")) {
            hasToken = true;
        }
    }
}
