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
 * @author Fatih
 */
public class Siswa_model{

    Database d;

    public Siswa_model() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.d = new Database();
    }

    public ResultSet Login(String username, String password) throws SQLException, Exception {
        ResultSet login = d.getData("SELECT * FROM siswa WHERE username='" + username + "' AND password = '" + password + "' LIMIT 1");
        if (!login.next()) {
            throw new Exception("Login gagal!\n periksa username dan password anda");
        } else {
            return login;
        }
    }

    public ResultSet getKomponenPelajaran(String id_guru, String id_siswa) throws SQLException {
        ResultSet rs = d.getData("SELECT * FROM nilai RIGHT JOIN kompomen USING(id_kompomen) WHERE id_guru='"+id_guru+"' AND (id_siswa='"+id_siswa+"' OR id_siswa IS NULL)");
        return rs;
    }
    public ResultSet loadNilai() throws SQLException{
        ResultSet rs = d.getData("SELECT * FROM guru");
        return rs;
    }
    public void insertKomplain(String id_guru, String id_siswa, String pesan) throws SQLException{
        d.query("INSERT INTO `komplain`(`id_guru`, `id_siswa`, `pesan`) VALUES ('"+id_guru+"','"+id_siswa+"','"+pesan+"')");
    }
}
