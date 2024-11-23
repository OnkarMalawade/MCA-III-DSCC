import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class DBServiceClient {

	public DBServiceClient() {
		// TODO Auto-generated constructor stub
		super();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "", ch = "";
		try {
			StudDBInf stub = (StudDBInf) Naming.lookup("rmi://localhost:1900/ROCforStudDB");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println("Select an Option");
				System.out.println("1. Retrieve Student Info : ");
				System.out.println("2. Insert Student Info : ");
				System.out.println("3. Exit ");
				System.out.println("Enter Your Choice : ");
				ch = br.readLine();
				
				if(ch.equals("1")) {
					sql = "select * from student";
					sql = stub.getData(sql);
				}
				else if(ch.equals("2")) {
					sql = "insert into student(rollno, studnm) values (101, 'Onkar')";
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
