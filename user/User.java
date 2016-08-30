package user;

import java.security.InvalidParameterException;
import java.util.*;

import comment.Comment;
import publication.Publication;
import publication.PublicationException;
import publication.ReportPublicationReasons;

public class User implements IUser,Comparable<User> {

	private final Profile profile;
	private Homepage homepage;
	private Set<User> followers;
	private Set<User> followed;
	private List<Publication> myPublications;
	private List<ReportUserReasons> reports = new ArrayList<ReportUserReasons>();
	
	private boolean isPrivateProfile = false;

	public User(String userName, String password, String email) {
		this.profile = new Profile(userName, password, email);
		this.followers = new TreeSet<User>();
		this.followed = new TreeSet<User>();
		this.myPublications = new ArrayList<>();
	}

	@Override
	public void post(Publication publication) throws UserException {
		if(publication != null){
			myPublications.add(publication);
		}else{
			throw new UserException("Invalid publication");
		}
	}


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
	public void reportUser(User user, ReportUserReasons reason) throws UserException {
		if(user != null){
			user.addReport(reason);
		}else{
			throw new UserException("Potrebitelq koito iskate da reportnete e nevaliden");
		}
	}
	
	@Override
	public void reportPublication(Publication pub,ReportPublicationReasons reason) throws UserException{
		if(pub != null){
			pub.addReport(reason);
		}else{
			throw new UserException("Publikaciqta koqto iskate da reportnete e nevalidna");
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
	public void logout() {
		//TODO:
	}
	
	// ----------------------------------------------------------------------------
	public Profile getProfile() {
		return profile;
	}

	Set<User> getFollowed() {
		return followed;
	}

	List<Publication> getMyPublications() {
		return myPublications;
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
	
	public void viewPublications(){
		for(Publication p : this.myPublications){
			System.out.println(p);
		}
	}

	private void addReport(ReportUserReasons reason) {
		this.reports.add(reason);
	}
	
	protected void viewReports(){
		System.out.println("Broi reporti: " + reports.size());
		for(ReportUserReasons report : reports){
			System.out.println(report.name());
		}
	}
	
	@Override
	public int compareTo(User o) {
		return this.getProfile().getUserName().compareTo(o.getProfile().getUserName());
	}

	public void setNameAndSurname(final String name) throws ProfileException {
		this.profile.setNameAndSurname(name);
	}
	
	public void setPhoneNumber(final String phone) throws ProfileException{
		this.profile.setPhoneNumber(phone);
	}
	
}