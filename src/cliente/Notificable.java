/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author ACER 53F4
 */
public interface Notificable {
    
    public void switchReactor(String mensaje, String action, String nombreCliente, String reactor);
    
    public void login(String mensaje);
    
}
