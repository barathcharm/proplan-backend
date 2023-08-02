package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.proplan.model.User;

public class BalanceDao {

	public static Connection getBalanceConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		return DriverManager.getConnection(url, user, password);
	}

	public static boolean createNewUserBalance(User user) throws SQLException {

		Connection con = null;

		try {
			con = getBalanceConnection();

			String query = "INSERT INTO balance(user_id,balance) VALUES(?,0)";

			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, UserDao.getUserIdByName(user.getName()));

			int rowAffected = psmt.executeUpdate();

			System.out.println(rowAffected + "row/rows affected & Balance has been set 0");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return true;
	}

	public static boolean updateUserBalance(User user, double balance) throws SQLException {

		Connection con = null;
		try {
			con = getBalanceConnection();

			String query = "UPDATE balance SET balance = ? WHERE user_id = ?";

			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setDouble(1, balance);
			psmt.setInt(2, UserDao.getUserIdByName(user.getName()));

			int rowAffected = psmt.executeUpdate();

			System.out.println(rowAffected + "row/rows affected & Balance has been updated");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return true;
	}

	public static double getBalanceByUser(User user) throws SQLException {

		UserDao.isUserExist(user);

		Connection con = null;
		double balance = 0;
		try {
			con = getBalanceConnection();
			String query = "SELECT balance FROM balance WHERE user_id=?";

			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, UserDao.getUserIdByName(user.getName()));

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				balance = rs.getDouble("balance");

			}

			System.out.println("Balance Successfully fetched");
			return balance;

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return 0;
	}

	public static boolean clearAllBalanceDetails() throws SQLException {

		Connection con = null;
		try {
			con = getBalanceConnection();
			String query = "TRUNCATE TABLE balance";

			Statement smt = con.createStatement();

			int rowAffected = smt.executeUpdate(query);

			System.out.println(rowAffected + "row/rows affected ");

			System.out.println("Balance Table values are cleared");

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return true;
	}
}
