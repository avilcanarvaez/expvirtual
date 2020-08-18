package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReAbrirExpVirtualData {
	@JsonProperty("codEstexpori")
	private String codEstexpori; 	// Estado del expediente origen
	@JsonProperty("codEtaexpori")
	private String codEtaexpori; 	// Etapa del expediente origen
	@JsonProperty("codTipexp")
	private String codTipexp;			// Código de Tipo de expediente
	@JsonProperty("codTipdoc")
	private String codTipdoc;			// Código de Tipo de documento a adjuntar
	@JsonProperty("numDoc")
	private String numDoc;				// Número del documento a adjuntar
	@JsonProperty(value = "fecDoc", required=false)
	private Date fecDoc;				// Fecha del documento a adjuntar
	@JsonProperty("codIdecm")
	private String codIdecm;			// Código del identificador del documento en el ECM
	@JsonProperty("binDoc")
	private String binDoc;				// Archivo binario adjunto
	@JsonProperty("fileExtension")
	private String fileExtension;		// Extensión del documento a adjuntar
	@JsonProperty("desArch")
	private String desArch;				// Nombre o descripción del archivo cargado
	@JsonProperty("metadata")
	private String metadata;			// Trama de palabras claves de búsqueda del documento
	@JsonProperty("codUsuregis")
	private String codUsuregis;			// Código del usuario solicitante
	@JsonProperty("indEmiAlerta")
	private String indEmiAlerta;		// Indicador de emisión de alerta
	
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
	public String getCodIdecm() {
		return codIdecm;
	}
	public void setCodIdecm(String codIdecm) {
		this.codIdecm = codIdecm;
	}
	public String getBinDoc() {
		return binDoc;
	}
	public void setBinDoc(String binDoc) {
		this.binDoc = binDoc;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getDesArch() {
		return desArch;
	}
	public void setDesArch(String desArch) {
		this.desArch = desArch;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public String getCodUsuregis() {
		return codUsuregis;
	}
	public void setCodUsuregis(String codUsuregis) {
		this.codUsuregis = codUsuregis;
	}
	public String getIndEmiAlerta() {
		return indEmiAlerta;
	}
	public void setIndEmiAlerta(String indEmiAlerta) {
		this.indEmiAlerta = indEmiAlerta;
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
	
}
