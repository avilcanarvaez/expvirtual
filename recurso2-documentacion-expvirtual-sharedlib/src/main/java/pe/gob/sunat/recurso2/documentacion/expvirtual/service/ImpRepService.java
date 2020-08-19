
	package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

	import java.util.List;
import java.util.Map;

	import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6616PedRepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6618RepGenExpBean;


	public interface ImpRepService {

		public List<T6611CabPlantiBean> listarPlantillas(Map<String, Object> mapParametrosBusqueda) throws Exception;
		public List<T6616PedRepBean> listarExpedientesPorPedido(Map<String, Object> mapParametrosBusqueda) throws Exception;
		public T6618RepGenExpBean obtenerExpedientePorPedido(Map<String, Object> parametrosBusqueda) throws Exception;
		public List<T6616PedRepBean> listarPedidos(Map<String, Object> mapParametrosBusqueda) throws Exception;
		public List<T6618RepGenExpBean> obtenerListaExpedientesTrabajo(Map<String, Object> parametrosBusqueda)throws Exception;
			
	}
