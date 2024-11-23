import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcOperation extends UnicastRemoteObject implements adder {
	private static final long serialVersionUID = 1L;
	
	CalcOperation() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public int getAddition(int n1, int n2) throws RemoteException {
		return n1 + n2;
	}
	
	public int getSubstraction(int n1, int n2) throws RemoteException {
		return n1 - n2;
	}
}
