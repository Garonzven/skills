/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Usuario
 */
class Configuracion {
    private String correoenvia;
    private String passwordCorreo;
    private int opcionenvio;

    public Configuracion(String correoenvia, String passwordCorreo, int opcionenvio) {
        this.correoenvia = correoenvia;
        this.passwordCorreo = passwordCorreo;
        this.opcionenvio = opcionenvio;
    }

    public Configuracion() {
    }

    public String getCorreoenvia() {
        return correoenvia;
    }

    public void setCorreoenvia(String correoenvia) {
        this.correoenvia = correoenvia;
    }

    public String getPasswordCorreo() {
        return passwordCorreo;
    }

    public void setPasswordCorreo(String passwordCorreo) {
        this.passwordCorreo = passwordCorreo;
    }

    public int getOpcionenvio() {
        return opcionenvio;
    }

    public void setOpcionenvio(int opcionenvio) {
        this.opcionenvio = opcionenvio;
    }

    @Override
    public String toString() {
        return "Configuracion{" + "correoenvia=" + correoenvia + ", passwordCorreo=" + passwordCorreo + ", opcionenvio=" + opcionenvio + '}';
    }
    
}
