package user;

public class Notification {

	private String notificationText;
	private boolean isSeen;
	
	public Notification(String text){
		try{
			setNotificationText(text);
		}catch(NotificationException e){
			System.err.println(e.getMessage());
		}
	}
	
	public String getNotificationText(){
		return this.notificationText;
	}
	
	private void setNotificationText(String text) throws NotificationException{
		if(text != null && text.trim().length() > 0){
			this.notificationText = text;
		}else{
			throw new NotificationException("Navaliden text pri suzdavane na notification");
		}
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	
	@Override
	public String toString() {
		return this.notificationText;
	}
}
