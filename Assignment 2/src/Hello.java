import java.rmi.Remote; 
import java.rmi.RemoteException;

// Creating Remote interface for our application 
public interface Hello extends Remote {  
    String printDate() throws RemoteException;  
    String calculateRoots(int[] coefficients) throws RemoteException;
    String flipString(String userInput) throws RemoteException;
    String decimalBaseConverter(int num) throws RemoteException;
    String checkEquation(String equation) throws RemoteException;
} 