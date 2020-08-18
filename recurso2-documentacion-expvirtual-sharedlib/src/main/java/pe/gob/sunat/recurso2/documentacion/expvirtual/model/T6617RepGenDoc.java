package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6617RepGenDoc {

	private Integer numReporteDocumento; 					//num_repdoc	INTEGER
	private Integer numReporteExpediente; 					//num_repexp	INTEGER
	private Integer numReportePedido; 						//num_repped	INTEGER
	private String numeroPedido; 							//num_pedido	CHAR
	private String 	codigoIdecm;							//cod_idecm		CHAR
	private String 	numExpedienteVirtual;					//num_expedev	CHAR
	private Date 	fecGeneracion;							//fec_genera	DATETIME
	private String 	codigoEstadoRepPorDocumento;			//cod_est		CHAR
	private String codUsuarioModifica;			//cod_usumodif	CHAR
	private Date fecModificacion;				//fec_modif	DATETIME
	
	public Integer getNumReporteDocumento() {
		return numReporteDocumento;
	}
	public void setNumReporteDocumento(Integer numReporteDocumento) {
		this.numReporteDocumento = numReporteDocumento;
	}
	public Integer getNumReporteExpediente() {
		return numReporteExpediente;
	}
	public void setNumReporteExpediente(Integer numReporteExpediente) {
		this.numReporteExpediente = numReporteExpediente;
	}
	public Integer getNumReportePedido() {
		return numReportePedido;
	}
	public void setNumReportePedido(Integer numReportePedido) {
		this.numReportePedido = numReportePedido;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getCodigoIdecm() {
		return codigoIdecm;
	}
	public void setCodigoIdecm(String codigoIdecm) {
		this.codigoIdecm = codigoIdecm;
	}	
	public Date getFecGeneracion() {
		return fecGeneracion;
	}
	public void setFecGeneracion(Date fecGeneracion) {
		this.fecGeneracion = fecGeneracion;
	}
	public String getCodigoEstadoRepPorDocumento() {
		return codigoEstadoRepPorDocumento;
	}
	public void setCodigoEstadoRepPorDocumento(String codigoEstadoRepPorDocumento) {
		this.codigoEstadoRepPorDocumento = codigoEstadoRepPorDocumento;
	}
	public String getCodUsuarioModifica() {
		return codUsuarioModifica;
	}
	public void setCodUsuarioModifica(String codUsuarioModifica) {
		this.codUsuarioModifica = codUsuarioModifica;
	}
	public Date getFecModificacion() {
		return fecModificacion;
	}
	public void setFecModificacion(Date fecModificacion) {
		this.fecModificacion = fecModificacion;
	}
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
}
