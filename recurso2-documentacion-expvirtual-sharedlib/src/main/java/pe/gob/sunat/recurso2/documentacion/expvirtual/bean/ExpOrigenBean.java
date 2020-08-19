package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

public class ExpOrigenBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codRpta;
	private String desError;
	private String codPro;
	private String codTipExp;
	private String numExpOri;
	private String desExp;
	private String codRespExp;
	private String desRespExp;
	private String codTipDocApe;
	private String numDocApe;
	private Date fecEmiDocApe;
	private Date fecNotDocApe;
	private String plbsClave;
	private String strFecEmiDocApe;
	
	public String getCodRpta() {
		return codRpta;
	}
	public void setCodRpta(String codRpta) {
		this.codRpta = codRpta;
	}
	public String getDesError() {
		return desError;
	}
	public void setDesError(String desError) {
		this.desError = desError;
	}
	public String getCodPro() {
		return codPro;
	}
	public void setCodPro(String codPro) {
		this.codPro = codPro;
	}
	public String getCodTipExp() {
		return codTipExp;
	}
	public void setCodTipExp(String codTipExp) {
		this.codTipExp = codTipExp;
	}
	public String getNumExpOri() {
		return numExpOri;
	}
	public void setNumExpOri(String numExpOri) {
		this.numExpOri = numExpOri;
	}
	public String getDesExp() {
		return desExp;
	}
	public void setDesExp(String desExp) {
		this.desExp = desExp;
	}
	public String getCodRespExp() {
		return codRespExp;
	}
	public void setCodRespExp(String codRespExp) {
		this.codRespExp = codRespExp;
	}
	public String getDesRespExp() {
		return desRespExp;
	}
	public void setDesRespExp(String desRespExp) {
		this.desRespExp = desRespExp;
	}
	public String getCodTipDocApe() {
		return codTipDocApe;
	}
	public void setCodTipDocApe(String codTipDocApe) {
		this.codTipDocApe = codTipDocApe;
	}
	public String getNumDocApe() {
		return numDocApe;
	}
	public void setNumDocApe(String numDocApe) {
		this.numDocApe = numDocApe;
	}
	public Date getFecEmiDocApe() {
		return fecEmiDocApe;
	}
	public void setFecEmiDocApe(Date fecEmiDocApe) {
		this.fecEmiDocApe = fecEmiDocApe;
	}
	public Date getFecNotDocApe() {
		return fecNotDocApe;
	}
	public void setFecNotDocApe(Date fecNotDocApe) {
		this.fecNotDocApe = fecNotDocApe;
	}
	public String getPlbsClave() {
		return plbsClave;
	}
	public void setPlbsClave(String plbsClave) {
		this.plbsClave = plbsClave;
	}
	public String getStrFecEmiDocApe() {
		return strFecEmiDocApe;
	}
	public void setStrFecEmiDocApe(String strFecEmiDocApe) {
		this.strFecEmiDocApe = strFecEmiDocApe;
	}
	
}
