package user;

import java.security.InvalidParameterException;
import java.util.*;

public class User implements IUser,Comparable<User> {

	private final Profile profile;
	private Homepage homepage;
	private Set<User> followers;
	private Set<User> followed;
	private List<Publication> myPublications;
	
	private boolean isPrivateProfile = false;

	public User(String userName, String password, String email) {
		this.profile = new Profile(userName, password, email);
		this.followers = new TreeSet<User>();
		this.followed = new TreeSet<User>();
		this.myPublications = new ArrayList<>();
	}

	/***
	 * Prints out hearts/ot likes/, time, user's comment and first comments on
	 * the screen
	 */
	void showPublication() {
		for (Publication publication : myPublications) {
			System.out.println(profile.getUserName());
			System.out.println(publication.getNumberOfLikes());
			System.out.println(publication.getDate());
			System.out.println(publication.getUserComment());
			for (Comment comment : publication.getComments())
				System.out.println(comment);
		}
	}

	@Override
	public void post(Publication publication) throws UserException {
		if(publication != null){
			myPublications.add(publication);
		}else{
			throw new UserException("Invalid publication");
		}
	}
	
	public void reportPublication(Publication pub,ReportPublicationReasons reason){
		pub.increaseReports();
	}

	public Publication getPublication() {
		return myPublications.get(myPublications.size() - 1);
	}
	// @Override
	// public void post(String text, Photo photo) {
	// Publication post = new Publication(text);
	// publications.set
	// post.setCreatorOfThePublication(this);
	// post.setPhoto(photo);
	// }

	@Override
	public void comment(String content, Publication publication) {
		Comment comment = new Comment(content);
		comment.setByWhom(this);
		publication.addComment(comment);
	}

	@Override
	public void unfollow(User user) {
		if (user != null && user != this){
			if(this.followed.contains(user)){
				followed.remove(user);
			}
			if(user.followers.contains(this)){
				user.followers.remove(this);
			}
		}else{
			throw new InvalidParameterException();
		}
	}

	@Override
	public void likeAPublication(Publication publication) throws UserException {
		if (publication != null) {
			if(!publication.getUsersWhoLikesThatPublication().contains(this)){
				try {
					publication.addUserToUsersWhoLikesThatPublication(this);
				} catch (PublicationException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}else{
				System.err.println("Veche ste haresali tazi publikaciq");
			}
		}else{
			throw new UserException("Invalid user");
		}
	}

	@Override
	public void setPrivateAccountPublic() {
		if(isPrivateProfile)
			isPrivateProfile = false;
	}

	@Override
	public void setPublicAccountPrivate() {
		if(!isPrivateProfile)
			isPrivateProfile = true;
	}

	@Override
	public void logout() {

	}
	
	public void addFollower(User user) throws UserException{
		if(user != null){
			if(!this.followers.contains(user)){
				this.followers.add(user);
			}else{
				System.err.println("Tozi User veche e v spisuka vi s posledvani");
			}
		}else{
			throw new UserException("Invalid user(null)");
		}
	}

	@Override
	public void follow(User user) throws UserException {
		if (user != null && user != this){
			if(!this.followed.contains(user)){
				this.followed.add(user);
			}
			user.addFollower(this);
		}else{
			System.err.println("Something went wrong. Please try again later!");
		}
			
	}

	// ----------------------------------------------------------------------------
	public Profile getProfile() {
		return profile;
	}
	
//	private void setFollowers(List<User> followers) {
//		if (followers != null)
//			this.followers = followers;
//	}

	Set<User> getFollowed() {
		return followed;
	}

//	private void setFollowed(List<User> followed) {
//		if (followed != null)
//			this.followed = followed;
//	}

	// Publication getLastPublication(){
	// if(!this.publications.isEmpty()){
	// return this.publications.
	// }
	// }
//	@Override
//	public boolean equals(Object obj) {
//		if(obj != null && obj instanceof User){
//			if(this.profile.getUserName().compareTo((((User)obj).getProfile().getUserName() == 0){
//				
//			}
//			return this.profile.getUserName().compareTo(((User)obj).getProfile().getUserName())
//		}
//	}
	
	

	List<Publication> getMyPublications() {
		return myPublications;
	}

	private void setPublications(List<Publication> publications) {
		if (publications != null)
			this.myPublications = publications;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public Homepage getHomepage() {
		return homepage;
	}

	public void setHomepage(Homepage homepage) {
		if (homepage != null)
			this.homepage = homepage;
	}

	@Override
	public int compareTo(User o) {
		return this.getProfile().getUserName().compareTo(o.getProfile().getUserName());
	}
	
	public void viewFollowers(){
		for(User follower : this.followers){
			System.out.println(follower.getProfile().getUserName() + '\n');
		}
	}
	
	public void viewFollowed(){
		for(User followed : this.followed){
			System.out.println(followed.getProfile().getUserName() + '\n');
		}
	}
	
}