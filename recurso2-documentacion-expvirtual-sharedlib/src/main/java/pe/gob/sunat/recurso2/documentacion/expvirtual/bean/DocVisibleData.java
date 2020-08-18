package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DocVisibleData {
	@JsonProperty("codTipdoc")
	private String codTipdoc; 	// Código de Tipo de documento
	@JsonProperty("numDoc")
	private String numDoc; 		// Número de documento
	@JsonProperty("codUsuario")
	private String codUsuario; 	// Código del Usuario
	@JsonProperty("indVisible")
	private String indVisible; 	// Indicador visibilidad

	public String getCodTipdoc() {
		return codTipdoc;
	}

	public void setCodTipdoc(String codTipdoc) {
		this.codTipdoc = codTipdoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getIndVisible() {
		return indVisible;
	}

	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}
}
