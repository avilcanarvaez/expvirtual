package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

public class BeanParametrosConsultaReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codTipBusquedaExp;
	private String numExp;
	private String codProceso;
	private String codTipexp;
	private String codTipBusquedaFecha;
	private String fecDesde;
	private String fecHasta;
	private String numRuc;
	private String razonSolcial;
	private String numPaginacionTabla;
	private String realizarBusqueda;
	//Inicio nchavez 14/07/2016
	private String codDependencia;
	//Fin nchavez 14/07/2016
	
	//manto plantilla
	private String selProceso;
	private String selTipoExpediente;
	private String fechaDes;
	private String fechaHas;
	
	//impresion reporte generado
	private String numPedido;
	
	//registro doc internos
	private String numExpe;
	private String selNumeroTipoExpediente;
	private String selTipoFecha;
	private String txtNumRuc;
	private String txtRazonSocial;
	
	//pagina actual de la grilla
	private String tblPage;
	
	private String numDoc; //[PAS20201U210500029] - HHC
	private String tipDoc; //[PAS20201U210500029] - HHC
	
	public String getCodTipBusquedaExp() {
		return codTipBusquedaExp;
	}
	public void setCodTipBusquedaExp(String codTipBusquedaExp) {
		this.codTipBusquedaExp = codTipBusquedaExp;
	}
	public String getNumExp() {
		return numExp;
	}
	public void setNumExp(String numExp) {
		this.numExp = numExp;
	}
	public String getCodProceso() {
		return codProceso;
	}
	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}
	public String getCodTipexp() {
		return codTipexp;
	}
	public void setCodTipexp(String codTipexp) {
		this.codTipexp = codTipexp;
	}
	public String getCodTipBusquedaFecha() {
		return codTipBusquedaFecha;
	}
	public void setCodTipBusquedaFecha(String codTipBusquedaFecha) {
		this.codTipBusquedaFecha = codTipBusquedaFecha;
	}
	public String getFecDesde() {
		return fecDesde;
	}
	public void setFecDesde(String fecDesde) {
		this.fecDesde = fecDesde;
	}
	public String getFecHasta() {
		return fecHasta;
	}
	public void setFecHasta(String fecHasta) {
		this.fecHasta = fecHasta;
	}
	public String getNumRuc() {
		return numRuc;
	}
	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}
	public String getRazonSolcial() {
		return razonSolcial;
	}
	public void setRazonSolcial(String razonSolcial) {
		this.razonSolcial = razonSolcial;
	}
	public String getNumPaginacionTabla() {
		return numPaginacionTabla;
	}
	public void setNumPaginacionTabla(String numPaginacionTabla) {
		this.numPaginacionTabla = numPaginacionTabla;
	}
	public String getRealizarBusqueda() {
		return realizarBusqueda;
	}
	public void setRealizarBusqueda(String realizarBusqueda) {
		this.realizarBusqueda = realizarBusqueda;
	}
	public String getSelProceso() {
		return selProceso;
	}
	public void setSelProceso(String selProceso) {
		this.selProceso = selProceso;
	}
	public String getSelTipoExpediente() {
		return selTipoExpediente;
	}
	public void setSelTipoExpediente(String selTipoExpediente) {
		this.selTipoExpediente = selTipoExpediente;
	}
	public String getFechaDes() {
		return fechaDes;
	}
	public void setFechaDes(String fechaDes) {
		this.fechaDes = fechaDes;
	}
	public String getFechaHas() {
		return fechaHas;
	}
	public void setFechaHas(String fechaHas) {
		this.fechaHas = fechaHas;
	}
	public String getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}
	public String getNumExpe() {
		return numExpe;
	}
	public void setNumExpe(String numExpe) {
		this.numExpe = numExpe;
	}
	public String getSelNumeroTipoExpediente() {
		return selNumeroTipoExpediente;
	}
	public void setSelNumeroTipoExpediente(String selNumeroTipoExpediente) {
		this.selNumeroTipoExpediente = selNumeroTipoExpediente;
	}
	public String getSelTipoFecha() {
		return selTipoFecha;
	}
	public void setSelTipoFecha(String selTipoFecha) {
		this.selTipoFecha = selTipoFecha;
	}
	public String getTxtNumRuc() {
		return txtNumRuc;
	}
	public void setTxtNumRuc(String txtNumRuc) {
		this.txtNumRuc = txtNumRuc;
	}
	public String getTxtRazonSocial() {
		return txtRazonSocial;
	}
	public void setTxtRazonSocial(String txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}
	public String getTblPage() {
	    return tblPage;
    }
	public void setTblPage(String tblPage) {
	    this.tblPage = tblPage;
    }
	public String getCodDependencia() {
		return codDependencia;
	}
	public void setCodDependencia(String codDependencia) {
		this.codDependencia = codDependencia;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getTipDoc() {
		return tipDoc;
	}
	public void setTipDoc(String tipDoc) {
		this.tipDoc = tipDoc;
	}
}
