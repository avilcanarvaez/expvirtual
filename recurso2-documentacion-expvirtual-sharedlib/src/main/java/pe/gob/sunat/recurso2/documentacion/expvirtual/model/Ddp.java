package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

public abstract class Ddp {
	
	private String numRuc;						// DDP_NUMRUC
	private String desRazonSocial;				// DDP_NOMBRE
	private String codDependencia;				// DDP_NUMREG
	private String codTipoPersona;				// DDP_TPOEMP
	private String codActividadEconocimica;		// DDP_CIIU V4
	private String codCondicionDomicilio;		// DDP_FLAG22
	private String codRegimenRenta;				// DDP_MCLASE
	private String codEstado;					// DDP_ESTADO
	
	//[eaguilar 03 JUN] campos extra
	private String tipoPersona;                 // DDP_IDENTI
	private String tipoDoc;                     // DDS_DOCIDE
	private String numDoc;                      // DDS_NRODOC

	
	public String getNumRuc() {
		return numRuc;
	}
	
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
	
	public String getDesRazonSocial() {
		return desRazonSocial;
	}
	
	public void setDesRazonSocial(String desRazonSocial) {
		this.desRazonSocial = desRazonSocial;
	}
	
	public String getCodDependencia() {
		return codDependencia;
	}
	
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
	
	public String getCodTipoPersona() {
		return codTipoPersona;
	}
	
	public void setCodTipoPersona(String codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}
	
	public String getCodActividadEconocimica() {
		return codActividadEconocimica;
	}
	
	public void setCodActividadEconocimica(String codActividadEconocimica) {
		this.codActividadEconocimica = codActividadEconocimica;
	}
	
	public String getCodCondicionDomicilio() {
		return codCondicionDomicilio;
	}
	
	public void setCodCondicionDomicilio(String codCondicionDomicilio) {
		this.codCondicionDomicilio = codCondicionDomicilio;
	}
	
	public String getCodRegimenRenta() {
		return codRegimenRenta;
	}
	
	public void setCodRegimenRenta(String codRegimenRenta) {
		this.codRegimenRenta = codRegimenRenta;
	}
	
	public String getCodEstado() {
		return codEstado;
	}
	
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}
	
	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	
}