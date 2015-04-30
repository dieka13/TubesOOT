/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Kelas;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fatih
 */
public class Siswa {
    private Database d;

    public Siswa() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        d = new Database();
    }
    
    public ResultSet Login(String username, String password) throws SQLException, Exception{
        ResultSet login = d.getData("SELECT * FROM siswa WHERE username='"+username+"' AND password = '"+password+"' LIMIT 1");
        if(!login.next()){
            throw new Exception("Login gagal!\n periksa username dan password anda");
        } else {
            return login;
        }
    }
}
