package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

public class CorreosBean implements Serializable {
	private static final long serialVersionUID = 1030346609505215783L;
	
	private String codPers;		//	cod_pers
	private String smtp;		//	smtp
	private String alias;		//	alias
	
	
	public String getCodPers() {
		return codPers;
	}
	public void setCodPers(String codPers) {
		this.codPers = codPers;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
}