package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T02Perdp;

public class T02PerdpBean extends T02Perdp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String desNombreCompleto;
	private String desUnidadOrganizacional;
	private String codDependencia;
	private String desDependencia;
	
	public String getDesNombreCompleto() {
		return desNombreCompleto;
	}
	
	public void setDesNombreCompleto(String desNombreCompleto) {
		this.desNombreCompleto = desNombreCompleto;
	}
	
	public String getDesUnidadOrganizacional() {
		return desUnidadOrganizacional;
	}
	
	public void setDesUnidadOrganizacional(String desUnidadOrganizacional) {
		this.desUnidadOrganizacional = desUnidadOrganizacional;
	}
	
	public String getCodDependencia() {
		return codDependencia;
	}
	
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
	
	public String getDesDependencia() {
		return desDependencia;
	}
	
	public void setDesDependencia(String desDependencia) {
		this.desDependencia = desDependencia;
	}
	
}