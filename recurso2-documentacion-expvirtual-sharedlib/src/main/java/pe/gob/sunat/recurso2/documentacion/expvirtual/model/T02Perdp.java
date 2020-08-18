package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

public abstract class T02Perdp {
	
	private String codPersona;					//	t02cod_pers
	private String desNombres;					//	t02nombres
	private String desApellidoPaterno;			//	t02ap_pate
	private String desApellidoMaterno;			//	t02ap_mate
	private String codUnidadOrganizacional;		//	t02cod_uorg
	private String indHabilitado;				//	t02cod_stat
	
	public String getCodPersona() {
		return codPersona;
	}
	
	public void setCodPersona(String codPersona) {
		this.codPersona = codPersona;
	}
	
	public String getDesNombres() {
		return desNombres;
	}
	
	public void setDesNombres(String desNombres) {
		this.desNombres = desNombres;
	}
	
	public String getDesApellidoPaterno() {
		return desApellidoPaterno;
	}
	
	public void setDesApellidoPaterno(String desApellidoPaterno) {
		this.desApellidoPaterno = desApellidoPaterno;
	}
	
	public String getDesApellidoMaterno() {
		return desApellidoMaterno;
	}
	
	public void setDesApellidoMaterno(String desApellidoMaterno) {
		this.desApellidoMaterno = desApellidoMaterno;
	}
	
	public String getCodUnidadOrganizacional() {
		return codUnidadOrganizacional;
	}
	
	public void setCodUnidadOrganizacional(String codUnidadOrganizacional) {
		this.codUnidadOrganizacional = codUnidadOrganizacional;
	}
	
	public String getIndHabilitado() {
		return indHabilitado;
	}
	
	public void setIndHabilitado(String indHabilitado) {
		this.indHabilitado = indHabilitado;
	}
	
}