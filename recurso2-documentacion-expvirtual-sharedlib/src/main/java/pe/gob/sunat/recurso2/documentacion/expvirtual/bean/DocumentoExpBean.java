package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.gob.sunat.framework.spring.util.ws.rest.Hyperlink;

public class DocumentoExpBean implements Serializable {
	private static final long serialVersionUID = 2378019429478945769L;

	private String numExpedo;
	private String numExpedv;
	private String numRuc;
	private String desTipdoc;
	private String numDoc;
	private String codIdecm;
	private Date fecEmidoc;
	private Date fecNotdoc;
	private Date fecCarg;
	private String codUsucarg;
	private String oriCargdoc;
	private List<Hyperlink> links = new ArrayList<Hyperlink>();

	public String getNumExpedo() {
		return numExpedo;
	}

	public void setNumExpedo(String numExpedo) {
		this.numExpedo = numExpedo;
	}

	public String getNumExpedv() {
		return numExpedv;
	}

	public void setNumExpedv(String numExpedv) {
		this.numExpedv = numExpedv;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getDesTipdoc() {
		return desTipdoc;
	}

	public void setDesTipdoc(String desTipdoc) {
		this.desTipdoc = desTipdoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getCodIdecm() {
		return codIdecm;
	}

	public void setCodIdecm(String codIdecm) {
		this.codIdecm = codIdecm;
	}

	public Date getFecEmidoc() {
		return fecEmidoc;
	}

	public void setFecEmidoc(Date fecEmidoc) {
		this.fecEmidoc = fecEmidoc;
	}

	public Date getFecNotdoc() {
		return fecNotdoc;
	}

	public void setFecNotdoc(Date fecNotdoc) {
		this.fecNotdoc = fecNotdoc;
	}

	public Date getFecCarg() {
		return fecCarg;
	}

	public void setFecCarg(Date fecCarg) {
		this.fecCarg = fecCarg;
	}

	public String getCodUsucarg() {
		return codUsucarg;
	}

	public void setCodUsucarg(String codUsucarg) {
		this.codUsucarg = codUsucarg;
	}

	public String getOriCargdoc() {
		return oriCargdoc;
	}

	public void setOriCargdoc(String oriCargdoc) {
		this.oriCargdoc = oriCargdoc;
	}

	public List<Hyperlink> getLinks() {
		return links;
	}

	public void setLinks(List<Hyperlink> links) {
		this.links = links;
	}
	
	public void add(Hyperlink link) {
		this.getLinks().add(link);
	}
}
