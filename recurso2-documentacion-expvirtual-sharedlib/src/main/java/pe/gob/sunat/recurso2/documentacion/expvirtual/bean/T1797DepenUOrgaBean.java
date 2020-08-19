package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T1797DepenUOrga;

public class T1797DepenUOrgaBean extends T1797DepenUOrga implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String desUnidadOrganizacional;
	private String desTipoDependencia;
	private String desDependencia;
	
	public String getDesUnidadOrganizacional() {
		return desUnidadOrganizacional;
	}
	
	public void setDesUnidadOrganizacional(String desUnidadOrganizacional) {
		this.desUnidadOrganizacional = desUnidadOrganizacional;
	}
	
	public String getDesTipoDependencia() {
		return desTipoDependencia;
	}
	
	public void setDesTipoDependencia(String desTipoDependencia) {
		this.desTipoDependencia = desTipoDependencia;
	}
	
	public String getDesDependencia() {
		return desDependencia;
	}
	
	public void setDesDependencia(String desDependencia) {
		this.desDependencia = desDependencia;
	}
	
}