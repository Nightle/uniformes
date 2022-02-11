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
import modelo.InventarioSuda;
import modelo.InventarioSudaDao;
import modelo.TallDao;
import modelo.Talla;
import vistas.FrmInventarioSuda;

/**
 *
 * @author troll
 */
public class ControladorInventarioSuda implements ActionListener{
    private InventarioSuda suda1;
    private InventarioSudaDao sudad;
    private FrmInventarioSuda fsuda;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorInventarioSuda(InventarioSuda suda1, InventarioSudaDao sudad, FrmInventarioSuda fsuda) throws SQLException{
        this.suda1 = suda1;
        this.sudad = sudad;
        this.fsuda = fsuda;
        this.fsuda.JcbTalla.addActionListener(this);
        this.fsuda.JbSalir.addActionListener(this);
        mostrarCategoria();
        modelo.addColumn("TALLA");
        modelo.addColumn("CANTIDAD CC");
        modelo.addColumn("CANTIDAD AC");
        fsuda.JtbInventario.setModel(modelo);
        
        ArrayList sudaderas = (ArrayList)sudad.listarsudaderas();
        
        for (int i=0; i<sudaderas.size();i++){
                String[] datos = new String[3];
                suda1 = (InventarioSuda)sudaderas.get(i);
                datos[0] = suda1.getTalla();
                datos[1] = suda1.getCantidadcc();
                datos[2] = suda1.getCantidadac();
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
            fsuda.JcbTalla.addItem(String.valueOf(ta.getCodigo()));
        }
    }
        
    public void limpiartabla(){
        while(fsuda.JtbInventario.getRowCount()!=0){
            ((DefaultTableModel)fsuda.JtbInventario.getModel()).removeRow(0);  
    }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fsuda.JcbTalla){
            Talla t = new Talla();
            TallDao td = new TallDao();
            int tal = Integer.parseInt(fsuda.JcbTalla.getSelectedItem().toString());
            
            try {
                t = td.consultaDato(tal);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioSuda.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            fsuda.JtxConsulta.setText(t.getTalla());
            
            String sql = "select * from inventario_sudaderas where codigo=" + tal;
            ArrayList sudaderas = null;
            
            try {
                sudaderas = (ArrayList)sudad.consultarDatos(sql);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInventarioSuda.class.getName()).log(Level.SEVERE, null, ex);
            }
            limpiartabla();
            
            for (int i=0; i<sudaderas.size();i++){
                String[] datos = new String[3];
                suda1 = (InventarioSuda)sudaderas.get(i);
                datos[0] = suda1.getTalla();
                datos[1] = suda1.getCantidadcc();
                datos[2] = suda1.getCantidadac();
                modelo.addRow(datos);
                
                
            }
        }
    
    
    if (e.getSource() == fsuda.JbSalir){
            int respuesta = JOptionPane.showConfirmDialog(fsuda, "¿Esta seguro de salir?", "Fin Inventario Sudaderas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                   fsuda.dispose();
            }
        }
    
    }
}
