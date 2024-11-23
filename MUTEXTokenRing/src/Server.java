import java.net.DatagramPacket;
import java.net.DatagramSocket;
 class TokenServer {
    public static void main(String[] args) throws Exception {
        // Create a single DatagramSocket bound to port 8000
        DatagramSocket socket = new DatagramSocket(8000);

        while (true) {
            Server sr = new Server(socket);  // Pass the socket to the Server instance
            sr.recData();
        }
    }
}

public class Server {
    boolean hasToken = false;
    boolean sendData = false;
    DatagramSocket ds;

    // Constructor to receive the DatagramSocket from TokenServer
    public Server(DatagramSocket ds) {
        this.ds = ds;
    }

    // Method to receive data
    void recData() throws Exception {
        byte buffer[] = new byte[256];
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

        // Receive data using the provided DatagramSocket
        ds.receive(dp);

        String receivedMessage = new String(dp.getData(), 0, dp.getLength());
        System.out.println("The message is: " + receivedMessage);
    }
}
