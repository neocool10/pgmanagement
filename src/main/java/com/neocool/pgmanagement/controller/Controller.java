package com.neocool.pgmanagement.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.neocool.pgmanagement.dao.BaseDAO;
import com.neocool.pgmanagement.utils.exception.DDLException;
import com.neocool.pgmanagement.utils.exception.DataException;

public abstract class Controller extends BaseDAO {
	static Logger logger = Logger.getLogger(Controller.class);

	Scanner sc = new Scanner(System.in);

	public abstract void main() throws DataException, DDLException;

	String result = null;

	public String login() {
		Connection conn = getConnection();
		try {

			logger.info("Are you an admin  : press 'y' :");
			logger.info("Are you an tenant : press 'n' :");
			String isAdmin = sc.nextLine();

			if (isAdmin.equalsIgnoreCase("y")) {
				logger.info("Enter username: ");
				String uname = sc.nextLine();
				logger.info("Enter password: ");
				String pass = sc.nextLine();
				String sql = "SELECT * FROM login WHERE uname=? AND pass=?";
				try (PreparedStatement stmt = conn.prepareStatement(sql)) {
					stmt.setString(1, uname);
					stmt.setString(2, pass);
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						logger.info("Login as admin successful!");
						return "admin";
					} else
						logger.info("Invalid response : TRY AGAIN!");

				}
			} else if (isAdmin.equalsIgnoreCase("n")) {

				return "user";
			} else
				logger.info("Invalid response : TRY AGAIN!");

		} catch (SQLException e) {
			logger.info("There is an error in login", e);

		}
		return "";
	}

	protected String printMenu() {
		logger.info("Wellcome to PG Management");

		logger.info("==================================");
		logger.info("Press 1 to Add Flat             :");
		logger.info("Press 2 to View flat availablity:");
		logger.info("Press 3 to View Rented Flats    :");
		logger.info("Press 4 to Delete Flat          :");
		logger.info("+================================");
		logger.info("Press 5 to Regiter Tenant       :");
		logger.info("Press 6 to Update Tenant        :");
		logger.info("Press 7 to View All Tenants     :");
		logger.info("Press 8 to View Tenant by Id    :");
		logger.info("Press 9 to Delete Tenant        :");
		logger.info("=================================");
		logger.info("Press 10 to New Allotment       :");
		logger.info("Press 11 to View Allotment      :");
		logger.info("Press 12 to Delete allotment    :");
		logger.info("=================================");
		logger.info("Press 13 to New Status          :");
		logger.info("Press 14 to Update Status       :");
		logger.info("Press 15 to View Status         :");
		logger.info("Press 16 to Delete Status       :");
		logger.info("Press 0 to Exit                 :");
		logger.info("==================================");
		result = sc.nextLine();

		return result;

	}

	protected String printMenuForUser() {
		logger.info("Wellcome to PG Managemant");
		logger.info("===============================");
		logger.info("Press 1 to Regiter           : ");
		logger.info("Press 2 to View Availability : ");
		logger.info("Press 3 to Update Status     : ");
		logger.info("Press 4 to View Status       : ");
		logger.info("Press 0 to Exit              : ");
		logger.info("==============================");
		result = sc.nextLine();

		return result;

	}
}