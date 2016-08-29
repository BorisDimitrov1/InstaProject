package user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Publication {
	private User creatorOfThePublication;
	private List<Comment> comments;
	private Set<User> usersWhoLikesThatPublication;
	private final LocalDateTime date;
	private String textToThePost;
	private int numberOfReports;
	private Photo photo;
	
	private int dislikes;

	/***
	 * Originally every post is not liked. Serves as flag.
	 */
	private boolean liked = false;

	public Publication(final String textToThePost,final Photo photo,final User creator) throws PublicationException {
		this.setTextToThePost(textToThePost);
		this.setCreatorOfThePublication(creator);
		setPhoto(photo);
		comments = new ArrayList<>();
		usersWhoLikesThatPublication = new TreeSet<User>();
		date = LocalDateTime.now();
		
	}

	private void setTextToThePost(final String textToThePost) throws PublicationException{
		if(textToThePost != null){
			this.textToThePost = textToThePost;
		}else{
			throw new PublicationException("Invalid Text to the post");
		}
	}
	
	public void setCreatorOfThePublication(User creatorOfThePublication) throws PublicationException {
		if (creatorOfThePublication != null){
			this.creatorOfThePublication = creatorOfThePublication;
		}else{
			throw new PublicationException("Invalid creator of the post");
		}	
	}
	
	public void increaseReports(){
		this.numberOfReports++;
	}
	
	
	void showPublication(){
		//TODO:
	}
	
	int getNumberOfLikes(){
		return usersWhoLikesThatPublication.size();
	}
	
//	void setPhoto(Photo photo){
//		if(photo != null)
//			this.photo = photo;
//	}

	void addComment(Comment newComment) {
		if (newComment != null && newComment.getByWhom() != null && newComment.getContent().trim().length() > 0)
			comments.add(newComment);
	}

//	void addHeart() {
//		hearts++;
//	}
	public Set<User> getUsersWhoLikesThatPublication() {
		return usersWhoLikesThatPublication;
	}
	
	void addUserToUsersWhoLikesThatPublication(User user) throws PublicationException{
		if(user != null){
			usersWhoLikesThatPublication.add(user);
		}else{
			throw new PublicationException("Invalid user");
		}
	}

	void viewAllComments() {
		for (Comment comment : comments)
			System.out.println(comment);
	}
	
	@Override
	public String toString() {
		return "Creator: " + creatorOfThePublication.getProfile().getUserName() +  "\nText: " + textToThePost + "\nDate:" + date;
	}

	List<Comment> getComments() {
		return comments;
	}

//	int getHearts() {
//		return hearts;
//	}

	String getUserComment() {
		return textToThePost;
	}

	LocalDateTime getDate() {
		return date;
	}

	

	void setPhoto(Photo photo) {
		if (photo != null )
			this.photo = photo;
	}

	void setLiked(boolean liked) {
		this.liked = liked;
	}

	 boolean getLiked() {
		return liked;
	}
}
