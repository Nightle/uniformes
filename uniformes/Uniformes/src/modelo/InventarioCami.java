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
public class InventarioCami {
    private String talla;
    private String cantidadccred;
    private String cantidadccper;
    private String cantidadacred;
    private String cantidadacv;
    private String cantidadacper;
    private int codigo;

    public InventarioCami() {
    }

    public InventarioCami(String talla, String cantidadccred, String cantidadccper, String cantidadacred, String cantidadacv, String cantidadacper, int codigo) {
        this.talla = talla;
        this.cantidadccred = cantidadccred;
        this.cantidadccper = cantidadccper;
        this.cantidadacred = cantidadacred;
        this.cantidadacv = cantidadacv;
        this.cantidadacper = cantidadacper;
        this.codigo = codigo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCantidadccred() {
        return cantidadccred;
    }

    public void setCantidadccred(String cantidadccred) {
        this.cantidadccred = cantidadccred;
    }

    public String getCantidadccper() {
        return cantidadccper;
    }

    public void setCantidadccper(String cantidadccper) {
        this.cantidadccper = cantidadccper;
    }

    public String getCantidadacred() {
        return cantidadacred;
    }

    public void setCantidadacred(String cantidadacred) {
        this.cantidadacred = cantidadacred;
    }

    public String getCantidadacv() {
        return cantidadacv;
    }

    public void setCantidadacv(String cantidadacv) {
        this.cantidadacv = cantidadacv;
    }

    public String getCantidadacper() {
        return cantidadacper;
    }

    public void setCantidadacper(String cantidadacper) {
        this.cantidadacper = cantidadacper;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    
    
    
    
}
