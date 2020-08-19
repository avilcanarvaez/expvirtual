package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6620Requerim {
	
	private String numRequerimiento;		//num_requerim
	private String numRequerimOrigen;		//num_reqorig
	private String numExpedienteVirtual;	//num_expedv
	private Date fechaRequerimiento; 		//fec_requerim
	private String codEstadoRequerim; 		//cod_estreq
	private Date fechaVencimiento; 			//fec_venc
	private String codOrigRequerimiento; 	//cod_orireq
	private Date fechaRegistro; 			//fec_regis
	private String codUsuRegis;				//cod_usuregis
	private Date fechaMod; 					//fec_mod
	private String codUsuMod;				//cod_usumod
	//Inicio - [oachahuic][PAS20175E210400016]
	private String desEliReq;				//des_elireq
	private String desAteReq;				//des_atereq
	private String codTipCierr;				//cod_tipcierr
	//Fin - [oachahuic][PAS20175E210400016]
	
	public String getNumRequerimiento() {
		return numRequerimiento;
	}
	public void setNumRequerimiento(String numRequerimiento) {
		this.numRequerimiento = numRequerimiento;
	}
	public String getNumRequerimOrigen() {
		return numRequerimOrigen;
	}
	public void setNumRequerimOrigen(String numRequerimOrigen) {
		this.numRequerimOrigen = numRequerimOrigen;
	}
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
	public Date getFechaRequerimiento() {
		return fechaRequerimiento;
	}
	public void setFechaRequerimiento(Date fechaRequerimiento) {
		this.fechaRequerimiento = fechaRequerimiento;
	}
	public String getCodEstadoRequerim() {
		return codEstadoRequerim;
	}
	public void setCodEstadoRequerim(String codEstadoRequerim) {
		this.codEstadoRequerim = codEstadoRequerim;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getCodOrigRequerimiento() {
		return codOrigRequerimiento;
	}
	public void setCodOrigRequerimiento(String codOrigRequerimiento) {
		this.codOrigRequerimiento = codOrigRequerimiento;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getCodUsuRegis() {
		return codUsuRegis;
	}
	public void setCodUsuRegis(String codUsuRegis) {
		this.codUsuRegis = codUsuRegis;
	}
	public Date getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}
	public String getCodUsuMod() {
		return codUsuMod;
	}
	public void setCodUsuMod(String codUsuMod) {
		this.codUsuMod = codUsuMod;
	}
	public String getDesEliReq() {
		return desEliReq;
	}
	public void setDesEliReq(String desEliReq) {
		this.desEliReq = desEliReq;
	}
	public String getDesAteReq() {
		return desAteReq;
	}
	public void setDesAteReq(String desAteReq) {
		this.desAteReq = desAteReq;
	}
	public String getCodTipCierr() {
		return codTipCierr;
	}
	public void setCodTipCierr(String codTipCierr) {
		this.codTipCierr = codTipCierr;
	}

}