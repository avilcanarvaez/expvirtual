//package pe.gob.sunat.tecnologia.signer.impl;
package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.gob.sunat.recurso2.documentacion.expvirtual.service.DocumentoItemRequerimientoServiceImpl;

/**
 * Permite almacenar el store de los certificados digitales en cache, aplicando el patron Singleton.
 * 
 * @author JVALDEZ
 *
 */
public class AlmacenFirmas {
	private static final Log log = LogFactory.getLog(AlmacenFirmas.class);
    /**
     * Almacen de certificados
     */
    private Map certificados;
    /**
     * singleton
     */
    private static AlmacenFirmas almacen;
    
    static {
        try {
            almacen = new AlmacenFirmas();
        } catch (Throwable e){
            e.printStackTrace();
        }
    }
    
    /**
     * Crea en forma estatica el almacen de los certificados, este almacen se crea de forma unica a nivel de VM
     */
    private AlmacenFirmas() {
        certificados = Collections.synchronizedMap(new HashMap());
    }
    /**
     * Obtiene la instancia del singleton
     * 
     * @return AlmacenFirmas
     */
    static public AlmacenFirmas getInstance(){
        return almacen;
    }
    
    /**
     * Carga el store del almacen.
     * Si el la diferencia de tiempo de vida de este objecto excede el tiempo de refresco, se retorna null para que se pueda
     * recargar el store.
     * 
     * @param alias String
     * @param refreshTime long
     * @return Map store
     */
    public Map getStore(String alias, long refreshTime){
        long ts = Calendar.getInstance().getTimeInMillis();
        
        Map store = (Map) certificados.get(alias);
        if(log.isDebugEnabled())log.debug("getStore:"+store);
        if(store != null){
            Long tsI = (Long) store.get("almacenFirmasTimeStamp");
            if(log.isDebugEnabled())log.debug("getStore - tsI:"+tsI);
            if (tsI!=null & ts-tsI>refreshTime){
                store = null;
            }
        }
        
        return store;
    }
    /**
     * Guarda el store en el almacen.
     * Agrega un timestamp al map que permite manejar tiempo de expiracion.
     * 
     * @param alias String
     * @param store Map
     */
    public void setStore(String alias, Map store ){
        if (store !=null){
            store.put("almacenFirmasTimeStamp", Calendar.getInstance().getTimeInMillis());
        }
        certificados.put(alias, store);
    }
}
