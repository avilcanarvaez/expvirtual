package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.gob.sunat.framework.util.io.dao.LdapDAO;


/**
 * @author MLIMAYLLA
 * @since 03/2010
 * F�brica de Firmas
 * 
 */
public class SignerFactory {

	private final Log log = LogFactory.getLog(this.getClass());

	
	

	/**
	 * Obtiene la clave privada y el certificado digital institucional
	 *  
	 * @return Map con la Llave Privada y el certificado digital SUNAT
	 * 		pkey: PrivateKey de SUNAT
	 * 		certificado: X509Certificate Certificado Digital de SUNAT
	 * @throws Exception 
	 */

	public Map getCertificadoDigital() throws Exception{

		Map mldap = null;
		LdapDAO ldapdao = new LdapDAO("ldapinternet");
		mldap = ldapdao.getCertificado("emisorrhcert");

		/*Validamos que el certificado est� activo*/
		try{
			X509Certificate cert = (X509Certificate)mldap.get("certificado");
			cert.checkValidity();			
		} catch(CertificateExpiredException e){
			if (log.isErrorEnabled()) log.error("getCertificadoDigital " , e);
			throw new CertificateException ("El certificado no se encuentra v�lido (Expir�).");
		} catch(CertificateNotYetValidException e){
			if (log.isErrorEnabled()) log.error("getCertificadoDigital " , e);
			throw new CertificateException ("El Certificado a�n no se encuentra v�lido.");
		} finally{
		}

		return mldap;
	}

//	Ini IMR
	public Map getCertificadoDigital(String alias)throws Exception
	{
		if (log.isErrorEnabled()) log.error("Inicio - getCertificadoDigital" );
		Map mldap = null;
		LdapDAO ldapdao = new LdapDAO("ldapinternet");
//		LdapDAO ldapdao = new LdapDAOImpl("ldapintranet");
		mldap = ldapdao.getCertificado(alias);
		
		/*Validamos que el certificado est� activo*/
		try{
			X509Certificate cert = (X509Certificate)mldap.get("certificado");
			cert.checkValidity();			
		} catch(CertificateExpiredException e){
			if (log.isErrorEnabled()) log.error("getCertificadoDigital " , e);
			throw new CertificateException ("El certificado no se encuentra v�lido (Expir�).");
		} catch(CertificateNotYetValidException e){
			if (log.isErrorEnabled()) log.error("getCertificadoDigital " , e);
			throw new CertificateException ("El Certificado a�n no se encuentra v�lido.");
		} finally{
		}

		return mldap;
	}
//	Fin IMR
	
	//TODO: Crear metodo accesorio para obtener un PFX

}
