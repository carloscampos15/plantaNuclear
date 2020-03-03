/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import cliente.MensajeEntrada;
import cliente.Notificable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class RedCliente {

    private Socket socket;
    private int puerto;
    private String destino;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private Notificable notificable;

    public RedCliente(int puerto, String destino) throws IOException {
        this.puerto = puerto;
        this.destino = destino;
        this.socket = new Socket(this.destino, this.puerto);
        entrada = new DataInputStream(socket.getInputStream());
        salida = new DataOutputStream(socket.getOutputStream());
    }

    public void procesar() {
        MensajeEntrada readMessage = new MensajeEntrada(this.notificable, entrada);
        readMessage.start();
    }

    public boolean updateNameUser(String name) throws IOException{
        String action = "name:"+name+",action:name,message:0,value:"+name;
        salida.writeUTF(action);
        salida.flush();
        return true;
    }
    
    public boolean switchReactor(String nombreUsuario, String reactor, String value) throws IOException {
        String action = "name:"+nombreUsuario+",action:switch,message:"+reactor+",value:"+value;
        salida.writeUTF(action);
        salida.flush();
        return true;
    }

    public Notificable getNotificable() {
        return notificable;
    }

    public void setNotificable(Notificable notificable) {
        this.notificable = notificable;
    }
}
