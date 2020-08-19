package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6620Requerim;

public class DetReqOrdFisBean  {
	
	private static final long serialVersionUID = 1L;
	
	//staype 26/12/2019 [PAS20191U210500291]
	private String numReq;
	private Integer item;
	private Integer subitem;
	private String textitem;
	private String estItem;
	private String descElim;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date  fecMod;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getFecMod() {
		return fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Integer getSubitem() {
		return subitem;
	}

	public void setSubitem(Integer subitem) {
		this.subitem = subitem;
	}


	


	public String getEstItem() {
		return estItem;
	}

	public void setEstItem(String estItem) {
		this.estItem = estItem;
	}

	public String getDescElim() {
		return descElim;
	}

	public void setDescElim(String descElim) {
		this.descElim = descElim;
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

	
	public String getNumReq() {
		return numReq;
	}

	public void setNumReq(String numReq) {
		this.numReq = numReq;
	}

	

	public String getTextitem() {
		return textitem;
	}

	public void setTextitem(String textitem) {
		this.textitem = textitem;
	}
	
	
	
	
	
	
}