package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.model.User;

public class TransactionDao {

	public static Connection getTransactionConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		return DriverManager.getConnection(url, user, password);
	}

	public boolean addIncome(User user, double amount, String remarks) throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();
			String query = "INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) "
					+ "VALUES(?,?,?,?,?,?)";

			PreparedStatement psmt = con.prepareStatement(query);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			double balance = BalanceDao.getBalanceByUser(user) + amount;
			psmt.setInt(1, UserDao.getUserIdByName(user.getName()));
			psmt.setString(2, TransactionType.INCOME.getStringValue());
			psmt.setDate(3, sqlDate);
			psmt.setDouble(4, amount);
			psmt.setDouble(5, balance);
			psmt.setString(6, remarks);

			int rowAffected = psmt.executeUpdate();

			BalanceDao.updateUserBalance(user, balance);

			System.out.println(rowAffected + "row/rows affected ");

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return true;
	}

	public boolean addExpense(User user, double amount, String remarks) throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();
			String query = "INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) "
					+ "VALUES(?,?,?,?,?,?)";

			PreparedStatement psmt = con.prepareStatement(query);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			double balance = BalanceDao.getBalanceByUser(user) - amount;
			psmt.setInt(1, UserDao.getUserIdByName(user.getName()));
			psmt.setString(2, TransactionType.EXPENSE.getStringValue());
			psmt.setDate(3, sqlDate);
			psmt.setDouble(4, amount);
			psmt.setDouble(5, balance);
			psmt.setString(6, remarks);

			int rowAffected = psmt.executeUpdate();

			BalanceDao.updateUserBalance(user, balance);

			System.out.println(rowAffected + "row/rows affected ");

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return true;
	}

	public static boolean clearAllTransactionDetails() throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();
			String query = "TRUNCATE TABLE transactions";

			Statement smt = con.createStatement();

			int rowAffected = smt.executeUpdate(query);

			System.out.println(rowAffected + "row/rows affected ");

			System.out.println("Transactions Table values are cleared");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return true;
	}

	public static ArrayList<ArrayList<String>> getIncomeTransactionDetails(User user) throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();

			String query = "SELECT transaction_type,date,amount,remarks FROM transactions where user_id=? AND transaction_type=?";

			int userId = UserDao.getUserIdByName(user.getName());

			PreparedStatement psmt = con.prepareStatement(query);

			psmt.setInt(1, userId);
			psmt.setString(2, TransactionType.INCOME.getStringValue());

			ResultSet rs = psmt.executeQuery();

			ArrayList<ArrayList<String>> incomeDetails = new ArrayList<ArrayList<String>>();

			while (rs.next()) {

				ArrayList<String> incomeDetail = new ArrayList<String>();
				incomeDetail.add(rs.getString("transaction_type"));
				incomeDetail.add(rs.getDate("date") + "");
				incomeDetail.add(rs.getDouble("amount") + "");
				incomeDetail.add(rs.getString("remarks"));
				incomeDetails.add(incomeDetail);

			}
			return incomeDetails;

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return null;

	}

	public static ArrayList<ArrayList<String>> getExpenseTransactionDetails(User user) throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();

			String query = "SELECT transaction_type,date,amount,remarks FROM transactions where user_id=? AND transaction_type=?";

			int userId = UserDao.getUserIdByName(user.getName());

			PreparedStatement psmt = con.prepareStatement(query);

			psmt.setInt(1, userId);
			psmt.setString(2, TransactionType.EXPENSE.getStringValue());

			ResultSet rs = psmt.executeQuery();

			ArrayList<ArrayList<String>> expenseDetails = new ArrayList<ArrayList<String>>();

			while (rs.next()) {

				ArrayList<String> expenseDetail = new ArrayList<String>();

				expenseDetail.add(rs.getString("transaction_type"));
				expenseDetail.add(rs.getDate("date") + "");
				expenseDetail.add(rs.getDouble("amount") + "");
				expenseDetail.add(rs.getString("remarks"));
				expenseDetails.add(expenseDetail);

			}
			return expenseDetails;

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return null;

	}

}
