import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BillSrv {

	public BillSrv() {
		super();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BillInf skeleton = new BillOps();
			LocateRegistry.createRegistry(1900);
			Naming.rebind("rmi://localhost:1900/ROCforBill", skeleton);
			System.out.println("Server Registered");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
