package pe.gob.sunat.recurso2.documentacion.expvirtual.model;


import java.util.Date;

public abstract class ResCoa {
	
	
	private String num_exp; //num_exp
	private String cod_tip_doc; //cod_tip_doc
	private String num_rc; //num_rc
	private Date fec_emi; //fec_emi
	
	
	public String getNum_exp() {
		return num_exp;
	}
	public void setNum_exp(String num_exp) {
		this.num_exp = num_exp;
	}
	public String getCod_tip_doc() {
		return cod_tip_doc;
	}
	public void setCod_tip_doc(String cod_tip_doc) {
		this.cod_tip_doc = cod_tip_doc;
	}
	public String getNum_rc() {
		return num_rc;
	}
	public void setNum_rc(String num_rc) {
		this.num_rc = num_rc;
	}
	public Date getFec_emi() {
		return fec_emi;
	}
	public void setFec_emi(Date fec_emi) {
		this.fec_emi = fec_emi;
	}


}
