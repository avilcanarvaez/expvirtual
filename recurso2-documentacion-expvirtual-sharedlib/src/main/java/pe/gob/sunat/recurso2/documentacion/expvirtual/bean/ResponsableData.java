package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponsableData {
	@JsonProperty("codColabnew")
	private String codColabnew;		// Código del Responsable reasignado
	
	@JsonProperty("codDepnew")
	private String codDepnew;		// Dependencia del Responsable reasignado
	
	@JsonProperty("codUsumodresp")
	private String codUsumodresp;	// Usuario que solicita el registro
	
	public String getCodColabnew() {
		return codColabnew;
	}
	public void setCodColabnew(String codColabnew) {
		this.codColabnew = codColabnew;
	}
	public String getCodDepnew() {
		return codDepnew;
	}
	public void setCodDepnew(String codDepnew) {
		this.codDepnew = codDepnew;
	}
	public String getCodUsumodresp() {
		return codUsumodresp;
	}
	public void setCodUsumodresp(String codUsumodresp) {
		this.codUsumodresp = codUsumodresp;
	}
}
