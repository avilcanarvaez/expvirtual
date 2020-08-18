package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.List;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6613DocExpVirt;

public class T6613DocExpVirtBean extends T6613DocExpVirt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String desTipdoc;
	private String desEstadoDocumento;
	private String desSumilla;
	private String desMotCierre;
	private String desOrigenDoc;
	private String desTipoDocSustento;
	private String indVisContribuyente;//ind_viscont	
	private String fechaRegistroDoc;
	private String nroRequerim;
	private String desEstDocRequerido;
	private String fechaDocumento;
	private String strFechaCarga;
	private Integer correlativo;
	private String responsableExpediente;
	private String codEstadoExpe;
	private String codOrigenExpe;
	private String desOrigenExpe;
	private String desEstadoExpe;
	private String desNombreCompuesto;
	private String indVisDocumento;
	private String numAcumulador;
	private String numAcumuladorVista;
	private String desEstadoEnvioDoc;
	//INICIO [ATORRESCH 20170307]
	private String desForNotif;
	//INICIO [ATORRESCH 20170307]
	private String strfecNotif;
	//INICIO LLRB 20191212
	private Integer numCordocrel;
	private String desTipdocRel;
	private String numDocRel;
	
	//INI [HHC][PAS20201U210500029]
	private String numRequerimOrigen;
	private String numRequerimiento;
	private String numRuc;
	private String desRazonSocial;
	private String desProceso;
	private String desTipoExpediente;
	private String codProceso;				//	cod_proc
	private String codTipoExpediente;		//	cod_tipexp
	private String numExpedienteVirtual;	//	num_expedv
	private String numExpedienteOrigen;		//	num_expedo
	//FIN [HHC][PAS20201U210500029]
	private List<T10373DocAdjReqBean> listDocAdj;
	
	public String getDesForNotif() {
		return desForNotif;
	}

	public void setDesForNotif(String desForNotif) {
		this.desForNotif = desForNotif;
	}

	//FIN    [ATORRESCH 20170307]
	public String getDesTipdoc() {
		return desTipdoc;
	}

	public void setDesTipdoc(String desTipdoc) {
		this.desTipdoc = desTipdoc;
	}
	
	public String getDesSumilla() {
		return desSumilla;
	}

	public void setDesSumilla(String desSumilla) {
		this.desSumilla = desSumilla;
	}

	public String getDesMotCierre() {
		return desMotCierre;
	}

	public void setDesMotCierre(String desMotCierre) {
		this.desMotCierre = desMotCierre;
	}

	public String getDesEstadoDocumento() {
		return desEstadoDocumento;
	}

	public void setDesEstadoDocumento(String desEstadoDocumento) {
		this.desEstadoDocumento = desEstadoDocumento;
	}

	public String getDesOrigenDoc() {
		return desOrigenDoc;
	}

	public void setDesOrigenDoc(String desOrigenDoc) {
		this.desOrigenDoc = desOrigenDoc;
	}

	public String getDesTipoDocSustento() {
		return desTipoDocSustento;
	}

	public void setDesTipoDocSustento(String desTipoDocSustento) {
		this.desTipoDocSustento = desTipoDocSustento;
	}

	public String getIndVisContribuyente() {
		return indVisContribuyente;
	}

	public void setIndVisContribuyente(String indVisContribuyente) {
		this.indVisContribuyente = indVisContribuyente;
	}

	public String getFechaRegistroDoc() {
		return fechaRegistroDoc;
	}

	public void setFechaRegistroDoc(String fechaRegistroDoc) {
		this.fechaRegistroDoc = fechaRegistroDoc;
	}

	public String getNroRequerim() {
		return nroRequerim;
	}

	public void setNroRequerim(String nroRequerim) {
		this.nroRequerim = nroRequerim;
	}

	public String getDesEstDocRequerido() {
		return desEstDocRequerido;
	}

	public void setDesEstDocRequerido(String desEstDocRequerido) {
		this.desEstDocRequerido = desEstDocRequerido;
	}
	
	public String getFechaDocumento() {
		return fechaDocumento;
	}

	public void setFechaDocumento(String fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	public String getStrFechaCarga() {
		return strFechaCarga;
	}

	public void setStrFechaCarga(String strFechaCarga) {
		this.strFechaCarga = strFechaCarga;
	}

	public String getResponsableExpediente() {
		return responsableExpediente;
	}

	public void setResponsableExpediente(String responsableExpediente) {
		this.responsableExpediente = responsableExpediente;
	}

	public String getCodEstadoExpe() {
		return codEstadoExpe;
	}

	public void setCodEstadoExpe(String codEstadoExpe) {
		this.codEstadoExpe = codEstadoExpe;
	}

	public String getCodOrigenExpe() {
		return codOrigenExpe;
	}

	public void setCodOrigenExpe(String codOrigenExpe) {
		this.codOrigenExpe = codOrigenExpe;
	}

	public String getDesOrigenExpe() {
		return desOrigenExpe;
	}

	public void setDesOrigenExpe(String desOrigenExpe) {
		this.desOrigenExpe = desOrigenExpe;
	}

	public String getDesEstadoExpe() {
		return desEstadoExpe;
	}

	public void setDesEstadoExpe(String desEstadoExpe) {
		this.desEstadoExpe = desEstadoExpe;
	}

	public String getDesNombreCompuesto() {
		return desNombreCompuesto;
	}

	public void setDesNombreCompuesto(String desNombreCompuesto) {
		this.desNombreCompuesto = desNombreCompuesto;
	}
	public String getIndVisDocumento() {
		return indVisDocumento;
	}

	public void setIndVisDocumento(String indVisDocumento) {
		this.indVisDocumento = indVisDocumento;
	}

	public String getNumAcumulador() {
		return numAcumulador;
	}

	public void setNumAcumulador(String numAcumulador) {
		this.numAcumulador = numAcumulador;
	}

	public String getNumAcumuladorVista() {
		return numAcumuladorVista;
	}

	public void setNumAcumuladorVista(String numAcumuladorVista) {
		this.numAcumuladorVista = numAcumuladorVista;
	}

	public String getDesEstadoEnvioDoc() {
		return desEstadoEnvioDoc;
	}

	public void setDesEstadoEnvioDoc(String desEstadoEnvioDoc) {
		this.desEstadoEnvioDoc = desEstadoEnvioDoc;
	}
	//INICIO JEFFIO 27/06/2017
	public String getStrfecNotif() {
		return strfecNotif;
	}

	public void setStrfecNotif(String strfecNotif) {
		this.strfecNotif = strfecNotif;
	}	
	//FIN JEFFIO 27/06/2017

	public Integer getNumCordocrel() {
		return numCordocrel;
	}

	public void setNumCordocrel(Integer numCordocrel) {
		this.numCordocrel = numCordocrel;
	}

	public String getDesTipdocRel() {
		return desTipdocRel;
	}

	public void setDesTipdocRel(String desTipdocRel) {
		this.desTipdocRel = desTipdocRel;
	}

	public String getNumDocRel() {
		return numDocRel;
	}

	public void setNumDocRel(String numDocRel) {
		this.numDocRel = numDocRel;
	}

	public String getNumRequerimOrigen() {
		return numRequerimOrigen;
	}

	public void setNumRequerimOrigen(String numRequerimOrigen) {
		this.numRequerimOrigen = numRequerimOrigen;
	}

	public String getNumRequerimiento() {
		return numRequerimiento;
	}

	public void setNumRequerimiento(String numRequerimiento) {
		this.numRequerimiento = numRequerimiento;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getDesRazonSocial() {
		return desRazonSocial;
	}

	public void setDesRazonSocial(String desRazonSocial) {
		this.desRazonSocial = desRazonSocial;
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

	public String getCodProceso() {
		return codProceso;
	}

	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}

	public String getCodTipoExpediente() {
		return codTipoExpediente;
	}

	public void setCodTipoExpediente(String codTipoExpediente) {
		this.codTipoExpediente = codTipoExpediente;
	}

	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}

	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}

	public String getNumExpedienteOrigen() {
		return numExpedienteOrigen;
	}

	public void setNumExpedienteOrigen(String numExpedienteOrigen) {
		this.numExpedienteOrigen = numExpedienteOrigen;
	}

	public List<T10373DocAdjReqBean> getListDocAdj() {
		return listDocAdj;
	}

	public void setListDocAdj(List<T10373DocAdjReqBean> listDocAdj) {
		this.listDocAdj = listDocAdj;
	}

}