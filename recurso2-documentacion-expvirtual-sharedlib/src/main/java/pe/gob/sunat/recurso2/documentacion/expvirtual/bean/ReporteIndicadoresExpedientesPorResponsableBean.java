package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class ReporteIndicadoresExpedientesPorResponsableBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codResponsable;
	private String nombreResponsable;	
	private Date fecRegistroResponsable;
	private String codDependencia;
	private String desDependencia;
	private String numRuc;
	private String numExpdteOrigen;
	private String numExpdteVirtual;
	private String codProceso;
	private String desProceso;
	private String codTipoExpediente;
	private String desTipoExpediente;
	private Date fecEmisionExpOrigen;
	private Date fecNotificacionExpOrigen;
	private Date fechaGeneracion;
	private String codOrigen;
	private String codEstado;
	private String desOrigen;
	private String desEstado;
	public String getCodResponsable() {
		return codResponsable;
	}
	public void setCodResponsable(String codResponsable) {
		this.codResponsable = codResponsable;
	}
	public String getNombreResponsable() {
		return nombreResponsable;
	}
	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}
	public Date getFecRegistroResponsable() {
		return fecRegistroResponsable;
	}
	public void setFecRegistroResponsable(Date fecRegistroResponsable) {
		this.fecRegistroResponsable = fecRegistroResponsable;
	}
	public String getCodDependencia() {
		return codDependencia;
	}
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
	public String getDesDependencia() {
		return desDependencia;
	}
	public void setDesDependencia(String desDependencia) {
		this.desDependencia = desDependencia;
	}
	public String getNumRuc() {
		return numRuc;
	}
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
	public String getNumExpdteOrigen() {
		return numExpdteOrigen;
	}
	public void setNumExpdteOrigen(String numExpdteOrigen) {
		this.numExpdteOrigen = numExpdteOrigen;
	}
	public String getNumExpdteVirtual() {
		return numExpdteVirtual;
	}
	public void setNumExpdteVirtual(String numExpdteVirtual) {
		this.numExpdteVirtual = numExpdteVirtual;
	}
	public String getCodProceso() {
		return codProceso;
	}
	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}
	public String getDesProceso() {
		return desProceso;
	}
	public void setDesProceso(String desProceso) {
		this.desProceso = desProceso;
	}
	public String getCodTipoExpediente() {
		return codTipoExpediente;
	}
	public void setCodTipoExpediente(String codTipoExpediente) {
		this.codTipoExpediente = codTipoExpediente;
	}
	public String getDesTipoExpediente() {
		return desTipoExpediente;
	}
	public void setDesTipoExpediente(String desTipoExpediente) {
		this.desTipoExpediente = desTipoExpediente;
	}
	public Date getFecEmisionExpOrigen() {
		return fecEmisionExpOrigen;
	}
	public void setFecEmisionExpOrigen(Date fecEmisionExpOrigen) {
		this.fecEmisionExpOrigen = fecEmisionExpOrigen;
	}
	public Date getFecNotificacionExpOrigen() {
		return fecNotificacionExpOrigen;
	}
	public void setFecNotificacionExpOrigen(Date fecNotificacionExpOrigen) {
		this.fecNotificacionExpOrigen = fecNotificacionExpOrigen;
	}
	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public String getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}
	public String getDesOrigen() {
		return desOrigen;
	}
	public void setDesOrigen(String desOrigen) {
		this.desOrigen = desOrigen;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
}
