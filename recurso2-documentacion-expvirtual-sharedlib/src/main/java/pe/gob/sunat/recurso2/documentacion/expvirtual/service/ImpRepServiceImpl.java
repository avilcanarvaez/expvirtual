	package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

	import java.util.HashMap;
import java.util.List;
import java.util.Map;

	import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

	import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6616PedRepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6618RepGenExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6611CabPlantiDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6616PedRepDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6618RepGenExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6619RepGenPedDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

	public class ImpRepServiceImpl implements ImpRepService {
		private static final Log log = LogFactory.getLog(ImpRepServiceImpl.class);
		private T6611CabPlantiDAO t6611CabPlantiDAO;
		private CatalogoService catalogoService;
		private PersonaService personaService;
		private T6616PedRepDAO t6616PedRepDAO;
		private T6618RepGenExpDAO t6618RepGenExpDAO;
		private T6619RepGenPedDAO t6619RepGenPedDAO;
		
		@Override
		public List<T6611CabPlantiBean> listarPlantillas(
				Map<String, Object> mapParametrosBusqueda) throws Exception {
			// TODO Auto-generated method stub
			List<T6611CabPlantiBean> listarPlantillasBean = null;
			Map<String, Object> mapa = null;
			List<T6616PedRepBean> listarPedidosBean = null;
			try {
				String codEstadoPlantilla;
				mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_ESTADOS_PLANTILLA_EXPEDIENTE);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaEstadoPalntilla = catalogoService.obtenerCatalogo(mapa);
				
				listarPlantillasBean = t6611CabPlantiDAO.listar(mapParametrosBusqueda);
				listarPedidosBean=t6616PedRepDAO.listarExpedientesPorPedido(mapParametrosBusqueda);
				Integer numOrden = 0;
				for (T6611CabPlantiBean CabPlantiBean : listarPlantillasBean) {
					numOrden++;
					
					mapa = new HashMap<String,Object>();
					mapa.put("codPersona", CabPlantiBean.getCodUsuRegistro().toString().trim());
					
					T02PerdpBean personaBean = personaService.validarPersona(mapa);
					if(personaBean != null) {
						personaBean = personaService.completarInformacionPersona(personaBean);
					}
					
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
				if (log.isDebugEnabled()) log.debug("Error - ImpRepServiceImpl.obtenerListaExpedienteVirtual");
				log.error(ex, ex);
				throw ex;
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - ImpRepServiceImpl.obtenerListaExpedienteVirtual");
				
				NDC.pop();
				NDC.remove();
			}
			return listarPlantillasBean;
		}
		 
		public List<T6616PedRepBean> listarExpedientesPorPedido(
				Map<String, Object> mapParametrosBusqueda) throws Exception {
			
			List<T6616PedRepBean> listT6616PedRepBean = null;
			
			if (log.isDebugEnabled()) log.debug("Inicio - ImpRepServiceImpl.listarExpedientesPorPedido");
			
			try {
				String codigoProceso;
				String codigoTipoExpediente;	
				String codigoEstadoReporte;
				String fechaGenera; 
				
				Map<String, Object> mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
				
				mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);												
				
				Map<String, Object> parametros;
				listT6616PedRepBean = t6616PedRepDAO.listarExpedientesPorPedido(mapParametrosBusqueda);
				if(!Utils.isEmpty(listT6616PedRepBean)){
					int i=1;
					for (T6616PedRepBean t6616PedRepBean : listT6616PedRepBean) {
						codigoProceso = t6616PedRepBean.getCodProceso();
						codigoTipoExpediente = t6616PedRepBean.getCodTipoExpediente();					
						codigoEstadoReporte =  t6616PedRepBean.getCodEstadoReporte();
											
						if(Utils.isEmpty(codigoEstadoReporte) || Utils.equiv(codigoEstadoReporte, ValidaConstantes.SEPARADOR_GUION)){
							t6616PedRepBean.setDesEstadoReporte("");
						}else{
							if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE)){
								t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
							}else{
								if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_GENERADO)){
									t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_GENERADO);
								}else{
									if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE)){										
										t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE);
									} else {
										if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_ERROR)){
											t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_ERROR);
										}	
									}
								}
							}
						}
						fechaGenera = Utils.dateUtilToStringDDMMYYYY(t6616PedRepBean.getFecGenera());
						if(fechaGenera.equals("01/01/1")){
							t6616PedRepBean.setFechaGenera("");					
						}else{
							t6616PedRepBean.setFechaGenera(fechaGenera);						
						}			
						t6616PedRepBean.setDesProceso(Utils.toStr(mapaProcesos.get(codigoProceso)));
						t6616PedRepBean.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(codigoTipoExpediente)));					
						
						parametros = new HashMap<String, Object>();
						parametros.put("codPersona", t6616PedRepBean.getCodResponsable());
						parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);
						
						T02PerdpBean responsable = personaService.validarPersona(parametros);
						if(!Utils.isEmpty(responsable)){
							responsable = personaService.completarInformacionPersona(responsable);					
							t6616PedRepBean.setNombrePersonaRegistro(responsable.getDesNombreCompleto());	
						}else{
							t6616PedRepBean.setNombrePersonaRegistro("");	
						}										
						t6616PedRepBean.setNumOrden(i);
						i++;
					}
				}
				
			} catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - ImpRepServiceImpl.listarExpedientesPorPedido");
				log.error(ex, ex);
				throw ex;
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - ImpRepServiceImpl.listarExpedientesPorPedido");
				
				NDC.pop();
				NDC.remove();
			}
			return listT6616PedRepBean;
		}
		
		public T6618RepGenExpBean obtenerExpedientePorPedido(Map<String, Object> parametrosBusqueda) throws Exception {
			if (log.isDebugEnabled()) log.debug("Inicio - ImpRepServiceImpl.obtenerExpedientePorPedido");
			
			T6618RepGenExpBean t6618RepGenExpBean;
			
			try {
				String codigoEstadoReporte;
				String fechaGeneracion; 
				
				t6618RepGenExpBean = t6618RepGenExpDAO.datosRepPorExpediente(parametrosBusqueda);
			
				codigoEstadoReporte =  t6618RepGenExpBean.getCodEstadoRepPorExpediente();
					
				if(Utils.isEmpty(codigoEstadoReporte) || Utils.equiv(codigoEstadoReporte, ValidaConstantes.SEPARADOR_GUION)){
					t6618RepGenExpBean.setDesCodEstadoRepPorExpediente("");
				}else{
					if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_EXP_PENDIENTE)){
						t6618RepGenExpBean.setDesCodEstadoRepPorExpediente(ValidaConstantes.DES_IND_ESTADO_REP_GEN_EXP_PENDIENTE);
					}else{
						if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_EXP_GENERADO)){
							t6618RepGenExpBean.setDesCodEstadoRepPorExpediente(ValidaConstantes.DES_IND_ESTADO_REP_GEN_EXP_GENERADO);
						}else{
							if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_EXP_PARCIALMENTE)){
								t6618RepGenExpBean.setDesCodEstadoRepPorExpediente(ValidaConstantes.DES_IND_ESTADO_REP_GEN_EXP_PARCIALMENTE);
							} else {
								if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_EXP_ERROR)){
									t6618RepGenExpBean.setDesCodEstadoRepPorExpediente(ValidaConstantes.DES_IND_ESTADO_REP_GEN_EXP_ERROR);
								}	
							}
						}
					}
				}				
				fechaGeneracion = Utils.dateUtilToStringDDMMYYYY(t6618RepGenExpBean.getFecGeneracion());
				if(fechaGeneracion.equals("01/01/1")){
					t6618RepGenExpBean.setFechaGeneracion("");					
				}else{
					t6618RepGenExpBean.setFechaGeneracion(fechaGeneracion);						
				}
				
			} catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - ImpRepServiceImpl.obtenerExpedientePorPedido");
				log.error(ex, ex);
				throw ex;
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - ImpRepServiceImpl.obtenerExpedientePorPedido");
				
				NDC.pop();
				NDC.remove();
			}
			return t6618RepGenExpBean;
		}
		
		 
		public List<T6616PedRepBean> listarPedidos(
				Map<String, Object> mapParametrosBusqueda) throws Exception {
			
			List<T6616PedRepBean> listT6616PedRepBean = null;
			
			if (log.isDebugEnabled()) log.debug("Inicio - ImpRepServiceImpl.listarPedidos");
			
			try {
				String codigoProceso;
				String codigoTipoExpediente;	
				String codigoEstadoReporte;
				String fechaRegistro; 
			
				
				Map<String, Object> mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_PROCESOS);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaProcesos = catalogoService.obtenerCatalogo(mapa);
				
				mapa = new HashMap<String,Object>();
				
				mapa.put("codClase", CatalogoConstantes.CATA_TIPOS_EXPEDIENTES);
				mapa.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
				
				Map<String, Object> mapaTiposExpedientes = catalogoService.obtenerCatalogo(mapa);												
				
				Map<String, Object> parametros;
				listT6616PedRepBean = t6616PedRepDAO.listarPedidos(mapParametrosBusqueda);
				if(!Utils.isEmpty(listT6616PedRepBean)){
					int i=1;
					for (T6616PedRepBean t6616PedRepBean : listT6616PedRepBean) {
						t6616PedRepBean.setNumeroPedido(t6616PedRepBean.getNumeroPedido().trim());
						codigoProceso = t6616PedRepBean.getCodProceso();
						codigoTipoExpediente = t6616PedRepBean.getCodTipoExpediente();					
						codigoEstadoReporte =  t6616PedRepBean.getCodEstadoReporte();
											
						if(Utils.isEmpty(codigoEstadoReporte) || Utils.equiv(codigoEstadoReporte, ValidaConstantes.SEPARADOR_GUION)){
							t6616PedRepBean.setDesEstadoReporte("");
						}else{
							if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE)){
								t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
							}else{
								if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_GENERADO)){
									t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_GENERADO);
								}else {
									if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE)) {
										t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE);
									} else {
										if(Utils.equiv(codigoEstadoReporte, ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_ERROR)) {
											t6616PedRepBean.setDesEstadoReporte(ValidaConstantes.DES_IND_ESTADO_REP_GEN_PEDIDO_ERROR);
										}
									}
								}
							}
						}
						fechaRegistro = Utils.dateUtilToStringDDMMYYYY(t6616PedRepBean.getFecRegistro());
						if(fechaRegistro.equals("01/01/1")){
							t6616PedRepBean.setFechaRegistro("");					
						}else{
							t6616PedRepBean.setFechaRegistro(fechaRegistro);						
						}				
						t6616PedRepBean.setDesProceso(Utils.toStr(mapaProcesos.get(codigoProceso)));
						t6616PedRepBean.setDesTipoExpediente(Utils.toStr(mapaTiposExpedientes.get(codigoTipoExpediente)));					
						
						parametros = new HashMap<String, Object>();
						parametros.put("codPersona", t6616PedRepBean.getCodUsuarioRegistro());
						parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);
						
						T02PerdpBean responsable = personaService.validarPersona(parametros);
						if(!Utils.isEmpty(responsable)){
							responsable = personaService.completarInformacionPersona(responsable);					
							t6616PedRepBean.setNombrePersonaRegistro(responsable.getDesNombreCompleto());	
						}else{
							t6616PedRepBean.setNombrePersonaRegistro("");	
						}									
						t6616PedRepBean.setNumOrden(i);
						i++;
					}
				}
				
			} catch (Exception ex) {
				if (log.isDebugEnabled()) log.debug("Error - ImpRepServiceImpl.listarPedidos");
				log.error(ex, ex);
				throw ex;
			} finally {
				if (log.isDebugEnabled()) log.debug("Final - ImpRepServiceImpl.listarPedidos");
				
				NDC.pop();
				NDC.remove();
			}
			return listT6616PedRepBean;
		}
		
		@Override
		public List<T6618RepGenExpBean> obtenerListaExpedientesTrabajo(Map<String, Object> parametrosBusqueda) throws Exception {
			
			if (log.isDebugEnabled()) log.debug("Inicio - ImpRepServiceImpl.obtenerListaExpedientesTrabajo");
			List<T6618RepGenExpBean> listT6618RepGenExpBean = null;
			
			try {
				
				listT6618RepGenExpBean = t6618RepGenExpDAO.listarExpedientesTrabajo(parametrosBusqueda);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
				
			return listT6618RepGenExpBean;
		}
		
		/*Sets */
		public void setT6611CabPlantiDAO(T6611CabPlantiDAO t6611CabPlantiDAO) {
			this.t6611CabPlantiDAO = t6611CabPlantiDAO;
		}
		public void setCatalogoService(CatalogoService catalogoService) {
			this.catalogoService = catalogoService;
		}

		public void setPersonaService(PersonaService personaService) {
			this.personaService = personaService;
		}	
		public void setT6616PedRepDAO(T6616PedRepDAO t6616PedRepDAO) {
			this.t6616PedRepDAO = t6616PedRepDAO;
		}

		public void setT6618RepGenExpDAO(T6618RepGenExpDAO t6618RepGenExpDAO) {
			this.t6618RepGenExpDAO = t6618RepGenExpDAO;
		}

		public void setT6619RepGenPedDAO(T6619RepGenPedDAO t6619RepGenPedDAO) {
			this.t6619RepGenPedDAO = t6619RepGenPedDAO;
		}
	}
