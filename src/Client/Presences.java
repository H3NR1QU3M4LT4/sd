package Client;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public class Presences extends UnicastRemoteObject implements PresencesInterface {
	
	private Hashtable<Aluno, AlunoInfo> presentIPs = new Hashtable<Aluno, AlunoInfo>();
	
	public Presences() throws RemoteException {
		super();
	}

	public Vector<Aluno> getContacts(Aluno IPAddress, NewPresencesInterface cl) throws RemoteException {
		
		long actualTime = new Date().getTime();
		
		synchronized(this) {
			if (presentIPs.containsKey(IPAddress)) {
				AlunoInfo newIp = presentIPs.get(IPAddress);
				newIp.setLastSeen(actualTime);
				newIp.setCl(cl);
				newIp.setAluno(IPAddress);
				newIp.setIP(IPAddress.getIP());
			}
			else {
				AlunoInfo newIP = new AlunoInfo(IPAddress.getIP(), actualTime, cl, IPAddress);
				presentIPs.put(IPAddress,newIP);
			}
		}
		for (Enumeration<AlunoInfo> e = presentIPs.elements(); e.hasMoreElements(); ) {
			AlunoInfo element = e.nextElement();
			if (element.getAluno() != IPAddress) {
				try {
				  element.getCl().setNewPresence(IPAddress);
				} catch( RemoteException exception) {
		  			System.out.println("Client "+ element.getIP() + " not available");
		  			synchronized(this) {
		  				presentIPs.remove(element.getAluno());
		  			}
		  		}
			}
		}
		return getIPList();
	}
	
	private Vector<Aluno> getIPList(){
		Vector<Aluno> result = new Vector<Aluno>();
		for (Enumeration<AlunoInfo> e = presentIPs.elements(); e.hasMoreElements(); ) {
			AlunoInfo element = e.nextElement();
			if (!element.timeOutPassed(180*1000)) {
				result.add(element.getAluno());
			}
		}
		return result;
	}
}

class AlunoInfo {
	
	private String ip;
	private long lastSeen;
	private NewPresencesInterface cl;
	private Aluno aluno;

	public AlunoInfo(String ip, long lastSeen, NewPresencesInterface cl, Aluno al) {
		this.ip = ip;
		this.lastSeen = lastSeen;
		this.cl = cl;
		this.aluno = al;
	}

	public void setAluno(Aluno al) {
		this.aluno = al;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public String getIP () {
		return this.ip;
	}

    public NewPresencesInterface getCl () {
		return this.cl;
	}

	public void setLastSeen(long time){
		this.lastSeen = time;
	}

	public void setCl(NewPresencesInterface cl){
		this.cl = cl;
	}

	public void setIP(String ip){
		this.ip = ip;
	}

	public boolean timeOutPassed(int timeout){
		boolean result = false;
		long timePassedSinceLastSeen = new Date().getTime() - this.lastSeen;
		if (timePassedSinceLastSeen >= timeout)
			result = true;
		return result;
	}
}



