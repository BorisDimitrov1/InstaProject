package user;

import java.io.File;

import photo.Photo;
import photo.PhotoException;
import publication.Publication;
import publication.PublicationException;
import publication.ReportPublicationReasons;

public class Demo {

	public static void main(String[] args) {
		
		try{
			User user = new User("aba", "pasSword1", "aba@abv.bg");
			user.setNameAndSurname("Boris Dimitrov");
			user.setPhoneNumber("0884541478");
			System.out.println(user);
			Photo photo1 = new Photo(new File("C:\\Users\\Boris\\Desktop\\4177422b.jpg"));
			System.out.println(photo1);
			Publication gotinSumPub = new Publication("Gotin sum", photo1,user);
			System.out.println(gotinSumPub);

			user.post(gotinSumPub);
			user.viewPublications();
			user.getProfile().setWebsite("www.abv.bg");

			User second = new User("dada", "paRola2", "dasd@abv.bg");
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
			
		}catch(UserException | PublicationException | ProfileException | PhotoException ex){
			System.out.println("CATCH" + ex.getMessage());
		}
		
		
	}
}
