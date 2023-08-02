package com.fssa.proplan.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.errormessages.TransactionError;

public class TestTransactionValidator {

	@Test
	public void testValidIncome() throws IllegalArgumentException, SQLException {
		TransactionValidator validator = new TransactionValidator();
		double validAmount = 1000.0;
		boolean result = validator.addIncome(validAmount);
		assertTrue(result);
	}

	@Test
	public void testInvalidIncomeAmount() throws SQLException {
		TransactionValidator validator = new TransactionValidator();
		double invalidAmount = -100.0; // Invalid amount (less than 1)
		assertThrows(IllegalArgumentException.class, () -> validator.addIncome(invalidAmount));
	}

	@Test
	public void testInvalidIncomeAmountExceptionMessage() throws SQLException {
		TransactionValidator validator = new TransactionValidator();
		double invalidAmount = 0.0; // Invalid amount (less than 1)
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> validator.addIncome(invalidAmount));
		assertEquals(TransactionError.INVALID_AMOUNT_ZERO, exception.getMessage());
	}

}
