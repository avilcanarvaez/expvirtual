package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: nchavez
 * @date : 24/08/2016
 * @time : 10:35:40
 */
public class ReporteIndicadoresRepresImprPorExpdteYDependenciaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codDependencia;
	private String numeroRuc;
	private String numExpdteOrigen;
	private String numExpdteVirtual;
	private Date fecRegistroExpdte;
	private String codOrigen;
	private String codEstado;
	private Integer cantidadDocumentos;
	private Integer cantidadExpRepImpUsu;
	private Date fecUltRepImpUsu;
	private Integer cantidadExpRepImpCont;
	private Date fecUltRepImpCont;

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}

	public String getNumeroRuc() {
		return numeroRuc;
	}

	public void setNumeroRuc(String numeroRuc) {
		this.numeroRuc = numeroRuc;
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

	public Date getFecRegistroExpdte() {
		return fecRegistroExpdte;
	}

	public void setFecRegistroExpdte(Date fecRegistroExpdte) {
		this.fecRegistroExpdte = fecRegistroExpdte;
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

	public Integer getCantidadDocumentos() {
		return cantidadDocumentos;
	}

	public void setCantidadDocumentos(Integer cantidadDocumentos) {
		this.cantidadDocumentos = cantidadDocumentos;
	}

	public Integer getCantidadExpRepImpUsu() {
		return cantidadExpRepImpUsu;
	}

	public void setCantidadExpRepImpUsu(Integer cantidadExpRepImpUsu) {
		this.cantidadExpRepImpUsu = cantidadExpRepImpUsu;
	}

	public Date getFecUltRepImpUsu() {
		return fecUltRepImpUsu;
	}

	public void setFecUltRepImpUsu(Date fecUltRepImpUsu) {
		this.fecUltRepImpUsu = fecUltRepImpUsu;
	}

	public Integer getCantidadExpRepImpCont() {
		return cantidadExpRepImpCont;
	}

	public void setCantidadExpRepImpCont(Integer cantidadExpRepImpCont) {
		this.cantidadExpRepImpCont = cantidadExpRepImpCont;
	}

	public Date getFecUltRepImpCont() {
		return fecUltRepImpCont;
	}

	public void setFecUltRepImpCont(Date fecUltRepImpCont) {
		this.fecUltRepImpCont = fecUltRepImpCont;
	}
}
