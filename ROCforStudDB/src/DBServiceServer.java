import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class DBServiceServer {
	
	public DBServiceServer() {
		// TODO Auto-generated constructor stub
		super();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			StudDBInf skeleton = new StudDBOperation();
			LocateRegistry.createRegistry(1900);
			Naming.rebind("rmi://localhost:1900/ROCforStudDB", skeleton);
			System.out.println("Server Registered");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
