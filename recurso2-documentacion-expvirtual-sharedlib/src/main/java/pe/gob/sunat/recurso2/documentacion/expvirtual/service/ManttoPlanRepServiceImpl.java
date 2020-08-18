package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6612DetPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6611CabPlantiDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6612DetPlantiDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class ManttoPlanRepServiceImpl implements ManttoPlanRepService {
	private static final Log log = LogFactory.getLog(ExpedienteVirtualServiceImpl.class);
	private T6611CabPlantiDAO t6611CabPlantiDAO;
	private CatalogoService catalogoService;
	private PersonaService personaService;
	private GeneralService generalService;
	private SequenceDAO sequenceDAO;
	private T6612DetPlantiDAO t6612DetPlantiDAO;
	
	@Override
	public List<T6611CabPlantiBean> listarPlantillas(
			Map<String, Object> mapParametrosBusqueda) throws Exception {
		// TODO Auto-generated method stub
		List<T6611CabPlantiBean> listarPlantillasBean = null;
		Map<String, Object> mapa = null;
		try {
			String codEstadoPlantilla;
			mapa = new HashMap<String,Object>();
			
			mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_PLANTILLA_EXPEDIENTE);
			mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			
			Map<String, Object> mapaEstadoPalntilla = catalogoService.obtenerCatalogo(mapa);
			
			listarPlantillasBean = t6611CabPlantiDAO.listar(mapParametrosBusqueda);
			Integer numOrden = 0;
			for (T6611CabPlantiBean CabPlantiBean : listarPlantillasBean) {
				numOrden++;
				
				mapa = new HashMap<String,Object>();
				mapa.put("codPersona", CabPlantiBean.getCodUsuRegistro().toString().trim());
				
				T02PerdpBean personaBean = personaService.validarPersona(mapa);
				
				personaBean = personaService.completarInformacionPersona(personaBean);
				
				if(personaBean!=null){
					CabPlantiBean.setUsuRegistro(personaBean.getDesNombreCompleto());
				}else{
					CabPlantiBean.setUsuRegistro(ValidaConstantes.CADENA_VACIA);
				}
				
				if(CabPlantiBean.getCodEstadoPlanti()!=null){
					codEstadoPlantilla = CabPlantiBean.getCodEstadoPlanti().toString().trim();
					CabPlantiBean.setEstadoPlantilla(Utils.toStr(mapaEstadoPalntilla.get(codEstadoPlantilla)));
				}else{
					CabPlantiBean.setEstadoPlantilla(ValidaConstantes.CADENA_VACIA);
				}
				CabPlantiBean.setIdSeleccion(false);		
				CabPlantiBean.setNumOrden(numOrden);
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			
			NDC.pop();
			NDC.remove();
		}
		return listarPlantillasBean;
	}
	
	@Override
	public Map<String, Object> darBajaPlantilla(Map<String, Object> parametros)
			throws Exception {
		
		Calendar fechaActual = Calendar.getInstance();
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		try {
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			/* Captura y seteo del Pool - Inicio */
			
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapaPool = generalService.obtenerOrigenDatosMap(mapa); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml		
			
			//parametros.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			
			parametros.put("fechaBaja", fechaActual.getTime());
			
			t6611CabPlantiDAO.actualizar(parametros);
			
		}catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.darBajaPlantilla");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.darBajaPlantilla");
			
			NDC.pop();
			NDC.remove();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<T6611CabPlantiBean> listarPlantillasPorExpe(Map<String, Object> parametros) throws Exception {
		// TODO Auto-generated method stub
		List<T6611CabPlantiBean> listarPlantillasBean = null;
		try {
		
			listarPlantillasBean = t6611CabPlantiDAO.listar(parametros);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			
			NDC.pop();
			NDC.remove();
		}
		return listarPlantillasBean;
	}
	
	@Override
	public String registrarPlantilla(HashMap<String, Object> mapParametros) throws Exception{
		
		HashMap<String, Object> parametros;
		T6611CabPlantiBean t6611CabPlantiBean;
		T6612DetPlantiBean t6612DetPlantiBean;
		String numPlantilla = "";
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		try {
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			/* Captura y seteo del Pool - Inicio */
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			mapaPool = generalService.obtenerOrigenDatosMap(mapa); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml		
			
			parametros = new HashMap<String, Object>();
			//parametros.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			
			/*****************************************/
			/**GENERAMOS LA CABEZERA DE LA PLANTILLA**/
			/*****************************************/
			t6611CabPlantiBean = new T6611CabPlantiBean();
			Long numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_PLANTI);
			String correlativoCompletado = String.format("%07d",Utils.toInteger(numCorrelativo));
			
			numPlantilla = Utils.toStr(mapParametros.get("codProceso"))+Utils.toStr(mapParametros.get("codTipExpediente"))+correlativoCompletado;
			t6611CabPlantiBean.setNumPlantilla(numPlantilla);
			t6611CabPlantiBean.setDesPlantilla(Utils.toStr(mapParametros.get("desPlantilla")));
			t6611CabPlantiBean.setCodProceso(Utils.toStr(mapParametros.get("codProceso")));
			t6611CabPlantiBean.setCodTipoExpediente(Utils.toStr(mapParametros.get("codTipExpediente")));
			t6611CabPlantiBean.setCodEstadoPlanti(ValidaConstantes.IND_ESTADO_PLANTILLA_ACTIVO);
			t6611CabPlantiBean.setCodUsuRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
			t6611CabPlantiBean.setIndRepTrib(Utils.toStr(mapParametros.get("codOpcion")));
			//Inicio [gangles 23/06/2016] Se inserta valores por defecto
			t6611CabPlantiBean.setFechaModifica(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
			t6611CabPlantiBean.setFechaBaja(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
			t6611CabPlantiBean.setCodUsuModifica(ValidaConstantes.SEPARADOR_GUION);
			t6611CabPlantiBean.setCodUsuBaja(ValidaConstantes.SEPARADOR_GUION);
			//Fin [gangles 23/06/2016]
			t6611CabPlantiBean.setFechaRegistro(new Date());
			parametros.put("t6611CabPlantiBean", t6611CabPlantiBean);
			
			t6611CabPlantiDAO.insertar(parametros);
			parametros.remove("t6611CabPlantiBean");
			/*****************************************/
			/**GENERAMOS EL DETALLE DE LA PLANTILLA***/
			/*****************************************/
			//documentos
			List<Map<String, Object>> listaDocumentos = (List<Map<String, Object>>) mapParametros.get("listaDocumentos");
			
			for (Map<String, Object> map : listaDocumentos) {
				
				t6612DetPlantiBean = new T6612DetPlantiBean();
				numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DETPLAN);
				t6612DetPlantiBean.setNumCorDetPlanti(Utils.toStr(numCorrelativo));
				t6612DetPlantiBean.setNumPlantilla(numPlantilla);
				t6612DetPlantiBean.setCodTipoDocumento(Utils.toStr(map.get("CODDOCUMENTO")));
				t6612DetPlantiBean.setCodRepTrbib(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
				t6612DetPlantiBean.setCodFormatoAdi(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
				t6612DetPlantiBean.setFechaRegistro(new Date());
				t6612DetPlantiBean.setUsuarioRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
				t6612DetPlantiBean.setIndicadorSelDoc(ValidaConstantes.IND_SEL_PLANTILLA_DET_SEL);
				t6612DetPlantiBean.setIndicadorSelRubro(ValidaConstantes.SEPARADOR_GUION);
				t6612DetPlantiBean.setIndicadorSelForma(ValidaConstantes.SEPARADOR_GUION);
				//Inicio [gangles 23/06/2016] Se inserta valores por defecto
				t6612DetPlantiBean.setFechaModificacion(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
				t6612DetPlantiBean.setFechaBaja(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
				t6612DetPlantiBean.setUsuarioMod(ValidaConstantes.SEPARADOR_GUION);
				t6612DetPlantiBean.setCodUsuarioBaja(ValidaConstantes.SEPARADOR_GUION);
				//Fin [gangles 23/06/2016]
				parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
				t6612DetPlantiDAO.insertar(parametros);
				parametros.remove("t6612detplantiBean");
			}
			
			//formatos adicionales
			List<Map<String, Object>> listaFormatos = (List<Map<String, Object>>) mapParametros.get("listaFormatos");
			
			for (Map<String, Object> map : listaFormatos) {
				
				t6612DetPlantiBean = new T6612DetPlantiBean();
				numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DETPLAN);
				t6612DetPlantiBean.setNumCorDetPlanti(Utils.toStr(numCorrelativo));
				t6612DetPlantiBean.setNumPlantilla(numPlantilla);
				t6612DetPlantiBean.setCodFormatoAdi(Utils.toStr(map.get("CODDOCUMENTO")));
				t6612DetPlantiBean.setCodTipoDocumento(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
				t6612DetPlantiBean.setCodRepTrbib(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
				t6612DetPlantiBean.setFechaRegistro(new Date());
				t6612DetPlantiBean.setUsuarioRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
				t6612DetPlantiBean.setIndicadorSelForma(ValidaConstantes.IND_SEL_PLANTILLA_DET_SEL);
				t6612DetPlantiBean.setIndicadorSelDoc(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
				t6612DetPlantiBean.setIndicadorSelRubro(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
				//Inicio [gangles 23/06/2016] Se inserta valores por defecto
				t6612DetPlantiBean.setFechaModificacion(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
				t6612DetPlantiBean.setFechaBaja(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
				t6612DetPlantiBean.setUsuarioMod(ValidaConstantes.SEPARADOR_GUION);
				t6612DetPlantiBean.setCodUsuarioBaja(ValidaConstantes.SEPARADOR_GUION);
				//Fin [gangles 23/06/2016]
				
				parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
				t6612DetPlantiDAO.insertar(parametros);
				parametros.remove("t6612detplantiBean");
			}
			
			//reportes tributarios aduaneros
			List<Map<String, Object>> listaReportes = (List<Map<String, Object>>) mapParametros.get("listaReportes");
			if(!Utils.isEmpty(listaReportes)){
				for (Map<String, Object> map : listaReportes) {
					
					t6612DetPlantiBean = new T6612DetPlantiBean();
					numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DETPLAN);
					t6612DetPlantiBean.setNumCorDetPlanti(Utils.toStr(numCorrelativo));
					t6612DetPlantiBean.setNumPlantilla(numPlantilla);
					t6612DetPlantiBean.setCodRepTrbib(Utils.toStr(map.get("CODDOCUMENTO")));
					t6612DetPlantiBean.setCodFormatoAdi(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setCodTipoDocumento(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setFechaRegistro(new Date());
					t6612DetPlantiBean.setUsuarioRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
					t6612DetPlantiBean.setIndicadorSelRubro(ValidaConstantes.IND_SEL_PLANTILLA_DET_SEL);
					t6612DetPlantiBean.setIndicadorSelForma(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setIndicadorSelDoc(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					//Inicio [gangles 23/06/2016] Se inserta valores por defecto
					t6612DetPlantiBean.setFechaModificacion(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
					t6612DetPlantiBean.setFechaBaja(Utils.stringToDate(ValidaConstantes.FECHA_VACIA,CatalogoConstantes.INT_FOUR));
					t6612DetPlantiBean.setUsuarioMod(ValidaConstantes.SEPARADOR_GUION);
					t6612DetPlantiBean.setCodUsuarioBaja(ValidaConstantes.SEPARADOR_GUION);
					//Fin [gangles 23/06/2016]
					parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
					t6612DetPlantiDAO.insertar(parametros);
					parametros.remove("t6612detplantiBean");
				}
			}
			
			
		}catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.registrarPlantilla");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.registrarPlantilla");
			
			NDC.pop();
			NDC.remove();
		}
		return numPlantilla;
	}
	
	@Override
	public String modificarPlantilla(HashMap<String, Object> mapParametros) throws Exception{
		
		HashMap<String, Object> parametros;
		T6612DetPlantiBean t6612DetPlantiBean;
		String numPlantilla = "";
		String desPlantilla = "";
		Long numCorrelativo;
		Boolean insertar=true;
		Boolean eliminar=true;
		String opcionSeleccionada="";
		String desPlantillaAnterior="";
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		try {
			
			Map<String, Object> mapa = new HashMap<String,Object>();
			
			/* Captura y seteo del Pool - Inicio */
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			numPlantilla = Utils.toStr(mapParametros.get("numPlantilla"));
			desPlantilla = Utils.toStr(mapParametros.get("desPlantilla"));
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			mapaPool = generalService.obtenerOrigenDatosMap(mapa); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml	
			
			parametros = new HashMap<String, Object>();
			//parametros.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta
			
			/**ACTUALIZAMOS LA CABECERA SI ES QUE SE REALIZO CAMBIOS**/
			List<T6611CabPlantiBean> listT6611CabPlantiBean = (List<T6611CabPlantiBean>) mapParametros.get("listT6611CabPlantiBean");
			for (T6611CabPlantiBean t6611CabPlantiBean : listT6611CabPlantiBean) {
				opcionSeleccionada = t6611CabPlantiBean.getIndRepTrib();
				desPlantillaAnterior = t6611CabPlantiBean.getDesPlantilla();
			}
			if(!Utils.equiv(opcionSeleccionada, Utils.toStr(mapParametros.get("codOpcion"))) || !Utils.equiv(desPlantilla, desPlantillaAnterior) ){
				HashMap<String, Object> param = new HashMap<String, Object>();
				param.put("numPlantilla", numPlantilla);
				param.put("desPlantilla", desPlantilla);
				param.put("indRepTrib", Utils.toStr(mapParametros.get("codOpcion")));
				param.put("codUsuModifica", Utils.toStr(mapParametros.get("usuarioRegistro")));
				param.put("fechaModifica", new Date());
				//param.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta
				t6611CabPlantiDAO.actualizar(param);
			};
			/*******************************************************/
			/**INSERTAMOS Y ELIMINAMOS EL DETALLE DE LA PLANTILLA***/
			/*******************************************************/
			List<T6612DetPlantiBean> listT6612documentos = (List<T6612DetPlantiBean>) mapParametros.get("listT6612documentos");
			List<T6612DetPlantiBean> listT6612FormatosAdicionales = (List<T6612DetPlantiBean>) mapParametros.get("listT6612FormatosAdicionales");;
			List<T6612DetPlantiBean> listT6612ReportesTribAdua =  (List<T6612DetPlantiBean>) mapParametros.get("listT6612ReportesTribAdua");;
			//documentos
			List<Map<String, Object>> listaDocumentos = (List<Map<String, Object>>) mapParametros.get("listaDocumentos");
			insertar = true; //insertar= true
			for (Map<String, Object> map : listaDocumentos) {
				//buscamos el codigo del documento seleccionado en nuestra lista antigua de documentos de la plantilla
				//si el codigo no se encuentra en dicha lista, se creará un nuevo detalle de plantilla
				for (T6612DetPlantiBean detallePlantilla : listT6612documentos) {
					if(Utils.equiv(Utils.toStr(map.get("CODDOCUMENTO")),detallePlantilla.getCodTipoDocumento())){
						insertar = false;
					}
				}
				
				if(insertar){
					t6612DetPlantiBean = new T6612DetPlantiBean();
					numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DETPLAN);
					t6612DetPlantiBean.setNumCorDetPlanti(Utils.toStr(numCorrelativo));
					t6612DetPlantiBean.setNumPlantilla(numPlantilla);
					t6612DetPlantiBean.setCodTipoDocumento(Utils.toStr(map.get("CODDOCUMENTO")));
					t6612DetPlantiBean.setCodRepTrbib(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setCodFormatoAdi(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setFechaRegistro(new Date());
					t6612DetPlantiBean.setUsuarioRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
					t6612DetPlantiBean.setIndicadorSelDoc(ValidaConstantes.IND_SEL_PLANTILLA_DET_SEL);
					t6612DetPlantiBean.setIndicadorSelRubro(ValidaConstantes.SEPARADOR_GUION);
					t6612DetPlantiBean.setIndicadorSelForma(ValidaConstantes.SEPARADOR_GUION);
					
					parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
					t6612DetPlantiDAO.insertar(parametros);
					parametros.remove("t6612detplantiBean");	
					
				}
				insertar = true;
			}
			
			eliminar = true; //eliminar= true
			for (T6612DetPlantiBean detallePlantilla : listT6612documentos) {
				//buscamos el codigo del documento de la plantilla en la lista nueva de documentos de la plantilla
				//si el codigo no se encuentra en dicha lista, se eliminará el de plantilla
				for (Map<String, Object> map : listaDocumentos) {
						
					if(Utils.equiv(detallePlantilla.getCodTipoDocumento(),Utils.toStr(map.get("CODDOCUMENTO")))){
						eliminar = false;
					}
					
				}
				if(eliminar){
					t6612DetPlantiBean = new T6612DetPlantiBean();
					t6612DetPlantiBean.setNumCorDetPlanti(detallePlantilla.getNumCorDetPlanti());
					parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
					t6612DetPlantiDAO.eliminar(parametros);
					parametros.remove("t6612detplantiBean");	
				}
				eliminar = true;
				
			}
			
			
			//formatos adicionales
			List<Map<String, Object>> listaFormatos = (List<Map<String, Object>>) mapParametros.get("listaFormatos");
			insertar = true; //insertar= true
			for (Map<String, Object> map : listaFormatos) {
				
				for (T6612DetPlantiBean detallePlantilla : listT6612FormatosAdicionales) {
					if(Utils.equiv(Utils.toStr(map.get("CODDOCUMENTO")),detallePlantilla.getCodTipoDocumento())){
						insertar = false;
					}
				}
				if(insertar){
					t6612DetPlantiBean = new T6612DetPlantiBean();
					numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DETPLAN);
					t6612DetPlantiBean.setNumCorDetPlanti(Utils.toStr(numCorrelativo));
					t6612DetPlantiBean.setNumPlantilla(numPlantilla);
					t6612DetPlantiBean.setCodFormatoAdi(Utils.toStr(map.get("CODDOCUMENTO")));
					t6612DetPlantiBean.setCodTipoDocumento(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setCodRepTrbib(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setFechaRegistro(new Date());
					t6612DetPlantiBean.setUsuarioRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
					t6612DetPlantiBean.setIndicadorSelForma(ValidaConstantes.IND_SEL_PLANTILLA_DET_SEL);
					t6612DetPlantiBean.setIndicadorSelDoc(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					t6612DetPlantiBean.setIndicadorSelRubro(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
					
					parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
					t6612DetPlantiDAO.insertar(parametros);
					parametros.remove("t6612detplantiBean");
				}
			}
			
			eliminar = true; //eliminar= true
			for (T6612DetPlantiBean detallePlantilla : listT6612FormatosAdicionales) {
				//buscamos el codigo del documento de la plantilla en la lista nueva de documentos de la plantilla
				//si el codigo no se encuentra en dicha lista, se eliminará el de plantilla
				for (Map<String, Object> map : listaFormatos) {
						
					if(Utils.equiv(detallePlantilla.getCodTipoDocumento(),Utils.toStr(map.get("CODDOCUMENTO")))){
						eliminar = false;
					}
				}
				if(eliminar){
					t6612DetPlantiBean = new T6612DetPlantiBean();
					t6612DetPlantiBean.setNumCorDetPlanti(detallePlantilla.getNumCorDetPlanti());
					parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
					t6612DetPlantiDAO.eliminar(parametros);
					parametros.remove("t6612detplantiBean");	
				}
				eliminar = true;
			}
			
			//reportes tributarios aduaneros
			List<Map<String, Object>> listaReportes = (List<Map<String, Object>>) mapParametros.get("listaReportes");
			insertar = true; //insertar= true
			if(!Utils.isEmpty(listaReportes)){
				for (Map<String, Object> map : listaReportes) {
					
					for (T6612DetPlantiBean detallePlantilla : listT6612ReportesTribAdua) {
						if(Utils.equiv(Utils.toStr(map.get("CODDOCUMENTO")),detallePlantilla.getCodTipoDocumento())){
							insertar = false;
						}
					}
					if(insertar){
						t6612DetPlantiBean = new T6612DetPlantiBean();
						numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DETPLAN);
						t6612DetPlantiBean.setNumCorDetPlanti(Utils.toStr(numCorrelativo));
						t6612DetPlantiBean.setNumPlantilla(numPlantilla);
						t6612DetPlantiBean.setCodRepTrbib(Utils.toStr(map.get("CODDOCUMENTO")));
						t6612DetPlantiBean.setCodFormatoAdi(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
						t6612DetPlantiBean.setCodTipoDocumento(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
						t6612DetPlantiBean.setFechaRegistro(new Date());
						t6612DetPlantiBean.setUsuarioRegistro(Utils.toStr(mapParametros.get("usuarioRegistro")));
						t6612DetPlantiBean.setIndicadorSelRubro(ValidaConstantes.IND_SEL_PLANTILLA_DET_SEL);
						t6612DetPlantiBean.setIndicadorSelForma(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
						t6612DetPlantiBean.setIndicadorSelDoc(ValidaConstantes.IND_SEL_PLANTILLA_DET_NO_SEL);
						
						parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
						t6612DetPlantiDAO.insertar(parametros);
						parametros.remove("t6612detplantiBean");
					}
				}
			}else{
				for (T6612DetPlantiBean detallePlantilla : listT6612ReportesTribAdua) {
						t6612DetPlantiBean = new T6612DetPlantiBean();
						t6612DetPlantiBean.setNumCorDetPlanti(detallePlantilla.getNumCorDetPlanti());
						parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
						t6612DetPlantiDAO.eliminar(parametros);
						parametros.remove("t6612detplantiBean");	
				}
			}
			
			eliminar = true; //eliminar= true
			for (T6612DetPlantiBean detallePlantilla : listT6612ReportesTribAdua) {
				for (Map<String, Object> map : listaReportes) {
						
					if(Utils.equiv(detallePlantilla.getCodTipoDocumento(),Utils.toStr(map.get("CODDOCUMENTO")))){
						eliminar = false;
					}
					
				}
				if(eliminar){
					t6612DetPlantiBean = new T6612DetPlantiBean();
					t6612DetPlantiBean.setNumCorDetPlanti(detallePlantilla.getNumCorDetPlanti());
					parametros.put("t6612DetPlantiBean", t6612DetPlantiBean);
					t6612DetPlantiDAO.eliminar(parametros);
					parametros.remove("t6612detplantiBean");	
				}
				eliminar = true;
			}
			
			
		}catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.registrarPlantilla");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.registrarPlantilla");
			
			NDC.pop();
			NDC.remove();
		}
		return numPlantilla;
	}
	
	@Override
	public List<T6612DetPlantiBean> listarDetPlantillasPorNumPlantilla(Map<String, Object> parametros) throws Exception {
		// TODO Auto-generated method stub
		List<T6612DetPlantiBean> listarDetPlantillasBean = null;
		try {
		
			listarDetPlantillasBean = t6612DetPlantiDAO.listar(parametros);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualServiceImpl.obtenerListaExpedienteVirtual");
			
			NDC.pop();
			NDC.remove();
		}
		return listarDetPlantillasBean;
	}
	
	/*Sets */
	public void setT6611CabPlantiDAO(T6611CabPlantiDAO t6611CabPlantiDAO) {
		this.t6611CabPlantiDAO = t6611CabPlantiDAO;
	}
	
	public void setT6612DetPlantiDAO(T6612DetPlantiDAO t6612DetPlantiDAO) {
		this.t6612DetPlantiDAO = t6612DetPlantiDAO;
	}
	
	public void setCatalogoService(CatalogoService catalogoService) {
		this.catalogoService = catalogoService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}
	
	public void setT6612DetPlantiDAO2(T6612DetPlantiDAO t6612DetPlantiDAO) {
		this.t6612DetPlantiDAO = t6612DetPlantiDAO;
	}
	

}
