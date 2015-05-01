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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Guru_model;
import view.Guru_view;
>>>>>>> origin/master
import view.Login_guru_view;

/**
 *
 * @author dieka
 */
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
                currentIdPel = res.getString("id_pelajaran");
                isAdmin = res.getBoolean("admin");
                gui_login.dispose();
                
                gui_guru = new Guru_view();
                gui_guru.addActionListener(this);
                gui_guru.addListSelectionListener(this);
                gui_guru.getLblNamaGuru().setText(currentName+" ("+currentUserName+") ");
                gui_guru.setVisible(true);
                
                refreshTabelKompomen();
                
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
            TableModel tm = tbl.getModel();
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
    
    public void clearTambahKompomen(){
        gui_guru.getTxtTambahBobot().setText("");
        gui_guru.getTxtTambahNama().setText("");
        gui_guru.getTxtAreaTambahKeterangan().setText("");
    }
>>>>>>> origin/master
}
