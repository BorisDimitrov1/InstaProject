package user;

import java.io.File;

import photo.Photo;
import photo.PhotoException;
import publication.Publication;
import publication.PublicationException;
import publication.ReportPublicationReasons;

public class Demo {

	public static void main(String[] args) {
		
			 
			
			Photo photo1 = null;
			Publication gotinSumPub = null;
			User user = null;
			User second = null;
			try{
				user = new User("aba", "pasSword1", "aba@abv.bg");
				second = new User("dada", "paRola2", "dasd@abv.bg");
				photo1 = new Photo(new File("C:\\Users\\Boris\\Desktop\\4177422b.jpg"));
				gotinSumPub = new Publication("Gotin sum", photo1,user);
			}catch(PhotoException | PublicationException | ProfileException e){
				System.err.println(e.getMessage());
			}
			
			user.changeNameAndSurname("Boris Dimitrov");
			user.changePhoneNumber("0884541478");
			System.out.println(user);
			
			System.out.println(photo1);
			
			System.out.println(gotinSumPub);

			user.post(gotinSumPub);
			user.viewMyPublications();
			user.changeWebSite("www.abv.bg");

			System.out.println(second);
			second.likeAPublication(gotinSumPub);
			
			second.comment("na maika ti..fs", gotinSumPub);
			gotinSumPub.viewAllComments();
				
			second.follow(user);
			second.follow(user);
			second.viewFollowed();
			second.viewFollowers();
			user.viewFollowed();
			user.viewFollowers();

			second.unfollow(user);

			second.reportPublication(gotinSumPub,ReportPublicationReasons.TAZI_SNIMKA_E_SPAM_ILI_IZMAMA);
			gotinSumPub.viewReports();
			
			second.reportUser(user,ReportUserReasons.HACKED_ACCOUNT);
			user.viewReports();
			
			user.viewUnreadNotifications();
			second.viewNotifications();
	}
}
