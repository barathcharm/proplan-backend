package com.fssa.proplan.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.TransactionValidator;

public class TransactionService {

	private TransactionDao transactionDao;
	private TransactionValidator transactionValidator;

	public TransactionService(TransactionDao transactionDao, TransactionValidator transactionValidator) {
		this.transactionDao = transactionDao;
		this.transactionValidator = transactionValidator;
	}

	public boolean addIncome(User user, double amount, String remarks) throws IllegalArgumentException, SQLException {

		if (user == null || !UserDao.isUserExist(user)) {
			throw new IllegalArgumentException("The user doesn't exist");
		}

		if (transactionValidator.addIncome(amount)) {
			transactionDao.addIncome(user, amount, remarks);
			System.out.println("Income has been added successfully");
			return true;
		}

		return false;
	}

	public boolean addExpense(User user, double amount, String remarks) throws IllegalArgumentException, SQLException {

		if (user == null || !UserDao.isUserExist(user)) {
			throw new IllegalArgumentException("The user doesn't exist");
		}

		if (transactionValidator.addExpense(user, amount)) {
			transactionDao.addExpense(user, amount, remarks);
			System.out.println("Expense has been added successfully");
			return true;
		}

		return false;
	}

	public static ArrayList<ArrayList<String>> getIncomeTransactionDetails(User user) throws SQLException {

		if (user == null || !UserDao.isUserExist(user)) {
			throw new IllegalArgumentException("The user doesn't exist");
		}

		return TransactionDao.getIncomeTransactionDetails(user);

	}

	public static ArrayList<ArrayList<String>> getExpenseTransactionDetails(User user) throws SQLException {

		if (user == null || !UserDao.isUserExist(user)) {
			throw new IllegalArgumentException("The user doesn't exist");
		}

		return TransactionDao.getExpenseTransactionDetails(user);

	}

}
