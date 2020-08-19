package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class T10458VersDocAdjBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numVerDoc;				//num_verdoc
	private String nomArchAdj; 				//nom_arc_adj
	private String numCopia;				//num_copia
	private String indVisible;				//ind_visible
	private Integer cntTamArch;				//cnt_tam_arc	
	private String codIdecm;				//cod_id_ecm
	private Integer numCorDoc; 				//num_cordoc	
	private Date fechaRegistro; 			//fec_regis
	private String codUsuRegis;				//cod_usuregis
	private Date fechaMod; 					//fec_mod
	private String codUsuMod;				//cod_usumod
	private Integer numOrden;
	private String esNuevo;
	
	public Integer getNumVerDoc() {
		return numVerDoc;
	}
	public void setNumVerDoc(Integer numVerDoc) {
		this.numVerDoc = numVerDoc;
	}
	public String getNomArchAdj() {
		return nomArchAdj;
	}
	public void setNomArchAdj(String nomArchAdj) {
		this.nomArchAdj = nomArchAdj;
	}
	public String getNumCopia() {
		return numCopia;
	}
	public void setNumCopia(String numCopia) {
		this.numCopia = numCopia;
	}
	public String getIndVisible() {
		return indVisible;
	}
	public void setIndVisible(String indVisible) {
		this.indVisible = indVisible;
	}
	public Integer getCntTamArch() {
		return cntTamArch;
	}
	public void setCntTamArch(Integer cntTamArch) {
		this.cntTamArch = cntTamArch;
	}

	public String getCodIdecm() {
		return codIdecm;
	}
	public void setCodIdecm(String codIdecm) {
		this.codIdecm = codIdecm;
	}
	public Integer getNumCorDoc() {
		return numCorDoc;
	}
	public void setNumCorDoc(Integer numCorDoc) {
		this.numCorDoc = numCorDoc;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}