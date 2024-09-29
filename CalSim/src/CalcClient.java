
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalcClient {
	Socket socket;
	int port;
	
	public CalcClient(int port) {
		this.port=port;
	}
	
	public void sndReq() throws Exception{
		socket=new Socket("localhost",port);
		
		DataInputStream din=new DataInputStream(socket.getInputStream());
		DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		String str="";
		int num1,num2;
		
		System.out.println("1:Addition \n2:Sub \n3:Multi \n4:Div \n5:Exit");
		System.out.println("Enter your choice ");
		int choice=Integer.parseInt(in.readLine());
		System.out.println("Val=" +choice);
		
		switch(choice) {
		case 1:
			str += choice+"-";
			System.out.println("Enter 1st Number\n");
			num1 =Integer.parseInt(in.readLine());
			str +=num1+"-";
			System.out.println("Enter 2nd Number\n");
			num2 =Integer.parseInt(in.readLine());
			str +=num2+"-";
			break;
			
		case 2:
			str += choice+"-";
			System.out.println("Enter 1st Number");
			num1 =Integer.parseInt(in.readLine());
			str +=num1+"-";
			System.out.println("Enter 2nd Number");
			num2 =Integer.parseInt(in.readLine());
			str +=num2+"-";
			break;
			
		case 3:
			str += choice+"-";
			System.out.println("Enter 1st Number");
			num1 =Integer.parseInt(in.readLine());
			str +=num1+"-";
			System.out.println("Enter 2nd Number");
			num2 =Integer.parseInt(in.readLine());
			str +=num2+"-";
			break;
			
		case 4:
			str += choice+"-";
			System.out.println("Enter 1st Number");
			num1 =Integer.parseInt(in.readLine());
			str +=num1+"-";
			System.out.println("Enter 2nd Number");
			num2 =Integer.parseInt(in.readLine());
			str +=num2+"-";
			break;
			
		case 5:
			System.out.println("Program Exited!");
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}
		System.out.println(str);
		dout.writeUTF(str);
		dout.flush();
		
		String result=din.readUTF();
		System.out.println("Result is"+result);
		
		din.close();
		dout.close();
		socket.close();
	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CalcClient cc=new CalcClient(5000);
			cc.sndReq();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

}