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

    public ResultSet getKomponenPelajaran(String id_siswa) throws SQLException {
        ResultSet rs = d.getData("SELECT guru.id_guru, kompomen.nama, kompomen.bobot FROM kompomen join guru using(id_guru)join nilai using(id_kompomen) where id_siswa ="+id_siswa);
        return rs;
    }
    public ResultSet loadNilai(String id_siswa) throws SQLException{
        ResultSet rs = d.getData("SELECT guru.nama,guru.pelajaran,nilai.nilai FROM nilai join kompomen using(id_kompomen)join guru using(id_guru) where id_siswa ="+id_siswa);
        return rs;
    }
    public void insertKomplain(String id_guru, String id_siswa, String pesan) throws SQLException{
        d.query("INSERT INTO `komplain`(`id_guru`, `id_siswa`, `pesan`) VALUES ('"+id_guru+"','"+id_siswa+"','"+pesan+"')");
    }
}
