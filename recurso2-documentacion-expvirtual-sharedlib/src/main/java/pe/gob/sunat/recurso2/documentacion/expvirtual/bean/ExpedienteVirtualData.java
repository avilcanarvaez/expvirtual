package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpedienteVirtualData {
	@JsonProperty("numExpedo")
	private String numExpedo; 		// Número de expediente origen
	@JsonProperty("codEstexpori")
	private String codEstexpori; 	// Estado del expediente origen
	@JsonProperty("codEtaexpori")
	private String codEtaexpori; 	// Etapa del expediente origen
	@JsonProperty("codUsuregis")
	private String codUsuregis; 	// Código del usuario que solicita el registro del expediente virtual
	@JsonProperty("numRuc")
	private String numRuc; 			// Número de RUC
	@JsonProperty("codProc")
	private String codProc; 		// Código del proceso
	@JsonProperty("codTipexp")
	private String codTipexp; 		// Código de Tipo de expediente
	@JsonProperty("codTipdoc")
	private String codTipdoc; 		// Código de Tipo de documento origen
	@JsonProperty("numDoc")
	private String numDoc; 			// Número de documento origen
	@JsonProperty(value = "fecDoc", required=false)
	private Date fecDoc; 			// Fecha del documento origen
	@JsonProperty("codDep")
	private String codDep; 			// Dependencia del proceso de negocio
	@JsonProperty("binDoc")
	private String binDoc; 			// Archivo binario adjunto
	@JsonProperty("codIdecm")
	private String codIdecm; 		// Código de identificación del archivo en el ECM
	@JsonProperty("metadata")
	private String metadata; 		// Trama de palabras claves de búsqueda del documento
	@JsonProperty("desExpedv")
	private String desExpedv; 		// Descripción del Expediente Virtual
	@JsonProperty("desArch")
	private String desArch; 		// Nombre del archivo cargado
	@JsonProperty("codColab")
	private String codColab; 		// Código del responsable
	@JsonProperty("indEmiAlerta")
	private String indEmiAlerta; 	// Indicador de emisión de alerta
	
	//CVG Inicio
		@JsonProperty("codTipvinc")
		private String codTipvinc; 	// Indicador de emisión de alerta
		@JsonProperty("fechaVinc")
		private Date fechaVinc; 	// Indicador de emisión de alerta
		@JsonProperty("codUsuvinc")
		private String codUsuvinc; 	// Indicador de emisión de alerta
		//CVG Fin

	public String getNumExpedo() {
		return numExpedo;
	}

	public void setNumExpedo(String numExpedo) {
		this.numExpedo = numExpedo;
	}

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

	public String getCodUsuregis() {
		return codUsuregis;
	}

	public void setCodUsuregis(String codUsuregis) {
		this.codUsuregis = codUsuregis;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getCodProc() {
		return codProc;
	}

	public void setCodProc(String codProc) {
		this.codProc = codProc;
	}

	public String getCodTipexp() {
		return codTipexp;
	}

	public void setCodTipexp(String codTipexp) {
		this.codTipexp = codTipexp;
	}

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

	public Date getFecDoc() {
		return fecDoc;
	}

	public void setFecDoc(Date fecDoc) {
		this.fecDoc = fecDoc;
	}

	public String getCodDep() {
		return codDep;
	}

	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}

	public String getBinDoc() {
		return binDoc;
	}

	public void setBinDoc(String binDoc) {
		this.binDoc = binDoc;
	}

	public String getCodIdecm() {
		return codIdecm;
	}

	public void setCodIdecm(String codIdecm) {
		this.codIdecm = codIdecm;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public String getDesExpedv() {
		return desExpedv;
	}

	public void setDesExpedv(String desExpedv) {
		this.desExpedv = desExpedv;
	}

	public String getDesArch() {
		return desArch;
	}

	public void setDesArch(String desArch) {
		this.desArch = desArch;
	}

	public String getCodColab() {
		return codColab;
	}

	public void setCodColab(String codColab) {
		this.codColab = codColab;
	}

	public String getIndEmiAlerta() {
		return indEmiAlerta;
	}

	public void setIndEmiAlerta(String indEmiAlerta) {
		this.indEmiAlerta = indEmiAlerta;
	}
	
	//Inicio CVG
		public String getCodTipvinc() {
			return codTipvinc;
		}

		public void setCodTipvinc(String codTipvinc) {
			this.codTipvinc = codTipvinc;
		}

		public Date getFechaVinc() {
			return fechaVinc;
		}

		public void setFechaVinc(Date fechaVinc) {
			this.fechaVinc = fechaVinc;
		}

		public String getCodUsuvinc() {
			return codUsuvinc;
		}

		public void setCodUsuvinc(String codUsuvinc) {
			this.codUsuvinc = codUsuvinc;
		}
		
		//Fin CVG
}
