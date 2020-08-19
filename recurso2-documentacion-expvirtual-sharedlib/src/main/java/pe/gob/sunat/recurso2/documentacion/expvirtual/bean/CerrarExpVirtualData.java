package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CerrarExpVirtualData {
	@JsonProperty("codEstexpori")	
	private String codEstexpori;		// Estado del expediente origen
	@JsonProperty("codEtaexpori")
	private String codEtaexpori;		// Etapa del expediente origen
	@JsonProperty("codEstcierrexp")
	private String codEstcierrexp;		// Código del estado de cierre
	@JsonProperty("codUsucierr")
	private String codUsucierr;			// Código del usuario que cierra el expediente virtual
	@JsonProperty("codTipdoc")
	private String codTipdoc;			// Código de Tipo de documento origen
	@JsonProperty("numDoc")
	private String numDoc;				// Número de documento origen
	@JsonProperty(value = "fecDoc", required=false)	
	private Date fecDoc;				// Fecha del documento origen
	@JsonProperty("desMotcierr")
	private String desMotcierr;			// Motivo de Cierre
	@JsonProperty("desSumilla")
	private String desSumilla;			// Sumilla
	@JsonProperty("binDoc")
	private String binDoc;				// Archivo binario adjunto
	@JsonProperty("codIdecm")
	private String codIdecm;			// Código de identificación del archivo en el ECM
	@JsonProperty("desArch")
	private String desArch;				// Nombre del archivo cargado
	@JsonProperty("fileExtension")
	private String fileExtension;		// Extensión del archivo
	@JsonProperty("metadata")
	private String metadata;			// Trama de palabras claves de búsqueda del documento
	@JsonProperty("indEmiAlerta")
	private String indEmiAlerta;		// Indicador de emisión de alerta
    //PAS20191U210500144 Inicio 400101 RF13 PSALDARRIAGA
	@JsonProperty(value = "fecCierre", required=false)	
	private Date fecCierre; 		    // Fecha de cierre
	//PAS20191U210500144 fin 400101 RF13 PSALDARRIAGA
	
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
	public String getCodEstcierrexp() {
		return codEstcierrexp;
	}
	public void setCodEstcierrexp(String codEstcierrexp) {
		this.codEstcierrexp = codEstcierrexp;
	}
	public String getCodUsucierr() {
		return codUsucierr;
	}
	public void setCodUsucierr(String codUsucierr) {
		this.codUsucierr = codUsucierr;
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
	public String getDesMotcierr() {
		return desMotcierr;
	}
	public void setDesMotcierr(String desMotcierr) {
		this.desMotcierr = desMotcierr;
	}
	public String getDesSumilla() {
		return desSumilla;
	}
	public void setDesSumilla(String desSumilla) {
		this.desSumilla = desSumilla;
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
	public String getDesArch() {
		return desArch;
	}
	public void setDesArch(String desArch) {
		this.desArch = desArch;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public String getIndEmiAlerta() {
		return indEmiAlerta;
	}
	public void setIndEmiAlerta(String indEmiAlerta) {
		this.indEmiAlerta = indEmiAlerta;
	}
    //PAS20191U210500144 Inicio 400101 RF13 PSALDARRIAGA
	public Date getFecCierre() {
		return fecCierre;
	}
	public void setFecCierre(Date fecCierre) {
		this.fecCierre = fecCierre;
	}
	//PAS20191U210500144 Fin 400101 RF13 PSALDARRIAGA	
}
