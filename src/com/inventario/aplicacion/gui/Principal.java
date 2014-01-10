package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.Configuracion;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class Principal extends javax.swing.JFrame implements Aplicacion {

    public static final Logger log = LoggerFactory.getLogger(Principal.class);
    private Map<String, Vista> vistas;

    private Vista vistaActual = null;
    private CardLayout cards;
    //
    private Configuracion conf;
    private SessionFactory hsFactory;

    public Principal(Configuracion conf, SessionFactory hsFactory) {
        initComponents();
        this.conf = conf;
        this.hsFactory = hsFactory;

        vistas = new HashMap<String, Vista>(20);
        cards = (CardLayout) jpContenido.getLayout();
    }

    public void mostrarTarea(String tarea) {
        if (vistaActual != null
                && (!vistaActual.ocultar() || vistaActual.getClass().getCanonicalName().equals(tarea))) {
            return; // No se puede cerrar la vista actual
        }

        Vista vista = vistas.get(tarea);
        if (vista == null) {
            try {
                vista = (Vista) Class.forName(tarea).newInstance();
                vista.inicializar(this);
                vistas.put(tarea, vista); // Agregar a la cache
                jpContenido.add(vista.getVista(), tarea); // Agregar al panel
            } catch (InstantiationException ex) {
                log.error(ex.getMessage(), ex);
            } catch (IllegalAccessException ex) {
                log.error(ex.getMessage(), ex);
            } catch (ClassNotFoundException ex) {
                log.error(ex.getMessage(), ex);
            } catch (InventarioException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        // Intentando mostrar
        if (vista != null) {
            try {
                // 
                vista.activar();
                cards.show(jpContenido, tarea);
            } catch (InventarioException ex) {
                log.error(ex.getMessage(), ex);
                // Mostrar un dialogo para decir que no se pudo mostrar la vista
            }
        }
    }

    @Override
    public SessionFactory getSessionFactory() {
        return hsFactory;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpContenido = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jmbMenu = new javax.swing.JMenuBar();
        jmAdmin = new javax.swing.JMenu();
        jmAcerca = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setState(javax.swing.JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jpContenido.setLayout(new java.awt.CardLayout());

        jLabel1.setText("jLabel1");
        jpContenido.add(jLabel1, "card2");

        getContentPane().add(jpContenido);

        jmAdmin.setText("Administración");
        jmbMenu.add(jmAdmin);

        jmAcerca.setText("Acerca de");
        jmbMenu.add(jmAcerca);

        setJMenuBar(jmbMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jmAcerca;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuBar jmbMenu;
    private javax.swing.JPanel jpContenido;
    // End of variables declaration//GEN-END:variables
}
