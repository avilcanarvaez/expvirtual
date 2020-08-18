package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

public class RepExpTrabDocBean implements Serializable {
	private static final long serialVersionUID = -294871250937376326L;

	// Datos de documento
	private String numDocumento;
	private String codigoTipoDocumento;
	private String nombreDocumento;
	private String codIdecm;

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getCodIdecm() {
		return codIdecm;
	}

	public void setCodIdecm(String codIdecm) {
		this.codIdecm = codIdecm;
	}
}