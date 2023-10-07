package com.neocool.pgmanagement.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.neocool.pgmanagement.utils.exception.SystemException;

public abstract class BaseDAO {
	
    private static Logger logger = Logger.getLogger(BaseDAO.class);
    static {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	logger.info("There is an error in BaseDAO" , e);        
        	}
    }

    protected Connection getConnection() {
        Connection conn = null;
        try(FileReader reader=new FileReader("src/main/resources/db.properties")) {
        	Properties props = new Properties();
        	props.load(reader);
        	String username = props.getProperty("datasource.username");
        	String url = props.getProperty("datasource.url");
        	String pass = props.getProperty("datasource.password");
        	conn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
        	logger.info("There is an error in Connection");
            throw new SystemException("Connection Problem",e);
        } catch (IOException e) {
        	logger.info("There is an error in BaseDAO" , e);
		} 
        return conn;
    }
}