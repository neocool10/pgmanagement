package com.neocool.pgmanagement.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import com.neocool.pgmanagement.utils.exception.DDLException;

public class TableDDLDao extends BaseDAO {
	private static final Logger logger = Logger.getLogger(TableDDLDao.class);

	private static TableDDLDao tableDDLdao = null;

	private TableDDLDao() {
	}

	public static TableDDLDao getInstance() {
		if (tableDDLdao == null) {
			tableDDLdao = new TableDDLDao();
		}
		return tableDDLdao;
	}

	public void createTable() throws DDLException {
		String sqlRegister = "Create table registertenant (id int NOT NULL AUTO_INCREMENT, "
				+ "tname varchar(30) DEFAULT NULL, " 
				+ "tphone varchar(10) DEFAULT NULL, "
				+ "tcity varchar(20) DEFAULT NULL, " 
				+ "jdate date DEFAULT NULL, "
				+ "aadhar varchar(30) DEFAULT NULL, " 
				+ "email varchar(30) DEFAULT NULL, "
				+ "PRIMARY KEY (id))";

		String sqlAllotment = "CREATE TABLE allotment (id int NOT NULL AUTO_INCREMENT, "
				+ "name varchar(30) DEFAULT NULL, " 
				+ "flatno varchar(30) DEFAULT NULL, "
				+ "rent int DEFAULT NULL, " 
				+ "agreement int DEFAULT NULL, "
				+ "startDate date DEFAULT NULL, " 
				+ "endDate date DEFAULT NULL, " 
				+ " PRIMARY KEY (id)";

		String sqlFlats = "CREATE TABLE flats ( " 
				+ "idflats int NOT NULL AUTO_INCREMENT, "
				+ "flatno varchar(45) NOT NULL, " 
				+ "stat varchar(45) NOT NULL DEFAULT 'yes', "
				+ "PRIMARY KEY (idflats)";

		String sqlLogin = "CREATE TABLE login ( " 
				+ "id int NOT NULL AUTO_INCREMENT, "
				+ "uname varchar(45) NOT NULL, " 
				+ "pass varchar(45) NOT NULL, " 
				+ "PRIMARY KEY (id)";

		String sqlStatus = "CREATE TABLE status ( " 
				+ "idstatus int NOT NULL AUTO_INCREMENT, "
				+ "flatno varchar(45) NOT NULL, " 
				+ "rent varchar(45) NOT NULL, "
				+ "maintenance varchar(45) NOT NULL, " 
				+ "parking varchar(45) NOT NULL, "
				+ "PRIMARY KEY (idstatus)";
		
		try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
			statement.execute(sqlRegister);
			logger.info("Created table ." + sqlRegister);
			statement.execute(sqlAllotment);
			logger.info("Created table . " + sqlAllotment);
			statement.execute(sqlFlats);
			logger.info("Created table .  " + sqlFlats);
			statement.execute(sqlLogin);
			logger.info("Created table .   " + sqlLogin);
			statement.execute(sqlStatus);
			logger.info("Created table ." + sqlStatus);
		} catch (SQLException sqld) {
			throw new DDLException(sqld);
		}

	}
}