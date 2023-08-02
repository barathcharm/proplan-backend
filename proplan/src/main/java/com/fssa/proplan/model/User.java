package com.fssa.proplan.model;

import java.sql.SQLException;
import java.util.ArrayList;
import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.service.TransactionService;
import com.fssa.proplan.service.UserService;
import com.fssa.proplan.validator.TransactionValidator;
import com.fssa.proplan.validator.UserValidator;

public class User {

	private String name;
	private String displayName;
	private String phoneNumber;
	private String profession;
	private String emailId;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber.trim();
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession.trim();
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public User(String name, String phoneNumber, String profession, String emailId, String password) {
		this.name = name.trim();
		this.phoneNumber = phoneNumber.trim();
		this.profession = profession.trim();
		this.emailId = emailId.trim();
		this.password = password.trim();
	}

	public User() {
		System.out.println("New Account is created successfully");
	}

	public static void main(String[] args) throws SQLException {

		User user1 = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");

		UserService userService = new UserService(new UserDao(), new UserValidator());

//		userService.addUser(user1);

		ArrayList<String> ar = UserDao.getAllUserEmails();

		System.out.println(ar);

		TransactionService TransactionService = new TransactionService(new TransactionDao(),
				new TransactionValidator());

		System.out.println(TransactionService.getIncomeTransactionDetails(user1));
		System.out.println(TransactionService.getExpenseTransactionDetails(user1));

//		TransactionService.addIncome(user1, 12000, "Salary");

//		TransactionService.addExpense(user1, 10000, "Entertainment");

//		UserDao.clearAllUsers();

	}

}
