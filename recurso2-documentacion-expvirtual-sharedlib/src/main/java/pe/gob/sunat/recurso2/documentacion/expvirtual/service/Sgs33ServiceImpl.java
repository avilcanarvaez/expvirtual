package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.Sgs33DAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
/**
 * @author nchavez
 * @date 25/05/2016
 * @time 4:06pm
 */
//Inicio [nchavez 26/05/2015]
public class Sgs33ServiceImpl implements Sgs33Service {
	private static final Log log = LogFactory.getLog(RequerimientoServiceImpl.class);
	
	private GeneralService generalService;
	private Sgs33DAO sgs33DAO;
	
	
	@Override
	/**
	 * @author nchavez
	 * @return boolean
	 * Invoca un SPL para validar si una fecha cae un día hábil
	 */
	public boolean validarFechaDiaHabil(Map<String, Object> parametros) throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - Sgs33ServiceImpl.validarFechaDiaHabil");
		Integer result=0;
		//eaguilar: agregador ori datos
		Map<String, Object> mapaDataSource = new HashMap<String,Object>();
		//DataSource origenDatos = null;//se comenta pues ya no se pasa el objeto Datasource a la consulta 
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		try {
			if(parametros.get("coddependencia") != null){
				mapaDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_NO_CENTRAL);
				mapaDataSource.put("preDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT);
				mapaDataSource.put("sufDatos", parametros.get("coddependencia").toString().trim());
			}else{
				mapaDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);				
				mapaDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RSIRAT);
			}
			
			//origenDatos = generalService.obtenerOrigenDatos(mapaDataSource); //se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapaPool = generalService.obtenerOrigenDatosMap(mapaDataSource); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml	
			//parametros.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			result=sgs33DAO.validarFechaDiaHabil(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - Sgs33ServiceImpl.validarFechaDiaHabil");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - Sgs33ServiceImpl.validarFechaDiaHabil");
			
			NDC.pop();
			NDC.remove();
		}		
		
		return result>0?true:false;
	}

	
	public Sgs33DAO getSgs33dao() {
		return sgs33DAO;
	}
	public void setSgs33dao(Sgs33DAO sgs33DAO) {
		this.sgs33DAO = sgs33DAO;
	}
	
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

}
//Fin [nchavez 26/05/2015]
