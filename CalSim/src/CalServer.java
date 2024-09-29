
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.StringTokenizer;

public class CalServer {
	int port;
	ServerSocket ss;
	Socket socket;
	
	public CalServer() {
		this.port=0;
	}
	
	public CalServer(int port) {
		this.port=port;
	}
	
	public double addition(int n1,int n2) {
		return n1+n2;
	}
	
	public double sub(int n1,int n2) {
		return n1-n2;
	}
	
	public double mul(int n1,int n2) {
		return n1*n2;
	}
	
	public double div(int n1,int n2) {
		return n1/n2;
	}
	
	public void listen() {
		try {
			System.out.println("Server started\n");
			ss=new ServerSocket(port);
			socket=ss.accept();
			
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
			double result=0.0;
			
			while(true) {
				String str=dis.readUTF();
				StringTokenizer st=new StringTokenizer(str,"-");
				int choice=Integer.parseInt(st.nextToken());
				int num1=Integer.parseInt(st.nextToken());
				int num2=Integer.parseInt(st.nextToken());
				CalServer cs=new CalServer();
				
				switch (choice) {
				case 1:
					result=cs.addition(num1, num2);
					break;
					
				case 2:
					result=cs.sub(num1, num2);
					break;
					
				case 3:
					result=cs.mul(num1, num2);
					break;
					
				case 4:
					result=cs.div(num1, num2);
					break;
				}
				
				System.out.println("Result for " +str+" is - ");
				String res=Double.toString(result);
				System.out.print(res);
				dout.writeUTF(res);
				dout.flush();
				dis.close();
				dout.close();
				socket.close();
			
				}
		
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			
		}
		
	}
	public static void main(String arg[]) {
		CalServer cc=new CalServer(5000);
		cc.listen();
	}

}
