import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BKInf extends Remote{
	public String getData(String strQry) throws RemoteException;
	public String insertData(String strQry) throws RemoteException;
}
