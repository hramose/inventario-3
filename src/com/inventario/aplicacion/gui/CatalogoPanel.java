package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.bd.ModeloItems;
import com.inventario.bd.NavegadorDatos;
import com.inventario.bd.Saver;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.interfaces.Vista;
import com.inventario.listener.MonitorListener;
import com.inventario.listener.PosicionListener;
import javax.swing.JComponent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Zulma
 * @param <T>
 */
public abstract class CatalogoPanel<T> extends javax.swing.JPanel implements Vista, PosicionListener {

    protected Aplicacion app;
    protected MonitorListener monitor;
    protected NavegadorDatos<T> nav;

    public CatalogoPanel() {
        initComponents();
    }

    @Override
    public void inicializar(Aplicacion app) throws InventarioException {
        this.app = app;
        this.monitor = new MonitorListener();

        inicializar();
    }

    @Override
    public JComponent getVista() {
        return this;
    }

    private void initNav() {
        Editor<T> editor = getEditor();
        jspEditor.setViewportView(editor.getComponente());
        jspEditor.getVerticalScrollBar().setUnitIncrement(50);

        ModeloItems<T> modelo = new ModeloItems(getProvider(), getSaver());
        nav = new NavegadorDatos<>(modelo, editor, monitor);
        
        jlItems.setModel(modelo);
        jlItems.addListSelectionListener(new ItemSelectionListener(this));
        nav.addPosicionListener(this);
        
        jpControles.add(new JLabelMonitor(monitor));
        jpControles.add(new JLabelEstado(nav));
        jpControles.add(new JLabelPosicion(nav));
        jpControles.add(new JPanelCtrlModelo(nav));
    }

    @Override
    public void activar() throws InventarioException {
        if (nav == null) {
            initNav();
        }
        nav.iniciar();
    }

    @Override
    public boolean ocultar() {
        try {
            return nav.puedeCerrar();
        } catch (InventarioException iex) {
            new AppMensaje(iex).mostrar(this);
            return false;
        }
    }

    @Override
    public void actualizarPosicion(int pos, int total) {
        if (pos <= 0 && pos < total) {
            jlItems.getSelectionModel().setSelectionInterval(pos, pos);
            jlItems.ensureIndexIsVisible(pos);
        } else {
            jlItems.getSelectionModel().clearSelection();
        }
    }

    protected abstract void inicializar();

    protected abstract Editor<T> getEditor();

    protected abstract Saver<T> getSaver();

    protected abstract DataProvider<T> getProvider();

    private class ItemSelectionListener implements ListSelectionListener {

        private JComponent padre;

        public ItemSelectionListener(JComponent padre) {
            this.padre = padre;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                if (nav.isAjustando()) {
                    int i = jlItems.getSelectedIndex();
                    if (i >= 0) {
                        try {
                            if (nav.puedeMover()) {
                                nav.moverA(i);
                            } else {
                                nav.regresar();
                            }
                        } catch (InventarioException iex) {
                            nav.regresar();
                            new AppMensaje(iex).mostrar(padre);
                        }
                    }
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpFiltro = new javax.swing.JPanel();
        jspItems = new javax.swing.JScrollPane();
        jlItems = new javax.swing.JList();
        jpContenido = new javax.swing.JPanel();
        jpControles = new javax.swing.JPanel();
        jspEditor = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());

        jpFiltro.setPreferredSize(new java.awt.Dimension(400, 10));

        javax.swing.GroupLayout jpFiltroLayout = new javax.swing.GroupLayout(jpFiltro);
        jpFiltro.setLayout(jpFiltroLayout);
        jpFiltroLayout.setHorizontalGroup(
            jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 871, Short.MAX_VALUE)
        );
        jpFiltroLayout.setVerticalGroup(
            jpFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        add(jpFiltro, java.awt.BorderLayout.PAGE_START);

        jlItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jspItems.setViewportView(jlItems);

        add(jspItems, java.awt.BorderLayout.LINE_START);

        jpContenido.setLayout(new java.awt.BorderLayout());

        jpControles.setPreferredSize(new java.awt.Dimension(612, 40));
        jpControles.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 0));
        jpContenido.add(jpControles, java.awt.BorderLayout.PAGE_START);
        jpContenido.add(jspEditor, java.awt.BorderLayout.CENTER);

        add(jpContenido, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jlItems;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpControles;
    private javax.swing.JPanel jpFiltro;
    private javax.swing.JScrollPane jspEditor;
    private javax.swing.JScrollPane jspItems;
    // End of variables declaration//GEN-END:variables

}
