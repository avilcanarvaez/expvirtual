package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class DocOrigenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codRpta;
	private String desError;
	private String codTipDoc;
	private String numDoc;
	private Date fecEmiDoc;
	private Date fecNotDoc;
	private String codTipDocRel;
	private String numDocRel;
	private String numCorDocRel;
	private String indVisDocRel;
	private String strFecEmiDoc;
	private Integer numCorDoc;
	
	public Integer getNumCorDoc() {
		return numCorDoc;
	}
	public void setNumCorDoc(Integer numCorDoc) {
		this.numCorDoc = numCorDoc;
	}
	private DocNotSineBean docNotSine;
	
	public String getCodRpta() {
		return codRpta;
	}
	public void setCodRpta(String codRpta) {
		this.codRpta = codRpta;
	}
	public String getDesError() {
		return desError;
	}
	public void setDesError(String desError) {
		this.desError = desError;
	}
	public String getCodTipDoc() {
		return codTipDoc;
	}
	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public Date getFecEmiDoc() {
		return fecEmiDoc;
	}
	public void setFecEmiDoc(Date fecEmiDoc) {
		this.fecEmiDoc = fecEmiDoc;
	}
	public Date getFecNotDoc() {
		return fecNotDoc;
	}
	public void setFecNotDoc(Date fecNotDoc) {
		this.fecNotDoc = fecNotDoc;
	}
	public String getCodTipDocRel() {
		return codTipDocRel;
	}
	public void setCodTipDocRel(String codTipDocRel) {
		this.codTipDocRel = codTipDocRel;
	}
	public String getNumDocRel() {
		return numDocRel;
	}
	public void setNumDocRel(String numDocRel) {
		this.numDocRel = numDocRel;
	}
	public String getNumCorDocRel() {
		return numCorDocRel;
	}
	public void setNumCorDocRel(String numCorDocRel) {
		this.numCorDocRel = numCorDocRel;
	}
	public String getIndVisDocRel() {
		return indVisDocRel;
	}
	public void setIndVisDocRel(String indVisDocRel) {
		this.indVisDocRel = indVisDocRel;
	}
	public String getStrFecEmiDoc() {
		return strFecEmiDoc;
	}
	public void setStrFecEmiDoc(String strFecEmiDoc) {
		this.strFecEmiDoc = strFecEmiDoc;
	}
	public DocNotSineBean getDocNotSine() {
		return docNotSine;
	}
	public void setDocNotSine(DocNotSineBean docNotSine) {
		this.docNotSine = docNotSine;
	}

}
