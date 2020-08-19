package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

//Inicio [jquispe 01/06/2016]
public abstract class T7731DocAdiExpVirt {

	private Integer numCorrDocAdi; // num_corrdocadi
	private String numExpedienteVir; // num_expedv
	private String numRUC; // num_ruc
	private String codUsuarioInterno; // cod_usuint
	private String codIdentificadorECM;// cod_idecm
	private String codUsuarioRegistro; // cod_usuregis
	private Date fechaRegistro; // fec_regis

	public Integer getNumCorrDocAdi() {
		return numCorrDocAdi;
	}

	public void setNumCorrDocAdi(Integer numCorrDocAdi) {
		this.numCorrDocAdi = numCorrDocAdi;
	}

	public String getNumExpedienteVir() {
		return numExpedienteVir;
	}

	public void setNumExpedienteVir(String numExpedienteVir) {
		this.numExpedienteVir = numExpedienteVir;
	}

	public String getNumRUC() {
		return numRUC;
	}

	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}

	public String getCodUsuarioInterno() {
		return codUsuarioInterno;
	}

	public void setCodUsuarioInterno(String codUsuarioInterno) {
		this.codUsuarioInterno = codUsuarioInterno;
	}

	public String getCodIdentificadorECM() {
		return codIdentificadorECM;
	}

	public void setCodIdentificadorECM(String codIdentificadorECM) {
		this.codIdentificadorECM = codIdentificadorECM;
	}

	public String getCodUsuarioRegistro() {
		return codUsuarioRegistro;
	}

	public void setCodUsuarioRegistro(String codUsuarioRegistro) {
		this.codUsuarioRegistro = codUsuarioRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	//Fin [jquispe 01/06/2016]
}
