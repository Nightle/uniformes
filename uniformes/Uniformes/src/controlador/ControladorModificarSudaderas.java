/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.InventarioSuda;
import modelo.ModificarSudaderasDao;
import modelo.TallDao;
import modelo.Talla;
import vistas.FrmModificarSudaderas;

/**
 *
 * @author troll
 */
public class ControladorModificarSudaderas implements ActionListener{
    private FrmModificarSudaderas fms;
    private InventarioSuda is;
    private ModificarSudaderasDao mds;

    public ControladorModificarSudaderas(FrmModificarSudaderas fms, InventarioSuda is, ModificarSudaderasDao mds) throws SQLException {
        this.fms = fms;
        this.is = is;
        this.mds = mds;
        this.fms.JbGuardar.addActionListener(this);
        this.fms.JbConsultar.addActionListener(this);
        this.fms.JbActualizar.addActionListener(this);
        this.fms.JbEliminar.addActionListener(this);
        this.fms.JbSalir.addActionListener(this);
        this.fms.JbNuevo.addActionListener(this);
        mostrarCategoria();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fms.JbGuardar){
            String tall = fms.JtxTalla.getText();
            int cod = Integer.parseInt(fms.JcbCodigo.getSelectedItem().toString());
            String cc = fms.JtxCantidadCC.getText();
            String ac = fms.JtxCantidadAC.getText();
            
            is = new InventarioSuda (tall,cc,ac,cod);
            
            if(mds.adicionar(is)){
                JOptionPane.showMessageDialog(fms, "Producto Registrado");
                limpiarcontroles();
            } else {
                JOptionPane.showMessageDialog(fms, "Valide Los Datos");
            }
        }
        
        if(e.getSource()==fms.JbSalir){
            int respuesta = JOptionPane.showConfirmDialog(fms, "¿Esta seguro de salir?", "Fin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
            fms.dispose();
            }   
            
        }
        
        if(e.getSource()==fms.JbConsultar){ 
            try {
                String tall = JOptionPane.showInputDialog(" ingrese la talla a consultar ");
                is = mds.consultardato(tall);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorModificarSudaderas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(is!=null){
            fms.JtxTalla.setText(is.getTalla());
            fms.JcbCodigo.setSelectedItem(String.valueOf(is.getCodigo()));
            fms.JtxCantidadCC.setText(is.getCantidadcc());
            fms.JtxCantidadAC.setText(is.getCantidadac());
            } else {
            JOptionPane.showMessageDialog(fms, "no esta en la base de datos");
            }
            
        }
        
        if(e.getSource()==fms.JbActualizar){
            String tall = fms.JtxTalla.getText();
            int cod = Integer.parseInt(fms.JcbCodigo.getSelectedItem().toString());
            String cc = fms.JtxCantidadCC.getText();
            String ac = fms.JtxCantidadAC.getText();
            
            is = new InventarioSuda (tall,cc,ac,cod);
            
            if(mds.actualizar(is)){
                JOptionPane.showMessageDialog(fms, "Producto Registrado");
            } else {
                JOptionPane.showMessageDialog(fms, "Valide Los Datos");
            }
        }
        
        if(e.getSource()==fms.JbEliminar){
            String cod = fms.JtxTalla.getText();
            int respuesta = JOptionPane.showConfirmDialog(fms, "¿Esta seguro?", "eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                if (mds.eliminar(cod)){
                    JOptionPane.showMessageDialog(fms,"Se eliminó con éxito");
                    limpiarcontroles();
                    
                }else{
                    JOptionPane.showMessageDialog(fms, "No se eliminó - Verificar datos");
                }           
            }  
            
        }
        
        if(e.getSource()==fms.JbNuevo){
            limpiarcontroles();
        }
        
        if (e.getSource() == fms.JcbCodigo){
            Talla t = new Talla();
            TallDao td = new TallDao();
            int tal = Integer.parseInt(fms.JcbCodigo.getSelectedItem().toString());
            
            try {
                t = td.consultaDato(tal);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioSuda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
        
        }
        
        
    
    
    }
        public void limpiarcontroles(){
            fms.JtxTalla.setText("");
            fms.JtxCantidadCC.setText("");
            fms.JtxCantidadAC.setText("");
            
        }
        
        public void mostrarCategoria() throws SQLException{
        Talla ta = new Talla();
        TallDao tad = new TallDao();
        String sql = "select * from tallas";
        ArrayList talla = (ArrayList)tad.consultaDatos();
        for (int i=0; i<talla.size(); i++){
            ta = (Talla)talla.get(i);
            fms.JcbCodigo.addItem(String.valueOf(ta.getCodigo()));
        }
    }
}
    



