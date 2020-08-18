package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6612DetPlanti;

public class T6612DetPlantiBean extends T6612DetPlanti implements Serializable {
//Inicio [gangles 23/05/2016] Se agrega las descripciones de los documentos, formatos y reportes adicionales
private static final long serialVersionUID = 1L;
	
	private String desTipdoc;
	private String desFormato;
	private String desRubro;
	private Integer numOrden;
	
	public String getDesTipdoc() {
		return desTipdoc;
	}
	public void setDesTipdoc(String desTipdoc) {
		this.desTipdoc = desTipdoc;
	}
	public String getDesFormato() {
		return desFormato;
	}
	public void setDesFormato(String desFormato) {
		this.desFormato = desFormato;
	}
	public String getDesRubro() {
		return desRubro;
	}
	public void setDesRubro(String desRubro) {
		this.desRubro = desRubro;
	}
	
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
//Fin [gangles 23/05/2016]
}