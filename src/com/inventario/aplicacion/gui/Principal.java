package com.inventario.aplicacion.gui;

import com.inventario.aplicacion.AppMensaje;
import com.inventario.aplicacion.Configuracion;
import com.inventario.aplicacion.InventarioApp;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.AccesoDatos;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import com.inventario.modelo.Usuario;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Zulma
 */
public class Principal extends javax.swing.JFrame implements Aplicacion, ActionListener {

    public static final Logger log = LoggerFactory.getLogger(Principal.class);
    private Map<String, Vista> vistas;
    private Map<String, AccesoDatos> datos;

    private Usuario usuario;
    private Vista vistaActual = null;
    private final CardLayout cards;
    //
    private Configuracion conf;
    private SessionFactory hsFactory;

    public Principal(Configuracion conf, SessionFactory hsFactory) {
        initComponents();
        this.conf = conf;
        this.hsFactory = hsFactory;

        vistas = new HashMap<>(20);
        datos = new HashMap<>(5);
        cards = (CardLayout) jpContenido.getLayout();

        // Ocultar el menu
        jmbMenu.setVisible(false);

        //
        jmiAreas.setActionCommand(InventarioApp.AREAS);
        jmiAreas.addActionListener(this);

        // Primera pantalla, el login
        mostrarTarea(InventarioApp.LOGIN);
    }

    public final void mostrarTarea(String tarea) {
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
            } catch (InstantiationException |
                    IllegalAccessException |
                    ClassNotFoundException |
                    InventarioException ex) {
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
                new AppMensaje(ex).mostrar(this);
                // Mostrar un dialogo para decir que no se pudo mostrar la vista
            }
        }
    }

    @Override
    public AccesoDatos getDatos(String clase) {
        AccesoDatos ad = datos.get(clase);
        if (ad == null) {
            try {
                ad = (AccesoDatos) Class.forName(clase).newInstance();
                ad.init(hsFactory);
                datos.put(clase, ad);
            } catch (ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return ad;
    }

    @Override
    public void ocuparPantallaCompleta() {
        jmbMenu.setVisible(false);
        jpEncabezado.setVisible(false);
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mostrarTarea(e.getActionCommand()); // Ok! n_n
    }

    @Override
    public Usuario getUsuario() {
        return usuario; // Ok, usuario que ingreso a la aplicación
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        jpEncabezado = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jmbMenu = new javax.swing.JMenuBar();
        jmAdmin = new javax.swing.JMenu();
        jmiAreas = new javax.swing.JMenuItem();
        jmAcerca = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventario");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setState(javax.swing.JFrame.MAXIMIZED_BOTH);

        jpContenido.setLayout(new java.awt.CardLayout());

        jLabel1.setText("jLabel1");
        jpContenido.add(jLabel1, "card2");

        getContentPane().add(jpContenido, java.awt.BorderLayout.CENTER);

        jpEncabezado.setPreferredSize(new java.awt.Dimension(100, 40));
        jpEncabezado.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(java.awt.Color.white);
        jLabel2.setFont(jLabel2.getFont().deriveFont(jLabel2.getFont().getStyle() | java.awt.Font.BOLD, jLabel2.getFont().getSize()+5));
        jLabel2.setText("<titulo>");
        jLabel2.setOpaque(true);
        jpEncabezado.add(jLabel2, java.awt.BorderLayout.CENTER);

        jLabel3.setBackground(java.awt.Color.white);
        jLabel3.setFont(jLabel3.getFont().deriveFont((jLabel3.getFont().getStyle() | java.awt.Font.ITALIC)));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<usuario>");
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 15));
        jpEncabezado.add(jLabel3, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jpEncabezado, java.awt.BorderLayout.PAGE_START);

        jmAdmin.setText("Administración");

        jmiAreas.setText("Areas");
        jmAdmin.add(jmiAreas);

        jmbMenu.add(jmAdmin);

        jmAcerca.setText("Acerca de");
        jmbMenu.add(jmAcerca);

        setJMenuBar(jmbMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jmAcerca;
    private javax.swing.JMenu jmAdmin;
    private javax.swing.JMenuBar jmbMenu;
    private javax.swing.JMenuItem jmiAreas;
    private javax.swing.JPanel jpContenido;
    private javax.swing.JPanel jpEncabezado;
    // End of variables declaration//GEN-END:variables
}
