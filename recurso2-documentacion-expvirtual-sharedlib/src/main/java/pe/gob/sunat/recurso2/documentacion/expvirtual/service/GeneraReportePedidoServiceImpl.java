package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6616PedRepDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6618RepGenExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6619RepGenPedDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class GeneraReportePedidoServiceImpl implements GeneraReportePedidoService {

	private static final Log log = LogFactory.getLog(GeneraReportePedidoServiceImpl.class);
	private GeneralService generalService;
	private SequenceDAO sequenceDAO;
	private T6616PedRepDAO t6616PedRepDAO;
	private T6619RepGenPedDAO t6619RepGenPedDAO;
	private T6618RepGenExpDAO t6618RepGenExpDAO;
	@Override
	public String registrarPedidoReporte (Map<String, Object> parametros) throws Exception {
		// TODO Auto-generated method stub
		// Fecha actual
		Calendar fechaActual = Calendar.getInstance();
		
		// Fecha fin
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);
		fechaActual.getTime();
		String numPedidoRetorno = null;
		String numPedido = null;
		
		try {
			Map<String, Object> paramsRegPedido = new HashMap<String, Object>();
			Map<String, Object> mapa = new HashMap<String,Object>();
			/* Captura y seteo del Pool - Inicio */
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			
			//Sequence t6616
			Long numCorPedido = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_PEDREP);
			
			numPedidoRetorno = StringUtils.leftPad(String.valueOf(numCorPedido), 7, "0");
			
			numPedido = parametros.get("codProceso").toString().trim()+parametros.get("codTipoExp").toString().trim()+numPedidoRetorno;
			
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);
			Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

			mapaPool = generalService.obtenerOrigenDatosMap(mapa);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());

			//paramsRegPedido.put("origenDatos", origenDatos);
			
			paramsRegPedido.put("numPedido", numPedido);
			paramsRegPedido.put("codPlantilla", parametros.get("codPlantilla").toString().trim());
			paramsRegPedido.put("codProceso", parametros.get("codProceso").toString().trim());
			paramsRegPedido.put("codTipoExp", parametros.get("codTipoExp").toString().trim());
			paramsRegPedido.put("tipoGenera", parametros.get("tipoGenera").toString().trim());
			paramsRegPedido.put("estado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
			paramsRegPedido.put("codUsuarioRegistro", parametros.get("respRegistro").toString().trim());
			paramsRegPedido.put("fecRegistro", fechaActual.getTime());
			paramsRegPedido.put("codUsuarioModif", ValidaConstantes.SEPARADOR_GUION);
			paramsRegPedido.put("fecModif", fechaVacia.getTime());
			t6616PedRepDAO.insertar(paramsRegPedido);
			
			String tipoGenera = parametros.get("tipoGenera").toString().trim();
			Map<String, Object> paramsPedidoPendiente = null;
			Map<String, Object> paramsExpePedidoPendiente = null;
			
			//Sequence t6619
			Long numCorRepPedido = null;
			
			//Sequence t6618
			Long numCorRepPedExp = null;
					
			if(tipoGenera.equals(ValidaConstantes.IND_TIPO_GEN_PEDIDO_RUC)){
				
				paramsPedidoPendiente = new HashMap<String, Object>();
				numCorRepPedido = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPPED);
				//paramsPedidoPendiente.put("origenDatos", origenDatos);
				paramsPedidoPendiente.put("numRepPedido", numCorRepPedido);
				paramsPedidoPendiente.put("numPedido", numPedido);
				paramsPedidoPendiente.put("numRuc", parametros.get("numRuc").toString().trim());
				paramsPedidoPendiente.put("codUsuarioResp", parametros.get("respRegistro").toString().trim());
				paramsPedidoPendiente.put("fecGenera", fechaVacia.getTime());
				paramsPedidoPendiente.put("estado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
				paramsPedidoPendiente.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
				paramsPedidoPendiente.put("fecModif", fechaVacia.getTime());
				t6619RepGenPedDAO.insertar(paramsPedidoPendiente);
				
				paramsExpePedidoPendiente = new HashMap<String, Object>();
				numCorRepPedExp = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPEXP);
				//paramsExpePedidoPendiente.put("origenDatos", origenDatos);
				paramsExpePedidoPendiente.put("numRepExp", numCorRepPedExp);
				paramsExpePedidoPendiente.put("numRepPedido", numCorRepPedido);
				paramsExpePedidoPendiente.put("numPedido", numPedido);
				paramsExpePedidoPendiente.put("codIdecm", ValidaConstantes.SEPARADOR_GUION);
				paramsExpePedidoPendiente.put("fecGenera", fechaVacia.getTime());
				paramsExpePedidoPendiente.put("estado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
				paramsExpePedidoPendiente.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
				paramsExpePedidoPendiente.put("fecModif", fechaVacia.getTime());
				paramsExpePedidoPendiente.put("codError", ValidaConstantes.SEPARADOR_GUION);
				t6618RepGenExpDAO.insertar(paramsExpePedidoPendiente);				
				
			}else if(tipoGenera.equals(ValidaConstantes.IND_TIPO_GEN_PEDIDO_MASIVO)){
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> lista = (List<Map<String, Object>>) parametros.get("listaRecorrido");
				paramsPedidoPendiente = new HashMap<String, Object>();
	
				for (Map<String, Object> map : lista) {
					
					numCorRepPedido = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPPED);
					//paramsPedidoPendiente.put("origenDatos", origenDatos);
					paramsPedidoPendiente.put("numRepPedido", numCorRepPedido);
					paramsPedidoPendiente.put("numPedido", numPedido);
					paramsPedidoPendiente.put("numRuc", map.get("numRuc").toString().trim());
					paramsPedidoPendiente.put("codUsuarioResp", map.get("respRucEnvio").toString().trim());
					paramsPedidoPendiente.put("fecGenera", fechaVacia.getTime());
					paramsPedidoPendiente.put("estado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
					paramsPedidoPendiente.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
					paramsPedidoPendiente.put("fecModif", fechaVacia.getTime());
					t6619RepGenPedDAO.insertar(paramsPedidoPendiente);
					
					paramsExpePedidoPendiente = new HashMap<String, Object>();
					numCorRepPedExp = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPEXP);
					//paramsExpePedidoPendiente.put("origenDatos", origenDatos);
					paramsExpePedidoPendiente.put("numRepExp", numCorRepPedExp);
					paramsExpePedidoPendiente.put("numRepPedido", numCorRepPedido);
					paramsExpePedidoPendiente.put("numPedido", numPedido);
					paramsExpePedidoPendiente.put("codIdecm", ValidaConstantes.SEPARADOR_GUION);
					paramsExpePedidoPendiente.put("fecGenera", fechaVacia.getTime());
					paramsExpePedidoPendiente.put("estado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
					paramsExpePedidoPendiente.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
					paramsExpePedidoPendiente.put("fecModif", fechaVacia.getTime());
					paramsExpePedidoPendiente.put("codError", ValidaConstantes.SEPARADOR_GUION);
					t6618RepGenExpDAO.insertar(paramsExpePedidoPendiente);
					
				}
			}
		
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - GeneraReportePedidoServiceImpl.registrarPedidoReporte");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - GeneraReportePedidoServiceImpl.registrarPedidoReporte");
			
			NDC.pop();
			NDC.remove();
		}
		
		return numPedido;
	}
	/*Sets*/
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}
	public void setT6616PedRepDAO(T6616PedRepDAO t6616PedRepDAO) {
		this.t6616PedRepDAO = t6616PedRepDAO;
	}
	public void setT6619RepGenPedDAO(T6619RepGenPedDAO t6619RepGenPedDAO) {
		this.t6619RepGenPedDAO = t6619RepGenPedDAO;
	}
	public void setT6618RepGenExpDAO(T6618RepGenExpDAO t6618RepGenExpDAO) {
		this.t6618RepGenExpDAO = t6618RepGenExpDAO;
	}
	
}
