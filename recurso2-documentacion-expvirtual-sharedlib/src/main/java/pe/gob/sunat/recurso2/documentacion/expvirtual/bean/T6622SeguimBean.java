package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6622Seguim;

public class T6622SeguimBean extends T6622Seguim implements Serializable {
	private static final long serialVersionUID = 1L;

	private String desTipoSeguim; 			//cod_tipseguim	CHARS 
	private String desServicioInvocado;		//cod_servinv	CHAR
	private String desRetornoServInv;		//cod_retservinv	CHAR
	private String nomUsuInvocaServicio;	//cod_usuinvserv	CHAR
	private String desOpcionConsulta; 		// cod_opccons	CHAR
	private String desAccionContrib;		//cod_accion	CHAR
	private String desRespuestaAccion; 		//cod_respacc	CHAR
	private String codTipoDocumento;		//cod_tipdoc	CHAR
	private String numDocumento;			//num_doc	CHAR
	private String codEstExpOrigen;			//cod_estexpori	CHAR
	private String codEtapaExpOrigen; 		//cod_etaexpori	CHAR
	private Integer numOrden; 		//cod_etaexpori	CHAR
	private String desTipoDocumento;
	private String fecVistaSegui;
	private String desEstado;
	private String desEtapa;
	
	//fechas String
	
	public String getFecVistaSegui() {
		return fecVistaSegui;
	}
	public void setFecVistaSegui(String fecVistaSegui) {
		this.fecVistaSegui = fecVistaSegui;
	}
	private String fechaConsulta; 				//fec_cons	DATETIME
	private Date fechaInvocacionServ;			//fec_invserv	DATETIME
	private Date fechaseguim; 				//fec_seguim	DATETIME
	private Date fechaCambioEstado;			//fec_cambest	DATETIME
	private Date fechaCambioEtapa;			//fec_cambeta	DATETIME
	
	public String getDesTipoSeguim() {
		return desTipoSeguim;
	}
	public void setDesTipoSeguim(String desTipoSeguim) {
		this.desTipoSeguim = desTipoSeguim;
	}
	public String getDesServicioInvocado() {
		return desServicioInvocado;
	}
	public void setDesServicioInvocado(String desServicioInvocado) {
		this.desServicioInvocado = desServicioInvocado;
	}
	public String getDesRetornoServInv() {
		return desRetornoServInv;
	}
	public void setDesRetornoServInv(String desRetornoServInv) {
		this.desRetornoServInv = desRetornoServInv;
	}
	public String getNomUsuInvocaServicio() {
		return nomUsuInvocaServicio;
	}
	public void setNomUsuInvocaServicio(String nomUsuInvocaServicio) {
		this.nomUsuInvocaServicio = nomUsuInvocaServicio;
	}
	public String getDesOpcionConsulta() {
		return desOpcionConsulta;
	}
	public void setDesOpcionConsulta(String desOpcionConsulta) {
		this.desOpcionConsulta = desOpcionConsulta;
	}
	public String getDesAccionContrib() {
		return desAccionContrib;
	}
	public void setDesAccionContrib(String desAccionContrib) {
		this.desAccionContrib = desAccionContrib;
	}
	public String getDesRespuestaAccion() {
		return desRespuestaAccion;
	}
	public void setDesRespuestaAccion(String desRespuestaAccion) {
		this.desRespuestaAccion = desRespuestaAccion;
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
	
	public String getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	public Date getFechaInvocacionServ() {
		return fechaInvocacionServ;
	}
	public void setFechaInvocacionServ(Date fechaInvocacionServ) {
		this.fechaInvocacionServ = fechaInvocacionServ;
	}
	public Date getFechaseguim() {
		return fechaseguim;
	}
	public void setFechaseguim(Date fechaseguim) {
		this.fechaseguim = fechaseguim;
	}
	public Date getFechaCambioEstado() {
		return fechaCambioEstado;
	}
	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}
	public Date getFechaCambioEtapa() {
		return fechaCambioEtapa;
	}
	public void setFechaCambioEtapa(Date fechaCambioEtapa) {
		this.fechaCambioEtapa = fechaCambioEtapa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	public String getDesTipoDocumento() {
		return desTipoDocumento;
	}
	public void setDesTipoDocumento(String desTipoDocumento) {
		this.desTipoDocumento = desTipoDocumento;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	public String getDesEtapa() {
		return desEtapa;
	}
	public void setDesEtapa(String desEtapa) {
		this.desEtapa = desEtapa;
	}
	
	
}
