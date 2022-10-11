package Client;

import java.rmi.registry.LocateRegistry;
import java.util.Iterator;
import java.util.Vector;

public class Client implements java.io.Serializable {
	
	String SERVICE_NAME="/PresencesRemote";
	String[] args;

	public Client()  {
		
	}

	public void putPresence() {
		/*
		if (args.length != 2) {
			System.out.println("Erro: use java ClientApp <ipClient> <ipNameServer>");
			System.exit(-1);
		}*/

		try {

			NewPresencesInterface cl = new NewPresences();
			Aluno al = new Aluno("127.0.0.6", "Silvino", "@gmail.com", "MIEGSI", 3306);

			PresencesInterface presences = (PresencesInterface) LocateRegistry.getRegistry("127.0.0.1").lookup(SERVICE_NAME);
			
			Vector<Aluno> presencesList = presences.getContacts(al, cl);
			
			Iterator<Aluno> it = presencesList.iterator();
			while(it.hasNext())
				System.out.println( it.next());
		} catch (Exception e) {
			System.err.println("Error");
			e.printStackTrace();
		}
	}    
}

