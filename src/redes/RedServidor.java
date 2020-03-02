/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import controllers.ClientHandler;
import controllers.ReactorController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlo
 */
public class RedServidor {
    private ReactorController controller;
    private ServerSocket listenSocket;
    private int port;
    public static Vector<ClientHandler> clientes = new Vector<>();

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

    public void run() {
        try {
            int i = 0;
            while (true) {

                System.out.println("SERVER: Esperando clientes");

                Socket clientSocket = listenSocket.accept();

                System.out.println("SERVER: Cliente recibido");
                
                DataInputStream entrada = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream salida = new DataOutputStream(clientSocket.getOutputStream());
                
                System.out.println("SERVER: Creando nuevo controlador para este cliente");
                
                ClientHandler clientHandler = new ClientHandler(clientSocket, "NAME", entrada, salida, controller);
                this.setController(clientHandler.getController());
                
                Thread t = new Thread(clientHandler);
                clientes.add(clientHandler);
                t.start();
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(RedServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
