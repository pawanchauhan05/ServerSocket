package models;

public class ChatMessage {
	private String msg;
	private String user;
	private boolean isRight;
	
	public ChatMessage(String msg, String user, boolean isRight) {
		super();
		this.msg = msg;
		this.user = user;
		this.isRight = isRight;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ChatMessage [msg=" + msg + ", user=" + user + "]";
	}
	
	
	
	
}

