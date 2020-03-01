/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import controllers.ReactorController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class RedServidor extends Observable implements Runnable {

    private ReactorController controller;
    private ServerSocket listenSocket;
    private int port;

    public RedServidor() {
        this.port = 9090;
    }

    public void setController(ReactorController controller) {
        this.controller = controller;
    }

    public void activar() throws IOException {
        System.out.println("<< SERVER: binding port");
        this.listenSocket = new ServerSocket(port);
        this.run();
    }

    public String recibir(Socket clientSocket) throws IOException {
        DataInputStream entrada = new DataInputStream(clientSocket.getInputStream());
        return entrada.readUTF();
    }

    public void enviar(Socket clientSocket, String mensaje) throws IOException {
        DataOutputStream salida = new DataOutputStream(clientSocket.getOutputStream());
        salida.writeUTF(mensaje);
        salida.flush();
    }

    @Override
    public void run() {
        try {
            while (true) {

                System.out.println("SERVER: Esperando clientes");

                Socket clientSocket = listenSocket.accept();

                System.out.println("SERVER: client received");

                while (true) {
                    System.out.println("SERVER: Esperando transferencia del cliente");
                    String data = recibir(clientSocket);

                    String mensaje = controller.recibirComando(data);

                    enviar(clientSocket, mensaje);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(RedServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
