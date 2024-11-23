import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
public class BillClient {
	public BillClient() {
		super();
	}
	public static void main(String[] args) {
		String sql = "", ch = "";
		try {
			BillInf stub = (BillInf) Naming.lookup("rmi://localhost:1900/ROCforBill");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				System.out.println("Select an Option");
				System.out.println("1. Retrieve Bill Info : ");
				System.out.println("2. Insert Bill Info : ");
				System.out.println("3. Exit ");
				System.out.println("Enter Your Choice : ");
				ch = br.readLine();
				if(ch.equals("1")) {
					sql = "select * from billData";
					sql = stub.getData(sql);
				}
				else if(ch.equals("2")) {
					sql = "insert into billData(consumeNm, billDt, billAmt) values ('Onkar', '10-11-2024', 500)";
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
			e.printStackTrace();
		}
	}
}
