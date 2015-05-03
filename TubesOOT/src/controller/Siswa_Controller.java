/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.Siswa_model;
import View.GUI_Siswa;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fatih
 */
public class Siswa_Controller implements ActionListener, ListSelectionListener {

    private Login gui_login;
    private GUI_Siswa gui_siswa;
    private Siswa_model md_siswa;
    private String currentUserName, currentName, currentIdSiswa,currentidGuru;

    public Siswa_Controller() {
        try {
            gui_login = new Login();
            md_siswa = new Siswa_model();
            gui_login.addActionListener(this);
            gui_login.setVisible(true);
            gui_login.getRootPane().setDefaultButton(gui_login.getBtnLogin());
            gui_login.getUser().grabFocus();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            JOptionPane.showMessageDialog(gui_login, ex.getMessage(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void loadTabelNilai() throws SQLException {
        DefaultTableModel t = (DefaultTableModel) gui_siswa.getTabelNilai().getModel();
        t.setNumRows(0);
        ResultSet rs = md_siswa.loadNilai(currentIdSiswa);
    }
    public void loadKompomen() throws SQLException {
        DefaultTableModel t = (DefaultTableModel) gui_siswa.getTableDetilNilai().getModel();
        t.setNumRows(0);
        ResultSet rs = md_siswa.getKomponenPelajaran(currentIdSiswa);
        currentidGuru = rs.getString("id_guru");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == gui_login.getBtnLogin()) {
            ResultSet rs = null;
            try {
                rs = md_siswa.Login(gui_login.getUser().getText(), gui_login.getPass().getText());
                currentUserName = rs.getString("username");
                currentName = rs.getString("nama");
                currentIdSiswa = rs.getString("id_siswa");
                gui_login.dispose();
                gui_siswa = new GUI_Siswa();
                gui_siswa.addActionListener(this);
                gui_siswa.getLblNama().setText(gui_siswa.getLblNama().getText() + currentName + " (" + currentUserName + ") ");
                gui_siswa.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }

        } else if (ae.getSource() == gui_siswa.getBtnview()) {
            try {
                loadTabelNilai();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == gui_siswa.getBtnKomplain()) {
            try {
                md_siswa.insertKomplain(currentidGuru, currentIdSiswa, gui_siswa.getTxtKomplain().getText());
                JOptionPane.showConfirmDialog(gui_login, ae);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex);
            }   
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        JTable tbl = gui_siswa.getTabelNilai();
        if(tbl.getSelectedRow()!=-1){
            try {
                loadKompomen();
            } catch (SQLException ex) {
                System.out.println("Kesalahan di value changed");
            }
        }
    }
}