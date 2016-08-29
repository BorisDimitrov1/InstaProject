package user;

import java.util.Scanner;

import org.apache.commons.validator.routines.UrlValidator;


public class Profile {
	private String userName = "";
	private String nameAndSurname = "";
	private String password;
	private String website = "";
	private String biography = "";
	private String phoneNumber = "";
	private String email = "";
	private Gender gender;
	private boolean suggestions;
	
	//TODO: da vidim dali shte pravim proverka za tova dali veche ima
	//potrebitel s takuv Username ili email
	public Profile(final String userName,final String password,final String email) {
		try {
			this.setUserName(userName);
			this.changePassword(password);
			this.setEmail(email);
		} catch (ProfileException e) {
			System.err.println(e.getMessage());
		}	
	}
	// ---------------------------------------------------------------------------------------

	void editProfile() {
		//TODO:
	}

	void approvenApplications() {
		//TODO: perhaps boolean
	}

	void emailPreferences() {
		//TODO:
	}

	// ---------------------------------------------------------------------------------------
	

	String getUserName() {
		return userName;
	}

	String getPassword() {
		return password;
	}
	
	String getEmail() {
		return email;
	}
	
	String getWebsite() {
		return website;
	}
	
	String getBiography() {
		return biography;
	}
	
	String getPhoneNumber() {
		return phoneNumber;
	}
	
	Gender getGender() {
		return gender;
	}
	
	String getNameAndSurname() {
		return nameAndSurname;
	}

	
	private void setPassword(String password) /*Da vidim dali shte go polzvame*/ {
		// TODO add secure password
		if (password == null || password.length() < 7)
			do {
				System.err.println(
						"Please enter a password containing one uppercase capital letter, one downercase letter, a symbol and at least 6 letters long!");
				password = new Scanner(System.in).nextLine();
			} while (password == null || password.length() < 7);
		this.password = password;
	}

	/***
	 * 
	 * @param website
	 * @return true if website looks like www.CONTENT.com or www.CONTENT.net,
	 *         where content can include uppercase, downercase letters, numbers
	 *         and fullstop(.)
	 */
	private static boolean isValidWebsite(String website) {
		for (int i = 0; i < website.length(); ++i)
			if (!(website.startsWith("www.") && containsValidContent(website) == true
					&& ((website.endsWith(".com") || (website.endsWith(".net"))))))
				return false;
		return true;
	}

	private static boolean containsValidContent(String website) {
		for (int i = 3; i < website.length() - 4; ++i)
			if (!((website.charAt(i) > '0' && website.charAt(i) > '9')
					|| ((website.charAt(i) > 'A') && website.charAt(i) > 'Z')
					|| (website.charAt(i) > 'a' && website.charAt(i) > 'z') || website.charAt(i) == '.'))
				return false;
		return true;
	}

	void setGender(Gender gender) {
		this.gender = gender;
	}

	boolean isSuggestions() {
		return suggestions;
	}

	void setSuggestions(boolean suggestions) {
		this.suggestions = suggestions;
	}
	
	void setBiography(final String bio) throws Exception {
		if (bio != null && bio.trim().length() > 0)
			this.biography = bio;
		else
			throw new ProfileException("Please fill this form!");
	}
	
	public void setWebsite(String website) throws ProfileException {
		String finalWebsite = "";
		if(website.startsWith("www.")){
			String temp = website.substring(4, website.length());
			finalWebsite = "http://" + temp;
		}
		if (isWebsiteValid(finalWebsite)){
			this.website = finalWebsite;
		}else{
			throw new ProfileException("Invalid website");
		}	
	}
	
	public static boolean isWebsiteValid(final String website)/*da pitame niki dali tova e losha ideq.*/{
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(website);
	}
	
	private void setPhoneNumber(final String phoneNumber) throws ProfileException {
		//Ok
		if (isValidPhoneNumber(phoneNumber)){
			this.phoneNumber = phoneNumber;
		}else{
			throw new ProfileException("Invalid Phone number");
		}	
	}
	
	private void setUserName(final String userName) throws ProfileException {
		//Ok
		if (userName != null && userName.trim().length() > 0)
			this.userName = userName.trim();
		else
			throw new ProfileException("Invalid username!");
	}
	
	private void setNameAndSurname(final String nameAndSurname) throws ProfileException {
		//Ok
		if (nameAndSurname != null){
			this.nameAndSurname = nameAndSurname.trim();
		}else{
			throw new ProfileException("Invalid Name and Surname");
		}
	}

	private void setEmail(final String email) throws ProfileException {
		//Ok
		if(isValidEmailAddress(email)){
			this.email = email;
		}else{
			throw new ProfileException("Email not valid");
		}
	}
	
	
	private static boolean isValidEmailAddress(final String email) {
		//OK
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	/***
	 * 
	 * @param phoneNumber
	 * @return true if phone number contains 10 or 13 numbers; Valid format is:
	 *         08XXXXXXXX or +3598XXXXXXXX
	 * @throws ProfileException 
	 */
	private static boolean isValidPhoneNumber(final String phoneNumber) {
		//OK
		if(phoneNumber == null )
			return false;
		
		if (((phoneNumber.trim().startsWith("+3598") && phoneNumber.length() == 13)
				|| (phoneNumber.trim().startsWith("08")) && phoneNumber.length() == 10)){
			return true;
		}
		return false;
	}
	
	
	private void changePassword(final String newPassword) throws ProfileException {
		//Ok
		if (isSecure(newPassword))
			this.password = newPassword;
		else
			throw new ProfileException("Invalid password!");
	}
	
	
	private static boolean isSecure(final String password) {
		//Ok
		boolean hasLowerCase = false;
		boolean hasUpperCase = false;
		boolean hasDigit = false;
		if (password == null || password.length() <= 6){
			return false;
		}
		for (int index = 0; index < password.length(); ++index) {
			if (password.charAt(index) >= 'a' && password.charAt(index) <= 'z')
				hasLowerCase = true;
			if (password.charAt(index) >= 'A' && password.charAt(index) <= 'Z')
				hasUpperCase = true;
			if (password.charAt(index) >= '0' && password.charAt(index) <= '9')
				hasDigit = true;
		}
		if (hasLowerCase && hasUpperCase && hasDigit){
			return true;
		}
		return false;
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Profile other = (Profile) obj;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (userName == null) {
//			if (other.userName != null)
//				return false;
//		} else if (!userName.equals(other.userName))
//			return false;
//		return true;
//	}

}
