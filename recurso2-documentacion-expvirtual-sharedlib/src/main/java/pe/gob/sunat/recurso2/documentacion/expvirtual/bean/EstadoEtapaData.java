package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EstadoEtapaData {
	@JsonProperty("codEstexpori")
	private String codEstexpori; // Estado del expediente origen
	@JsonProperty("codEtaexpori")
	private String codEtaexpori; // Etapa del expediente origen
	@JsonProperty("codUsuario")
	private String codUsuario; 	 // Código del usuario

	public String getCodEstexpori() {
		return codEstexpori;
	}

	public void setCodEstexpori(String codEstexpori) {
		this.codEstexpori = codEstexpori;
	}

	public String getCodEtaexpori() {
		return codEtaexpori;
	}

	public void setCodEtaexpori(String codEtaexpori) {
		this.codEtaexpori = codEtaexpori;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
}
