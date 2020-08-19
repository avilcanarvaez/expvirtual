package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6611CabPlanti {

	private String numPlantilla; 		//num_planti
	private String desPlantilla; 		//des_planti
	private String codProceso;			//cod_proc
	private String codTipoExpediente;		//cod_tipexp
	private String codEstadoPlanti;		//cod_estplant
	private String codUsuRegistro;		//cod_usuregis
	private Date fechaRegistro;		//fec_regis
	private String codUsuModifica;		//cod_usumodif
	private Date fechaModifica;		//fec_modif
	private String codUsuBaja;		    //cod_usubaja
	private Date fechaBaja;		    //fec_baja
	private String indRepTrib; 		//ind_reptrip
	
	public String getNumPlantilla() {
		return numPlantilla;
	}
	public void setNumPlantilla(String numPlantilla) {
		this.numPlantilla = numPlantilla;
	}
	public String getDesPlantilla() {
		return desPlantilla;
	}
	public void setDesPlantilla(String desPlantilla) {
		this.desPlantilla = desPlantilla;
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
	public String getCodEstadoPlanti() {
		return codEstadoPlanti;
	}
	public void setCodEstadoPlanti(String codEstadoPlanti) {
		this.codEstadoPlanti = codEstadoPlanti;
	}
	public String getCodUsuRegistro() {
		return codUsuRegistro;
	}
	public void setCodUsuRegistro(String codUsuRegistro) {
		this.codUsuRegistro = codUsuRegistro;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getCodUsuModifica() {
		return codUsuModifica;
	}
	public void setCodUsuModifica(String codUsuModifica) {
		this.codUsuModifica = codUsuModifica;
	}
	public Date getFechaModifica() {
		return fechaModifica;
	}
	public void setFechaModifica(Date fechaModifica) {
		this.fechaModifica = fechaModifica;
	}
	public String getCodUsuBaja() {
		return codUsuBaja;
	}
	public void setCodUsuBaja(String codUsuBaja) {
		this.codUsuBaja = codUsuBaja;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getIndRepTrib() {
		return indRepTrib;
	}
	public void setIndRepTrib(String indRepTrib) {
		this.indRepTrib = indRepTrib;
	}
		
	
}
