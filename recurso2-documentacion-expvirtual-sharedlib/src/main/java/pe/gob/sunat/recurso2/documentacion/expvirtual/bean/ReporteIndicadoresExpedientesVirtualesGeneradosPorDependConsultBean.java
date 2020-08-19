package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codDependencia;
	private String numRuc;
	private String numExpdteOrigen;
	private String numExpdteVirtual;
	private String codProceso;
	private String codTipoExpediente;
	private Date fechaGeneracion;
	private String codOrigen;
	private String codEstado;
	private Integer indicLectUsuInt;
	private Integer indicLectContrib;
	private Date fecUltLecUsuInt;
	private Date fecUltLecContrib;

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
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

	public String getCodTipoExpediente() {
		return codTipoExpediente;
	}

	public void setCodTipoExpediente(String codTipoExpediente) {
		this.codTipoExpediente = codTipoExpediente;
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

	public Integer getIndicLectUsuInt() {
		return indicLectUsuInt;
	}

	public void setIndicLectUsuInt(Integer indicLectUsuInt) {
		this.indicLectUsuInt = indicLectUsuInt;
	}

	public Integer getIndicLectContrib() {
		return indicLectContrib;
	}

	public void setIndicLectContrib(Integer indicLectContrib) {
		this.indicLectContrib = indicLectContrib;
	}

	public Date getFecUltLecUsuInt() {
		return fecUltLecUsuInt;
	}

	public void setFecUltLecUsuInt(Date fecUltLecUsuInt) {
		this.fecUltLecUsuInt = fecUltLecUsuInt;
	}

	public Date getFecUltLecContrib() {
		return fecUltLecContrib;
	}

	public void setFecUltLecContrib(Date fecUltLecContrib) {
		this.fecUltLecContrib = fecUltLecContrib;
	}

}
