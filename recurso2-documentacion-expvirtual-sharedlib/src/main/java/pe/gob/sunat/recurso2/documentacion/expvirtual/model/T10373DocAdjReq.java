package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T10373DocAdjReq {
	
	private Integer numArcAdj;		// num_arc_adj
	private String numReq; 		// num_requerim
	private Integer numItem;	// num_item
	private Integer  numSubItem; 	// num_sub_item
	private Integer numArcItem; //num_arc_item
	private String nomArcAdj; 	//nom_arc_adj
	private String numArc;			//num_archivo
	private Integer cntTamArc; 	//cnt_tam_arc
	private Integer numFolios;	//num_folios
	private String codTipAlm;	//cod_tip_alm
	private String codIdEcm; 			//cod_id_ecm
	private Integer numCorDoc;				//num_cordoc
	private String codOrigDoc;			//cod_origdoc
	private String codUsuRegis;		//cod_usuregis
	private Date fecRegis;		//fec_regis
	private String codUsuModif;		//cod_usumodif
	private Date fecModif; 		//fec_modif

	public Integer getNumArcAdj() {
		return numArcAdj;
	}
	public void setNumArcAdj(Integer numArcAdj) {
		this.numArcAdj = numArcAdj;
	}
	public String getNumReq() {
		return numReq;
	}
	public void setNumReq(String numReq) {
		this.numReq = numReq;
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
	public String getNomArcAdj() {
		return nomArcAdj;
	}
	public void setNomArcAdj(String nomArcAdj) {
		this.nomArcAdj = nomArcAdj;
	}
	public String getNumArc() {
		return numArc;
	}
	public void setNumArc(String numArc) {
		this.numArc = numArc;
	}
	public Integer getCntTamArc() {
		return cntTamArc;
	}
	public void setCntTamArc(Integer cntTamArc) {
		this.cntTamArc = cntTamArc;
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
	public String getCodIdEcm() {
		return codIdEcm;
	}
	public void setCodIdEcm(String codIdEcm) {
		this.codIdEcm = codIdEcm;
	}
	public Integer getNumCorDoc() {
		return numCorDoc;
	}
	public void setNumCorDoc(Integer numCorDoc) {
		this.numCorDoc = numCorDoc;
	}
	public String getCodOrigDoc() {
		return codOrigDoc;
	}
	public void setCodOrigDoc(String codOrigDoc) {
		this.codOrigDoc = codOrigDoc;
	}
	public String getCodUsuRegis() {
		return codUsuRegis;
	}
	public void setCodUsuRegis(String codUsuRegis) {
		this.codUsuRegis = codUsuRegis;
	}
	public Date getFecRegis() {
		return fecRegis;
	}
	public void setFecRegis(Date fecRegis) {
		this.fecRegis = fecRegis;
	}
	public String getCodUsuModif() {
		return codUsuModif;
	}
	public void setCodUsuModif(String codUsuModif) {
		this.codUsuModif = codUsuModif;
	}
	public Date getFecModif() {
		return fecModif;
	}
	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}
	
	
	
}
	