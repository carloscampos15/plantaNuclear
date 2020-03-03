/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controllers.ReactorController;
import java.io.IOException;
import models.Reactor;
import redes.RedServidor;

/**
 *Se encarga de todo lo necesario para encerder el servidor
 * @author Karen Castaño Orjuela Castaño
 * @author Carlos Alberto Campos Armero
 */
public class PlantaServidor {
    private RedServidor redServidor;
    private Reactor[] reactores;
    private ReactorController controller;
  
    public PlantaServidor(RedServidor redServidor, Reactor[] reactores){
        this.redServidor = redServidor;
        this.controller = new ReactorController(reactores);
        this.redServidor.setController(this.controller);
    }
 /**
  * Se encarga de iniciar el servidor para que se conecte con el cliente
  */   
    public void ejecutarServidor(){
        try {
            this.redServidor.activar();
        } catch (IOException ex) {
            System.out.println("<< SERVER: NO PUDE INICIAR MIS SERVICIOS");
        }
    }
    
    public static void main(String[] args) {
        RedServidor red = new RedServidor();
        Reactor[] reactores = {new Reactor(1, "REACTOR1"), new Reactor(2, "REACTOR2"), new Reactor(3, "REACTOR3")};
        
        PlantaServidor servidor = new PlantaServidor(red, reactores);
        servidor.ejecutarServidor();
    }
}
