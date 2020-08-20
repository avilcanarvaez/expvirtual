package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

public class ExportarDocumentosData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numExpOrigen;
	private String numExpDestino;
	private String codUsuario;
	
	public String getNumExpOrigen() {
		return numExpOrigen;
	}
	public void setNumExpOrigen(String numExpOrigen) {
		this.numExpOrigen = numExpOrigen;
	}
	public String getNumExpDestino() {
		return numExpDestino;
	}
	public void setNumExpDestino(String numExpDestino) {
		this.numExpDestino = numExpDestino;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

}
