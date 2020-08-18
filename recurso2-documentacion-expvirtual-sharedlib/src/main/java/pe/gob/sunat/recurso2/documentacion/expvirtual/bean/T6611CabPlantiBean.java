package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6611CabPlanti;

public class T6611CabPlantiBean extends T6611CabPlanti implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer numOrden;
	private String estadoPlantilla;
	private String usuRegistro;
	private boolean idSeleccion;

	public String getEstadoPlantilla() {
		return estadoPlantilla;
	}

	public void setEstadoPlantilla(String estadoPlantilla) {
		this.estadoPlantilla = estadoPlantilla;
	}

	public Integer getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}

	public String getUsuRegistro() {
		return usuRegistro;
	}

	public void setUsuRegistro(String usuRegistro) {
		this.usuRegistro = usuRegistro;
	}

	public boolean isIdSeleccion() {
		return idSeleccion;
	}

	public void setIdSeleccion(boolean idSeleccion) {
		this.idSeleccion = idSeleccion;
	}
	
	
}
