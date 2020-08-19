package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.ResCoa;

public class ResCoaBean extends ResCoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String indEstado; 
	private String indEtapa; 
	private String indAcum;
	private String fechaEmision;
	private String fechaEmisionCompleta;
	private String fechaNotificacion;
	private String docRelacionado;//[oachahuic][PAS20165E210400270]
	private String docRelVisible;//[oachahuic][PAS20165E210400270]
	private Integer	numCorDocRel;//[oachahuic][PAS20165E210400270]

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
		
	public String getIndEstado() {
		return indEstado;
	}
	public void setIndEstado(String indEstado) {
		this.indEstado = indEstado;
	}
	public String getIndEtapa() {
		return indEtapa;
	}
	public void setIndEtapa(String indEtapa) {
		this.indEtapa = indEtapa;
	}
	public String getIndAcum() {
		return indAcum;
	}
	public void setIndAcum(String indAcum) {
		this.indAcum = indAcum;
	}

	public String getFechaEmisionCompleta() {
		return fechaEmisionCompleta;
	}

	public void setFechaEmisionCompleta(String fechaEmisionCompleta) {
		this.fechaEmisionCompleta = fechaEmisionCompleta;
	}

	public String getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(String fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public String getDocRelacionado() {
		return docRelacionado;
	}

	public void setDocRelacionado(String docRelacionado) {
		this.docRelacionado = docRelacionado;
	}

	public String getDocRelVisible() {
		return docRelVisible;
	}

	public void setDocRelVisible(String docRelVisible) {
		this.docRelVisible = docRelVisible;
	}

	public Integer getNumCorDocRel() {
		return numCorDocRel;
	}

	public void setNumCorDocRel(Integer numCorDocRel) {
		this.numCorDocRel = numCorDocRel;
	}
	
}
