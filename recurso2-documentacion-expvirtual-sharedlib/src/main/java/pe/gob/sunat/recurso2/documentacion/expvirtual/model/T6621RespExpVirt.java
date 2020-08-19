package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6621RespExpVirt {
	
	private Long numCorrelativo;			//	num_corresp
	private String numExpedienteVirtual;	//	num_expedv
	private String codColaborador;			//	cod_colab
	private String indResponsable;			//	ind_resp 1 Principal 2 Adicional
	private String codUsuarioRegistro;		//	cod_usuregis
	private Date fecRegistro;				//	fec_regis
	private String codUsuarioModificacion;	//	cod_usumodif
	private Date fecModificacion;			//	fec_modif
	private String indEliminado;			//	ind_del
	
	public Long getNumCorrelativo() {
		return numCorrelativo;
	}
	
	public void setNumCorrelativo(Long numCorrelativo) {
		this.numCorrelativo = numCorrelativo;
	}
	
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
	
	public String getIndResponsable() {
		return indResponsable;
	}

	public void setIndResponsable(String indResponsable) {
		this.indResponsable = indResponsable;
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
	
}