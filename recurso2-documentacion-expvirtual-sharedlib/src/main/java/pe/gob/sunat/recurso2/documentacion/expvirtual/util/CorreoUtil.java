package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class CorreoUtil {
	private static final Log log = LogFactory.getLog(CorreoUtil.class);
	
	public static void enviarCorreo(Map<String, Object> parametros) {
        JavaMailSenderImpl javaMailSender;
        MimeMessageHelper mimeMessageHelper;
        MimeMessage message;
        String mailSunatSubject;
        String host;
        String address;
        String name;
        InternetAddress internetAddress;
        String contenidoMensaje = null;
        String destinatario = null;
        boolean formatHTML = false;
        
        try {
        	   if (log.isDebugEnabled()) log.debug("Inicio - CorreoUtil.enviarCorreo");	
        	
               javaMailSender = new JavaMailSenderImpl();
               internetAddress = new InternetAddress();
               mailSunatSubject = parametros.get("asunto").toString();
               host = (String)parametros.get("servidor");
               address = (String)parametros.get("correoRemitentePorDefecto");
               name = (String)parametros.get("asuntoPorDefecto");
               javaMailSender.setHost(host);
               javaMailSender.setUsername(address);
               internetAddress.setAddress(address);
               internetAddress.setPersonal(name);
               message = javaMailSender.createMimeMessage();
               
               if(parametros.get("formatoHTML") != null) {
            	   mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED);
            	   formatHTML = true;
               } else {
            	   mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO);
               }
               
               mimeMessageHelper.setFrom(internetAddress);
               mimeMessageHelper.setSubject(mailSunatSubject);
               
               if(parametros.get("destinatario") != null) {
            	   destinatario = parametros.get("destinatario").toString().trim();
               }
                
               //DESCOMENTAR PARA PRODUCCION               
               mimeMessageHelper.setTo(destinatario);
               
               //COMENTAR PARA PRODUCCION
               //mimeMessageHelper.setTo("oachahuic@sunat.gob.pe");
               //mimeMessageHelper.setTo("icapunay@sunat.gob.pe");
               contenidoMensaje = parametros.get("mensaje").toString();
               
               if(formatHTML) {
            	   message.setContent(contenidoMensaje, "text/html");
               } else {
            	   mimeMessageHelper.setText(contenidoMensaje);
               }
               
               javaMailSender.send(message);
               
               if (log.isDebugEnabled()) log.debug("Enviando correo a: " + destinatario +  " Mensaje: " + contenidoMensaje);
        } catch (UnsupportedEncodingException e) {
               log.error("UnsupportedEncodingException");
               if (log.isDebugEnabled()) log.debug("No se puedo enviar correo a: " + destinatario);
        } catch (MessagingException e) {
               log.error("MessagingException");
               if (log.isDebugEnabled()) log.debug("No se puedo enviar correo a: " + destinatario);
        } catch (Exception e){
               log.error("Ha ocurrido un error inesperado al enviar mail");
               if (log.isDebugEnabled()) log.debug("No se puedo enviar correo a: " + destinatario);
        }
        
        if (log.isDebugEnabled()) log.debug("Fin - CorreoUtil.enviarCorreo");
  }
}