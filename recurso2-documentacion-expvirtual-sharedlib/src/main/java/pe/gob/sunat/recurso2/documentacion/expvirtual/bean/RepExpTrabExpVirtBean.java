package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RepExpTrabExpVirtBean implements Serializable {
	private static final long serialVersionUID = -294871250937376326L;
	
	private String numExpedienteVirtual;
	private String numExpedienteOrigen;
	private String codigoDependencia;
	private String codigoProceso;
	private String codigoTipExp;

	// Documento origen
	private String numDocumentoOrigen;
	private Date fechaDocOrigen;
	private List<RepExpTrabDocBean> listaRepExpTrabDocBeans;
	
	public String getNumDocumentoOrigen() {
		return numDocumentoOrigen;
	}
	public void setNumDocumentoOrigen(String numDocumentoOrigen) {
		this.numDocumentoOrigen = numDocumentoOrigen;
	}
	public Date getFechaDocOrigen() {
		return fechaDocOrigen;
	}
	public void setFechaDocOrigen(Date fechaDocOrigen) {
		this.fechaDocOrigen = fechaDocOrigen;
	}
	public List<RepExpTrabDocBean> getListaRepExpTrabDocBeans() {
		return listaRepExpTrabDocBeans;
	}
	public void setListaRepExpTrabDocBeans(
			List<RepExpTrabDocBean> listaRepExpTrabDocBeans) {
		this.listaRepExpTrabDocBeans = listaRepExpTrabDocBeans;
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
	public String getCodigoProceso() {
		return codigoProceso;
	}
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	public String getCodigoTipExp() {
		return codigoTipExp;
	}
	public void setCodigoTipExp(String codigoTipExp) {
		this.codigoTipExp = codigoTipExp;
	}
	public String getCodigoDependencia() {
		return codigoDependencia;
	}
	public void setCodigoDependencia(String codigoDependencia) {
		this.codigoDependencia = codigoDependencia;
	}
}