/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entis;

import java.util.Date;

/**
 *
 * @author Lorena Portillo
 */
public class Listado {
   
    private String dato1;
    private String dato2;
    private Date   datetime;

    public Listado() {
        
    }
    public Listado(String dato1, String dato2, Date datetime) {
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.datetime = datetime;
    }

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }


    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Listado{" + "dato1=" + dato1 + ", dato2=" + dato2 + ", datetime=" + datetime + '}';
    }
    
    
}
