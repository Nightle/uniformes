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
import modelo.InventarioChaq;
import modelo.InventarioChaqDao;
import modelo.TallDao;
import modelo.Talla;
import vistas.FrmInventarioChaq;

/**
 *
 * @author troll
 */
public class ControladorInventarioChaq implements ActionListener{
    private InventarioChaq chaq1;
    private InventarioChaqDao chaqd;
    private FrmInventarioChaq fchaq;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorInventarioChaq(InventarioChaq chaq1, InventarioChaqDao chaqd, FrmInventarioChaq fchaq) throws SQLException {
        this.chaq1 = chaq1;
        this.chaqd = chaqd;
        this.fchaq = fchaq;
        this.fchaq.JcbTalla.addActionListener(this);
        this.fchaq.JbSalir.addActionListener(this);
        this.fchaq.JbModificar.addActionListener(this);
        mostrarCategoria();
        modelo.addColumn("TALLA");
        modelo.addColumn("CANTIDAD");
        fchaq.JtbInventario.setModel(modelo);
        
        ArrayList chaquetas = (ArrayList)chaqd.listarchaquetas();
        
        for (int i=0; i<chaquetas.size();i++){
                String[] datos = new String[2];
                chaq1 = (InventarioChaq)chaquetas.get(i);
                datos[0] = chaq1.getTalla();
                datos[1] = chaq1.getCantidad();
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
            fchaq.JcbTalla.addItem(String.valueOf(ta.getCodigo()));
        }
    }
    
    public void limpiartabla(){
        while(fchaq.JtbInventario.getRowCount()!=0){
            ((DefaultTableModel)fchaq.JtbInventario.getModel()).removeRow(0);  
    }
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fchaq.JcbTalla){
            Talla t = new Talla();
            TallDao td = new TallDao();
            int tal = Integer.parseInt(fchaq.JcbTalla.getSelectedItem().toString());
            
            try {
                t = td.consultaDato(tal);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioChaq.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            fchaq.JtxTalla.setText(t.getTalla());
            
            String sql = "select * from inventario_chaquetas where codigo=" + tal;
            ArrayList chaquetas = null;
            
            try {
                chaquetas = (ArrayList)chaqd.consultarDatos(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioChaq.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            limpiartabla();
            
            for (int i=0; i<chaquetas.size();i++){
                String[] datos = new String[2];
                chaq1 = (InventarioChaq)chaquetas.get(i);
                datos[0] = chaq1.getTalla();
                datos[1] = chaq1.getCantidad();
                modelo.addRow(datos);
            }
        }
        
        if (e.getSource() == fchaq.JbSalir){
            int respuesta = JOptionPane.showConfirmDialog(fchaq, "¿Esta seguro de salir?", "Fin Inventario Chaquetas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                   fchaq.dispose();
            }
        }
        
        
    }

}