package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class ExpCoa {

	private String num_exp_coa;
	private String num_ruc_ori;
	private String num_ruc;
	private String cod_tip_doc;
	private String ind_est_ecc; 
	private String ind_eta_ecc; 
	private String ind_acu;
	private Date fec_emi;	
	
	public String getNum_exp_coa() {
		return num_exp_coa;
	}
	public void setNum_exp_coa(String num_exp_coa) {
		this.num_exp_coa = num_exp_coa;
	}
	public String getNum_ruc_ori() {
		return num_ruc_ori;
	}
	public void setNum_ruc_ori(String num_ruc_ori) {
		this.num_ruc_ori = num_ruc_ori;
	}
	public String getNum_ruc() {
		return num_ruc;
	}
	public void setNum_ruc(String num_ruc) {
		this.num_ruc = num_ruc;
	}
	public String getCod_tip_doc() {
		return cod_tip_doc;
	}
	public void setCod_tip_doc(String cod_tip_doc) {
		this.cod_tip_doc = cod_tip_doc;
	}
	public String getInd_est_ecc() {
		return ind_est_ecc;
	}
	public void setInd_est_ecc(String ind_est_ecc) {
		this.ind_est_ecc = ind_est_ecc;
	}
	public String getInd_eta_ecc() {
		return ind_eta_ecc;
	}
	public void setInd_eta_ecc(String ind_eta_ecc) {
		this.ind_eta_ecc = ind_eta_ecc;
	}
	public String getInd_acu() {
		return ind_acu;
	}
	public void setInd_acu(String ind_acu) {
		this.ind_acu = ind_acu;
	}
	public Date getFec_emi() {
		return fec_emi;
	}
	public void setFec_emi(Date fec_emi) {
		this.fec_emi = fec_emi;
	}
	
}
