package pe.gob.sunat.recurso2.documentacion.expvirtual.model;
import java.util.Date;
public abstract class  T6616PedRep {
	
	private String numeroPedido; 				//num_pedido	CHAR	
	private String numeroPlantilla;			//num_planti	INTEGER
	private String codProceso;					//cod_proc	CHAR
	private String codTipoExpediente;			//cod_tipexp	CHAR
	private String codTipoGeneracionReporte;	//cod_tipgenera	CHAR
	private String codEstadoReporte;			//cod_estado	CHAR
	private String codUsuarioRegistro;			//cod_usuregis	CHAR
	private String codUsuarioModifica;			//cod_usumodif	CHAR
	private Date fecModificacion;				//fec_modif	DATETIME
	private Date fecRegistro;					//fec_regis	DATETIME

	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	public String getNumeroPlantilla() {
		return numeroPlantilla;
	}
	public void setNumeroPlantilla(String numeroPlantilla) {
		this.numeroPlantilla = numeroPlantilla;
	}
	public String getCodProceso() {
		return codProceso;
	}
	public void setCodProceso(String codProceso) {
		this.codProceso = codProceso;
	}
	public String getCodTipoExpediente() {
		return codTipoExpediente;
	}
	public void setCodTipoExpediente(String codTipoExpediente) {
		this.codTipoExpediente = codTipoExpediente;
	}
	public String getCodTipoGeneracionReporte() {
		return codTipoGeneracionReporte;
	}
	public void setCodTipoGeneracionReporte(String codTipoGeneracionReporte) {
		this.codTipoGeneracionReporte = codTipoGeneracionReporte;
	}
	public String getCodEstadoReporte() {
		return codEstadoReporte;
	}
	public void setCodEstadoReporte(String codEstadoReporte) {
		this.codEstadoReporte = codEstadoReporte;
	}
	public String getCodUsuarioRegistro() {
		return codUsuarioRegistro;
	}
	public void setCodUsuarioRegistro(String codUsuarioRegistro) {
		this.codUsuarioRegistro = codUsuarioRegistro;
	}
	
	public String getCodUsuarioModifica() {
		return codUsuarioModifica;
	}
	public void setCodUsuarioModifica(String codUsuarioModifica) {
		this.codUsuarioModifica = codUsuarioModifica;
	}
	public Date getFecModificacion() {
		return fecModificacion;
	}
	public void setFecModificacion(Date fecModificacion) {
		this.fecModificacion = fecModificacion;
	}
	public Date getFecRegistro() {
		return fecRegistro;
	}
	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	
	
}
