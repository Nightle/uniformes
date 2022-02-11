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
import modelo.InventarioCami;
import modelo.ModificarCamisasDao;
import modelo.TallDao;
import modelo.Talla;
import vistas.FrmModificarCamisas;

/**
 *
 * @author troll
 */
public class ControladorModificarCamisas implements ActionListener{
    private FrmModificarCamisas fmc;
    private InventarioCami ic;
    private ModificarCamisasDao mcd;

    public ControladorModificarCamisas(FrmModificarCamisas fmc, InventarioCami ic, ModificarCamisasDao mcd) throws SQLException {
        this.fmc = fmc;
        this.ic = ic;
        this.mcd = mcd;
        this.fmc.JbGuardar.addActionListener(this);
        this.fmc.JbConsultar.addActionListener(this);
        this.fmc.JbActualizar.addActionListener(this);
        this.fmc.JbEliminar.addActionListener(this);
        this.fmc.JbSalir.addActionListener(this);
        this.fmc.JbNuevo.addActionListener(this);
        mostrarCategoria();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fmc.JbGuardar){
            String tall = fmc.JtxTalla.getText();
            int cod = Integer.parseInt(fmc.JcbCodigo.getSelectedItem().toString());
            String ccred = fmc.JtxCantidadCCRed.getText();
            String ccper = fmc.JtxCantidadCCPer.getText();
            String acred = fmc.JtxCantidadACRed.getText();
            String acv = fmc.JtxCantidadACV.getText();
            String acper = fmc.JtxCantidadACPer.getText();
           
            
            ic = new InventarioCami (tall,ccred,ccper,acred,acv,acper,cod);
            
            if(mcd.adicionar(ic)){
                JOptionPane.showMessageDialog(fmc, "Producto Registrado");
                limpiarcontroles();
            } else {
                JOptionPane.showMessageDialog(fmc, "Valide Los Datos");
            }
        }
        
        if(e.getSource()==fmc.JbSalir){
            int respuesta = JOptionPane.showConfirmDialog(fmc, "¿Esta seguro de salir?", "Fin", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
            fmc.dispose();
            }   
            
        }
        
        if(e.getSource()==fmc.JbConsultar){
            String tall = JOptionPane.showInputDialog(" ingrese la talla a consultar ");
            try {
                ic = mcd.consultardato(tall);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorModificarCamisas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(ic!=null){
                fmc.JtxTalla.setText(ic.getTalla());
                fmc.JcbCodigo.setSelectedItem(String.valueOf(ic.getCodigo()));
                fmc.JtxCantidadCCRed.setText(ic.getCantidadccred());
                fmc.JtxCantidadCCPer.setText(ic.getCantidadccper());
                fmc.JtxCantidadACRed.setText(ic.getCantidadacred());
                fmc.JtxCantidadACV.setText(ic.getCantidadacv());
                fmc.JtxCantidadACPer.setText(ic.getCantidadacper());
            } else {
                JOptionPane.showMessageDialog(fmc, "no esta en la base de datos");
            }
        }
        
        if(e.getSource()==fmc.JbActualizar){
            String tall = fmc.JtxTalla.getText();
            int cod = Integer.parseInt(fmc.JcbCodigo.getSelectedItem().toString());
            String ccred = fmc.JtxCantidadCCRed.getText();
            String ccper = fmc.JtxCantidadCCPer.getText();
            String acred = fmc.JtxCantidadACRed.getText();
            String acv = fmc.JtxCantidadACV.getText();
            String acper = fmc.JtxCantidadACPer.getText();
           
            
            ic = new InventarioCami (tall,ccred,ccper,acred,acv,acper,cod);
            
            if(mcd.actualizar(ic)){
                JOptionPane.showMessageDialog(fmc, "Producto Registrado");
            } else {
                JOptionPane.showMessageDialog(fmc, "Valide Los Datos");
            }
        }
        
        if(e.getSource()==fmc.JbEliminar){
        String cod = fmc.JtxTalla.getText();
            int respuesta = JOptionPane.showConfirmDialog(fmc, "¿Esta seguro?", "eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                if (mcd.eliminar(cod)){
                    JOptionPane.showMessageDialog(fmc,"Se eliminó con éxito");
                    limpiarcontroles();
                    
                }else{
                    JOptionPane.showMessageDialog(fmc, "No se eliminó - Verificar datos");
                }           
            }
        }
        
        if(e.getSource()==fmc.JbNuevo){
            limpiarcontroles();
        }
        
        Talla t = new Talla();
            TallDao td = new TallDao();
            int tal = Integer.parseInt(fmc.JcbCodigo.getSelectedItem().toString());
            
        try {
            t = td.consultaDato(tal);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorModificarCamisas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarcontroles(){
            fmc.JtxTalla.setText("");
            fmc.JtxCantidadCCRed.setText("");
            fmc.JtxCantidadCCPer.setText("");
            fmc.JtxCantidadACRed.setText("");
            fmc.JtxCantidadACV.setText("");
            fmc.JtxCantidadACPer.setText("");
            
            
        }
        
        public void mostrarCategoria() throws SQLException{
        Talla ta = new Talla();
        TallDao tad = new TallDao();
        String sql = "select * from tallas";
        ArrayList talla = (ArrayList)tad.consultaDatos();
        for (int i=0; i<talla.size(); i++){
            ta = (Talla)talla.get(i);
            fmc.JcbCodigo.addItem(String.valueOf(ta.getCodigo()));
        }
    }
    
    
}
