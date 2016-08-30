package user;

public interface IAnonymous {
	void register(String username, String password, String email);

	void login(String username, String password);
}
