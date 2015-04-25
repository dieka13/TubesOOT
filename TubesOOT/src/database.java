

import java.sql.*;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class database {
    private String dbuser = "root";
    private String dbpass = "";
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    
    public database(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/oot", dbuser, dbpass);
            stmt =  con.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Tidak dapat melakukan koneksi ke database : \n"+ex.getMessage());
        }
    }
    
    public ResultSet getData(String SQLString){
        try {
            rs = stmt.executeQuery(SQLString);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : \n"+ex.getMessage());
        }
        return rs;
    }
    
    public void query(String SQLString){
        try {
            stmt.executeUpdate(SQLString);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan : \n"+ex.getMessage());
        }
    }
}