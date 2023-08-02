package com.fssa.proplan.enumclass;

public enum TransactionType {
	INCOME("Income"), EXPENSE("Expense");

	String value;

	TransactionType(String value) {
		this.value = value;
	}

	public  String getStringValue() {
		 
		return value; 
	}

}
