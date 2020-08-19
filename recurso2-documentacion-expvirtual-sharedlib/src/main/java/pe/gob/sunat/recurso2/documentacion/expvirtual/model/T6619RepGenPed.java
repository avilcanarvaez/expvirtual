package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6619RepGenPed {
	
	private Integer numReportePedido; 			//num_repped	INTEGER
	private String numeroPedido; 				//num_pedido	CHAR
	private String numRUC; 						//num_ruc	CHAR
	private String codigoIdecm;					//cod_idecm	CHAR
	private String codigoUsuResp;				//cod_usuresp	CHAR
	private Date fecGeneracion;					//fec_genera	DATETIME
	private String codEstadoRepPorPedido;		//cod_est	CHAR
	private String codUsuarioModifica;			//cod_usumodif	CHAR
	private Date fecModificacion;				//fec_modif	DATETIME
	
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
	public String getCodEstadoRepPorPedido() {
		return codEstadoRepPorPedido;
	}
	public void setCodEstadoRepPorPedido(String codEstadoRepPorPedido) {
		this.codEstadoRepPorPedido = codEstadoRepPorPedido;
	}
	public String getNumRUC() {
		return numRUC;
	}
	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}
	public String getCodigoUsuResp() {
		return codigoUsuResp;
	}
	public void setCodigoUsuResp(String codigoUsuResp) {
		this.codigoUsuResp = codigoUsuResp;
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
}
