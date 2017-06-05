package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import models.ChatMessage;
import models.Users;

import com.google.gson.Gson;

public class HandleClient extends Thread {
	String name = "";
	BufferedReader bufferedReader;
	PrintWriter printWriter;
	Socket client;
	ChatServer chatServer;

	public HandleClient(Socket socket, ChatServer chatServer) {
		try {
			this.client = socket;
			this.bufferedReader = new BufferedReader( new InputStreamReader(client.getInputStream())) ;
			this.printWriter = new PrintWriter (client.getOutputStream(),true);
			this.name = bufferedReader.readLine();
			this.chatServer = chatServer;
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUserName() {
		return name;
	}
	
	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public void updateFriends(List<String> users, ChatMessage chatMessage) {
		List<String> strings = new ArrayList<String>();
		strings.addAll(users);
		strings.remove(name);
		System.out.println(strings.toString() + " update Friends");
		Users usersList = new Users(strings, chatMessage);
		printWriter.println(new Gson().toJson(usersList));
	}
	
	public void run() {
		String line;
		try {
			while (true) {
				line = bufferedReader.readLine();
				if (line.equals("end")) {
					chatServer.getClients().remove(this);
					chatServer.getUsers().remove(name);
					for(HandleClient handleClient :chatServer.getClients()) {
						handleClient.updateFriends(chatServer.getUsers(), null);
					}
					System.out.println(chatServer.getUsers().toString());
					break;
				}
				if(line != null) {
					ChatMessage chatMessage = new Gson().fromJson(line, ChatMessage.class);
					if(chatMessage != null) {
						chatServer.sendMessage(chatMessage);
					}
					System.out.println(chatMessage.toString());
				}
			} 
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HandleClient other = (HandleClient) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	

}

