/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniformes;

import controlador.ControladorUsuarioUni;
import modelo.UsuarioUni;
import modelo.UsuarioDaoUni;
import vistas.FrmIngresoUni;

/**
 *
 * @author troll
 */
public class Uniformes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmIngresoUni fmenu = new FrmIngresoUni();
        UsuarioUni usu = new UsuarioUni();
        UsuarioDaoUni usuDao = new UsuarioDaoUni();
        ControladorUsuarioUni contra = new ControladorUsuarioUni(fmenu,usuDao,usu);
        
        
        
        fmenu.setVisible(true);
    }
    
}
