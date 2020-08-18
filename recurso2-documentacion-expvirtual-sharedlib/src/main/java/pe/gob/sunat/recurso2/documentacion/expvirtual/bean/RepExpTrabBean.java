package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.List;

public class RepExpTrabBean implements Serializable {
	private static final long serialVersionUID = -294871250937376326L;
	
	private Integer numRepPed;
	private Integer numRepExp;
	private String numRUC;
	private String razonSocial;
	private String numPlantilla;
	private String desDependencia;
	private String desProceso;
	private String codProceso;
	private String desTipoExpediente;
	private String codTipoExpediente;
	private String indReporteTrib;
	private List<String> listaRepExpTrabRubro;
	private List<String> listaRepExpTrabFormatAdic;
	
	//[eaguilar 03 JUN] campos extra
	private String tipoPersona;
	private String tipoDoc;
	private String numDoc;
	
	private List<RepExpTrabExpVirtBean> listaRepExpTrabExpVirtBeans = null;

	public Integer getNumRepExp() {
		return numRepExp;
	}

	public void setNumRepExp(Integer numRepExp) {
		this.numRepExp = numRepExp;
	}

	public String getNumRUC() {
		return numRUC;
	}

	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
	}

	public String getNumPlantilla() {
		return numPlantilla;
	}

	public void setNumPlantilla(String numPlantilla) {
		this.numPlantilla = numPlantilla;
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

	public List<RepExpTrabExpVirtBean> getListaRepExpTrabExpVirtBeans() {
		return listaRepExpTrabExpVirtBeans;
	}

	public void setListaRepExpTrabExpVirtBeans(
			List<RepExpTrabExpVirtBean> listaRepExpTrabExpVirtBeans) {
		this.listaRepExpTrabExpVirtBeans = listaRepExpTrabExpVirtBeans;
	}

	public String getIndReporteTrib() {
		return indReporteTrib;
	}

	public void setIndReporteTrib(String indReporteTrib) {
		this.indReporteTrib = indReporteTrib;
	}

	public List<String> getListaRepExpTrabRubro() {
		return listaRepExpTrabRubro;
	}

	public void setListaRepExpTrabRubro(List<String> listaRepExpTrabRubro) {
		this.listaRepExpTrabRubro = listaRepExpTrabRubro;
	}

	public List<String> getListaRepExpTrabFormatAdic() {
		return listaRepExpTrabFormatAdic;
	}

	public void setListaRepExpTrabFormatAdic(List<String> listaRepExpTrabFormatAdic) {
		this.listaRepExpTrabFormatAdic = listaRepExpTrabFormatAdic;
	}

	public Integer getNumRepPed() {
		return numRepPed;
	}

	public void setNumRepPed(Integer numRepPed) {
		this.numRepPed = numRepPed;
	}

	public String getDesProceso() {
		return desProceso;
	}

	public void setDesProceso(String desProceso) {
		this.desProceso = desProceso;
	}

	public String getDesTipoExpediente() {
		return desTipoExpediente;
	}

	public void setDesTipoExpediente(String desTipoExpediente) {
		this.desTipoExpediente = desTipoExpediente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDesDependencia() {
		return desDependencia;
	}

	public void setDesDependencia(String desDependencia) {
		this.desDependencia = desDependencia;
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