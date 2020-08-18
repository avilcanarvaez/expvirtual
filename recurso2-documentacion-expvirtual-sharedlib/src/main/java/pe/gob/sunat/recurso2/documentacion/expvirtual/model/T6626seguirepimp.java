package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

/**
 * @author: nchavez
 * @date : 18/08/2016
 * @time : 13:08:55
 */
public abstract class T6626seguirepimp {

	private String numSeguiRep;	// num_seguirep INTEGER not null,
	private String numRuc;		// num_ruc CHAR(11),
	private String numRepImp;	// num_repimp CHAR(17),
	private String codTipConsul;// cod_tipconsul CHAR(2),
	private Date fecConsulta;	// fec_consulta DATETIME YEAR TO SECOND,
	private String codUsuReg;	// cod_usureg CHAR(32) not null,
	private Date fecReg;		// fec_reg DATETIME YEAR TO SECOND not null,
	private String codUsuMod;	// cod_usumod CHAR(32) not null,
	private Date fecMod;		// fec_mod DATETIME YEAR TO SECOND not null

	public String getNumSeguiRep() {
		return numSeguiRep;
	}

	public void setNumSeguiRep(String numSeguiRep) {
		this.numSeguiRep = numSeguiRep;
	}

	public String getNumRuc() {
		return numRuc;
	}

	public void setNumRuc(String numRuc) {
		this.numRuc = numRuc;
	}

	public String getNumRepImp() {
		return numRepImp;
	}

	public void setNumRepImp(String numRepImp) {
		this.numRepImp = numRepImp;
	}

	public String getCodTipConsul() {
		return codTipConsul;
	}

	public void setCodTipConsul(String codTipConsul) {
		this.codTipConsul = codTipConsul;
	}

	public Date getFecConsulta() {
		return fecConsulta;
	}

	public void setFecConsulta(Date fecConsulta) {
		this.fecConsulta = fecConsulta;
	}

	public String getCodUsuReg() {
		return codUsuReg;
	}

	public void setCodUsuReg(String codUsuReg) {
		this.codUsuReg = codUsuReg;
	}

	public Date getFecReg() {
		return fecReg;
	}

	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
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
