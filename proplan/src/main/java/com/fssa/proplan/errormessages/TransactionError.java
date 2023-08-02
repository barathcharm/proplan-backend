package com.fssa.proplan.errormessages;

public interface TransactionError {

	public static final String INVALID_AMOUNT_NULL = "The Amount value cannot be null";
	public static final String INVALID_AMOUNT_ZERO = "The Amount value should be greater than Zero";
	public static final String INVALID_TRANSACTION_TYPE = "The Transaction type should be an Income or an Expense";
	public static final String INVALID_TRANSACTIONDATE_NULL = "The Date should not be null";
	public static final String INVALID_TRANSACTIONDATE_FORMAT = "The Date should be in the format (\"yyyy-MM-dd\")";
	public static final String INVALID_WITHDRAW_LIMIT_REACHES = "Insufficient account balance. Enter lesser amount";

}
