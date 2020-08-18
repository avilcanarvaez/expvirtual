package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.Ddp;

public class DdpBean extends Ddp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String desDependencia;
	private String desTipoPersona;
	private String desActividadEconocimica;
	private String desCondicionDomicilio;
	private String desRegimenRenta;
	private String desEstado;
	
	public String getDesDependencia() {
		return desDependencia;
	}
	
	public void setDesDependencia(String desDependencia) {
		this.desDependencia = desDependencia;
	}
	
	public String getDesTipoPersona() {
		return desTipoPersona;
	}
	
	public void setDesTipoPersona(String desTipoPersona) {
		this.desTipoPersona = desTipoPersona;
	}
	
	public String getDesActividadEconocimica() {
		return desActividadEconocimica;
	}
	
	public void setDesActividadEconocimica(String desActividadEconocimica) {
		this.desActividadEconocimica = desActividadEconocimica;
	}
	
	public String getDesCondicionDomicilio() {
		return desCondicionDomicilio;
	}
	
	public void setDesCondicionDomicilio(String desCondicionDomicilio) {
		this.desCondicionDomicilio = desCondicionDomicilio;
	}
	
	public String getDesRegimenRenta() {
		return desRegimenRenta;
	}
	
	public void setDesRegimenRenta(String desRegimenRenta) {
		this.desRegimenRenta = desRegimenRenta;
	}
	
	public String getDesEstado() {
		return desEstado;
	}
	
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
}