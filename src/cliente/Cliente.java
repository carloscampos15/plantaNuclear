/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import redes.RedCliente;

/**
 * Se encarga de tener todos los metodos necesarios para generar un nuevo cliente y poder editarlo llamando estos metodos
 * @author Karen Castaño Orjuela Castaño
 * @author Carlos Alberto Campos Armero
 */
public class Cliente{

    private RedCliente redCliente;
    private String nombre;
    private int puerto;

    public Cliente(String nombre) throws IOException {      
        this.nombre = nombre;
        this.puerto = 9090;
        redCliente = new RedCliente(this.puerto, "192.168.1.66");
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
    
    public boolean cargaReactor(String nombreUsuario, String reactor, String value) throws IOException{
        this.redCliente.cargaReactor(nombreUsuario, reactor, value);
        return true;
    }
    
    public void ejecutarCliente(){
        this.redCliente.procesar();
    }
    
    public void setNotificableRed(Notificable notificable){
        this.redCliente.setNotificable(notificable);
    }
}
