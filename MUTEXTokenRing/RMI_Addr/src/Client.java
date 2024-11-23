import java.rmi.Naming;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Client Program Started");
			adder stub = (adder)Naming.lookup("rmi://localhost:5000/CalcOpService");
			System.out.println("Enter Value A :");
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
			System.out.println("Enter Value B :");
			int b = sc.nextInt();
			System.out.println("Addition : "+ stub.getAddition(a,b));
			System.out.println("Substraction : "+ stub.getSubstraction(a,b));
			sc.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
