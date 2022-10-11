package Client;

import Client.PresencesServer;

public class ServerApp {
	
	public static void main(String[] args) {
		
		PresencesServer ps = new PresencesServer();
		ps.createPresences();
	}
}