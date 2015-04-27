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
import model.Guru_model;
import view.Guru_view;
import view.Login_guru_view;

/**
 *
 * @author dieka
 */
public class Guru_controller implements ActionListener{
    private Login_guru_view gui_login;
    private Guru_view gui_guru;
    private Guru_model md_guru;
    private String currentUserName, currentName;
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
                isAdmin = res.getBoolean("admin");
                gui_login.dispose();
                
                gui_guru = new Guru_view();
                gui_guru.addActionListener(this);
                gui_guru.getLblNamaGuru().setText(currentName+" ("+currentUserName+") ");
                gui_guru.setVisible(true);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui_login, ex.toString(), "Terjadi Kesalahan", JOptionPane.ERROR_MESSAGE);
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
    
    
}
