package pe.gob.sunat.recurso2.documentacion.expvirtual.model;

import java.util.Date;

public abstract class T10461SolDes {
	
	private String	numSolDescarga; //  NUM_SOL_DES
	private String numExpdVirtual;  // NUM_EXPEDV
	private String codUsuarioResp;	//  COD_USU_RESP 
	private String codEstadoSol;    //  COD_EST_SOL
	private String descripRuta;     //  DES_RUTA
	private Date fecGeneracionIni;  // FEC_GEN_INI  
	private Date fecGeneracionFin;  // FEC_GEN_FIN
	private Integer cantidadFolio;	//  CNT_FOLIO
	private Integer numTamanioArchiv; //  NUM_TAM_ARCH   
	private Date fecDescarga;         //  FEC_DESCARGA  
	private String codUsuarioReg;     //COD_USUREG 
	private Date fechaRegistro;       //FEC_REG
	private String codUsuarioModif;   //COD_USUMODIF 
	private Date fechaModif;          //  FEC_MODIF
	public String getNumSolDescarga() {
		return numSolDescarga;
	}
	public void setNumSolDescarga(String numSolDescarga) {
		this.numSolDescarga = numSolDescarga;
	}
	public String getNumExpdVirtual() {
		return numExpdVirtual;
	}
	public void setNumExpdVirtual(String numExpdVirtual) {
		this.numExpdVirtual = numExpdVirtual;
	}
	public String getCodUsuarioResp() {
		return codUsuarioResp;
	}
	public void setCodUsuarioResp(String codUsuarioResp) {
		this.codUsuarioResp = codUsuarioResp;
	}
	public String getCodEstadoSol() {
		return codEstadoSol;
	}
	public void setCodEstadoSol(String codEstadoSol) {
		this.codEstadoSol = codEstadoSol;
	}
	public String getDescripRuta() {
		return descripRuta;
	}
	public void setDescripRuta(String descripRuta) {
		this.descripRuta = descripRuta;
	}
	public Date getFecGeneracionIni() {
		return fecGeneracionIni;
	}
	public void setFecGeneracionIni(Date fecGeneracionIni) {
		this.fecGeneracionIni = fecGeneracionIni;
	}
	public Date getFecGeneracionFin() {
		return fecGeneracionFin;
	}
	public void setFecGeneracionFin(Date fecGeneracionFin) {
		this.fecGeneracionFin = fecGeneracionFin;
	}
	public Integer getCantidadFolio() {
		return cantidadFolio;
	}
	public void setCantidadFolio(Integer cantidadFolio) {
		this.cantidadFolio = cantidadFolio;
	}
	public Integer getNumTamanioArchiv() {
		return numTamanioArchiv;
	}
	public void setNumTamanioArchiv(Integer numTamanioArchiv) {
		this.numTamanioArchiv = numTamanioArchiv;
	}
	public Date getFecDescarga() {
		return fecDescarga;
	}
	public void setFecDescarga(Date fecDescarga) {
		this.fecDescarga = fecDescarga;
	}
	public String getCodUsuarioReg() {
		return codUsuarioReg;
	}
	public void setCodUsuarioReg(String codUsuarioReg) {
		this.codUsuarioReg = codUsuarioReg;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getCodUsuarioModif() {
		return codUsuarioModif;
	}
	public void setCodUsuarioModif(String codUsuarioModif) {
		this.codUsuarioModif = codUsuarioModif;
	}
	public Date getFechaModif() {
		return fechaModif;
	}
	public void setFechaModif(Date fechaModif) {
		this.fechaModif = fechaModif;
	}
	
	
	
}