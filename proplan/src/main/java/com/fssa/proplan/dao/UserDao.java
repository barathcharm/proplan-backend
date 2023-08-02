package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.proplan.model.User;

public class UserDao {

	public static Connection getUserConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		return DriverManager.getConnection(url, user, password);
	}

	public static boolean addUser(User user) throws SQLException, IllegalArgumentException {
		Connection con = null;

	
		try {
			con = getUserConnection();
			String query = "INSERT INTO user(name,phone_num,profession,email_id,password) " + "VALUES(?,?,?,?,?)";

			PreparedStatement psmt = con.prepareStatement(query);

			psmt.setString(1, user.getName());
			psmt.setString(2, user.getPhoneNumber());
			psmt.setString(3, user.getProfession());
			psmt.setString(4, user.getEmailId());
			psmt.setString(5, user.getPassword());

			int rowAffected = psmt.executeUpdate();

			System.out.println(rowAffected + "row/rows affected "); 

			BalanceDao.createNewUserBalance(user);

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return true;
	}

	public static ArrayList<String> getAllUserEmails() throws SQLException {
 
		Connection con = null;
 
		ArrayList<String> userNames = new ArrayList<String>();
		try {
			con = getUserConnection();
			String query = "SELECT email_id FROM USER";

			Statement smt = con.createStatement();

			ResultSet resultSet = smt.executeQuery(query);

			while (resultSet.next()) {
				userNames.add(resultSet.getString(1));
			}

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return userNames;

	}

	public static boolean clearAllUsers() throws SQLException {

		Connection con = null;

		try {
			con = getUserConnection();
			String query = "TRUNCATE TABLE user";

			Statement smt = con.createStatement();

			int rowAffected = smt.executeUpdate(query);

			System.out.println(rowAffected + "row/rows affected ");

			System.out.println("User Table values are cleared");

			BalanceDao.clearAllBalanceDetails();
			TransactionDao.clearAllTransactionDetails();

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return true;

	}
 
	public static boolean isUserExist(User user) throws SQLException {

		ArrayList<String> userEmails = getAllUserEmails(); 

		return userEmails.contains(user.getEmailId()); 
	}

	public static int getUserIdByName(String name) throws SQLException {

		Connection con = null;

		int user_id = 0;

		try {
			con = getUserConnection();
			String query = "SELECT user_id FROM user where name=?";

			PreparedStatement psmt = con.prepareStatement(query);

			psmt.setString(1, name);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				user_id = rs.getInt("user_id");
			}

			return user_id;

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();

			}
		}

	}

}
