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
    
    public ResultSet getAllMataKuliah() throws SQLException{
        ResultSet res = d.getData("SELECT * FROM guru");
        return res;
    }
    
    public ResultSet getAllKompomen(String id_guru) throws SQLException{
        ResultSet res = d.getData("SELECT * FROM kompomen JOIN guru USING(id_guru) WHERE id_guru ='"+id_guru+"'");
        return res;
    }
    
    public void insertKompomen(String idGuru, String nama, String bobot, String ket) throws SQLException{
        try{
            d.query("INSERT INTO kompomen(nama, bobot, keterangan, id_guru) VALUES('"+nama+"','"+bobot+"','"+ket+"', '"+idGuru+"')");
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
    
    public ResultSet getAllSiswa() throws SQLException{
        ResultSet rs = d.getData("SELECT * FROM siswa");
        return rs;
    }
    
    public ResultSet getNilai(String id_guru, String id_siswa) throws SQLException{
        ResultSet rs = d.getData("SELECT * FROM kompomen CROSS JOIN siswa "
                + "LEFT JOIN nilai USING(id_siswa)"
                + " WHERE id_siswa='"+id_siswa+"' "
                + " AND id_guru ='"+id_guru+"'");
                
        return rs;
    }
    
    public void insertNilai(String id_siswa, String id_kompomen, String nilai) throws SQLException{
        d.query("INSERT INTO nilai(id_siswa, id_kompomen, nilai) VALUES('"+id_siswa+"','"+id_kompomen+"','"+nilai+"')");
    }
    
    public void updateNilai(String id_siswa, String id_kompomen, String nilai) throws SQLException{
        d.query("UPDATE nilai SET nilai ='"+nilai+"' WHERE id_siswa='"+id_siswa+"' AND id_kompomen='"+id_kompomen+"'");
    }
    
    public ResultSet getAllGuru() throws SQLException{
        ResultSet rs = d.getData("SELECT * FROM guru");
        return rs;
    }
    
    public void insertGuru(String nama, String username, String password, String pelajaran, String admin) throws SQLException{
        d.query("INSERT INTO guru(nama, username, password, pelajaran, admin) VALUES('"+nama+"','"+username+"','"+password+"','"+pelajaran+"', '"+admin+"')");
    }
    
    public void updateGuru(String id_guru, String nama, String username, String password, String pelajaran, String admin) throws SQLException{
        if(password.equals("")){
            d.query("UPDATE guru SET nama='"+nama+"', username='"+username+"', pelajaran='"+pelajaran+"', admin='"+admin+"' WHERE id_guru='"+id_guru+"'");
        } else {
            d.query("UPDATE guru SET nama='"+nama+"', username='"+username+"', password='"+password+"', pelajaran='"+pelajaran+"', admin='"+admin+"' WHERE id_guru='"+id_guru+"' ");
        }
        
    }
    
    public void deleteGuru(String id_guru) throws SQLException{
        d.query("DELETE FROM guru WHERE id_guru='"+id_guru+"'");
    }
    
    public void insertSiswa(String nama, String kelas, String username, String pass) throws SQLException{
        d.query("INSERT INTO siswa(nama, kelas, username, password) VALUES('"+nama+"','"+kelas+"','"+username+"','"+pass+"')");
    }
    
    public void updateSiswa(String id_siswa, String nama, String kelas, String username, String pass) throws SQLException{
        if(pass.equals("")){
            d.query("UPDATE siswa SET nama='"+nama+"', kelas='"+kelas+"', username='"+username+"' WHERE id_siswa='"+id_siswa+"'");
        } else {
            d.query("UPDATE siswa SET nama='"+nama+"', kelas='"+kelas+"', username='"+username+"', password='"+pass+"' WHERE id_siswa='"+id_siswa+"'");
        }        
    }
    
    public void deleteSiswa(String id_siswa) throws SQLException{
        d.query("DELETE FROM siswa WHERE id_siswa='"+id_siswa+"'");
    }
}
