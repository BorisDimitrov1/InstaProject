package user;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Demo {

	public static void main(String[] args) throws UserException, PublicationException, ProfileException {
		
		User user = new User("aba", "pasSword1", "aba@abv.bg");
		Photo photo1 = new Photo(new File("C:\\Users\\Boris\\Desktop\\4177422b.jpg"));
		Publication gotinSumPub = new Publication("Gotin sum", photo1,user);

		user.post(gotinSumPub);
		user.getProfile().setWebsite("www.abv.bg");

		User second = new User("dada", "paRola2", "dasd@abv.bg");
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
		
	}
}
