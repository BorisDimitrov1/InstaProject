package user;

public interface IUser {

	void post(Publication publication) throws UserException;

	void comment(String content, Publication publication);

	void follow(User user) throws UserException;

	void unfollow(User user);

	void likeAPublication(Publication publication) throws UserException;

	void setPrivateAccountPublic();

	void setPublicAccountPrivate();
	
	void reportPublication(Publication pub,ReportPublicationReasons reason);

	void logout();
}
