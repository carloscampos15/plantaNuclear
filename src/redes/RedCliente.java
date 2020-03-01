/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

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
public class RedCliente{

    private Socket socket;
    private int puerto;
    private String destino;

    public RedCliente(int puerto, String destino) throws IOException {
        this.puerto = puerto;
        this.destino = destino;
        this.socket = new Socket(this.destino, this.puerto);
    }

    public String recibir() throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        return entrada.readUTF();
    }

    public void enviar(String mensaje) throws IOException {
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        salida.writeUTF(mensaje);
        salida.flush();
    }
    
    public void procesar() {
        try {
            Scanner myObj = new Scanner(System.in);
            while (true) {
                System.out.println("CLIENT: sending data to server");
                String mensaje = myObj.nextLine();
                enviar(mensaje);

                System.out.println("CLIENT: receiving data from server");
                String output = recibir();

                System.out.println("RESPONSE: " + output);
            }
        } catch (IOException ex) {
            Logger.getLogger(RedCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
