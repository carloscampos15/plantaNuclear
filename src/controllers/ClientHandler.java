/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;
import redes.RedServidor;

/**
 *
 * @author Karen Castaño Orjuela Castaño
 * @author Carlos Alberto Campos Armero
 */
public class ClientHandler implements Runnable {

    private String name;
    private final DataInputStream entrada;
    private final DataOutputStream salida;
    private Socket clientSocket;
    private boolean isloggedin;
    private ReactorController controller;

    /**
     *
     * @param clientSocket
     * @param name
     * @param entrada
     * @param salida
     * @param controller
     */
    public ClientHandler(Socket clientSocket, String name, DataInputStream entrada, DataOutputStream salida, ReactorController controller) {
        this.entrada = entrada;
        this.salida = salida;
        this.name = name;
        this.clientSocket = clientSocket;
        this.isloggedin = true;
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public boolean isIsloggedin() {
        return isloggedin;
    }

    public void setIsloggedin(boolean isloggedin) {
        this.isloggedin = isloggedin;
    }

    @Override
    public void run() {
        String received;
        while (true) {
            try {
                // receive the string 
                received = entrada.readUTF();
                String[] keys = received.split(",");
                String[] nameKey = keys[0].split(":");
                String[] actionKey = keys[1].split(":");
                String[] messageKey = keys[2].split(":");
                String[] valueKey = keys[3].split(":");

                if (actionKey[1].equals("name")) {
                    this.name = valueKey[1];
                }

                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.clientSocket.close();
                    break;
                }

                String mensaje = controller.recibirComando(received);

                if (mensaje == null) {
                    mensaje = "code:200,action:name,value:EL USUARIO HA INIADO SESION";
                }

                // search for the recipient in the connected devices list. 
                // ar is the vector storing client of active users 
                for (ClientHandler mc : RedServidor.clientes) {
                    mc.salida.writeUTF("name:" + nameKey[1] + "," + mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            // closing resources 
            this.entrada.close();
            this.salida.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ReactorController getController() {
        return controller;
    }
}
