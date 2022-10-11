package Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class NewPresences extends UnicastRemoteObject implements NewPresencesInterface {

	public NewPresences() throws RemoteException {
		super();
	}
	
	public void setNewPresence(Aluno al) throws RemoteException {
		System.out.println(al);
	}

}

