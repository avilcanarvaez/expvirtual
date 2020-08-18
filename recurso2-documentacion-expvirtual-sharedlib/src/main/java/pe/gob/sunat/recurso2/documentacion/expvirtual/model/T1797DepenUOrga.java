package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

public abstract class T1797DepenUOrga {
	
	private String codUnidadOrganizacional;		//	cod_uorga
	private String codTipoDependencia;			//	tip_depend
	private String codDependencia;				//	cod_depend
	
	public String getCodUnidadOrganizacional() {
		return codUnidadOrganizacional;
	}
	
	public void setCodUnidadOrganizacional(String codUnidadOrganizacional) {
		this.codUnidadOrganizacional = codUnidadOrganizacional;
	}
	
	public String getCodTipoDependencia() {
		return codTipoDependencia;
	}
	
	public void setCodTipoDependencia(String codTipoDependencia) {
		this.codTipoDependencia = codTipoDependencia;
	}
	
	public String getCodDependencia() {
		return codDependencia;
	}
	
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
	
}