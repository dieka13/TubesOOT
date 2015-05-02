/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.awt.event.WindowEvent;
=======
>>>>>>> origin/master
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
<<<<<<< HEAD
import model.Guru_model;
=======
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Guru_model;
import view.Guru_view;
>>>>>>> origin/master
import view.Login_guru_view;

/**
 *
 * @author dieka
 */
<<<<<<< HEAD
public class Guru_controller implements ActionListener, ListSelectionListener, ChangeListener{
    private Login_guru_view gui_login;
    private Guru_view gui_guru;
    private Guru_model md_guru;
<<<<<<< HEAD
    private String currentUserName, currentName, currentId, currentIdPel, currentSiswaId;
=======
<<<<<<< HEAD
public class Guru_controller implements ActionListener{
    private Login_guru_view gui_guru;
    private Guru_model md_guru;
    private String currentUser;
=======
public class Guru_controller implements ActionListener, ListSelectionListener{
    private Login_guru_view gui_login;
    private Guru_view gui_guru;
    private Guru_model md_guru;
    private String currentUserName, currentName, currentId, currentIdPel;
>>>>>>> origin/master
>>>>>>> origin/master
=======
    private String currentUserName, currentName, currentId, currentSiswaId;
>>>>>>> origin/guru
    private boolean isAdmin = false;
    
