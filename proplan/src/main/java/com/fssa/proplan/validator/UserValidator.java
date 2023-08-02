package com.fssa.proplan.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.proplan.errormessages.UserValidationErrors;
import com.fssa.proplan.model.User;

public class UserValidator {

	public static boolean isValidUser(User user) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_USER_NULL);
		}
		isValidName(user.getName());
		isValidEmail(user.getEmailId());
		isValidPassword(user.getPassword());
		isValidProfession(user.getProfession());
		isValidPhoneNumber(user.getPhoneNumber());

		return true;
	}  

	public static boolean isValidName(String name) throws IllegalArgumentException {

		if (name == null || name.trim() == null|| name.trim().equals("")) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_NAME_NULL);
		}
		String regexPattern = "^[^0-9]*$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(name);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_NAME);
		}
		return true;
	}

	public static boolean isValidEmail(String email) throws IllegalArgumentException {

		if (email == null || email.trim() == null||email.trim()=="") {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_EMAIL_NULL);
		}
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email.trim());

		if (!matcher.matches()) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_EMAIL);
		}
		return true;
	}

	public static boolean isValidPassword(String password) throws IllegalArgumentException {
		if (password == null || password.trim() == null||password.trim()=="") {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_PASSWORD_NULL);
		}
		// At least one special character (e.g., !@#$%^&*()-_=+[]{}|;:'",.<>?/)
		String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

		// Create a Pattern object from the regex pattern
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object to match the input password against the pattern
		Matcher matcher = pattern.matcher(password);

		// Return true if the password matches the pattern, otherwise false
		if (!matcher.matches()) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_PASSWORD);
		} 
		return true;
	}

	public static boolean isValidProfession(String profession) throws IllegalArgumentException {
		if (profession == null || profession.trim() == null||profession.trim()=="") {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_PROFESSION_NULL);
		}

		String regexPattern = "^[^0-9]*$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(profession);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_PROFESSION);
		}
		return true;

	}

	public static boolean isValidPhoneNumber(String phNo) throws IllegalArgumentException {
		if (phNo == null || phNo.trim() == null||phNo.trim()=="") {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_PHNO_NULL);
		}
		String regexPattern = "^[0-9]{10}$";

		// Create a Pattern object from the regex pattern
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object to perform the validation
		Matcher matcher = pattern.matcher(phNo.trim());

		if (!matcher.matches()) {
			throw new IllegalArgumentException(UserValidationErrors.INVALID_PHNO);
		}
		return true;
	} 

}
