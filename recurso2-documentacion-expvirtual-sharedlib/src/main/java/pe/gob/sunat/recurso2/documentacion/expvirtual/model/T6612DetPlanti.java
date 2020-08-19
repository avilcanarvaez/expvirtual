package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6612DetPlanti {

	String numCorDetPlanti;		//num_cordetplanti
	String numPlantilla; 		//num_planti
	String codTipoDocumento;	//cod_tipdoc
	String codRepTrbib;			//cod_rubr
	String codFormatoAdi;		//cod_formadi
	String usuarioRegistro;		//cod_usuregis
	Date fechaRegistro;			//fec_regis
	String usuarioMod;			//cod_usumodif
	Date fechaModificacion;		//fec_modif
	String codUsuarioBaja;		//cod_usubaja
	Date fechaBaja;				//fec_baja
	String indicadorSelDoc;		//ind_seldoc
	String indicadorSelRubro;	//ind_selrubro
	String indicadorSelForma;	//ind_selforma
	
	public String getNumCorDetPlanti() {
		return numCorDetPlanti;
	}
	public void setNumCorDetPlanti(String numCorDetPlanti) {
		this.numCorDetPlanti = numCorDetPlanti;
	}
	public String getNumPlantilla() {
		return numPlantilla;
	}
	public void setNumPlantilla(String numPlantilla) {
		this.numPlantilla = numPlantilla;
	}
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getCodFormatoAdi() {
		return codFormatoAdi;
	}
	public void setCodFormatoAdi(String codFormatoAdi) {
		this.codFormatoAdi = codFormatoAdi;
	}
	public String getCodRepTrbib() {
		return codRepTrbib;
	}
	public void setCodRepTrbib(String codRepTrbib) {
		this.codRepTrbib = codRepTrbib;
	}
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getUsuarioMod() {
		return usuarioMod;
	}
	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getCodUsuarioBaja() {
		return codUsuarioBaja;
	}
	public void setCodUsuarioBaja(String codUsuarioBaja) {
		this.codUsuarioBaja = codUsuarioBaja;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getIndicadorSelDoc() {
		return indicadorSelDoc;
	}
	public void setIndicadorSelDoc(String indicadorSelDoc) {
		this.indicadorSelDoc = indicadorSelDoc;
	}
	public String getIndicadorSelRubro() {
		return indicadorSelRubro;
	}
	public void setIndicadorSelRubro(String indicadorSelRubro) {
		this.indicadorSelRubro = indicadorSelRubro;
	}
	public String getIndicadorSelForma() {
		return indicadorSelForma;
	}
	public void setIndicadorSelForma(String indicadorSelForma) {
		this.indicadorSelForma = indicadorSelForma;
	}
		
}