    public Guru_controller(){
        try {
<<<<<<< HEAD
            gui_guru = new Login_guru_view();
            md_guru = new Guru_model();
            gui_guru.addActionListener(this);
            gui_guru.setVisible(true);
            gui_guru.getRootPane().setDefaultButton(gui_guru.getButtonLogin());
            gui_guru.getTxtUsername().grabFocus();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            JOptionPane.showMessageDialog(gui_guru, ex.getMessage(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
=======
            gui_login = new Login_guru_view();
            md_guru = new Guru_model();
            gui_login.addActionListener(this);
            gui_login.setVisible(true);
            gui_login.getRootPane().setDefaultButton(gui_login.getButtonLogin());
            gui_login.getTxtUsername().grabFocus();
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            JOptionPane.showMessageDialog(gui_login, ex.getMessage(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
>>>>>>> origin/master
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
<<<<<<< HEAD
        if(ae.getSource() == gui_guru.getButtonLogin()){
            ResultSet res = null;
            try {
                res = md_guru.login(gui_guru.getTxtUsername().getText(), gui_guru.getTxtPassword().getText());
                currentUser = res.getString("username");
                isAdmin = res.getBoolean("admin");
                gui_guru.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui_guru, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Login_guru_view getView() {
        return gui_guru;
=======
        if(ae.getSource() == gui_login.getButtonLogin()){
            ResultSet res = null;
            try {
                res = md_guru.login(gui_login.getTxtUsername().getText(), gui_login.getTxtPassword().getText());
                currentUserName = res.getString("username");
                currentName = res.getString("nama");
                currentId = res.getString("id_guru");
                isAdmin = res.getBoolean("admin");
                gui_login.dispose();
                
                gui_guru = new Guru_view();
                gui_guru.addActionListener(this);
                gui_guru.addListSelectionListener(this);
                gui_guru.addChangeListener(this);
                
                gui_guru.getLblNamaGuru().setText(currentName+" ("+currentUserName+") ");
                gui_guru.setVisible(true);
                
                refreshTabelKompomen();
                refreshTabelSiswa();
                
                if(isAdmin){
                    refreshTabelGuru();
                    refreshTabelKelolaSiswa();
                } else {
                    gui_guru.getTabPaneGuru().remove(3);
                    gui_guru.getTabPaneGuru().remove(3);
                }
                
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (ae.getSource() == gui_guru.getBtnKompomenTambah()) {
            try {
                md_guru.insertKompomen(currentId, gui_guru.getTxtTambahNama().getText(), gui_guru.getTxtTambahBobot().getText(), gui_guru.getTxtAreaTambahKeterangan().getText());
                refreshTabelKompomen();
                clearTambahKompomen();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (ae.getSource() == gui_guru.getBtnKompomenHapus()){
            JTable tbl = gui_guru.getTblKompomen();
            try {
                md_guru.deleteKompomen(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
                refreshTabelKompomen();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (ae.getSource() == gui_guru.getBtnKompomenSimpan()) {
            JTable tbl = gui_guru.getTblKompomen();
            try {
                md_guru.updateKompomen(tbl.getValueAt(tbl.getSelectedRow(), 0).toString(), gui_guru.getTxtEditNama().getText(), gui_guru.getTxtEditBobot().getText(), gui_guru.getTxtAreaEditKeterangan().getText());
                refreshTabelKompomen();
            
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource() == gui_guru.getBtnNilai()){
            gui_guru.getTabPaneNilai().setSelectedIndex(1);
            
        } else if(ae.getSource() == gui_guru.getBtnInput()){
            JTable tbl = gui_guru.getTblNilai();
            if(tbl.getValueAt(tbl.getSelectedRow(), 2) == null){
                
                try {
                    md_guru.insertNilai(currentSiswaId, tbl.getValueAt(tbl.getSelectedRow(), 0).toString(), gui_guru.getTxtNilai().getText());
                    gui_guru.getTxtNilai().setText("");
                    refreshTabelNilai();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(gui_login, ex, "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                
                try {
                    md_guru.updateNilai(currentSiswaId, tbl.getValueAt(tbl.getSelectedRow(), 0).toString(), gui_guru.getTxtNilai().getText());
                    gui_guru.getTxtNilai().setText("");
                    refreshTabelNilai();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        } else if(ae.getSource() == gui_guru.getBtnGuruTambah()){
            
            try {
                md_guru.insertGuru(gui_guru.getTxtGuruTambahNama().getText(), gui_guru.getTxtGuruTambahUsername().getText(), gui_guru.getTxtGuruTambahPassword().getText(), gui_guru.getTxtGuruTambahPelajaran().getText(), gui_guru.getChkGuruTambahAdmin().isSelected()? "1":"0");
                refreshTabelGuru();
                clearTambahGuru();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource() == gui_guru.getBtnGuruSimpan()){
            
            try {
                JTable tbl = gui_guru.getTblGuru();
                md_guru.updateGuru(tbl.getValueAt(tbl.getSelectedRow(), 0).toString(), gui_guru.getTxtGuruEditNama().getText(), gui_guru.getTxtGuruEditUsername().getText(), gui_guru.getTxtGuruEditPassword().getText(), gui_guru.getTxtGuruEditPelajaran().getText(), gui_guru.getChkGuruEditAdmin().isSelected()? "1":"0");
                refreshTabelGuru();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource() == gui_guru.getBtnGuruHapus()){
            
            try {
                JTable tbl = gui_guru.getTblGuru();
                md_guru.deleteGuru(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
                refreshTabelGuru();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource() == gui_guru.getBtnSiswaTambah()){
            try {
                md_guru.insertSiswa(gui_guru.getTxtSiswaTambahNama().getText(), gui_guru.getTxtSiswaTambahKelas().getText(), gui_guru.getTxtSiswaTambahUsername().getText(), gui_guru.getTxtSiswaTambahPassword().getText());
                refreshTabelKelolaSiswa();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(ae.getSource() == gui_guru.getBtnSiswaSimpan()){
            try {
                JTable tbl = gui_guru.getTblKelolaSiswa();
                md_guru.updateSiswa(tbl.getValueAt(tbl.getSelectedRow(), 0).toString(), gui_guru.getTxtSiswaEditNama().getText(), gui_guru.getTxtSiswaEditKelas().getText(), gui_guru.getTxtSiswaEditUsername().getText(), gui_guru.getTxtSiswaEditPassword().getText());
                refreshTabelKelolaSiswa();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        
        } else if(ae.getSource() == gui_guru.getBtnSiswaHapus()){
            try {
                JTable tbl = gui_guru.getTblKelolaSiswa();
                md_guru.deleteSiswa(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
                refreshTabelKelolaSiswa();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getSource() == gui_guru.getTblKompomen().getSelectionModel() && !lse.getValueIsAdjusting()){
            if(gui_guru.getTabPaneGuru().getSelectedIndex() == 0){
                JTable tbl = gui_guru.getTblKompomen();
                if(tbl.getSelectedRow() != -1){
                    gui_guru.getTxtEditNama().setText(tbl.getValueAt(tbl.getSelectedRow(), 1).toString());
                    gui_guru.getTxtEditBobot().setText(tbl.getValueAt(tbl.getSelectedRow(), 2).toString());
                    gui_guru.getTxtAreaEditKeterangan().setText(tbl.getValueAt(tbl.getSelectedRow(), 3).toString());
                } 
            }
            
        } else if (lse.getSource() == gui_guru.getTblNilai().getSelectionModel() && !lse.getValueIsAdjusting()){
            
            JTable tbl = gui_guru.getTblNilai();
            if(tbl.getSelectedRow() != -1){
                gui_guru.getTxtNilai().setText(tbl.getValueAt(tbl.getSelectedRow(), 2) == null ? "" : tbl.getValueAt(tbl.getSelectedRow(), 2).toString());
            }
            
        } else if(lse.getSource() == gui_guru.getTblGuru().getSelectionModel() && !lse.getValueIsAdjusting()){
            
            JTable tbl = gui_guru.getTblGuru();
            if(tbl.getSelectedRow() != -1){
                gui_guru.getTxtGuruEditNama().setText(tbl.getValueAt(tbl.getSelectedRow(), 1).toString());
                gui_guru.getTxtGuruEditUsername().setText(tbl.getValueAt(tbl.getSelectedRow(), 2).toString());
                gui_guru.getTxtGuruEditPelajaran().setText(tbl.getValueAt(tbl.getSelectedRow(), 3).toString());
                gui_guru.getChkGuruEditAdmin().setSelected((boolean)tbl.getValueAt(tbl.getSelectedRow(), 4));
            }
            
        } else if (lse.getSource() == gui_guru.getTblKelolaSiswa().getSelectionModel() && !lse.getValueIsAdjusting()){
            
            JTable tbl = gui_guru.getTblKelolaSiswa();
            if(tbl.getSelectedRow() != -1){
                gui_guru.getTxtSiswaEditNama().setText(tbl.getValueAt(tbl.getSelectedRow(), 1).toString());
                gui_guru.getTxtSiswaEditUsername().setText(tbl.getValueAt(tbl.getSelectedRow(), 3).toString());
                gui_guru.getTxtSiswaEditKelas().setText(tbl.getValueAt(tbl.getSelectedRow(), 2).toString());
            }
        }
    }
    
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        if(ce.getSource() == gui_guru.getTabPaneNilai()){
            if(gui_guru.getTabPaneNilai().getSelectedIndex() == 1){
                try {
                    
                    JTable tbl = gui_guru.getTblSiswa();
                    gui_guru.getLblNamaSiswa().setText("Nama : "+tbl.getValueAt(tbl.getSelectedRow(), 1).toString());
                    currentSiswaId = tbl.getValueAt(tbl.getSelectedRow(), 0).toString();
                    
                    refreshTabelNilai();
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }
    }
    
    public Login_guru_view getView() {
        return gui_login;
>>>>>>> origin/master
    }

    public Guru_model getModel() {
        return md_guru;
    }

<<<<<<< HEAD
    public String getCurrentUser() {
        return currentUser;
=======
    public String getCurrentUserName() {
        return currentUserName;
>>>>>>> origin/master
    }

    public boolean isAdmin() {
        return isAdmin;
    }
<<<<<<< HEAD
=======
<<<<<<< HEAD
    
    
=======

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        if(lse.getSource() == gui_guru.getTblKompomen().getSelectionModel() && !lse.getValueIsAdjusting()){
            if(gui_guru.getTabPaneGuru().getSelectedIndex() == 0){
                JTable tbl = gui_guru.getTblKompomen();
                if(tbl.getSelectedRow() != -1){
                    gui_guru.getTxtEditNama().setText(tbl.getValueAt(tbl.getSelectedRow(), 1).toString());
                    gui_guru.getTxtEditBobot().setText(tbl.getValueAt(tbl.getSelectedRow(), 2).toString());
                    gui_guru.getTxtAreaEditKeterangan().setText(tbl.getValueAt(tbl.getSelectedRow(), 3).toString());
                }
                
            }
        }
    }
>>>>>>> origin/master
    
    public void refreshTabelKompomen() throws SQLException{
        DefaultTableModel tm = (DefaultTableModel) gui_guru.getTblKompomen().getModel();
        tm.setNumRows(0);

        ResultSet rs_kompomen = md_guru.getAllKompomen(currentId);
        while(rs_kompomen.next()){
            tm.addRow(new Object[]{rs_kompomen.getString("id_kompomen"), rs_kompomen.getString("nama"), rs_kompomen.getString("bobot"), rs_kompomen.getString("keterangan")});
        }
        
        if(tm.getRowCount() > 0){
            gui_guru.getTblKompomen().setRowSelectionInterval(0,0);
            gui_guru.getTabPaneKompomen().setSelectedIndex(0);
        } else {
            gui_guru.getTabPaneKompomen().setSelectedIndex(1);
        }
    }
    
    public void refreshTabelSiswa() throws SQLException{
        DefaultTableModel tm = (DefaultTableModel) gui_guru.getTblSiswa().getModel();
        tm.setNumRows(0);
        
        ResultSet rs_siswa = md_guru.getAllSiswa();
        while(rs_siswa.next()){
            tm.addRow(new Object[]{rs_siswa.getString("id_siswa"), rs_siswa.getString("nama"), rs_siswa.getString("kelas")});
        }
        
        if(tm.getRowCount() > 0){
            gui_guru.getTblSiswa().setRowSelectionInterval(0,0);
        } 
    }
    
    public void refreshTabelNilai() throws SQLException{
        DefaultTableModel tm = (DefaultTableModel) gui_guru.getTblNilai().getModel();
        tm.setNumRows(0);
        
        JTable tab = gui_guru.getTblSiswa();
        ResultSet rs_nilai = md_guru.getNilai(currentId, tab.getValueAt(tab.getSelectedRow(), 0).toString());
        while(rs_nilai.next()){
            tm.addRow(new Object[]{rs_nilai.getString("kompomen.id_kompomen"), rs_nilai.getString("kompomen.nama"), rs_nilai.getString("nilai")});
        }
    }
    
    public void refreshTabelGuru() throws SQLException{
        DefaultTableModel tm = (DefaultTableModel) gui_guru.getTblGuru().getModel();
        tm.setNumRows(0);
        
        ResultSet rs = md_guru.getAllGuru();
        while(rs.next()){
            tm.addRow(new Object[]{rs.getString("id_guru"), rs.getString("guru.nama"), rs.getString("username"), rs.getString("pelajaran"), rs.getBoolean("admin")});
        }
        
        if(tm.getRowCount() > 0){
            gui_guru.getTblGuru().setRowSelectionInterval(0,0);
        } 
    }
    
    public void refreshTabelKelolaSiswa() throws SQLException{
        DefaultTableModel tm = (DefaultTableModel) gui_guru.getTblKelolaSiswa().getModel();
        tm.setNumRows(0);
        
        ResultSet rs_siswa = md_guru.getAllSiswa();
        while(rs_siswa.next()){
            tm.addRow(new Object[]{rs_siswa.getString("id_siswa"), rs_siswa.getString("nama"), rs_siswa.getString("kelas"), rs_siswa.getString("username")});
        }
        
        if(tm.getRowCount() > 0){
            gui_guru.getTblKelolaSiswa().setRowSelectionInterval(0,0);
        } 
    }
    
    public void clearTambahKompomen(){
        gui_guru.getTxtTambahBobot().setText("");
        gui_guru.getTxtTambahNama().setText("");
        gui_guru.getTxtAreaTambahKeterangan().setText("");
    }
<<<<<<< HEAD
>>>>>>> origin/master
=======
    
    public void clearTambahGuru(){
        gui_guru.getTxtGuruTambahNama().setText("");
        gui_guru.getTxtGuruTambahPassword().setText("");
        gui_guru.getTxtGuruTambahUsername().setText("");
        gui_guru.getTxtGuruTambahPelajaran().setText("");
    }
>>>>>>> origin/guru
}
