package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6621RespExpVirt;

public class T6621RespExpVirtBean extends T6621RespExpVirt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String desColaborador;
	private String nombres;
	private String apellidosPaterno;
	private String apellidosMaterno;
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private Boolean indSeleccion;
	
	//[PAS20191U210500291]: JMC - INI
	private String unidOrganizacional;
	//[PAS20191U210500291]: JMC - FIN
	
	public String getDesColaborador() {
		return desColaborador;
	}
	
	public void setDesColaborador(String desColaborador) {
		this.desColaborador = desColaborador;
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidosPaterno() {
		return apellidosPaterno;
	}

	public void setApellidosPaterno(String apellidosPaterno) {
		this.apellidosPaterno = apellidosPaterno;
	}

	public String getApellidosMaterno() {
		return apellidosMaterno;
	}

	public void setApellidosMaterno(String apellidosMaterno) {
		this.apellidosMaterno = apellidosMaterno;
	}
	
	//[PAS20191U210500291]: JMC - INI
	public String getUnidOrganizacional() {
		return unidOrganizacional;
	}

	public void setUnidOrganizacional(String unidOrganizacional) {
		this.unidOrganizacional = unidOrganizacional;
	}
	//[PAS20191U210500291]: JMC - FIN
	
}