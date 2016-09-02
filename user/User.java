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
	private List<Notification> notifications = new ArrayList<Notification>();
	
	private boolean isPrivateProfile = false;

	public User(String userName, String password, String email) throws ProfileException {
		this.profile = new Profile(userName, password, email);
		this.followers = new TreeSet<User>();
		this.followed = new TreeSet<User>();
		this.myPublications = new ArrayList<>();
	}

	@Override
	public void post(Publication publication) {
		try{
			addToMyPublication(publication);
		}catch(UserException e){
			System.err.println(e.getMessage());
		}
	}


	@Override
	public void comment(String content, Publication publication) {
		Comment comment = new Comment(content);
		comment.setByWhom(this);
		publication.addComment(comment);
		publication.getCreatorOfThePublication().sendNotification("komentira vasha publikaciq");
	}
	
	@Override
	public void follow(User user)  {
		if (user != null && user != this){
			if(!this.followed.contains(user)){
				this.followed.add(user);
				user.sendNotification("vi posledva");
			}
			try{
				user.addFollower(this);
			}catch(UserException e){
				System.out.println(e.getMessage());
			}
		}else{
			System.err.println("Something went wrong. Please try again later!");
		}	
	}

	@Override
	public void unfollow(User user) {
		if (user != null && user != this){
			if(this.followed.contains(user)){
				followed.remove(user);
				user.sendNotification("Veche ne vi sledva");
			}
			if(user.followers.contains(this)){
				user.followers.remove(this);
			}
		}else{
			throw new InvalidParameterException();
		}
	}
	
	@Override
	public void likeAPublication(Publication publication) {
		if (publication != null) {
			if(!publication.getUsersWhoLikesThatPublication().contains(this)){
				try {
					publication.addUserToUsersWhoLikesThatPublication(this);
					publication.getCreatorOfThePublication().sendNotification("Haresa vasha publikaciq");
				} catch (PublicationException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}else{
				System.err.println("Veche ste haresali tazi publikaciq");
			}
		}else{
			System.err.println("Invalid user");
		}
	}
	
	@Override
	public void reportUser(User user, ReportUserReasons reason) {
		if(user != null){
			user.addReport(reason);
		}else{
			System.err.println("Potrebitelq koito iskate da reportnete e nevaliden");
		}
	}
	
	@Override
	public void reportPublication(Publication pub,ReportPublicationReasons reason){
		if(pub != null){
			pub.addReport(reason);
		}else{
			System.err.println("Publikaciqta koqto iskate da reportnete e nevalidna");
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
	
	
	
	private void sendNotification(String notificationText) {
		if(notificationText != null){
			this.notifications.add(new Notification(this.getUserName() + " " + notificationText));
		}
	}
	
	private void addToMyPublication(Publication publication) throws UserException{
		if(publication != null){
			myPublications.add(publication);
		}else{
			throw new UserException("Invalid publication");
		}
	}

	private void addFollower(User user) throws UserException{
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

//	private Set<User> getFollowed() {
//		return followed;
//	}
//
//	private List<Publication> getMyPublications() {
//		return myPublications;
//	}
//
//	private Set<User> getFollowers() {
//		return followers;
//	}
//
//	private Homepage getHomepage() {
//		return homepage;
//	}
//
//	private void setHomepage(Homepage homepage) {
//		if (homepage != null)
//			this.homepage = homepage;
//	}
	
	public void viewNotifications(){
		for(Notification notification : notifications){
			System.out.println(notification);
			notification.setSeen(true);
		}
	}
	
	public void viewUnreadNotifications(){
		for(Notification notification : notifications){
			if(!notification.isSeen()){
				System.out.println(notification);
				notification.setSeen(true);
			}
		}
	}
	
	public void viewFollowers(){
		for(User follower : this.followers){
			System.out.println(follower.getUserName() + '\n');
		}
	}
	
	public void viewFollowed(){
		for(User followed : this.followed){
			System.out.println(followed.getUserName() + '\n');
		}
	}
	
	public void viewMyPublications(){
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

	public void changeNameAndSurname(final String name) {
		try{
			this.profile.setNameAndSurname(name);
		}catch(ProfileException e){
			System.err.println(e.getMessage());
		}
		
	}
	
	public void changePhoneNumber(final String phone){
		try{
			this.profile.setPhoneNumber(phone);
		}catch(ProfileException e){
			System.err.println(e.getMessage());
		}
	}

	public void changeWebSite(final String string) {
		try{
			this.profile.setWebsite("www.abv.bg");
		}catch(ProfileException e){
			System.out.println(e.getMessage());
		}
	}
	
	private String getUserName(){
		return this.profile.getUserName();
	}
	
}