import java.rmi.Naming;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Client Program Started");
			adder stub = (adder)Naming.lookup("rmi://localhost:5000/CalcOpService");
			System.out.println("Addition : "+ stub.getAddition(34,3));
			System.out.println("Substraction : "+ stub.getSubstraction(34,3));
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
