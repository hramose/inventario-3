package com.inventario.aplicacion.gui;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import javax.swing.JComponent;

/**
 *
 * @author
 */
public class Home extends javax.swing.JPanel implements Vista {

    
    public Home() {
        initComponents();
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        
    }

    @Override
    public JComponent getVista() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTitulo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void activar() throws InventarioException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean ocultar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
