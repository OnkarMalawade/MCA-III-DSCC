import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class BKClient {

	public BKClient() {
		super();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "", ch = "";
		try {
			BKInf stub = (BKInf) Naming.lookup("rmi://localhost:1900/ROCforBook");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println("Select an Option");
				System.out.println("1. Retrieve Book Info : ");
				System.out.println("2. Insert Book Info : ");
				System.out.println("3. Exit ");
				System.out.println("Enter Your Choice : ");
				ch = br.readLine();
				
				if(ch.equals("1")) {
					sql = "select * from libData";
					sql = stub.getData(sql);
				}
				else if(ch.equals("2")) {
					sql = "insert into libData(bookId, bookName, bookAuthor) values (101, 'The Alamack N R', 'Tim Drewes')";
					sql = stub.insertData(sql);
				}
				else if(ch.equals("3")) {
					System.exit(0);
				}
				else {
					sql = "Please enter valid Input";
				}
				System.out.println(sql);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
