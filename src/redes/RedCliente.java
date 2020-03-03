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
 * @author Karen Castaño Orjuela Castaño
 * @author Carlos Alberto Campos Armero
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

    /**
     *
     */
    public void procesar() {
        MensajeEntrada readMessage = new MensajeEntrada(this.notificable, entrada);
        readMessage.start();
    }

    /**
     *
     * @param name
     * @return
     * @throws IOException
     */
    public boolean updateNameUser(String name) throws IOException {
        String action = "name:" + name + ",action:name,message:0,value:" + name;
        salida.writeUTF(action);
        salida.flush();
        return true;
    }

    /**
     *
     * @param nombreUsuario
     * @param reactor
     * @param value
     * @return
     * @throws IOException
     */
    public boolean switchReactor(String nombreUsuario, String reactor, String value) throws IOException {
        String action = "name:" + nombreUsuario + ",action:switch,message:" + reactor + ",value:" + value;
        salida.writeUTF(action);
        salida.flush();
        return true;
    }

    /**
     *
     * @param nombreUsuario
     * @param reactor
     * @param value
     * @return
     * @throws IOException
     */
    public boolean cargaReactor(String nombreUsuario, String reactor, String value) throws IOException {
        String action = "name:" + nombreUsuario + ",action:update,message:" + reactor + ",value:" + value;
        salida.writeUTF(action);
        salida.flush();
        return true;
    }

    public boolean repairReactor(String nombreUsuario, String reactor) throws IOException {
        String action = "name:" + nombreUsuario + ",action:repair,message:" + reactor + ",value:" + 0;
        salida.writeUTF(action);
        salida.flush();
        return true;
    }

    public boolean sendMessage(String nombreUsuario, String mensaje) throws IOException {
        String action = "name:" + nombreUsuario + ",action:message,message:0,value:"+mensaje;
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
