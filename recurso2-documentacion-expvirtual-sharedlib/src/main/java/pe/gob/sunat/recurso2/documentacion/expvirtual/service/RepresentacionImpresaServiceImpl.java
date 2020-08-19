package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6625repimpexpvirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6626seguirepimpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class RepresentacionImpresaServiceImpl implements RepresentacionImpresaService {
	
	private static final Log log = LogFactory.getLog(RepresentacionImpresaServiceImpl.class);

	private T6625repimpexpvirtDAO t6625repimpexpvirtDAO;
	private T6626seguirepimpDAO t6626seguirepimpDAO;
	private GeneralService generalService;
	private SequenceDAO sequenceDAO;
	
	@Override
	public void grabarRepresentacionImpresa(T6625repimpexpvirtBean t6625repimpexpvirtBean) throws Exception {
		
		Map<String, Object> mapa = null;
		Map<String, Object> mapaPool = null;
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - RepresentacionImpresaServiceImpl.grabarRepresentacionImpresa");
			mapa = new HashMap<String,Object>();
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			
			mapaPool = generalService.obtenerOrigenDatosMap(mapa);
		    DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
			
			t6625repimpexpvirtDAO.grabarRepresentacionImpresa(t6625repimpexpvirtBean);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - RepresentacionImpresaServiceImpl.grabarRepresentacionImpresa");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - RepresentacionImpresaServiceImpl.grabarRepresentacionImpresa");
			NDC.pop();
			NDC.remove();
		}
		
	}

	//17 AGO jtejada
	
	public T6625repimpexpvirtBean buscarExpedienteRepreImpr(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - RepresentacionImpresaServiceImpl.buscarExpedienteRepreImpr");
		T6625repimpexpvirtBean t6625repimpexpvirtBean = null;

		try {
			t6625repimpexpvirtBean = t6625repimpexpvirtDAO.obtenerCodigoRepresentacionImpresa(parametros);
			if (t6625repimpexpvirtBean != null && Boolean.valueOf(Utils.toStr(parametros.get("grabarSeguim")))) {
				insertarSeguimiento(parametros);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled())
				log.debug("Error - RepresentacionImpresaServiceImpl.buscarExpedienteRepreImpr");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled())
				log.debug("Final - RepresentacionImpresaServiceImpl.buscarExpediente");
			NDC.pop();
			NDC.remove();
		}

		return t6625repimpexpvirtBean;
	}

	private void insertarSeguimiento(Map<String, Object> parametros) throws Exception {
		Long numSeguiRep;
		Map<String, Object> parametrosSeguimiento;
		parametrosSeguimiento = new HashMap<String, Object>();
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaVacia = Calendar.getInstance();
		fechaVacia.set(1, 0, 1, 0, 0, 0);

		parametrosSeguimiento.putAll(parametros);

		numSeguiRep = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_SEGREPI);

		parametrosSeguimiento.put("numSeguiRep", numSeguiRep);
		parametrosSeguimiento.put("fecConsulta", fechaActual.getTime());
		parametrosSeguimiento.put("codUsuReg", parametros.get("codUsuReg"));
		parametrosSeguimiento.put("fecReg", fechaActual.getTime());
		parametrosSeguimiento.put("codUsuMod", ValidaConstantes.SEPARADOR_GUION);
		parametrosSeguimiento.put("fecMod", fechaVacia.getTime());
		parametrosSeguimiento.put("codTipConsul", parametros.get("codTipConsul"));
		DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
		t6626seguirepimpDAO.insertar(parametrosSeguimiento);
	}

	// FIN 17 AGO jtejada
	
	public T6625repimpexpvirtDAO getT6625repimpexpvirtDAO() {
		return t6625repimpexpvirtDAO;
	}
	
	public void setT6625repimpexpvirtDAO(T6625repimpexpvirtDAO t6625repimpexpvirtDAO) {
		this.t6625repimpexpvirtDAO = t6625repimpexpvirtDAO;
	}
	
	public T6626seguirepimpDAO getT6626seguirepimpDAO() {
		return t6626seguirepimpDAO;
	}
	
	public void setT6626seguirepimpDAO(T6626seguirepimpDAO t6626seguirepimpDAO) {
		this.t6626seguirepimpDAO = t6626seguirepimpDAO;
	}
	
	public GeneralService getGeneralService() {
		return generalService;
	}
	
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public SequenceDAO getSequenceDAO() {
		return sequenceDAO;
	}
	
	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}
}