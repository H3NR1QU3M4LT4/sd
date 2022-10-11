package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface PresencesInterface extends Remote {

	public Vector<Aluno> getContacts(Aluno al, NewPresencesInterface cl) throws RemoteException;

}
