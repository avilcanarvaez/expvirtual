package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

//Inicio CVG 16/01/2020 [PAS20191U210500291] 
public class T10460VincExpVirtBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String numExpepri;
	private String numExpevin;
	private String codEst;
	private Date fecVinc;
	private String usuVinc;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date  fecMod;
	
	
	public String getNumExpepri() {
		return numExpepri;
	}
	public void setNumExpepri(String numExpepri) {
		this.numExpepri = numExpepri;
	}
	public String getNumExpevin() {
		return numExpevin;
	}
	public void setNumExpevin(String numExpevin) {
		this.numExpevin = numExpevin;
	}
	public String getCodEst() {
		return codEst;
	}
	public void setCodEst(String codEst) {
		this.codEst = codEst;
	}
	public Date getFecVinc() {
		return fecVinc;
	}
	public void setFecVinc(Date fecVinc) {
		this.fecVinc = fecVinc;
	}
	public String getUsuVinc() {
		return usuVinc;
	}
	public void setUsuVinc(String usuVinc) {
		this.usuVinc = usuVinc;
	}
	public String getUsuReg() {
		return usuReg;
	}
	public void setUsuReg(String usuReg) {
		this.usuReg = usuReg;
	}
	public Date getFecReg() {
		return fecReg;
	}
	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}
	public String getUsuMod() {
		return usuMod;
	}
	public void setUsuMod(String usuMod) {
		this.usuMod = usuMod;
	}
	public Date getFecMod() {
		return fecMod;
	}
	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}


//[PAS20191U210500291] - CVG : FIN