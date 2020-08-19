package pe.gob.sunat.recurso2.documentacion.expvirtual.model;
import java.util.Date;
public abstract class T6615ObsExp {
	
	private int numObservacion; 			//num_corobs;
	private String numExpedienteVirtual ;	//num_expedv;	
	private String desObservacion;			//des_obs;
	private String codUsuarioGeneraObs;		// cod_usugenobs;
	private Date fechaGeneracionObs;		// fec_genobs; 		
	private String codUsuRegis; 			//cod_usuregis; 	
	private Date fechaRegistro; 			//fec_regis

	public int getNumObservacion() {
		return numObservacion;
	}
	public void setNumObservacion(int numObservacion) {
		this.numObservacion = numObservacion;
	}
	public String getNumExpedienteVirtual() {
		return numExpedienteVirtual;
	}
	public void setNumExpedienteVirtual(String numExpedienteVirtual) {
		this.numExpedienteVirtual = numExpedienteVirtual;
	}
	public String getDesObservacion() {
		return desObservacion;
	}
	public void setDesObservacion(String desObservacion) {
		this.desObservacion = desObservacion;
	}
	public String getCodUsuarioGeneraObs() {
		return codUsuarioGeneraObs;
	}
	public void setCodUsuarioGeneraObs(String codUsuarioGeneraObs) {
		this.codUsuarioGeneraObs = codUsuarioGeneraObs;
	}
	public Date getFechaGeneracionObs() {
		return fechaGeneracionObs;
	}
	public void setFechaGeneracionObs(Date fechaGeneracionObs) {
		this.fechaGeneracionObs = fechaGeneracionObs;
	}
	public String getCodUsuRegis() {
		return codUsuRegis;
	}
	public void setCodUsuRegis(String codUsuRegis) {
		this.codUsuRegis = codUsuRegis;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}


	
}
