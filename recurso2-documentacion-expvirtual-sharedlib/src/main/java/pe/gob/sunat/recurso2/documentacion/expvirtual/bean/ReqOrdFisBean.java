package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6620Requerim;

public class ReqOrdFisBean  {
	
	private static final long serialVersionUID = 1L;
	
	//staype 26/12/2019 [PAS20191U210500291] tabla req_ord_fis tabla dependencia y mov_req_ordfis
	private String numReq;
	private Date fecVencReq;
	//fecha de requerimiento fec_mov_req-mov_req_ordfis
	private Date fecReq;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNumReq() {
		return numReq;
	}
	public void setNumReq(String numReq) {
		this.numReq = numReq;
	}
	public Date getFecVencReq() {
		return fecVencReq;
	}
	public void setFecVencReq(Date fecVencReq) {
		this.fecVencReq = fecVencReq;
	}
	public Date getFecReq() {
		return fecReq;
	}
	public void setFecReq(Date fecReq) {
		this.fecReq = fecReq;
	}
	
	
}