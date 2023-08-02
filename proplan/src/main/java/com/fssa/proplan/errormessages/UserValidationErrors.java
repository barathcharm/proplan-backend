package com.fssa.proplan.errormessages;

public interface UserValidationErrors {
	public static final String INVALID_USER_NULL = "User should not be null";
	public static final String INVALID_NAME_NULL = "Name should not be null";
	public static final String INVALID_NAME = "Name should not contain numbers";
	public static final String INVALID_EMAIL_NULL = "Email should not be null";
	public static final String INVALID_EMAIL = "Enter valid Email id format";
	public static final String INVALID_PASSWORD_NULL = "Password should not be null";
	public static final String INVALID_PASSWORD = "Password must contain atleast one special character, one number and one Uppercase and Lowercase letters";
	public static final String INVALID_PROFESSION_NULL = "Profession should not be null";
	public static final String INVALID_PROFESSION = "Profession should not contain numbers";
	public static final String INVALID_PHNO_NULL = "Phone number should not be null";
	public static final String INVALID_PHNO = "Enter a valid phone number with 10 digits";
	
}
