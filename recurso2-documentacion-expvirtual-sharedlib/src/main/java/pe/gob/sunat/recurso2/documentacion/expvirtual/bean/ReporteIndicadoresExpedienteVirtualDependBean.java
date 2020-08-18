package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ReporteIndicadoresExpedienteVirtualDependBean extends ReporteIndicadoresExpedienteVirtualBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Datos de expediente
	private String codDependencia;

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
