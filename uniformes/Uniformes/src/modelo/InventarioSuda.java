/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author troll
 */
public class InventarioSuda {
    private String talla;
    private String cantidadcc;
    private String cantidadac;
    private int codigo;

    public InventarioSuda() {
    }

    public InventarioSuda(String talla, String cantidadcc, String cantidadac, int codigo) {
        this.talla = talla;
        this.cantidadcc = cantidadcc;
        this.cantidadac = cantidadac;
        this.codigo = codigo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCantidadcc() {
        return cantidadcc;
    }

    public void setCantidadcc(String cantidadcc) {
        this.cantidadcc = cantidadcc;
    }

    public String getCantidadac() {
        return cantidadac;
    }

    public void setCantidadac(String cantidadac) {
        this.cantidadac = cantidadac;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    

    
    
    
}

    
