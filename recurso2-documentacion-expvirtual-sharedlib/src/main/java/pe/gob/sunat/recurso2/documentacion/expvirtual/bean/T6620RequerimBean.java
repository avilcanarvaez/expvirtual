package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6620Requerim;

public class T6620RequerimBean extends T6620Requerim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* Descripciones de los campos */
	private String desEstado;
	private String nomUsuReq;
	private Integer correlativo;
	private String desProceso;
	private String desTipoExpediente;
	private String desOrigRequerimiento;
	//[nchavez 26/05/2016]
	private String desRazonSocial;
	
	/**Exp Virtual**/
	private String numRuc;
	private Date fecRegistroExpediente;
	
	//[PAS20191U210500291]: JMC - INI
	private String codEstadoExp; 
	private String codTipoExp;
	private String codDependencia;
	//[PAS20191U210500291]: JMC - FIN
	
	//CVG inicio [PAS20191U210500291]
	private String codTipDoc;
	//CVG fin
	
	// INI [HHC PAS20201U210500029]
	private String numDocEscrito; // num_doc de t6613
	private String strfechaDocumentoOrigen;
	// FIN [HHC PAS20201U210500029]		
	/** Documentos**/
	private String numExpedienteOrigen;
	private Date fechaDocumentoOrigen;
	
	
	/*Fechas en String*/
	private String fecRequerimiento;
	private String fecVencimiento;

	//INICIO[ATORRESCH 20170412]
	private String fecRegistro;
	public String getFecRegistro() {
		return fecRegistro;
	}
	public void setFecRegistro(String fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	//FIN [ATORRESCH 20170412]	
	
	private Integer plazoVigencia;
	private String plazoVigenciaDias;
	
	//INICIO[ATORRESCH 20170313]
	private String strfecNotifOrigen;
	private String desForNotifOrigen;
	private String desTipCierr;
	
	// PAS20201U210500029 -HHC -INICIO alternativo
	private String codSupervisor;
	private String UuOOSupervisor;

	// PAS20201U210500029 -HHC - FIN
	
	public String getStrfecNotifOrigen() {
		return strfecNotifOrigen;
	}
	public void setStrfecNotifOrigen(String strfecNotifOrigen) {
		this.strfecNotifOrigen = strfecNotifOrigen;
	}
	public String getDesForNotifOrigen() {
		return desForNotifOrigen;
	}
	public void setDesForNotifOrigen(String desForNotifOrigen) {
		this.desForNotifOrigen = desForNotifOrigen;
	}
	
	public String getDesTipCierr() {
		return desTipCierr;
	}
	public void setDesTipCierr(String desTipCierr) {
		this.desTipCierr = desTipCierr;
	}
	//FIN   [ATORRESCH 20170313]
	
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	public String getNomUsuReq() {
		return nomUsuReq;
	}
	public void setNomUsuReq(String nomUsuReq) {
		this.nomUsuReq = nomUsuReq;
	}
	public Integer getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}
	public String getFecVencimiento() {
		return fecVencimiento;
	}
	public void setFecVencimiento(String fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}
	public String getFecRequerimiento() {
		return fecRequerimiento;
	}
	public void setFecRequerimiento(String fecRequerimiento) {
		this.fecRequerimiento = fecRequerimiento;
	}
	public String getDesProceso() {
		return desProceso;
	}
	public void setDesProceso(String desProceso) {
		this.desProceso = desProceso;
	}
	public String getDesTipoExpediente() {
		return desTipoExpediente;
	}
	public void setDesTipoExpediente(String desTipoExpediente) {
		this.desTipoExpediente = desTipoExpediente;
	}
	public String getNumRuc() {
		return numRuc;
	}
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
	public Date getFecRegistroExpediente() {
		return fecRegistroExpediente;
	}
	public void setFecRegistroExpediente(Date fecRegistroExpediente) {
		this.fecRegistroExpediente = fecRegistroExpediente;
	}
	public String getNumExpedienteOrigen() {
		return numExpedienteOrigen;
	}
	public void setNumExpedienteOrigen(String numExpedienteOrigen) {
		this.numExpedienteOrigen = numExpedienteOrigen;
	}
	public Date getFechaDocumentoOrigen() {
		return fechaDocumentoOrigen;
	}
	public void setFechaDocumentoOrigen(Date fechaDocumentoOrigen) {
		this.fechaDocumentoOrigen = fechaDocumentoOrigen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getPlazoVigencia() {
		return plazoVigencia;
	}
	public void setPlazoVigencia(Integer plazoVigencia) {
		this.plazoVigencia = plazoVigencia;
	}
	public String getPlazoVigenciaDias() {
		return plazoVigenciaDias;
	}
	public void setPlazoVigenciaDias(String plazoVigenciaDias) {
		this.plazoVigenciaDias = plazoVigenciaDias;
	}
	public String getDesOrigRequerimiento() {
		return desOrigRequerimiento;
	}
	public void setDesOrigRequerimiento(String desOrigRequerimiento) {
		this.desOrigRequerimiento = desOrigRequerimiento;
	}
	public String getDesRazonSocial() {
		return desRazonSocial;
	}
	public void setDesRazonSocial(String desRazonSocial) {
		this.desRazonSocial = desRazonSocial;
	}
	//[PAS20191U210500291]: JMC - INI
	public String getCodEstadoExp() {
		return codEstadoExp;
	}
	public void setCodEstadoExp(String codEstadoExp) {
		this.codEstadoExp = codEstadoExp;
	}	
	public String getCodTipoExp() {
		return codTipoExp;
	}
	public void setCodTipoExp(String codTipoExp) {
		this.codTipoExp = codTipoExp;
	}
	public String getCodDependencia() {
		return codDependencia;
	}
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}	
	//[PAS20191U210500291]: JMC - FIN
	//CVG inicio [PAS20191U210500291]
	public String getCodTipDoc() {
		return codTipDoc;
	}
	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}
	//CVG fin
	public String getNumDocEscrito() {
		return numDocEscrito;
	}
	public void setNumDocEscrito(String numDocEscrito) {
		this.numDocEscrito = numDocEscrito;
	}
	public String getStrfechaDocumentoOrigen() {
		return strfechaDocumentoOrigen;
	}
	public void setStrfechaDocumentoOrigen(String strfechaDocumentoOrigen) {
		this.strfechaDocumentoOrigen = strfechaDocumentoOrigen;
	}
	public String getCodSupervisor() {
		return codSupervisor;
	}
	public void setCodSupervisor(String codSupervisor) {
		this.codSupervisor = codSupervisor;
	}
	public String getUuOOSupervisor() {
		return UuOOSupervisor;
	}
	public void setUuOOSupervisor(String uuOOSupervisor) {
		UuOOSupervisor = uuOOSupervisor;
	}
	
}