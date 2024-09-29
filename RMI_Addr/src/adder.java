import java.rmi.Remote;
import java.rmi.RemoteException;

public interface adder extends Remote{
	public int getAddition(int n1, int n2) throws RemoteException;
	public int getSubstraction(int n1, int n2) throws RemoteException;
}
