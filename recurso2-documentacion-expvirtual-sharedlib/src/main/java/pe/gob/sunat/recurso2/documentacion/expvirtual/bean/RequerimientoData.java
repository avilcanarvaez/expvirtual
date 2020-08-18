package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RequerimientoData {
	@JsonProperty("numReqOrig")
	private String numReqOrig; // Número de requerimiento origen
	@JsonProperty(value = "fechaVenc", required=false)
	private Date fechaVenc; // Fecha de vencimiento de atención al requerimiento
	@JsonProperty(value = "fechaReq", required=false)
	private Date fechaReq; // Fecha del requerimiento
	@JsonProperty("codUsuReg")
	private String codUsuReg; // Usuario solicitante
	@JsonProperty("listDocumentos")
	private List<RequerimientoDocData> listDocumentos; // Json
	@JsonProperty("indEmiAlerta")
	private String indEmiAlerta; // Indicador de emisión de alerta
	@JsonProperty("numExpOrig")
	private String numExpOrig; //Numero de expediente origen
	
	@JsonProperty("codTipDocReq")
	private String codTipDocReq; //Numero de expediente origen
	@JsonProperty("codDepen")
	private String codDepen; //Numero de expediente origen
	
	
	
	
	
	
	public String getNumExpOrig() {
		return numExpOrig;
	}

	public void setNumExpOrig(String numExpOrig) {
		this.numExpOrig = numExpOrig;
	}

	public String getCodTipDocReq() {
		return codTipDocReq;
	}

	public void setCodTipDocReq(String codTipDocReq) {
		this.codTipDocReq = codTipDocReq;
	}

	public String getCodDepen() {
		return codDepen;
	}

	public void setCodDepen(String codDepen) {
		this.codDepen = codDepen;
	}

	public String getNumReqOrig() {
		return numReqOrig;
	}

	public void setNumReqOrig(String numReqOrig) {
		this.numReqOrig = numReqOrig;
	}

	public Date getFechaVenc() {
		return fechaVenc;
	}

	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	public Date getFechaReq() {
		return fechaReq;
	}

	public void setFechaReq(Date fechaReq) {
		this.fechaReq = fechaReq;
	}

	public String getCodUsuReg() {
		return codUsuReg;
	}

	public void setCodUsuReg(String codUsuReg) {
		this.codUsuReg = codUsuReg;
	}

	public List<RequerimientoDocData> getListDocumentos() {
		return listDocumentos;
	}

	public void setListDocumentos(List<RequerimientoDocData> listDocumentos) {
		this.listDocumentos = listDocumentos;
	}

	public String getIndEmiAlerta() {
		return indEmiAlerta;
	}

	public void setIndEmiAlerta(String indEmiAlerta) {
		this.indEmiAlerta = indEmiAlerta;
	}
}
