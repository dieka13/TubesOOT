/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Guru_model;
import view.Guru_view;
import view.Login_guru_view;

/**
 *
 * @author dieka
 */
public class Guru_controller implements ActionListener, ListSelectionListener, ChangeListener{
    private Login_guru_view gui_login;
    private Guru_view gui_guru;
    private Guru_model md_guru;
    private String currentUserName, currentName, currentId, currentIdPel, currentSiswaId;
    private boolean isAdmin = false;
    
    public Guru_controller(){
        try {
            gui_login = new Login_guru_view();
            md_guru = new Guru_model();
            gui_login.addActionListener(this);
            gui_login.setVisible(true);
            gui_login.getRootPane().setDefaultButton(gui_login.getButtonLogin());
            gui_login.getTxtUsername().grabFocus();
            
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            JOptionPane.showMessageDialog(gui_login, ex.getMessage(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == gui_login.getButtonLogin()){
            ResultSet res = null;
            try {
                res = md_guru.login(gui_login.getTxtUsername().getText(), gui_login.getTxtPassword().getText());
                currentUserName = res.getString("username");
                currentName = res.getString("nama");
                currentId = res.getString("id_guru");
                currentIdPel = res.getString("id_pelajaran");
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
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (ae.getSource() == gui_guru.getBtnKompomenTambah()) {
            try {
                md_guru.insertKompomen(currentIdPel, gui_guru.getTxtTambahNama().getText(), gui_guru.getTxtTambahBobot().getText(), gui_guru.getTxtAreaTambahKeterangan().getText());
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
    }

    public Guru_model getModel() {
        return md_guru;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    
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
        ResultSet rs_nilai = md_guru.getNilai(currentIdPel, tab.getValueAt(tab.getSelectedRow(), 0).toString());
        while(rs_nilai.next()){
            tm.addRow(new Object[]{rs_nilai.getString("kompomen.id_kompomen"), rs_nilai.getString("kompomen.nama"), rs_nilai.getString("nilai")});
        }
    }
    
    public void clearTambahKompomen(){
        gui_guru.getTxtTambahBobot().setText("");
        gui_guru.getTxtTambahNama().setText("");
        gui_guru.getTxtAreaTambahKeterangan().setText("");
    }
}
