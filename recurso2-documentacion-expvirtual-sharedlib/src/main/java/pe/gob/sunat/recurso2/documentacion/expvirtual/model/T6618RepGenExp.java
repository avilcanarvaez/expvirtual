package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6618RepGenExp {
	
	private Integer numReporteExpediente; 			//num_repexp	INTEGER
	private Integer numReportePedido;    			//num_repped	INTEGER
	private String  numeroPedido;					//num_pedido	CHAR
	private String 	codigoIdecm;					//cod_idecm	CHAR
	private Date 	fecGeneracion;					//fec_genera	DATETIME
	private String 	codEstadoRepPorExpediente;		//cod_est	CHAR
	private String codUsuarioModifica;			//cod_usumodif	CHAR
	private Date fecModificacion;				//fec_modif		DATETIME
	private String codError;					//cod_error 	VARCHAR 250
	
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
	public String getCodEstadoRepPorExpediente() {
		return codEstadoRepPorExpediente;
	}
	public void setCodEstadoRepPorExpediente(String codEstadoRepPorExpediente) {
		this.codEstadoRepPorExpediente = codEstadoRepPorExpediente;
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
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
}
