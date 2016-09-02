package publication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import comment.Comment;
import photo.Photo;
import user.User;

public class Publication {
	private User creatorOfThePublication;
	private List<Comment> comments;
	private Set<User> usersWhoLikesThatPublication;
	private final LocalDateTime dateAndTimeOfCreation;
	private String textToThePost;
	private List<ReportPublicationReasons> reports = new ArrayList<ReportPublicationReasons>(); 
	private Photo photo;


	public Publication(final String textToThePost,final Photo photo,final User creator) throws PublicationException {
		this.setTextToThePost(textToThePost);
		this.setCreatorOfThePublication(creator);
		setPhoto(photo);
		comments = new ArrayList<>();
		usersWhoLikesThatPublication = new TreeSet<User>();
		dateAndTimeOfCreation = LocalDateTime.now();
	}

	private void setTextToThePost(final String textToThePost) throws PublicationException{
		if(textToThePost != null){
			this.textToThePost = textToThePost;
		}else{
			throw new PublicationException("Invalid Text to the post");
		}
	}
	
	private void setCreatorOfThePublication(User creatorOfThePublication) throws PublicationException {
		if (creatorOfThePublication != null){
			this.creatorOfThePublication = creatorOfThePublication;
		}else{
			throw new PublicationException("Invalid creator of the post");
		}	
	}
	
	void showPublication(){
		//TODO:
	}
	
	int getNumberOfLikes(){
		return usersWhoLikesThatPublication.size();
	}

	public void addComment(Comment newComment) {
		if (newComment != null && newComment.getByWhom() != null && newComment.getContent().trim().length() > 0)
			comments.add(newComment);
	}


	public Set<User> getUsersWhoLikesThatPublication() {
		return usersWhoLikesThatPublication;
	}
	
	public void addUserToUsersWhoLikesThatPublication(User user) throws PublicationException{
		if(user != null){
			usersWhoLikesThatPublication.add(user);
		}else{
			throw new PublicationException("Invalid user");
		}
	}

	public void viewAllComments() {
		for (Comment comment : comments)
			System.out.println(comment);
	}
	
	@Override
	public String toString() {
		return "Creator: " + creatorOfThePublication.getProfile().getUserName() +  "\nText: " + textToThePost + "\nDate:" + dateAndTimeOfCreation;
	}

	List<Comment> getComments() {
		return comments;
	}

	String getUserComment() {
		return textToThePost;
	}
	
	public User getCreatorOfThePublication(){
		return this.creatorOfThePublication;
	}

	LocalDateTime getDate() {
		return dateAndTimeOfCreation;
	}

	void setPhoto(Photo photo) {
		if (photo != null )
			this.photo = photo;
	}
	 
	public void addReport(ReportPublicationReasons reason){
		this.reports.add(reason);
	}
	
	public void viewReports(){
		System.out.println("Broi reporti: " + reports.size());
		for(ReportPublicationReasons report : reports){
			System.out.println(report.name());
		}
	}
}
