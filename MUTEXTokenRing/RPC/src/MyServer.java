import java.io.*;
import java.net.*;

public class MyServer{

	public static void main(String[] args)
	{
			try{

				//1. Open the Server Socket and Register service on port
				ServerSocket ss =new ServerSocket(2222);

				//2. Wait for the Client Request and accept a
				Socket s=ss.accept();

				DataInputStream in=new DataInputStream(System.in);

				//3. Create I/O streams for communicating to the client
				DataInputStream dis = new DataInputStream(s.getInputStream());

				DataOutputStream dout=new DataOutputStream(s.getOutputStream());
				
				//4. Perform communication with
				String str=dis.readUTF();

				System.out.println("message= "+str);
				System.out.println("Enter a message for the Client...");
				@SuppressWarnings("deprecation")
				String str1 = in.readLine();

				dout.writeUTF(str1);

				//5. flush and close
				dout.flush();

				dout.close();

				//6. Close
				s.close();
				
				ss.close();

			}

			catch(Exception e){

				System.out.println(e);

			}

		}

	}