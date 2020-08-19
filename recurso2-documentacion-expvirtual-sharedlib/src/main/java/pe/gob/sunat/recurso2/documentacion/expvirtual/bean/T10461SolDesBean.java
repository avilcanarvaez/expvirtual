package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T10461SolDes;

public class T10461SolDesBean extends T10461SolDes implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private String	descripcionEstSol; // descripcion cod_est_sol
	private String fechaGeneracion;
	private String nombrePersonaRegistro;
	private Integer numOrden;
	private String numExpedienteOrigen;
	
	public String getDescripcionEstSol() {
		return descripcionEstSol;
	}

	public void setDescripcionEstSol(String descripcionEstSol) {
		this.descripcionEstSol = descripcionEstSol;
	}

	public String getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getNombrePersonaRegistro() {
		return nombrePersonaRegistro;
	}

	public void setNombrePersonaRegistro(String nombrePersonaRegistro) {
		this.nombrePersonaRegistro = nombrePersonaRegistro;
	}

	public Integer getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}

	public String getNumExpedienteOrigen() {
		return numExpedienteOrigen;
	}

	public void setNumExpedienteOrigen(String numExpedienteOrigen) {
		this.numExpedienteOrigen = numExpedienteOrigen;
	}
	
	
}