package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6614ExpVirtual {
	
	private String numExpedienteVirtual;	//	num_expedv
	private String numRuc;					//	num_ruc
	private String numExpedienteOrigen;		//	num_expedo
	private String codProceso;				//	cod_proc
	private String codTipoExpediente;		//	cod_tipexp
	private String desExpedienteVirtual;	//	des_expedv
	private String codEstado;				//	cod_estado
	private String codOrigen;				//	cod_origen
	private String codUsuarioRegistro;		//	cod_usuregis
	private Date fecRegistro;				//	fec_regis
	private String codDependencia;			//	cod_dep
	private String codOrigenCierre;			//	cod_oricierr
	private String codEstadoCierre;			//	cod_estcierr
	private String desMotivoCierre;			//	des_motcierr
	private String desSumilla;				//	des_sumilla
	private String codUsuarioCierre;		//	cod_usucierr
	private Date fecCierre;					//	fec_cierre
	private String codRetornoServicio;		//	cod_retserv -- A ver si se elimina
	private Date fechaCarga;				//fec_carg
	private String indicadorAcumulado;		//	ind_acu
	private String numExpedienteAcumulador;		//	num_acu
	//Inicio - [oachahuic][PAS20175E210400016]
	private String desEliExp;		//des_eliexp
	private String codUsuModif;		//cod_usumodif
	private Date fecModif;			//fec_modif
	//Fin - [oachahuic][PAS20175E210400016]
	
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
	
	public String getNumRuc() {
		return numRuc;
	}
	
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
	
	public String getNumExpedienteOrigen() {
		return numExpedienteOrigen;
	}
	
	public void setNumExpedienteOrigen(String numExpedienteOrigen) {
		this.numExpedienteOrigen = numExpedienteOrigen;
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
	
	public String getDesExpedienteVirtual() {
		return desExpedienteVirtual;
	}
	
	public void setDesExpedienteVirtual(String desExpedienteVirtual) {
		this.desExpedienteVirtual = desExpedienteVirtual;
	}
	
	public String getCodEstado() {
		return codEstado;
	}
	
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}
	
	public String getCodOrigen() {
		return codOrigen;
	}
	
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	
	public String getCodUsuarioRegistro() {
		return codUsuarioRegistro;
	}
	
	public void setCodUsuarioRegistro(String codUsuarioRegistro) {
		this.codUsuarioRegistro = codUsuarioRegistro;
	}
	
	public Date getFecRegistro() {
		return fecRegistro;
	}
	
	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	
	public String getCodDependencia() {
		return codDependencia;
	}
	
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
	
	public String getCodOrigenCierre() {
		return codOrigenCierre;
	}
	
	public void setCodOrigenCierre(String codOrigenCierre) {
		this.codOrigenCierre = codOrigenCierre;
	}
	
	public String getCodEstadoCierre() {
		return codEstadoCierre;
	}
	
	public void setCodEstadoCierre(String codEstadoCierre) {
		this.codEstadoCierre = codEstadoCierre;
	}
	
	public String getDesMotivoCierre() {
		return desMotivoCierre;
	}
	
	public void setDesMotivoCierre(String desMotivoCierre) {
		this.desMotivoCierre = desMotivoCierre;
	}
	
	public String getDesSumilla() {
		return desSumilla;
	}
	
	public void setDesSumilla(String desSumilla) {
		this.desSumilla = desSumilla;
	}
	
	public String getCodUsuarioCierre() {
		return codUsuarioCierre;
	}
	
	public void setCodUsuarioCierre(String codUsuarioCierre) {
		this.codUsuarioCierre = codUsuarioCierre;
	}
	
	public Date getFecCierre() {
		return fecCierre;
	}
	
	public void setFecCierre(Date fecCierre) {
		this.fecCierre = fecCierre;
	}
	
	public String getCodRetornoServicio() {
		return codRetornoServicio;
	}
	
	public void setCodRetornoServicio(String codRetornoServicio) {
		this.codRetornoServicio = codRetornoServicio;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getIndicadorAcumulado() {
		return indicadorAcumulado;
	}

	public void setIndicadorAcumulado(String indicadorAcumulado) {
		this.indicadorAcumulado = indicadorAcumulado;
	}

	public String getNumExpedienteAcumulador() {
		return numExpedienteAcumulador;
	}

	public void setNumExpedienteAcumulador(String numExpedienteAcumulador) {
		this.numExpedienteAcumulador = numExpedienteAcumulador;
	}

	public String getDesEliExp() {
		return desEliExp;
	}

	public void setDesEliExp(String desEliExp) {
		this.desEliExp = desEliExp;
	}

	public String getCodUsuModif() {
		return codUsuModif;
	}

	public void setCodUsuModif(String codUsuModif) {
		this.codUsuModif = codUsuModif;
	}

	public Date getFecModif() {
		return fecModif;
	}

	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}
	
}