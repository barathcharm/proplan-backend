package com.fssa.proplan.validator;

import java.sql.SQLException;

import com.fssa.proplan.dao.BalanceDao;
import com.fssa.proplan.errormessages.TransactionError;
import com.fssa.proplan.model.User;



public class TransactionValidator {

	public boolean addIncome(double amount) throws IllegalArgumentException, SQLException {

		if (amount < 1) {
			throw new IllegalArgumentException(TransactionError.INVALID_AMOUNT_ZERO);
		}

		return true;
	}
	
	public boolean addExpense(User user,double amount) throws IllegalArgumentException, SQLException {

		if (amount < 1) { 
			throw new IllegalArgumentException(TransactionError.INVALID_AMOUNT_ZERO);
		}
		
		double balance = BalanceDao.getBalanceByUser(user);
		
		if(amount>balance) {
			throw new IllegalArgumentException(TransactionError.INVALID_WITHDRAW_LIMIT_REACHES);
		}

		return true;
	}


}
