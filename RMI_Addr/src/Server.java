import java.rmi.*;
import java.rmi.registry.LocateRegistry;
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Calculator Service Server Started!");
			adder stub = new CalcOperation();
			System.out.println("Calculator Service Binding...");
			LocateRegistry.createRegistry(5000);
			Naming.rebind("rmi://localhost:5000/CalcOpService", stub);
			System.out.println("Calculator Service is register is Registery");
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
