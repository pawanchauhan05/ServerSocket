package server;

import java.util.*;
import java.net.*;

import models.ChatMessage;

public class ChatServer {
	List<String> users = new ArrayList<String>();
	List<HandleClient> clients = new ArrayList<HandleClient>();

	public void process() throws Exception {
		ServerSocket server = new ServerSocket(9999, 10);
		System.out.println("Server Started...");
		while (true) {
			Socket client = server.accept();
			HandleClient c = new HandleClient(client, this);
			clients.add(c);
			users.add(c.getUserName());
			for (HandleClient handleClient : clients) {
				handleClient.updateFriends(users, null);
			}
		}
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public List<HandleClient> getClients() {
		return clients;
	}

	public void setClients(List<HandleClient> clients) {
		this.clients = clients;
	}

	public static void main(String... args) throws Exception {
		new ChatServer().process();	
	}
	
	public void sendMessage(ChatMessage chatMessage) {
		for (HandleClient handleClient : clients) {
			if (handleClient.getUserName().equals(chatMessage.getUser())) {
				handleClient.updateFriends(users, chatMessage);
			}
		}
	}

}

