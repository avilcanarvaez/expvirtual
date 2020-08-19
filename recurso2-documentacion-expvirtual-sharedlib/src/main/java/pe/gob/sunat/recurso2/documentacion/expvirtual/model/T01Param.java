package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

public abstract class T01Param {
	
	private String codClase;		//	t01_numero
	private String indTipo;			//	t01_tipo
	private String codParametro;	//	t01_argumento
	private String desParametro;	//	t01_funcion
	
	public String getCodClase() {
		return codClase;
	}
	
	public void setCodClase(String codClase) {
		this.codClase = codClase;
	}
	
	public String getIndTipo() {
		return indTipo;
	}
	
	public void setIndTipo(String indTipo) {
		this.indTipo = indTipo;
	}
	
	public String getCodParametro() {
		return codParametro;
	}
	
	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}
	
	public String getDesParametro() {
		return desParametro;
	}
	
	public void setDesParametro(String desParametro) {
		this.desParametro = desParametro;
	}
	
}
