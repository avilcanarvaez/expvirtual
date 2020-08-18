package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6613DocExpVirt {
	
	private String numDoc;		// num_doc
	private Date fecDoc; 		// fec_doc
	private String codTipDoc;	// cod_tipdoc
	private Integer	numCorDoc;	// num_cordoc
	private Date  fecRegis; 	// fec_regis
	private String desMotsoldoc; //des_motsoldoc
	private String numExpedienteVir; 	//num_expedv
	private String codTipExp;			//cod_tipexp
	private String numRequerimiento; 	//num_requerim
	private String estadoDocumentoReq;	//cod_estdocreq
	private String codigoTipoDocumentoSust;	//cod_tipdocsust
	private String codUsucarga; 			//cod_usucarg
	private Date fechaCarga;				//fec_carg
	private String codUsuarioRegistro;			//cod_usuregis
	private String descripcionArchivo;		//DES_ARCH
	private String codIdentificadorECM;		//cod_idecm
	private String codOrigenDocuento;		//cod_origdoc
	private String numDocRelacionado; 		//num_cordocrel
	//Inicio - [oachahuic][PAS20175E210400016]
	private Date fecNotif;		//fec_notif
	private String codForNotif;	//cod_for_notif
	private String codEstDoc;	//cod_estdoc
	private String desEliDoc;	//des_elidoc
	private String codUsuModif;	//cod_usumodif
	private Date fecModif;		//fec_modif
	private String indVisible;	//ind_visible
	//Fin - [oachahuic][PAS20175E210400016]
	
	//[PAS20191U210500144] Inicio
	private String indReserTrib;	//ind_resertrib
	private Integer numCorDocRel;    //num_cordocrel
	//[PAS20191U210500144] Fin 

	
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
	public String getCodTipDoc() {
		return codTipDoc;
	}
	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}
	public Integer getNumCorDoc() {
		return numCorDoc;
	}
	public void setNumCorDoc(Integer numCorDoc) {
		this.numCorDoc = numCorDoc;
	}
	public Date getFecRegis() {
		return fecRegis;
	}
	public void setFecRegis(Date fecRegis) {
		this.fecRegis = fecRegis;
	}

	public String getDesMotsoldoc() {
		return desMotsoldoc;
	}
	public void setDesMotsoldoc(String desMotsoldoc) {
		this.desMotsoldoc = desMotsoldoc;
	}
	public String getNumExpedienteVir() {
		return numExpedienteVir;
	}
	public void setNumExpedienteVir(String numExpedienteVir) {
		this.numExpedienteVir = numExpedienteVir;
	}
	public String getCodTipExp() {
		return codTipExp;
	}
	public void setCodTipExp(String codTipExp) {
		this.codTipExp = codTipExp;
	}
	public String getNumRequerimiento() {
		return numRequerimiento;
	}
	public void setNumRequerimiento(String numRequerimiento) {
		this.numRequerimiento = numRequerimiento;
	}
	public String getEstadoDocumentoReq() {
		return estadoDocumentoReq;
	}
	public void setEstadoDocumentoReq(String estadoDocumentoReq) {
		this.estadoDocumentoReq = estadoDocumentoReq;
	}
	public String getCodigoTipoDocumentoSust() {
		return codigoTipoDocumentoSust;
	}
	public void setCodigoTipoDocumentoSust(String codigoTipoDocumentoSust) {
		this.codigoTipoDocumentoSust = codigoTipoDocumentoSust;
	}
	public String getCodUsucarga() {
		return codUsucarga;
	}
	public void setCodUsucarga(String codUsucarga) {
		this.codUsucarga = codUsucarga;
	}
	public Date getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public String getCodUsuarioRegistro() {
		return codUsuarioRegistro;
	}
	public void setCodUsuarioRegistro(String codUsuarioRegistro) {
		this.codUsuarioRegistro = codUsuarioRegistro;
	}
	public String getDescripcionArchivo() {
		return descripcionArchivo;
	}
	public void setDescripcionArchivo(String descripcionArchivo) {
		this.descripcionArchivo = descripcionArchivo;
	}
	public String getCodIdentificadorECM() {
		return codIdentificadorECM;
	}
	public void setCodIdentificadorECM(String codIdentificadorECM) {
		this.codIdentificadorECM = codIdentificadorECM;
	}
	public String getCodOrigenDocuento() {
		return codOrigenDocuento;
	}
	public void setCodOrigenDocuento(String codOrigenDocuento) {
		this.codOrigenDocuento = codOrigenDocuento;
	}
	public String getNumDocRelacionado() {
		return numDocRelacionado;
	}
	public void setNumDocRelacionado(String numDocRelacionado) {
		this.numDocRelacionado = numDocRelacionado;
	}
	public Date getFecNotif() {
		return fecNotif;
	}
	public void setFecNotif(Date fecNotif) {
		this.fecNotif = fecNotif;
	}
	public String getCodForNotif() {
		return codForNotif;
	}
	public void setCodForNotif(String codForNotif) {
		this.codForNotif = codForNotif;
	}
	public String getCodEstDoc() {
		return codEstDoc;
	}
	public void setCodEstDoc(String codEstDoc) {
		this.codEstDoc = codEstDoc;
	}
	public String getDesEliDoc() {
		return desEliDoc;
	}
	public void setDesEliDoc(String desEliDoc) {
		this.desEliDoc = desEliDoc;
	}
	public String getCodUsuModif() {
		return codUsuModif;
	}
	public void setCodUsuModif(String codUsuModif) {
		this.codUsuModif = codUsuModif;
	}
	public Date getFecModif() {
		return fecModif;
	}
	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}
	public String getIndVisible() {
		return indVisible;
	}
	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}
	public String getIndReserTrib() {
		return indReserTrib;
	}
	public void setIndReserTrib(String indReserTrib) {
		this.indReserTrib = indReserTrib;
	}
	public Integer getNumCorDocRel() {
		return numCorDocRel;
	}
	public void setNumCorDocRel(Integer numCorDocRel) {
		this.numCorDocRel = numCorDocRel;
	}

}