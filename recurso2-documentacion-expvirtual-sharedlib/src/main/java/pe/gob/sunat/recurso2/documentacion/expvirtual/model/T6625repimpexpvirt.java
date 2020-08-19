package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T6625repimpexpvirt {

	// t6625repimpexpvirt (
	private String numRepImp; // num_repimp CHAR(17) not null,
	private String numRuc; // num_ruc CHAR(11) not null,
	private String numExpedo; // num_expedo CHAR(17) not null,
	private String codTipExp; // cod_tipexp CHAR(6) not null,
	private String codTipDoc; // cod_tipdoc CHAR(6) not null,
	private String numCorDoc; // num_cordoc INTEGER,
	private String numDoc; // num_doc CHAR(50) not null,
	private String codOriDes; // cod_orides CHAR(2) not null,
	private String codUsu; // cod_usu CHAR(11) not null,
	private Date fecDoc; // fec_doc DATETIME YEAR TO SECOND not null,
	private String codUsuRegis; // cod_usuregis CHAR(32) not null,
	private Date fecRegis; // fec_regis DATETIME YEAR TO SECOND not null,
	private String codUsuMod; // cod_usumod CHAR(32) not null,
	private Date fecMod; // fec_mod DATETIME YEAR TO SECOND not null

	public String getNumRepImp() {
		return numRepImp;
	}

	public void setNumRepImp(String numRepImp) {
		this.numRepImp = numRepImp;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getNumExpedo() {
		return numExpedo;
	}

	public void setNumExpedo(String numExpedo) {
		this.numExpedo = numExpedo;
	}

	public String getCodTipExp() {
		return codTipExp;
	}

	public void setCodTipExp(String codTipExp) {
		this.codTipExp = codTipExp;
	}

	public String getCodTipDoc() {
		return codTipDoc;
	}

	public void setCodTipDoc(String codTipDoc) {
		this.codTipDoc = codTipDoc;
	}

	public String getNumCorDoc() {
		return numCorDoc;
	}

	public void setNumCorDoc(String numCorDoc) {
		this.numCorDoc = numCorDoc;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getCodOriDes() {
		return codOriDes;
	}

	public void setCodOriDes(String codOriDes) {
		this.codOriDes = codOriDes;
	}

	public String getCodUsu() {
		return codUsu;
	}

	public void setCodUsu(String codUsu) {
		this.codUsu = codUsu;
	}

	public Date getFecDoc() {
		return fecDoc;
	}

	public void setFecDoc(Date fecDoc) {
		this.fecDoc = fecDoc;
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

	public String getCodUsuMod() {
		return codUsuMod;
	}

	public void setCodUsuMod(String codUsuMod) {
		this.codUsuMod = codUsuMod;
	}

	public Date getFecMod() {
		return fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

}
