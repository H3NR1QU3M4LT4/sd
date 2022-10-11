package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NewPresencesInterface extends Remote {

	public void setNewPresence(Aluno al) throws RemoteException;

}