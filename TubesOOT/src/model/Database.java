package model;

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
    
    public void query(String SQLString) throws SQLException{
            stmt.executeUpdate(SQLString);
    }
    
}
