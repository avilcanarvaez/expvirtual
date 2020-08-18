package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T10459AccepExpVirt;

public class T10459AccepExpVirtBean extends T10459AccepExpVirt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String desColaborador;
	private String nombres;
	private String apellidosPaterno;
	private String apellidosMaterno;
	private String desEstado;
	private String desMotivo;
	private String desTipo;
	private String desPeriodo;
	
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
	
	
	//[PAS20201U210500082]: CVG - INI
	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}

	public String getDesMotivo() {
		return desMotivo;
	}

	public void setDesMotivo(String desMotivo) {
		this.desMotivo = desMotivo;
	}

	public String getDesTipo() {
		return desTipo;
	}

	public void setDesTipo(String desTipo) {
		this.desTipo = desTipo;
	}

	public String getDesPeriodo() {
		return desPeriodo;
	}

	public void setDesPeriodo(String desPeriodo) {
		this.desPeriodo = desPeriodo;
	}
	
	//[PAS20201U210500082]: CVG - FIN
	
	
	
}