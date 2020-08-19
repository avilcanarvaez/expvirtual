package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6622Seguim {
	private int numSeguimiento; 			//num_corseg	INTEGER
	private String numExpedienteVirtual; 	//num_expvirt	CHAR
	private String codTipoSeguim; 			//cod_tipseguim	CHARS 
	private Date fec_seguim; 				//fec_seguim	DATETIME
	private String codServicioInvocado;		//cod_servinv	CHAR
	private String codRetornoServInv;		//cod_retservinv	CHAR
	private String codUsuInvocaServicio;	//cod_usuinvserv	CHAR
	private Date fecInvocacionServ;			//fec_invserv	DATETIME
	private String desRequestServInv; 		//des_request	VARCHAR
	private String desResponseSerInv; 		//des_response	VARCHAR
	private String numRUC;					//num_ruc	CHAR
	private String codOpcionConsulta; 		// cod_opccons	CHAR
	private String codAccionContrib;		//cod_accion	CHAR
	private String codRespuestaAccion; 		//cod_respacc	CHAR
	private String desDatosConsulta;		//des_datcons	VARCHAR
	private Date fecConsulta; 				//fec_cons	DATETIME
	private String codTipoDocumento;		//cod_tipdoc	CHAR
	private String numDocumento;			//num_doc	CHAR
	private String codEstExpOrigen;			//cod_estexpori	CHAR
	private String codEtapaExpOrigen; 		//cod_etaexpori	CHAR
	private Date fecCambioEstado;			//fec_cambest	DATETIME
	private Date fecCambioEtapa;			//fec_cambeta	DATETIME
	

    public int getNumSeguimiento() {
		return numSeguimiento;
	}
	public void setNumSeguimiento(int numSeguimiento) {
		this.numSeguimiento = numSeguimiento;
	}
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
	public String getCodTipoSeguim() {
		return codTipoSeguim;
	}
	public void setCodTipoSeguim(String codTipoSeguim) {
		this.codTipoSeguim = codTipoSeguim;
	}
	public Date getFec_seguim() {
		return fec_seguim;
	}
	public void setFec_seguim(Date fec_seguim) {
		this.fec_seguim = fec_seguim;
	}
	public String getCodServicioInvocado() {
		return codServicioInvocado;
	}
	public void setCodServicioInvocado(String codServicioInvocado) {
		this.codServicioInvocado = codServicioInvocado;
	}
	public String getCodRetornoServInv() {
		return codRetornoServInv;
	}
	public void setCodRetornoServInv(String codRetornoServInv) {
		this.codRetornoServInv = codRetornoServInv;
	}
	public String getCodUsuInvocaServicio() {
		return codUsuInvocaServicio;
	}
	public void setCodUsuInvocaServicio(String codUsuInvocaServicio) {
		this.codUsuInvocaServicio = codUsuInvocaServicio;
	}
	public Date getFecInvocacionServ() {
		return fecInvocacionServ;
	}
	public void setFecInvocacionServ(Date fecInvocacionServ) {
		this.fecInvocacionServ = fecInvocacionServ;
	}
	public String getDesRequestServInv() {
		return desRequestServInv;
	}
	public void setDesRequestServInv(String desRequestServInv) {
		this.desRequestServInv = desRequestServInv;
	}
	public String getDesResponseSerInv() {
		return desResponseSerInv;
	}
	public void setDesResponseSerInv(String desResponseSerInv) {
		this.desResponseSerInv = desResponseSerInv;
	}
	public String getNumRUC() {
		return numRUC;
	}
	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}
	public String getCodOpcionConsulta() {
		return codOpcionConsulta;
	}
	public void setCodOpcionConsulta(String codOpcionConsulta) {
		this.codOpcionConsulta = codOpcionConsulta;
	}
	public String getCodAccionContrib() {
		return codAccionContrib;
	}
	public void setCodAccionContrib(String codAccionContrib) {
		this.codAccionContrib = codAccionContrib;
	}
	public String getCodRespuestaAccion() {
		return codRespuestaAccion;
	}
	public void setCodRespuestaAccion(String codRespuestaAccion) {
		this.codRespuestaAccion = codRespuestaAccion;
	}
	public String getDesDatosConsulta() {
		return desDatosConsulta;
	}
	public void setDesDatosConsulta(String desDatosConsulta) {
		this.desDatosConsulta = desDatosConsulta;
	}
	public Date getFecConsulta() {
		return fecConsulta;
	}
	public void setFecConsulta(Date fecConsulta) {
		this.fecConsulta = fecConsulta;
	}
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getCodEstExpOrigen() {
		return codEstExpOrigen;
	}
	public void setCodEstExpOrigen(String codEstExpOrigen) {
		this.codEstExpOrigen = codEstExpOrigen;
	}
	public String getCodEtapaExpOrigen() {
		return codEtapaExpOrigen;
	}
	public void setCodEtapaExpOrigen(String codEtapaExpOrigen) {
		this.codEtapaExpOrigen = codEtapaExpOrigen;
	}
	public Date getFecCambioEstado() {
		return fecCambioEstado;
	}
	public void setFecCambioEstado(Date fecCambioEstado) {
		this.fecCambioEstado = fecCambioEstado;
	}
	public Date getFecCambioEtapa() {
		return fecCambioEtapa;
	}
	public void setFecCambioEtapa(Date fecCambioEtapa) {
		this.fecCambioEtapa = fecCambioEtapa;
	}

}


