package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;


import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6615ObsExp;

public class T6615ObsExpBean extends T6615ObsExp implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numOrden;
	private String nomUsuGeneraObs;
	private String nomUsuRegistraObs;

	/*Fechas en String*/
	private String fecRegistroObs;
	private String fecGeneracionObs;

	public String getNomUsuGeneraObs() {
		return nomUsuGeneraObs;
	}
	public void setNomUsuGeneraObs(String nomUsuGeneraObs) {
		this.nomUsuGeneraObs = nomUsuGeneraObs;
	}
	public String getNomUsuRegistraObs() {
		return nomUsuRegistraObs;
	}
	public void setNomUsuRegistraObs(String nomUsuRegistraObs) {
		this.nomUsuRegistraObs = nomUsuRegistraObs;
	}	
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	
	public String getFecRegistroObs() {
		return fecRegistroObs;
	}
	public void setFecRegistroObs(String fecRegistroObs) {
		this.fecRegistroObs = fecRegistroObs;
	}
	
	public String getFecGeneracionObs() {
		return fecGeneracionObs;
	}
	public void setFecGeneracionObs(String fecGeneracionObs) {
		this.fecGeneracionObs = fecGeneracionObs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
