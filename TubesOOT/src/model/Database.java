/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Fatih
 */

import java.sql.*;

public class Database {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    public Database() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oot", "root", "");
        stmt = conn.createStatement();
    }
    
    public ResultSet getData(String SQLString) throws SQLException{
        rs = stmt.executeQuery(SQLString);
        return rs;
    }
    
    public int query(String SQLString) throws SQLException{
        int id = stmt.executeUpdate(SQLString, Statement.RETURN_GENERATED_KEYS);
        return id;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStatement() {
        return stmt;
    }
}

