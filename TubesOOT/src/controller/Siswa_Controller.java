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
        
        ResultSet rs = md_siswa.loadNilai();
        while(rs.next()){
            t.addRow(new Object[]{rs.getString("id_guru"), rs.getString("nama"), rs.getString("pelajaran")});
        }
        
    }
    public void loadKompomen(String id_guru, String id_siswa) throws SQLException {
        DefaultTableModel t = (DefaultTableModel) gui_siswa.getTableDetilNilai().getModel();
        t.setNumRows(0);
                
        ResultSet rs = md_siswa.getKomponenPelajaran(id_guru, id_siswa);
        while(rs.next()){
            t.addRow(new Object[]{rs.getString("id_kompomen"), rs.getString("nama"), rs.getString("bobot"), rs.getString("nilai")});
        }
        
        if(t.getRowCount() > 0){
            gui_siswa.getTableDetilNilai().setRowSelectionInterval(0, 0);
            
            int total_bobot=0;
            double total = 0;
            for (int i = 0; i < t.getRowCount(); i++) {
                total_bobot += t.getValueAt(i, 2) != null ? Integer.valueOf(t.getValueAt(i, 2).toString()) : 0;
            }

            for (int i = 0; i < t.getRowCount(); i++) {
                double bobot = t.getValueAt(i, 2) != null ? Double.valueOf(t.getValueAt(i, 2).toString()) : 0;
                double nilai = t.getValueAt(i, 3) != null ? Double.valueOf(t.getValueAt(i, 3).toString()) : 0;
                total += nilai*(bobot/total_bobot);
            }
            t.addRow(new Object[]{null,"TOTAL NILAI", null,total});
        }
        
        
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
                
               gui_siswa.addListSelectionListener(this);
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
                gui_siswa.getTxtKomplain().setText("");
                JOptionPane.showMessageDialog(gui_login, "Komplain telah berhasil dikirim", "Komplain Dikirim", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        JTable tbl = gui_siswa.getTabelNilai();
        if(tbl.getSelectedRow()!=-1){
            
            try {
                currentidGuru = (String) tbl.getValueAt(tbl.getSelectedRow(), 0);
                loadKompomen(currentidGuru, currentIdSiswa);
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
}