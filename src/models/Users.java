package models;

import java.util.ArrayList;
import java.util.List;

public class Users {
	private List<String> users = new ArrayList<String>();
	private ChatMessage chatMessage;
	
	public Users(List<String> users, ChatMessage chatMessage) {
		super();
		this.users = users;
		this.chatMessage = chatMessage;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
	

	public ChatMessage getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(ChatMessage chatMessage) {
		this.chatMessage = chatMessage;
	}

	@Override
	public String toString() {
		return "Users [users=" + users + "]";
	}
	
}

