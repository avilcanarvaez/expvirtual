package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T10459AccepExpVirt {
	
	private String numSoli;			//	num_corresp
	private String numExpedienteVirtual;	//	num_expedv
	private String codColaborador;			//	cod_colab
	private String codMotivo;
	private String tipSensib;				//	ind_resp 1 Principal 2 Adicional
	private String codPeriodo;
	private String codEstado;
	private Date fecInicio;
	private Date fecFinal;
	private String codUsuarioRegistro;		//	cod_usuregis
	private Date fecRegistro;				//	fec_regis
	private Date fecSoli;				//	fec_soli
	private String codUsuarioModificacion;	//	cod_usumodif
	private Date fecModificacion;			//	fec_modif
	private String indEliminado;			//	ind_del
	private String indResponsable;	
	

	
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
	
	public String getCodColaborador() {
		return codColaborador;
	}
	
	public void setCodColaborador(String codColaborador) {
		this.codColaborador = codColaborador;
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
	
	public String getCodUsuarioModificacion() {
		return codUsuarioModificacion;
	}
	
	public void setCodUsuarioModificacion(String codUsuarioModificacion) {
		this.codUsuarioModificacion = codUsuarioModificacion;
	}
	
	public Date getFecModificacion() {
		return fecModificacion;
	}
	
	public void setFecModificacion(Date fecModificacion) {
		this.fecModificacion = fecModificacion;
	}
	
	public String getIndEliminado() {
		return indEliminado;
	}
	
	public void setIndEliminado(String indEliminado) {
		this.indEliminado = indEliminado;
	}

	public String getNumSoli() {
		return numSoli;
	}

	public void setNumSoli(String numSoli) {
		this.numSoli = numSoli;
	}

	public String getCodMotivo() {
		return codMotivo;
	}

	public void setCodMotivo(String codMotivo) {
		this.codMotivo = codMotivo;
	}

	public String getTipSensib() {
		return tipSensib;
	}

	public void setTipSensib(String tipSensib) {
		this.tipSensib = tipSensib;
	}

	public String getCodPeriodo() {
		return codPeriodo;
	}

	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}

	public String getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public Date getFecInicio() {
		return fecInicio;
	}

	public void setFecInicio(Date fecInicio) {
		this.fecInicio = fecInicio;
	}

	public Date getFecFinal() {
		return fecFinal;
	}

	public void setFecFinal(Date fecFinal) {
		this.fecFinal = fecFinal;
	}

	public String getIndResponsable() {
		return indResponsable;
	}

	public void setIndResponsable(String indResponsable) {
		this.indResponsable = indResponsable;
	}

	public Date getFecSoli() {
		return fecSoli;
	}

	public void setFecSoli(Date fecSoli) {
		this.fecSoli = fecSoli;
	}
	
	
	
	
	
}