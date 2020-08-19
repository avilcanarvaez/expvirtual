package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.ExpCoa;

public class ExpCoaBean extends ExpCoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fechaEmisionCompleta;
	
	// Inicio [jjurado 26/05/2016] Datos adicionales para el Ejecutor
	private String codEjecutor;
	private String desEjecutor;
	private Date fechaNotificacion;
	// Fin [jjurado 26/05/2016]
	
	public String getFechaEmisionCompleta() {
		return fechaEmisionCompleta;
	}

	public void setFechaEmisionCompleta(String fechaEmisionCompleta) {
		this.fechaEmisionCompleta = fechaEmisionCompleta;
	}

	public String getCodEjecutor() {
		return codEjecutor;
	}

	public void setCodEjecutor(String codEjecutor) {
		this.codEjecutor = codEjecutor;
	}

	public String getDesEjecutor() {
		return desEjecutor;
	}

	public void setDesEjecutor(String desEjecutor) {
		this.desEjecutor = desEjecutor;
	}

	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
}