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
public class Talla {
    
    private int codigo;
    private String talla;

    public Talla() {
    }

    public Talla(int codigo, String talla) {
        this.codigo = codigo;
        this.talla = talla;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
    
    
    
}
