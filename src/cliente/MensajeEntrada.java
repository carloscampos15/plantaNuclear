/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Es el hilo encargado de estar siempre pendiente de recibir un mensaje
 * @author Karen Castaño Orjuela Castaño
 * @author Carlos Alberto Campos Armero
 */
public class MensajeEntrada extends Thread {
    
    private Notificable notificable;
    private DataInputStream entrada;

    public MensajeEntrada(Notificable notificable, DataInputStream entrada){
        this.notificable = notificable;
        this.entrada = entrada;
    }    
/**
 * Segun el mensaje recibido le informa a la interfaz notificable para que este realice el procedimeinto correspondiente
 * @param mensaje
 * @return 
 */
    public String procesarMensaje(String mensaje){
        String[] keys = mensaje.split(",");
        
        String[] nameKey = keys[0].split(":"); 
        String[] codeKey = keys[1].split(":");
        String[] actionKey = keys[2].split(":");
        String[] valueKey = keys[3].split(":");
        String[] reactorKey;
        
        switch(actionKey[1]){
            case "name":
                this.notificable.login(nameKey[1] + ": " + valueKey[1]);
                break;
            case "switch":
                reactorKey = keys[4].split(":");
                this.notificable.switchReactor(nameKey[1] + ": " + valueKey[1], valueKey[1], nameKey[1], reactorKey[1]);
                break;
            case "update":
                reactorKey = keys[4].split(":");
                String[] cargaKey = keys[5].split(":");
                this.notificable.cargaReactor(nameKey[1] + ": " + valueKey[1], valueKey[1], nameKey[1], reactorKey[1], cargaKey[1]);
                break;
        }
        
        
        return "";
    }

    @Override
    public void run() {
        while (true) {
            try {
                // read the message sent to this client
                String msg = entrada.readUTF();
                System.out.println(msg);
                procesarMensaje(msg);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

}
