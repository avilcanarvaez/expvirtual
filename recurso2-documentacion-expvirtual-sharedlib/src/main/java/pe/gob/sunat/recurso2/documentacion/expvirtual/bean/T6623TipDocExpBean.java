package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6623TipDocExp;

public class T6623TipDocExpBean extends T6623TipDocExp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* Descripciones de los campos */
	private String desTipoDocumento;
	private String desTipoExpediente;
	private String desTipoDocumentoCompuesto;
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private Boolean indSeleccion;
	private String estadoSeleccion;
	
	public String getDesTipoDocumento() {
		return desTipoDocumento;
	}
	
	public void setDesTipoDocumento(String desTipoDocumento) {
		this.desTipoDocumento = desTipoDocumento;
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
	
	public String getDesTipoDocumentoCompuesto() {
		return desTipoDocumentoCompuesto;
	}
	
	public void setDesTipoDocumentoCompuesto(String desTipoDocumentoCompuesto) {
		this.desTipoDocumentoCompuesto = desTipoDocumentoCompuesto;
	}

	public String getEstadoSeleccion() {
		return estadoSeleccion;
	}

	public void setEstadoSeleccion(String estadoSeleccion) {
		this.estadoSeleccion = estadoSeleccion;
	}
	
}