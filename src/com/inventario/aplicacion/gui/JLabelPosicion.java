package com.inventario.aplicacion.gui;

import com.inventario.bd.NavegadorDatos;
import com.inventario.listener.PosicionListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Zulma
 */
public class JLabelPosicion extends JLabel implements PosicionListener {

    public JLabelPosicion(NavegadorDatos nav) {
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
        nav.addPosicionListener(this);
    }
    
    @Override
    public void actualizarPosicion(int pos, int total) {
        setText(String.format("<html><b>%s</b> de <b>%s</b></html>", pos, total));
    }

}
