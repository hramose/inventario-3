package com.inventario.aplicacion.gui;

import com.inventario.listener.MonitorListener;
import com.inventario.listener.MonitorViewInterface;
import javax.swing.JLabel;

/**
 *
 * @author Zulma
 */
public class JLabelMonitor extends JLabel implements MonitorViewInterface {

    public JLabelMonitor(MonitorListener monitor) {
        super();
        monitor.addView(this);
    }

    @Override
    public void changeState(boolean state) {
        setText(state ? "*" : "");
    }
}
