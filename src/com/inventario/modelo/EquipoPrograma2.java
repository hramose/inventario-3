package com.inventario.modelo;

import com.inventario.util.Format;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Zulma
 */
@Entity
@Table(name = "equipo_programa")
public class EquipoPrograma2 implements Serializable {

	private static final long serialVersionUID = 14L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@ManyToOne
	@JoinColumn(name = "id_equipo")
	private EquipoComputo equipo;
	@ManyToOne
	@JoinColumn(name = "id_programa")
	private Programa programa;
	@Column
	@Temporal(TemporalType.DATE)
	private Date vigencia;

	public EquipoPrograma2() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EquipoComputo getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoComputo equipo) {
		this.equipo = equipo;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Date getVigencia() {
		return vigencia;
	}

	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	
	@Override
	public String toString() {
		return String.format("%s (%s)", programa, Format.DATE.format(vigencia));
	}

}
