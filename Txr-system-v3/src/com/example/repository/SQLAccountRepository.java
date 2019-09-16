package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.example.db.MySQLConnectionFactory;
import com.example.model.Account;

public class SQLAccountRepository implements AccountRepository {

	private static final Logger logger = Logger.getLogger("txr-system");

	public SQLAccountRepository() {
		logger.info("SQLAccountRepository instance created..");
	}

	public Account loadAccount(String number) {
		// ... jdbc / jpa
		logger.info("loading account " + number);
		Account account = null;
		try {
			Connection connection = MySQLConnectionFactory.getConnection();

			String sql = "select * from my_bank.ACCOUNTS where num=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, number);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setNumber(rs.getString(1));
				account.setBalance(rs.getDouble(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public Account updateAccount(Account account) {
		// ...
		logger.info("updating account " + account.getNumber());
		try {
			Connection connection = MySQLConnectionFactory.getConnection();

			String sql = "update my_bank.ACCOUNTS set balance=? where num=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setString(2, account.getNumber());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

}
