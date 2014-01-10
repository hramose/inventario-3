package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.bd.NavegadorDatos;
import com.inventario.error.InventarioException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Zulma
 */
public class JPanelCtrlModelo extends javax.swing.JPanel implements ActionListener {

    private final NavegadorDatos nav;

    public JPanelCtrlModelo(NavegadorDatos nav) {
        initComponents();
        this.nav = nav;

        jbNuevo.addActionListener(this);
        jbBorrar.addActionListener(this);
        jbGuardar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(jbNuevo)) {
                nav.registrar();
            } else if (e.getSource().equals(jbBorrar)) {
                nav.borrar();
            } else if (e.getSource().equals(jbGuardar)) {
                nav.guardarCambios();
            }
        } catch (InventarioException ex) {
            new AppMensaje("Error del modelo", ex).mostrar(this);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbNuevo = new javax.swing.JButton();
        jbBorrar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 40));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 2));

        jbNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/add_page.png"))); // NOI18N
        jbNuevo.setPreferredSize(new java.awt.Dimension(40, 36));
        add(jbNuevo);

        jbBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/delete_page.png"))); // NOI18N
        jbBorrar.setPreferredSize(new java.awt.Dimension(40, 36));
        add(jbBorrar);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/inventario/iconos/save.png"))); // NOI18N
        jbGuardar.setPreferredSize(new java.awt.Dimension(40, 36));
        add(jbGuardar);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbBorrar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbNuevo;
    // End of variables declaration//GEN-END:variables

}
