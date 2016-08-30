package user;

import publication.Publication;
import publication.ReportPublicationReasons;

public interface IUser {

	void post(Publication publication) throws UserException;

	void comment(String content, Publication publication);

	void follow(User user) throws UserException;

	void unfollow(User user);

	void likeAPublication(Publication publication) throws UserException;

	void setPrivateAccountPublic();

	void setPublicAccountPrivate();
	
	void reportPublication(Publication pub,ReportPublicationReasons reason) throws UserException;
	
	void reportUser(User user, ReportUserReasons reason) throws UserException;

	void logout();
	
	
}
