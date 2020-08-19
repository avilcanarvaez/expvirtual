package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocExpVirtualData {
	@JsonProperty("codEstexpori")
	private String codEstexpori;		// Estado del expediente origen
	@JsonProperty("codEtaexpori")
	private String codEtaexpori; 		// Etapa del expediente origen
	@JsonProperty("codTipexp")
	private String codTipexp;			// Código de Tipo de expediente
	@JsonProperty("codTipdoc")
	private String codTipdoc;			// Código de Tipo de documento a adjuntar
	@JsonProperty("numDoc")
	private String numDoc;				// Número del documento a adjuntar
	@JsonProperty(value = "fecDoc", required=false)
	private Date fecDoc;				// Fecha del documento a adjuntar
	@JsonProperty("binDoc")
	private String binDoc;				// Archivo binario adjunto
	@JsonProperty("codIdecm")
	private String codIdecm;			// Código del identificador del documento en el ECM
	@JsonProperty("fileExtension")
	private String fileExtension;		// Extensión del documento a adjuntar
	@JsonProperty("desArch")
	private String desArch;				// Nombre o descripción del archivo cargado
	@JsonProperty("metadata")
	private String metadata;			// Trama de palabras claves de búsqueda del documento
	@JsonProperty("codUsuregis")
	private String codUsuregis;			// Código del usuario solicitante
	@JsonProperty("codTipdocRel")
	private String codTipdocRel;		// Código de Tipo de documento relacionado
	@JsonProperty("numDocRel")
	private String numDocRel;			// Número del documento relacionado
	@JsonProperty("indAcu")
	private String indAcu;				// Indicador de acumulación del documento
	@JsonProperty("indEmiAlerta")
	private String indEmiAlerta;		// Indicador de emisión de alerta
	@JsonProperty("fecNotDocRel")
	private Date fecNotDocRel;			// Fecha de notificación del documento relacionado
	@JsonProperty("indVisDocRel")
	private String indVisDocRel;		// Indicador de visible al contribuyente del documento relacionado
    //PAS20191U210500144 Inicio 400101 RF06 PSALDARRIAGA
	@JsonProperty("indReserTrib")
	private String indReserTrib;		    // Indicador de reserva tributaria
	//PAS20191U210500144 Inicio 400101 RF06 PSALDARRIAGA
	
	//Inicio CVG
			@JsonProperty("listDocumentos")
			private List<RequerimientoDocData> listDocumentos; // Json
		//fin CVG
	
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
	public String getCodTipdocRel() {
		return codTipdocRel;
	}
	public void setCodTipdocRel(String codTipdocRel) {
		this.codTipdocRel = codTipdocRel;
	}
	public String getNumDocRel() {
		return numDocRel;
	}
	public void setNumDocRel(String numDocRel) {
		this.numDocRel = numDocRel;
	}
	public String getIndAcu() {
		return indAcu;
	}
	public void setIndAcu(String indAcu) {
		this.indAcu = indAcu;
	}
	public String getIndEmiAlerta() {
		return indEmiAlerta;
	}
	public void setIndEmiAlerta(String indEmiAlerta) {
		this.indEmiAlerta = indEmiAlerta;
	}
	public Date getFecNotDocRel() {
		return fecNotDocRel;
	}
	public void setFecNotDocRel(Date fecNotDocRel) {
		this.fecNotDocRel = fecNotDocRel;
	}
	public String getIndVisDocRel() {
		return indVisDocRel;
	}
	public void setIndVisDocRel(String indVisDocRel) {
		this.indVisDocRel = indVisDocRel;
	}
    //PAS20191U210500144 Inicio 400101 RF06 PSALDARRIAGA
	public String getIndReserTrib() {
		return indReserTrib;
	}
	public void setIndReserTrib(String indReserTrib) {
		this.indReserTrib = indReserTrib;
	}
	//PAS20191U210500144 Fin 400101 RF06 PSALDARRIAGA	
	
	//Inicio CVG
		public List<RequerimientoDocData> getListDocumentos() {
			return listDocumentos;
		}
		public void setListDocumentos(List<RequerimientoDocData> listDocumentos) {
			this.listDocumentos = listDocumentos;
		}
		//Fin CVG
}
