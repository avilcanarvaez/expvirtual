package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6624TipExpProc;

public class T6624TipExpProcBean extends T6624TipExpProc implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* Descripciones de los campos */
	private String desProceso;
	private String desTipoExpediente;
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private Boolean indSeleccion;
	
	public String getDesProceso() {
		return desProceso;
	}
	
	public void setDesProceso(String desProceso) {
		this.desProceso = desProceso;
	}
	
	public String getDesTipoExpediente() {
		return desTipoExpediente;
	}
	
	public void setDesTipoExpediente(String desTipoExpediente) {
		this.desTipoExpediente = desTipoExpediente;
	}
	
	public Integer getNumOrden() {
		return numOrden;
	}
	
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	
	public Boolean getIndSeleccion() {
		return indSeleccion;
	}
	
	public void setIndSeleccion(Boolean indSeleccion) {
		this.indSeleccion = indSeleccion;
	}
	
}