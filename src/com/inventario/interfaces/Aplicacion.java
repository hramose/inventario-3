package com.inventario.interfaces;

import com.inventario.modelo.Usuario;
import org.hibernate.SessionFactory;

/**
 *
 * @author Zulma
 */
public interface Aplicacion {

    public SessionFactory getSessionFactory();
    
    public Usuario getUsuario();
    
    public void setUsuario(Usuario usuario);
    
    public AccesoDatos getDatos(String clase);
    
    public void ocuparPantallaCompleta();
    
}
