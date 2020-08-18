package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

public class ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codDependencia;
	private String codTipoDoc;
	private String cantidadDocumentos;

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}

	public String getCodTipoDoc() {
		return codTipoDoc;
	}

	public void setCodTipoDoc(String codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
	}

	public String getCantidadDocumentos() {
		return cantidadDocumentos;
	}

	public void setCantidadDocumentos(String cantidadDocumentos) {
		this.cantidadDocumentos = cantidadDocumentos;
	}

}
