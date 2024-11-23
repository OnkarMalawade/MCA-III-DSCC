import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BKSrv {

	public BKSrv() {
		super();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BKInf skeleton = new BKOps();
			LocateRegistry.createRegistry(1900);
			Naming.rebind("rmi://localhost:1900/ROCforBook", skeleton);
			System.out.println("Server Registered");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
