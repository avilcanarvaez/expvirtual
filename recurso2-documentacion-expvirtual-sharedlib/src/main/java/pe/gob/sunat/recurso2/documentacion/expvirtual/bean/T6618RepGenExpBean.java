package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6618RepGenExp;

public class T6618RepGenExpBean extends T6618RepGenExp implements Serializable{
	private static final long serialVersionUID = 1L;

	/* Descripciones de los campos */
	private String desCodigoNivelReporte;	
	private String desCodEstadoRepPorExpediente;	
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private String correlativo;
	private String fechaGeneracion;
	private Integer numReportePedido;
	
	public String getDesCodigoNivelReporte() {
		return desCodigoNivelReporte;
	}
	public void setDesCodigoNivelReporte(String desCodigoNivelReporte) {
		this.desCodigoNivelReporte = desCodigoNivelReporte;
	}
	public String getDesCodEstadoRepPorExpediente() {
		return desCodEstadoRepPorExpediente;
	}
	public void setDesCodEstadoRepPorExpediente(String desCodEstadoRepPorExpediente) {
		this.desCodEstadoRepPorExpediente = desCodEstadoRepPorExpediente;
	}
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getNumReportePedido() {
		return numReportePedido;
	}
	public void setNumReportePedido(Integer numReportePedido) {
		this.numReportePedido = numReportePedido;
	}	
		
}
