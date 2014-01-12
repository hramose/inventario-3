package com.inventario.datas;

import com.inventario.bd.Transaccion;
import com.inventario.error.InventarioException;
import com.inventario.interfaces.AccesoDatos;
import com.inventario.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Zulma
 */
public class DatosGeneral implements AccesoDatos {

    private SessionFactory factory;

    @Override
    public void init(SessionFactory factory) {
        this.factory = factory;
    }

    public Usuario getUsuario(final String usuario, final String contra) throws InventarioException {
        return (Usuario) new Transaccion(factory) {
            
            @Override
            public Object execInTransaction(Session s, Object... params) throws InventarioException {
                return s.createCriteria(Usuario.class)
                        .add(Restrictions.eq("usuario", usuario))
                        .add(Restrictions.eq("contrasena", contra))
                        .uniqueResult();
            }
        }.exec();
    }

}
