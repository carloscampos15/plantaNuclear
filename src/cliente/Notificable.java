/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 * Interfaz que conecta la interzas con el cliente
 * @author Karen Castaño Orjuela Castaño
 * @author Carlos Alberto Campos Armero
 */
public interface Notificable {
    
    public void switchReactor(String mensaje, String action, String nombreCliente, String reactor);
    
    public void cargaReactor(String mensaje, String action, String nombreCliente, String reactor, String carga);
    
    public void repairReactor(String mensaje, String action, String nombreCliente, String reactor);
    
    public void sendMessage(String mensaje);
    
    public void login(String mensaje);
    
}
