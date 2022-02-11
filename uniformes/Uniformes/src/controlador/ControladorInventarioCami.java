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
import javax.swing.table.DefaultTableModel;
import modelo.InventarioCami;
import modelo.InventarioCamiDao;
import modelo.TallDao;
import modelo.Talla;
import vistas.FrmInventarioCamisas;

/**
 *
 * @author troll
 */
public class ControladorInventarioCami implements ActionListener{
    private InventarioCami cami1;
    private InventarioCamiDao camid;
    private FrmInventarioCamisas fcami;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorInventarioCami(InventarioCami cami1, InventarioCamiDao sudad, FrmInventarioCamisas fsuda) throws SQLException{
        this.cami1 = cami1;
        this.camid = sudad;
        this.fcami = fsuda;
        this.fcami.JcbTalla.addActionListener(this);
        this.fcami.JbSalir.addActionListener(this);
        this.fcami.JbModificar.addActionListener(this);
        mostrarCategoria();
        modelo.addColumn("TALLA");
        modelo.addColumn("CC RED");
        modelo.addColumn("CC PER");
        modelo.addColumn("AC RED");
        modelo.addColumn("AC V");
        modelo.addColumn("AC PER");
        fcami.JtbInventario.setModel(modelo);  
        
        ArrayList camisas = (ArrayList)camid.listarcamisas();
        
        for (int i=0; i<camisas.size();i++){
                String[] datos = new String[6];
                cami1 = (InventarioCami)camisas.get(i);
                datos[0] = cami1.getTalla();
                datos[1] = cami1.getCantidadccred();
                datos[2] = cami1.getCantidadccper();
                datos[3] = cami1.getCantidadacred();
                datos[4] = cami1.getCantidadacv();
                datos[5] = cami1.getCantidadacper();
                modelo.addRow(datos);
                
                
            }
        
        
    }
    
    public void mostrarCategoria() throws SQLException{
        Talla ta = new Talla();
        TallDao tad = new TallDao();
        String sql = "select * from tallas";
        ArrayList talla = (ArrayList)tad.consultaDatos();
        for (int i=0; i<talla.size(); i++){
            ta = (Talla)talla.get(i);
            fcami.JcbTalla.addItem(String.valueOf(ta.getCodigo()));
        }
    }
    
    public void limpiartabla(){
        while(fcami.JtbInventario.getRowCount()!=0){
            ((DefaultTableModel)fcami.JtbInventario.getModel()).removeRow(0);  
    }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fcami.JcbTalla){
            Talla t = new Talla();
            TallDao td = new TallDao();
            int tal = Integer.parseInt(fcami.JcbTalla.getSelectedItem().toString());
            
            try {
                t = td.consultaDato(tal);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioCami.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            fcami.JtxTalla.setText(t.getTalla());
            
            String sql = "select * from inventario_camisas where codigo=" + tal;
            ArrayList camisas = null;
            
            try {
                camisas = (ArrayList)camid.consultarDatos(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioCami.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            limpiartabla();
            
            for (int i=0; i<camisas.size();i++){
                String[] datos = new String[6];
                cami1 = (InventarioCami)camisas.get(i);
                datos[0] = cami1.getTalla();
                datos[1] = cami1.getCantidadccred();
                datos[2] = cami1.getCantidadccper();
                datos[3] = cami1.getCantidadacred();
                datos[4] = cami1.getCantidadacv();
                datos[5] = cami1.getCantidadacper();
                modelo.addRow(datos);
                
                
            }
            
        }
        
        if (e.getSource() == fcami.JbSalir){
            int respuesta = JOptionPane.showConfirmDialog(fcami, "¿Esta seguro de salir?", "Fin Inventario Camisas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                   fcami.dispose();
    
            
     
            
            }
            
       }
    }
}
