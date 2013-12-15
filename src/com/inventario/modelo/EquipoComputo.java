package com.inventario.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Zulma
 */
@Entity
@Table(name = "equipo_computo")
public class EquipoComputo implements Serializable{

    private static final long serialVersionUID = 3L;
    
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column
    private String serie;
    @Column(name = "activo_fijo")
    private String activoFijo;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column
    private String descripcion;
    @Column
    private byte[] caracteristicas;
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoEquipo tipo;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
    @Column
    private String estado;

    public EquipoComputo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(String activoFijo) {
        this.activoFijo = activoFijo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(byte[] caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public TipoEquipo getTipo() {
        return tipo;
    }

    public void setTipo(TipoEquipo tipo) {
        this.tipo = tipo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
