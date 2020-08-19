/**
 * 
 */
package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6616PedRep;

/**
 * @author USER
 *
 */
public class T6616PedRepBean extends T6616PedRep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* Descripciones de los campos */
	private String desProceso;
	private String desTipoExpediente;
	private String desTipoGeneracionReporte;
	private String desEstadoReporte;	
	private String nombrePersonaRegistro;
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private String numExpedienteVirtual;
	private String numExpedienteOrigen;
	private String numRuc;
	private String desRazonSocial;
	private String codResponsable;		
	private String nombreResponsable;
	private String correlativo;
	private Date fecGenera;						
	private String fechaGenera;
	private String fechaRegistro;
	private Integer numReportePedido;
	private String descripcionPlantilla;
	
	private List<String> lstErrors; //eaguilar: para mostrar los errores en la vista
	
	public String getFechaGenera() {
		return fechaGenera;
	}
	public void setFechaGenera(String fechaGenera) {
		this.fechaGenera = fechaGenera;
	}
	public String getDesProceso() {
		return desProceso;
	}
	public void setDesProceso(String desProceso) {
		this.desProceso = desProceso;
	}
	public String getDesTipoExpediente() {
		return desTipoExpediente;
	}
	public void setDesTipoExpediente(String desTipoExpediente) {
		this.desTipoExpediente = desTipoExpediente;
	}
	public String getDesTipoGeneracionReporte() {
		return desTipoGeneracionReporte;
	}
	public void setDesTipoGeneracionReporte(String desTipoGeneracionReporte) {
		this.desTipoGeneracionReporte = desTipoGeneracionReporte;
	}
	public String getDesEstadoReporte() {
		return desEstadoReporte;
	}
	public void setDesEstadoReporte(String desEstadoReporte) {
		this.desEstadoReporte = desEstadoReporte;
	}
	public String getNombrePersonaRegistro() {
		return nombrePersonaRegistro;
	}
	public void setNombrePersonaRegistro(String nombrePersonaRegistro) {
		this.nombrePersonaRegistro = nombrePersonaRegistro;
	}
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	public String getNumExpedienteOrigen() {
		return numExpedienteOrigen;
	}
	public void setNumExpedienteOrigen(String numExpedienteOrigen) {
		this.numExpedienteOrigen = numExpedienteOrigen;
	}
	public String getNumRuc() {
		return numRuc;
	}
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
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
	public String getDesRazonSocial() {
		return desRazonSocial;
	}
	public void setDesRazonSocial(String desRazonSocial) {
		this.desRazonSocial = desRazonSocial;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
	public Integer getNumReportePedido() {
		return numReportePedido;
	}
	public void setNumReportePedido(Integer numReportePedido) {
		this.numReportePedido = numReportePedido;
	}
	public String getDescripcionPlantilla() {
		return descripcionPlantilla;
	}
	public void setDescripcionPlantilla(String descripcionPlantilla) {
		this.descripcionPlantilla = descripcionPlantilla;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFecGenera() {
		return fecGenera;
	}
	public void setFecGenera(Date fecGenera) {
		this.fecGenera = fecGenera;
	}
	public List<String> getLstErrors() {
		return lstErrors;
	}
	public void setLstErrors(List<String> lstErrors) {
		this.lstErrors = lstErrors;
	}
	
}