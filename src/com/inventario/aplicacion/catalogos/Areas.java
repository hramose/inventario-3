package com.inventario.aplicacion.catalogos;

import com.inventario.aplicacion.gui.CatalogoPanel;
import com.inventario.bd.ListaProvider;
import com.inventario.bd.Saver;
import com.inventario.interfaces.DataProvider;
import com.inventario.interfaces.Editor;
import com.inventario.modelo.Area;

/**
 *
 * @author Zulma
 */
public class Areas extends CatalogoPanel<Area> {

    private AreasEditor editor;

    @Override
    protected void inicializar() {
        editor = new AreasEditor(monitor);
    }

    @Override
    protected Editor<Area> getEditor() {
        return editor;
    }

    @Override
    protected Saver<Area> getSaver() {
        return new Saver<Area>(app);
    }

    @Override
    protected DataProvider<Area> getProvider() {
        return new ListaProvider<Area>(app) {

            @Override
            public Class getClase() {
                return Area.class;
            }
        };
    }

    @Override
    public String getTitulo() {
        return "Areas";
    }

}
