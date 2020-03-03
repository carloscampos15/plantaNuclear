/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import redes.RedCliente;

/**
 *
 * @author carlo
 */
public class Cliente{

    private RedCliente redCliente;
    private String nombre;
    private int puerto;

    public Cliente(String nombre) throws IOException {      
        this.nombre = nombre;
        this.puerto = 9090;
        redCliente = new RedCliente(this.puerto, "127.0.0.1");
    }

    public String getNombre() {
        return nombre;
    }
    
    public boolean updateName(String name) throws IOException{
        this.redCliente.updateNameUser(name);
        return true;
    }
    
    public boolean switchReactor(String nombreUsuario, String reactor, String value) throws IOException{
        this.redCliente.switchReactor(nombreUsuario, reactor, value);
        return true;
    }
    
    public void ejecutarCliente(){
        this.redCliente.procesar();
    }
    
    public void setNotificableRed(Notificable notificable){
        this.redCliente.setNotificable(notificable);
    }
}
