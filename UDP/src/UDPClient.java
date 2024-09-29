import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub
		DatagramPacket dpac;
		DatagramSocket dsoc = new DatagramSocket(1314);
		byte[] b = new byte[64];
		String data = "No Data";
		System.out.println("Client up");
		try {
			while(true) {
				dpac = new DatagramPacket(b, b.length);
				dsoc.receive(dpac);
				data = new String(dpac.getData());
				System.out.println("We received Data : " + data);
			}
		}catch (IOException e) {
			// TODO: handle exception
			System.out.println("IOException");	
		}
		dsoc.close();
	}

}
