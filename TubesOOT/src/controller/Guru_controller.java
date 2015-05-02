/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Guru_model;
import view.Login_guru_view;

/**
 *
 * @author dieka
 */
public class Guru_controller implements ActionListener, ListSelectionListener{
    private Login_guru_view gui_guru;
    private Guru_model md_guru;
    private String currentUser;
    private boolean isAdmin = false;
    
    public Guru_controller(){
        try {
            gui_guru = new Login_guru_view();
            md_guru = new Guru_model();
            gui_guru.addActionListener(this);
            gui_guru.setVisible(true);
            gui_guru.getRootPane().setDefaultButton(gui_guru.getButtonLogin());
            gui_guru.getTxtUsername().grabFocus();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            JOptionPane.showMessageDialog(gui_guru, ex.getMessage(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
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
    }

    public Guru_model getModel() {
        return md_guru;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
