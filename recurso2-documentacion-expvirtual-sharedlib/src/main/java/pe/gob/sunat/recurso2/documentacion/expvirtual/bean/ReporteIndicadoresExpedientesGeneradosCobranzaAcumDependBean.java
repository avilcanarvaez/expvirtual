package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

public class ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean {
	private static final long serialVersionUID = 1L;

	private String codDependencia;
	private String numRuc;
	private String numExpdteOrigen;
	private String indicAcum;
	private String expdteRelac;
	private String cantDocsExpdteVirt;
	private String codOrigenExpdte;

	public String getCodDependencia() {
		return codDependencia;
	}

	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getNumExpdteOrigen() {
		return numExpdteOrigen;
	}

	public void setNumExpdteOrigen(String numExpdteOrigen) {
		this.numExpdteOrigen = numExpdteOrigen;
	}

	public String getIndicAcum() {
		return indicAcum;
	}

	public void setIndicAcum(String indicAcum) {
		this.indicAcum = indicAcum;
	}

	public String getExpdteRelac() {
		return expdteRelac;
	}

	public void setExpdteRelac(String expdteRelac) {
		this.expdteRelac = expdteRelac;
	}

	public String getCantDocsExpdteVirt() {
		return cantDocsExpdteVirt;
	}

	public void setCantDocsExpdteVirt(String cantDocsExpdteVirt) {
		this.cantDocsExpdteVirt = cantDocsExpdteVirt;
	}

	public String getCodOrigenExpdte() {
		return codOrigenExpdte;
	}

	public void setCodOrigenExpdte(String codOrigenExpdte) {
		this.codOrigenExpdte = codOrigenExpdte;
	}

}
