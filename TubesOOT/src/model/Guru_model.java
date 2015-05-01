/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dieka
 */
public class Guru_model {
    Database d;

    public Guru_model() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.d = new Database();
    }
    
    public ResultSet login(String username, String password) throws SQLException, Exception{
        ResultSet l = d.getData("SELECT * FROM guru WHERE username='"+username+"' AND password = '"+password+"' LIMIT 1");
        if(!l.next()){
            throw new Exception("Login gagal, periksa kembali username dan password anda");
        } else {
            return l;
        }
    }
}
