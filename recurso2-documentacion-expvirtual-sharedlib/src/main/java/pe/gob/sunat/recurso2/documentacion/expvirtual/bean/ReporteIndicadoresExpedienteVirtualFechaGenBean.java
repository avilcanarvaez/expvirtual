package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class ReporteIndicadoresExpedienteVirtualFechaGenBean extends ReporteIndicadoresExpedienteVirtualBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Datos de expediente
	private Date fechaGeneracion;

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
