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
public class Siswa_model {
    private Database d;

    public Siswa_model() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        d = new Database();
    }

    public ResultSet Login(String username, String password) throws SQLException, Exception {
        ResultSet login = d.getData("SELECT * FROM siswa WHERE username='" + username + "' AND password = '" + password + "' LIMIT 1");
        if (!login.next()) {
            throw new Exception("Login gagal!\n periksa username dan password anda");
        } else {
            return login;
        }
    }

    public ResultSet getAll(String id_siswa) throws SQLException {
        ResultSet rs = d.getData("SELECT * FROM nilai JOIN kompomen USING(id_kompomen) JOIN kompomen_pelajaran USING(id_kompomen) JOIN pelajaran USING(id_pelajaran)JOIN guru USING(id_guru) WHERE id_siswa ='" + id_siswa + "'");
        return rs;
    }

    public ResultSet getKomponenPelajaran(String id_pelajaran) throws SQLException {
        ResultSet rs = d.getData("SELECT * FROM kompomen JOIN kompomen_pelajaran USING(id_kompomen) WHERE id_pelajaran ='"+id_pelajaran+"'");
        return rs;
    }
    public ResultSet insertKomplain() throws SQLException{
        ResultSet rs = d.getData("");
        return rs = d.getData("");
    }

}
