package server;

import java.util.ArrayList;
import java.util.List;

public class Clients {
	private List<HandleClient> clients = new ArrayList<HandleClient>();

	public Clients(List<HandleClient> clients) {
		super();
		this.clients = clients;
	}

	public List<HandleClient> getClients() {
		return clients;
	}

	public void setClients(List<HandleClient> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Clients [clients=" + clients + "]";
	}
	
	

}
