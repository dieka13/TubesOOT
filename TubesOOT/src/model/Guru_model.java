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
<<<<<<< HEAD
        ResultSet l = d.getData("SELECT * FROM guru WHERE username='"+username+"' AND password = '"+password+"' LIMIT 1");
=======
        ResultSet l = d.getData("SELECT * FROM guru JOIN pelajaran WHERE username='"+username+"' AND password = '"+password+"' LIMIT 1");
>>>>>>> origin/master
        if(!l.next()){
            throw new Exception("Login gagal, periksa kembali username dan password anda");
        } else {
            return l;
        }
    }
<<<<<<< HEAD
=======
    
    public ResultSet getAllMataKuliah() throws SQLException{
        ResultSet res = d.getData("SELECT * FROM guru JOIN pelajaran ON('id_guru')");
        return res;
    }
    
    public ResultSet getAllKompomen(String id_guru) throws SQLException{
        ResultSet res = d.getData("SELECT * FROM kompomen_pelajaran JOIN kompomen USING(id_kompomen) JOIN pelajaran USING(id_pelajaran) WHERE id_guru ='"+id_guru+"'");
        return res;
    }
    
    public void insertKompomen(String idPel, String nama, String bobot, String ket) throws SQLException{
        try{
            d.query("INSERT INTO kompomen(nama, bobot, keterangan) VALUES('"+nama+"','"+bobot+"','"+ket+"')");
            ResultSet rs = d.getStatement().getGeneratedKeys();
            rs.next();
            d.query("INSERT INTO kompomen_pelajaran VALUES('"+idPel+"','"+rs.getInt(1)+"')");
        } catch(SQLException e) {
            throw e;
        }
    }
    
    public void deleteKompomen(String idKomp) throws SQLException{
        d.query("DELETE FROM kompomen WHERE id_kompomen ='"+idKomp+"' LIMIT 1");
    }
    
    public void updateKompomen(String idKomp, String nama, String bobot, String ket) throws SQLException{
        d.query("UPDATE kompomen SET nama='"+nama+"', bobot='"+bobot+"', keterangan='"+ket+"' WHERE id_kompomen='"+idKomp+"' LIMIT 1");
    }
<<<<<<< HEAD
    
    public ResultSet getAllSiswa() throws SQLException{
        ResultSet rs = d.getData("SELECT * FROM siswa");
        return rs;
    }
    
    public ResultSet getNilai(String id_pelajaran, String id_siswa) throws SQLException{
        ResultSet rs = d.getData("SELECT * FROM kompomen CROSS JOIN siswa "
                + "LEFT JOIN nilai USING(id_siswa, id_kompomen)"
                + "JOIN kompomen_pelajaran USING(id_kompomen)"
                + " WHERE id_siswa='"+id_siswa+"'"
                + " AND id_pelajaran='"+id_pelajaran+"'");
        return rs;
    }
    
    public void insertNilai(String id_siswa, String id_kompomen, String nilai) throws SQLException{
        d.query("INSERT INTO nilai(id_siswa, id_kompomen, nilai) VALUES('"+id_siswa+"','"+id_kompomen+"','"+nilai+"')");
    }
    
    public void updateNilai(String id_siswa, String id_kompomen, String nilai) throws SQLException{
        d.query("UPDATE nilai SET nilai ='"+nilai+"' WHERE id_siswa='"+id_siswa+"' AND id_kompomen='"+id_kompomen+"'");
    }
=======
>>>>>>> origin/master
>>>>>>> origin/master
}
