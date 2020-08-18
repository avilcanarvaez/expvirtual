package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class T10373DocAdjReqBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numArchAdj;				//num_arc_adj
	private String numRequerimiento;		//num_requerim
	private Integer numItem;				//num_item
	private Integer numSubItem;				//num_sub_item
	private Integer numArcItem;				//num_arc_item
	private String nomArchAdj; 				//nom_arc_adj
	private String numArchivo;				//num_archivo
	private Integer cntTamArch;				//cnt_tam_arc
	private Integer numFolios;				//num_folios
	private String codTipAlm;				//cod_tip_alm
	private String codIdECM;				//cod_id_ecm
	private Integer numCorDoc; 				//num_cordoc
	private String codOriDoc;				//cod_origdoc
	private Date fechaRegistro; 			//fec_regis
	private String codUsuRegis;				//cod_usuregis
	private Date fechaMod; 					//fec_mod
	private String codUsuMod;				//cod_usumod
	private Integer numOrden;
	private String esNuevo;
	private String mimeType;
	private Integer tamArch;
	private Integer folios;
	
	public Integer getNumArchAdj() {
		return numArchAdj;
	}
	public void setNumArchAdj(Integer numArchAdj) {
		this.numArchAdj = numArchAdj;
	}
	public String getNumRequerimiento() {
		return numRequerimiento;
	}
	public void setNumRequerimiento(String numRequerimiento) {
		this.numRequerimiento = numRequerimiento;
	}
	public Integer getNumItem() {
		return numItem;
	}
	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}
	public Integer getNumSubItem() {
		return numSubItem;
	}
	public void setNumSubItem(Integer numSubItem) {
		this.numSubItem = numSubItem;
	}
	public Integer getNumArcItem() {
		return numArcItem;
	}
	public void setNumArcItem(Integer numArcItem) {
		this.numArcItem = numArcItem;
	}
	public String getNomArchAdj() {
		return nomArchAdj;
	}
	public void setNomArchAdj(String nomArchAdj) {
		this.nomArchAdj = nomArchAdj;
	}
	public String getNumArchivo() {
		return numArchivo;
	}
	public void setNumArchivo(String numArchivo) {
		this.numArchivo = numArchivo;
	}
	public Integer getCntTamArch() {
		return cntTamArch;
	}
	public void setCntTamArch(Integer cntTamArch) {
		this.cntTamArch = cntTamArch;
	}
	public Integer getNumFolios() {
		return numFolios;
	}
	public void setNumFolios(Integer numFolios) {
		this.numFolios = numFolios;
	}
	public String getCodTipAlm() {
		return codTipAlm;
	}
	public void setCodTipAlm(String codTipAlm) {
		this.codTipAlm = codTipAlm;
	}
	public String getCodIdECM() {
		return codIdECM;
	}
	public void setCodIdECM(String codIdECM) {
		this.codIdECM = codIdECM;
	}
	public Integer getNumCorDoc() {
		return numCorDoc;
	}
	public void setNumCorDoc(Integer numCorDoc) {
		this.numCorDoc = numCorDoc;
	}
	public String getCodOriDoc() {
		return codOriDoc;
	}
	public void setCodOriDoc(String codOriDoc) {
		this.codOriDoc = codOriDoc;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getCodUsuRegis() {
		return codUsuRegis;
	}
	public void setCodUsuRegis(String codUsuRegis) {
		this.codUsuRegis = codUsuRegis;
	}
	public Date getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
	}
	public String getCodUsuMod() {
		return codUsuMod;
	}
	public void setCodUsuMod(String codUsuMod) {
		this.codUsuMod = codUsuMod;
	}
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	public String getEsNuevo() {
		return esNuevo;
	}
	public void setEsNuevo(String esNuevo) {
		this.esNuevo = esNuevo;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Integer getTamArch() {
		return tamArch;
	}
	public void setTamArch(Integer tamArch) {
		this.tamArch = tamArch;
	}
	public Integer getFolios() {
		return folios;
	}
	public void setFolios(Integer folios) {
		this.folios = folios;
	}
	
	
}