package user;

import java.time.LocalDateTime;

public class Comment {
	private User byWhom;
	private String content;
	private LocalDateTime creationOfTheComment;

	public Comment(String content) {
		setContent(content);
		creationOfTheComment = LocalDateTime.now();
	}

	public void setByWhom(User user) {
		if (user != null)
			this.byWhom = user;
	}

	@Override
	public String toString() {
		return "Comment " + "From: " + byWhom.getProfile().getUserName() + " \ncontent: " + content + " \nDateAndTime(Comment): " + creationOfTheComment;
	}

	public User getByWhom() {
		return byWhom;
	}

	public void setContent(String content) {
		if (content != null && content.trim().length() > 0)
			this.content = content;
	}

	public String getContent() {
		return content;
	}
	
	
}
