package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6624TipExpProc {
	
	private String codProceso;
	private String codTipoExpediente;
	private String codUsuarioRegistro;
	private Date fecRegistro;
	private String codUsuarioModificacion;
	private Date fecModificacion;
	private String indEliminado;
	
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
