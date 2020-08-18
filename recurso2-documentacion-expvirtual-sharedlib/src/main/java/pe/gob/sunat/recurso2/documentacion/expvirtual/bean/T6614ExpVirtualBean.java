package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6614ExpVirtual;

public class T6614ExpVirtualBean extends T6614ExpVirtual implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* Descripciones de los campos */
	private String desProceso;
	private String desTipoExpediente;
	private String desEstado;
	private String desOrigen;
	private String desDependencia;
	private String desOrigenCierre;
	private String desEstadoCierre;
	private Date fechaDocumentoOrigen;
	private String desRazonSocial;
	private String codOrigenDoc;
	private String nombrePersonaCargo;
	private String correlativo;
	private String codResponsable;
	private String nombreResponsable;
	private String desIndicadorAcumulado;
	//INICIO [ATORRESCH 20170307]
	private Date fecNotifOrigen;
	private String strFecNotifOrigen;
	private String codForNotifOrigen;
	private String desForNotifOrigen;

	
	public Date getFecNotifOrigen() {
		return fecNotifOrigen;
	}

	public void setFecNotifOrigen(Date fecNotifOrigen) {
		this.fecNotifOrigen = fecNotifOrigen;
	}

	public String getDesForNotifOrigen() {
		return desForNotifOrigen;
	}

	public void setDesForNotifOrigen(String desForNotifOrigen) {
		this.desForNotifOrigen = desForNotifOrigen;
	}
	//FIN    [ATORRESCH 20170307]
	
	//SET GET
	public String getCodOrigenDoc() {
		return codOrigenDoc;
	}

	public void setCodOrigenDoc(String codOrigenDoc) {
		this.codOrigenDoc = codOrigenDoc;
	}

	/* Adicionales para las tablas */
	private Integer numOrden;
	
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
	
	public String getDesEstado() {
		return desEstado;
	}
	
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
	public String getDesOrigen() {
		return desOrigen;
	}
	
	public void setDesOrigen(String desOrigen) {
		this.desOrigen = desOrigen;
	}
	
	public String getDesDependencia() {
		return desDependencia;
	}
	
	public void setDesDependencia(String desDependencia) {
		this.desDependencia = desDependencia;
	}
	
	public String getDesOrigenCierre() {
		return desOrigenCierre;
	}
	
	public void setDesOrigenCierre(String desOrigenCierre) {
		this.desOrigenCierre = desOrigenCierre;
	}
	
	public String getDesEstadoCierre() {
		return desEstadoCierre;
	}
	
	public void setDesEstadoCierre(String desEstadoCierre) {
		this.desEstadoCierre = desEstadoCierre;
	}

	public Date getFechaDocumentoOrigen() {
		return fechaDocumentoOrigen;
	}

	public void setFechaDocumentoOrigen(Date fechaDocumentoOrigen) {
		this.fechaDocumentoOrigen = fechaDocumentoOrigen;
	}

	public Integer getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}

	public String getDesRazonSocial() {
		return desRazonSocial;
	}

	public void setDesRazonSocial(String desRazonSocial) {
		this.desRazonSocial = desRazonSocial;
	}

	public String getNombrePersonaCargo() {
		return nombrePersonaCargo;
	}

	public void setNombrePersonaCargo(String nombrePersonaCargo) {
		this.nombrePersonaCargo = nombrePersonaCargo;
	}

	public String getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}

	public String getCodResponsable() {
		return codResponsable;
	}

	public void setCodResponsable(String codResponsable) {
		this.codResponsable = codResponsable;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public String getDesIndicadorAcumulado() {
		return desIndicadorAcumulado;
	}

	public void setDesIndicadorAcumulado(String desIndicadorAcumulado) {
		this.desIndicadorAcumulado = desIndicadorAcumulado;
	}

	public String getCodForNotifOrigen() {
		return codForNotifOrigen;
	}

	public void setCodForNotifOrigen(String codForNotifOrigen) {
		this.codForNotifOrigen = codForNotifOrigen;
	}

	public String getStrFecNotifOrigen() {
		return strFecNotifOrigen;
	}

	public void setStrFecNotifOrigen(String strFecNotifOrigen) {
		this.strFecNotifOrigen = strFecNotifOrigen;
	}

}