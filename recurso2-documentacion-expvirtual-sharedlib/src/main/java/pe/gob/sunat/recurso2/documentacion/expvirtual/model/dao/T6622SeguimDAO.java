package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;

public interface T6622SeguimDAO {

	public void insertar(Map<String, Object> parametros);
	public List<T6622SeguimBean> listarSeguimientos(Map<String, Object> mapParametrosBusqueda);
	
	// [ATORRESCH 2017/02/21] CONTABILIZA SI UN EXPEDIENTE FUE VISUALIZADO EN LA TABLA DE SEGUIMIENTO
	public Integer contarExpedienteSeguimiento(Map<String, Object> parametros);
	
	// [ATORRESCH 2017/05/18] ACTUALIZA EL SEGUIMIENTO DE CAMBIO DE ESTADO Y ETAPA DEL EXPEDIENTE A 99
	public void eliminarSeguimientoCambioEstadoEtapa(Map<String, Object> parametros);
}
