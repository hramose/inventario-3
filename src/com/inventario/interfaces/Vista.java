package com.inventario.interfaces;

import com.inventario.error.InvException;
import javax.swing.JComponent;

/**
 *
 * @author Zulma
 */
public interface Vista {

    /**
     * Confiere acceso a los ajustes generales de la aplicación, así como
     * configura la pantalla para mostrarse por primera vez.
     *
     * @param app La aplicación principal.
     * @throws InvException Si no puede configurarse la pantalla.
     */
    public void inicializar(Aplicacion app) throws InvException;

    public JComponent getVista();

    public String getTitulo();

    public void activar() throws InvException;

    public boolean ocultar();
}
