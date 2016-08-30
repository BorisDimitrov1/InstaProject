package comment;

import java.time.LocalDateTime;

import user.User;

public class Comment {
	private User byWhom;
	private String content;
	private LocalDateTime creationOfTheComment;

	public Comment(String content) {
		setContent(content);
		this.creationOfTheComment = LocalDateTime.now();
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

	private void setContent(String content) {
		if (content != null && content.trim().length() > 0)
			this.content = content;
	}

	public String getContent() {
		return content;
	}
	
	
}
