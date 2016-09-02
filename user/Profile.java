package user;

import org.apache.commons.validator.routines.UrlValidator;


public class Profile {
	private static final String CHANGE_PASSWORD_INVALID_PASSWORD = "Invalid password!";
	private static final int COUNT_DIGITS_PHONE_NUMBER = 10;
	private static final int COUNT_DIGITS_PHONE_NUMBER_STARTING_WITH_359 = 13;
	private static final String SET_EMAIL_INAVLID_EMAIL = "Email not valid";
	private static final String SET_NAME_SURNAME_INVALID_INPUT = "Invalid Name and Surname";
	private static final String SET_USERNAME_INVALID_USERNAME = "Invalid username!";
	private static final String SET_PHONE_INVALID_PHONE_NUMBER = "Invalid Phone number";
	private static final String SET_WEBSITE_INVALID_WEBSITE = "Invalid website";
	private static final String SET_BIOGRAPHY_INVALID_INPUT = "Please fill this form!";
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
	public Profile(final String userName,final String password,final String email) throws ProfileException {
			this.setUserName(userName);
			this.changePassword(password);
			this.setEmail(email);
		
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
	

	public String getUserName() {
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
		//Ok
		if (bio != null && bio.trim().length() > 0){
			this.biography = bio;
		}else{
			throw new ProfileException(SET_BIOGRAPHY_INVALID_INPUT);
		}
	}
	
	public void setWebsite(String website) throws ProfileException {
		//Ok
		if(website != null){
			String finalWebsite = "";
			if(website.startsWith("www.")){
				String temp = website.substring(4, website.length());
				finalWebsite = "http://" + temp;
			}
			if (isWebsiteValid(finalWebsite)){
				this.website = finalWebsite;
			}else{
				throw new ProfileException(SET_WEBSITE_INVALID_WEBSITE);
			}	
		}
	}
	
	public static boolean isWebsiteValid(final String website)/*da pitame niki dali tova e losha ideq.*/{
		//Ok
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(website);
	}
	
	protected void setPhoneNumber(final String phoneNumber) throws ProfileException {
		//Ok
		if (isValidPhoneNumber(phoneNumber)){
			this.phoneNumber = phoneNumber;
		}else{
			throw new ProfileException(SET_PHONE_INVALID_PHONE_NUMBER);
		}	
	}
	
	private void setUserName(final String userName) throws ProfileException {
		//Ok
		if (userName != null && userName.trim().length() > 0)
			this.userName = userName.trim();
		else
			throw new ProfileException(SET_USERNAME_INVALID_USERNAME);
	}
	
	protected void setNameAndSurname(final String nameAndSurname) throws ProfileException {
		//Ok
		if (nameAndSurname != null){
			this.nameAndSurname = nameAndSurname.trim();
		}else{
			throw new ProfileException(SET_NAME_SURNAME_INVALID_INPUT);
		}
	}

	private void setEmail(final String email) throws ProfileException {
		//Ok
		if(isValidEmailAddress(email)){
			this.email = email;
		}else{
			throw new ProfileException(SET_EMAIL_INAVLID_EMAIL);
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
		
		if (((phoneNumber.trim().startsWith("+3598") && phoneNumber.length() == COUNT_DIGITS_PHONE_NUMBER_STARTING_WITH_359)
				|| (phoneNumber.trim().startsWith("08")) && phoneNumber.length() == COUNT_DIGITS_PHONE_NUMBER)){
			return true;
		}
		return false;
	}
	
	private void changePassword(final String newPassword) throws ProfileException {
		//Ok
		if (isPasswordSecure(newPassword))
			this.password = newPassword;
		else
			throw new ProfileException(CHANGE_PASSWORD_INVALID_PASSWORD);
	}
	
	private static boolean isPasswordSecure(final String password) {
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

}
