import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Token1Client {
    public static void main(String[] args) throws Exception {
        InetAddress lclhost = InetAddress.getLocalHost();
        BufferedReader br;
        String input;
        TokenClient12 client = new TokenClient12(lclhost);

        client.setRecPort(8002); // Receiving port
        client.setSendPort(9004); // Sending port

        while (true) {
            if (client.hasToken) {
                System.out.println("Do you want to enter the Data -> Yes/No");
                br = new BufferedReader(new InputStreamReader(System.in));
                input = br.readLine();

                if (input.equalsIgnoreCase("yes")) {
                    System.out.println("Ready to send data...");
                    client.setSendData = true;
                    client.sendData();
                } else {
                    System.out.println("Passing the token...");
                    client.hasToken = false;
                    client.sendData();
                    client.recData();
                }
            } else {
                System.out.println("Entering Receiving Mode ...");
                client.recData();
                client.hasToken = true;
            }
        }
    }
}

class TokenClient12 {
    InetAddress lclhost;
    int sendPort, recPort;
    boolean hasToken = true;
    boolean setSendData = false;

    public TokenClient12(InetAddress lclhost) {
        this.lclhost = lclhost;
    }

    public void setSendPort(int sendPort) {
        this.sendPort = sendPort;
    }

    public void setRecPort(int recPort) {
        this.recPort = recPort;
    }

    // Method to send data
    void sendData() throws Exception {
        BufferedReader br;
        String message = "Token"; // Default message if no data is entered

        if (setSendData) {
            System.out.println("Enter the data:");
            br = new BufferedReader(new InputStreamReader(System.in));
            message = "Client One... " + br.readLine();
        }

        // Create a socket without binding it to a specific port for sending
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), lclhost, sendPort - 1000);
        socket.send(packet);
        socket.close();

        System.out.println("Data Sent: " + message);
        setSendData = false;
        hasToken = false;
    }

    // Method to receive data
    void recData() throws Exception {
        byte[] buffer = new byte[256];
        DatagramSocket socket = new DatagramSocket(recPort);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);
        socket.close();

        String receivedData = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received data: " + receivedData);

        if (receivedData.equals("Token")) {
            hasToken = true;
        }
    }
}
