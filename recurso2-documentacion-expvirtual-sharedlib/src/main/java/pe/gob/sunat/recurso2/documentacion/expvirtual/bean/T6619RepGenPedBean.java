package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6619RepGenPed;

public class T6619RepGenPedBean extends T6619RepGenPed implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/* Descripciones de los campos */
	private String desCodigoNivelReporte;	
	private String desCodEstadoRepPorPedido;	
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private String numExpedienteOrigen;
	private String numRuc;
	private String desRazonSocial;
	private String codResponsable;		
	private String nombreResponsable;
	private String correlativo;
	
	public String getDesCodigoNivelReporte() {
		return desCodigoNivelReporte;
	}
	public void setDesCodigoNivelReporte(String desCodigoNivelReporte) {
		this.desCodigoNivelReporte = desCodigoNivelReporte;
	}
	public String getDesCodEstadoRepPorPedido() {
		return desCodEstadoRepPorPedido;
	}
	public void setDesCodEstadoRepPorPedido(String desCodEstadoRepPorPedido) {
		this.desCodEstadoRepPorPedido = desCodEstadoRepPorPedido;
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
	public String getDesRazonSocial() {
		return desRazonSocial;
	}
	public void setDesRazonSocial(String desRazonSocial) {
		this.desRazonSocial = desRazonSocial;
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
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
