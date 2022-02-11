/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioUni;
import modelo.UsuarioDaoUni;
import vistas.FrmIngresoUni;
import vistas.FrmPrincipalUni;

/**
 *
 * @author troll
 */
public class ControladorUsuarioUni implements ActionListener{
    
    FrmIngresoUni fvalida;
    UsuarioDaoUni usuDao;
    UsuarioUni usu;

    public ControladorUsuarioUni(FrmIngresoUni fvalida, UsuarioDaoUni usuDao, UsuarioUni usu) {
        this.fvalida = fvalida;
        this.usuDao = usuDao;
        this.usu = usu;
        fvalida.setLocationRelativeTo(null);
        fvalida.JbIngreso.addActionListener(this);
        fvalida.JbSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fvalida.JbIngreso){ 
                String usuario = fvalida.JtxUsuario.getText();
                String contraseña = fvalida.JbPass.getText();
            if (usuDao.validar(usuario, contraseña)){
                FrmPrincipalUni fmenu = new FrmPrincipalUni();
                fmenu.setVisible(true);
                fmenu.setExtendedState(fmenu.MAXIMIZED_BOTH); 
                limpiarcontroles();
            }else{
                JOptionPane.showMessageDialog(fvalida,"Usuario y/o contraseÃ±a invalidos");
                limpiarcontroles();
            }
        }
        
        if(e.getSource()==fvalida.JbSalir){
            int respuesta = JOptionPane.showConfirmDialog(fvalida, "Esta seguro de salir?", "Fin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                   System.exit(0);
            }
        }
    }
    
    public void limpiarcontroles(){
        fvalida.JtxUsuario.setText("");
        fvalida.JbPass.setText("");
    }
    
    
    
    

    
    
    
}
