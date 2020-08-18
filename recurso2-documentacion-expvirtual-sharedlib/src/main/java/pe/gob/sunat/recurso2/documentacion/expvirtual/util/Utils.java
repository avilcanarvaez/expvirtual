
package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author jjurado
 * @since 27.08.2015 Utils.
 */

/**
 * @author Eduardo Aguilar
 *
 */
@SuppressWarnings({ "unchecked" })
public class Utils extends PdfPageEventHelper implements Comparator<Map<String, Object>> {
	
	private static final Log log = LogFactory.getLog(Utils.class);
    /**
     * Devuelve el tipo MIME en base a la extension
     * 
     * @author eaguilar
     * @since 04.07.2016
     * @param extension
     * @return
     */
    public static String obtenerTipoMime(String extension) {
        try {
        	String tipoMIME = "application/octet-stream";
            if(extension.trim().equals("pdf") || extension.trim().equals(".pdf")){
            	tipoMIME = "application/pdf";
            }
            else if(extension.trim().equals("zip") || extension.trim().equals(".zip")){
            	tipoMIME = "application/zip";
            }
            else if(extension.trim().equals("docx") || extension.trim().equals(".docx") || extension.trim().equals("doc") || extension.trim().equals(".doc")){
            	tipoMIME = "application/msword";
            }
            else if(extension.trim().equals("xlsx") || extension.trim().equals(".xlsx") || extension.trim().equals("xls") || extension.trim().equals(".xls")){
            	tipoMIME = "application/excel";
            }
            else if(extension.trim().equals("pptx") || extension.trim().equals(".pptx") || extension.trim().equals("ppt") || extension.trim().equals(".ppt")){
            	tipoMIME = "application/mspowerpoint";
            }
            return tipoMIME;
        } catch (Exception e) {
            e.printStackTrace();
            return "application/octet-stream";
        }
    }

    /**
     * Metodo mapea una determinada clase, Te crea una objeto de una determinada
     * clase y le matea los parametros del request.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param <T>
     * @param HttpServletRequest
     * @param clase
     * @return
     */
    public static <T> T mapearBean(HttpServletRequest request, Class<T> clase) {
        try {

            String className = clase.getName();
            Object generico = Class.forName(className).newInstance();
            mapearBean( generico, request );
            return clase.cast(generico);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static Map<String, Field> _toMapFields(Class<?> clase) {
        Field[] fields = clase.getDeclaredFields();
        Map<String, Field> mapFields = new HashMap<String, Field>();
        for (Field field : fields) {
            mapFields.put(field.getName(), field);
        }
        return mapFields;
    }
    
    private static void mapearBean(Object generico, HttpServletRequest request) throws Exception {

        Map<String, String[]> mapaDatos = request.getParameterMap();
        
        Class<?> clase = generico.getClass();
        Iterator<String> itMapaDatos = mapaDatos.keySet().iterator();
        Map<String, Field> mapFields = _toMapFields( clase );
        boolean isAccessible;
        Object valor = null;
        Field field;
        String key;
        String[] value;

        // si el bean no tiene atributos
        if (mapFields.isEmpty()) {
            return;
        }

        while (itMapaDatos.hasNext()) {
            key = itMapaDatos.next();
            value = mapaDatos.get(key);
            if (value == null || value.length == 0) {
                continue;
            }

            field = mapFields.get(key);
            // verificamos si existe un campo que tenga el mismo nombre del key
            // (mapa)
            if (!isEmpty(field)) {
                try {
                    // se asigna valor del mapa al object (se castea)
                    if (equiv(field.getType(), Long.class)) {
                        valor = toLong( value[0] );
                    } else if (equiv( field.getType(), Short.class )) {
                        valor = toShort( value[0] );
                    } else if (equiv(field.getType(), Integer.class)) {
                        valor = toInteger(value[0]);
                    } else if (equiv(field.getType(), Double.class)) {
                        valor = toDouble( !isEmpty( value[0] ) ? value[0].replaceAll( "\\,", "" ) : value[0] );
                    } else if (equiv(field.getType(), Float.class)) {
                        valor = toFloat( !isEmpty( value[0] ) ? value[0].replaceAll( "\\,", "" ) : value[0] );
                    } else if (equiv( field.getType(), Date.class )) {
                        valor =stringToDate(value[0], CatalogoConstantes.INT_TWO ) ;
                    } else if (equiv( field.getType(), String[].class )) {
                        // esto xEjemplo: cuando sean varios checkbox con el mismo nombre
                        valor = value;
                    } else {
                        valor = value[0];
                    }

                    // si el atributo no es accesible (private) lo volvemos
                    // accesible temporalmente
                    if (!(isAccessible = field.isAccessible())) {
                        field.setAccessible(true);
                    }
                    field.set(generico, valor);
                    // se le quita la accesibilidad si es que no lo era
                    if (!isAccessible) {
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        }
        
        /** INICIO DE FormFiles **/
        Enumeration<String> attributeNames = request.getAttributeNames();
        if (attributeNames.hasMoreElements()) {
            key = attributeNames.nextElement();
            valor = request.getAttribute( key );
            if (!isEmpty( valor )) {
                if (equiv( FormFile.class, valor.getClass() )) {
                    field = mapFields.get( key );
                    // verificamos si existe un campo que tenga el mismo nombre
                    // del key (mapa)
                    if (!isEmpty( field )) {
                        if (equiv( field.getType(), FormFile.class )) {

                            // si el atributo no es accesible (private) lo
                            // volvemos
                            // accesible temporalmente
                            if (!(isAccessible = field.isAccessible())) {
                                field.setAccessible( true );
                            }
                            field.set( generico, valor );
                            // se le quita la accesibilidad si es que no lo era
                            if (!isAccessible) {
                                field.setAccessible( false );
                            }

                        }
                    }
                }
            }
        }
        /** FIN FormFiles **/
    }
    
    /**
     * Metodo convierte una cadena a long.
     * 
     *@author jjurado
     * @since 27.08.2015
     * @param objeto
     * @return
     */
    public static Long toLong(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Long.parseLong(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a integer.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param objeto
     * @return
     */
    public static Integer toInteger(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Integer.parseInt(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a short.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param objeto
     * @return
     */
    public static Short toShort(Object objeto) {
        if (isEmpty( objeto )) {
            return null;
        }
        try {
            return Short.parseShort( objeto.toString() );
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Metodo convierte una cadena a double.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param object
     * @return
     */
    public static Double toDouble(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Double.parseDouble(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a float.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param object
     * @return
     */
    public static Float toFloat(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Float.parseFloat(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte un objeto a cadena
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param object
     * @return
     */
    public static String toStr(Object cadena) {
        return isEmpty(cadena) ? null : toBlank(cadena.toString());
    }

    /**
     * Metodo devuelve una cadena formateada.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param cadena
     * @return
     */
    public static String toBlank(String cadena) {
        return isEmpty(cadena) ? "" : cadena;
    }

    /**
     * Metodo devuelve una cadena, cadena vacia si el objeto es null (uso para
     * grilla).
     * 
     *  @author jjurado
     * @since 27.08.2015
     * @param object
     * @return
     */
    public static String toBlankObject(Object object) {
        return isEmpty(object) ? "" : object.toString();
    }

    /**
     * Verifica si un objecto es vacio: 
     * - Object: Verifica si es nulo (o vacio de ser List)
     * - String: El valor es nulo o vacio
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return object.toString().trim().length() == 0;
        }
        if (object instanceof StringBuilder) {
            return object.toString().trim().length() == 0;
        }
        if (object instanceof List<?> || object instanceof ArrayList<?>) {
            return ((List<?>) object).isEmpty();
        }
        return false;
    }

    /**
     * Verifica si todas las posiciones de un array son vacios.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param array
     * @return
     */
    public static boolean isEmptyArray(Object[] array) {
        if (array == null) {
            return true;
        }
        if (array.length == 0) {
            return true;
        }
        for (Object object : array) {
            if (!isEmpty(object)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Compara si el valor de dos objetos es igual
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param object1
     * @param object2
     * @return
     */
	public static boolean equiv(Object object1, Object object2) {
		if (object1 instanceof String && object2 instanceof String) {
			return toBlank(object1.toString()).equals(toBlank(object2.toString()));
		}

		if ((isEmpty(object1) && !isEmpty(object2)) || (!isEmpty(object1) && isEmpty(object2))) {
			return false;
		}
		return object1.equals(object2);
	}

    /**
     * Metodo busca una cadena en una arreglo de cadenas.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param cadena
     * @param valores
     * @return
     */
    public static boolean inList(String cadena, String... valores) {
        for (String valor : valores) {
            if (cadena.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que elimina una saltos de l�nea en una cadena.
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param cadena
     * @return
     */
    public static String quitarSaltosLinea(String cadena){
        if(!isEmpty(cadena)){
            return cadena.replaceAll("[\n\r]","");
        }
        
        return cadena;
    }
    
    /**
     * Metodo que devuelve un subString desde la posicion 0 hasta una posicion final,
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param cadena
     * @param indexFin
     * @return
     */
    public static String subStr(String cadena, int indexFin){
       return subStr(cadena, 0, indexFin);
    }
    
    /**
     * Metodo que devuelve un subString desde una posicion inicial hasta una posicion final,
     * 
     * @author jjurado
     * @since 27.08.2015
     * @param cadena
     * @param indexInicio
     * @param indexFin
     * @return
     */
    public static String subStr(String cadena, int indexInicio, int indexFin){
       if(isEmpty(cadena)){
          return cadena;
       }
       int posicionFinal = cadena.length() - 1;
       if( posicionFinal < indexInicio){
          return null;
       }
       if(posicionFinal < indexFin){
          return cadena.substring(indexInicio);
       }
       
       return cadena.substring(indexInicio, indexFin);
    }

    public static Date stringToDate(String string, Integer option) {
        SimpleDateFormat formatter = null;
        Date date = null;
        if (option.equals(CatalogoConstantes.INT_ONE)) {
                        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        } else if (option.equals(CatalogoConstantes.INT_TWO)) {
                        formatter = new SimpleDateFormat("dd/MM/yyyy");
        } else if (option.equals(CatalogoConstantes.INT_THREE)) {
                        formatter = new SimpleDateFormat("HH:mm:ss");
        } else if (option.equals(CatalogoConstantes.INT_FOUR)) {
        				formatter = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_REGISTRO);
        }else if (option.equals(CatalogoConstantes.INT_FIVE)) {
        				formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        
        try {
        		formatter.setLenient(false);
        		date = formatter.parse(string);
        } catch (ParseException ex) {
                        ex.printStackTrace();
                        date = null;
        }
        return date;
        
    }
    
	public static String dateUtilToStringDDMMYYYY(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH) + 1;
		String mesStr="";
		int dia = cal.get(Calendar.DATE);
		String diaStr="";
		if(mes<10){
			mesStr = "0"+mes;
		}else{
			mesStr=""+ mes;
		}
		if(dia<10){
			diaStr = "0"+dia;
		}else{
			diaStr = ""+dia;
		} 
		String formatedDate = diaStr + "/" + (mesStr) + "/" +  cal.get(Calendar.YEAR);
		return formatedDate;
	}
    
    public static String dateUtilToStringhhmm(Date date){
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	int minuto = cal.get(Calendar.MINUTE) + 1;
    	String minutoStr = "";
    	if (minuto<10){
    		minutoStr = "0"+ minuto;
    	} else {
    		minutoStr = "" + minuto;
    	}
    	String formatedDate = cal.get(Calendar.HOUR) + ":" + minutoStr;
    	return formatedDate;
    }

    // redondea al nro de decimales establecido sin el problema de la coma flotante/double de Java
    public static BigDecimal roundBigDecimal(double numero, int nroDecimales) {
        return new BigDecimal(numero + "").setScale(nroDecimales, BigDecimal.ROUND_HALF_UP);
    }

    // redondea y retorna double
    public static double round(double numero, int nroDecimales) {
        return roundBigDecimal(numero, nroDecimales).doubleValue();
    }

    // redondea y formatea como cadena
    public static String roundString(double numero, int nroDecimales) {
        return roundBigDecimal(numero, nroDecimales).toString();
    }

    // convierte un date a string en un format especificado 
    public static String convertirDateToString(Date date, String format) {
		DateFormat df = null;		
		String dateString = null;
		
		if(date != null) {
			df = new SimpleDateFormat(format);			
			dateString = df.format(date);
		}
		
		return dateString;
	}
    
    // convierte un date a string en un format especificado 
    public static boolean esFechaVacia(Date date) {
    	boolean resp = false;
    	Calendar cal = null;
		
		if(date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
			
			if(	cal.get(Calendar.YEAR) == 1 &&
				cal.get(Calendar.MONTH) == 0 &&
				cal.get(Calendar.DAY_OF_MONTH) == 1) {
				resp = true;
			}
		}
		
		return resp;
	}
    
    public static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
    {
            File convFile = new File(convertirUnicode(multipart.getOriginalFilename())); //se valida unicode para ie8
            multipart.transferTo(convFile);
            return convFile;
    }
    
    public static int obtenerDifDias(Date fecha1, Date fecha2){
    	
    	final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
    	Long diferencia = (  fecha1.getTime() - fecha2.getTime() )/ MILLSECS_PER_DAY;
    	
    	return diferencia.intValue();
    }
    
    public static boolean esArchivoPDF(String nombreArchivo) {
    	boolean resp = false;
    	String extension = null;
    	
    	if(nombreArchivo != null) {
    		extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    		if(extension != null && extension.length() > 0) {
    			extension = extension.toUpperCase();
    			
    			if(extension.equals(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
    				resp = true;
    			}
    		}
    	}
    	
    	return resp;
    }
    
    public static String obtenerExtension(String nombreArchivo) {
    	String extension = null;
    	
    	if(nombreArchivo != null) {
    		extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
    		extension = extension.toUpperCase();
    	}
    	
    	return extension;
    }
    
    public static String obtenerNombreSinExtension(String nombreArchivo) {
    	String nombreSinExtension = null;
    	
    	if(nombreArchivo != null) {
    		nombreArchivo = nombreArchivo.trim();
    		nombreSinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
    	}
    	
    	return nombreSinExtension;
    }
    
    public static void mergePDF(String carpetaBase, String[] pdfs, String pdfCombinado)  {
    	Document PDFCombineUsingJava = null;
    	PdfCopy copy = null;
    	PdfReader ReadInputPDF = null; 
        int number_of_pages = 0;
    	
        try {
	        PDFCombineUsingJava = new Document();
	        copy = new PdfCopy(PDFCombineUsingJava, new FileOutputStream(carpetaBase + pdfCombinado));
	        PDFCombineUsingJava.open();
	      
	        for(int i = 0; i < pdfs.length; i++) {
	        	ReadInputPDF = new PdfReader(carpetaBase + pdfs[i]);
	        	number_of_pages = ReadInputPDF.getNumberOfPages();
	          
	        	for(int page = 0; page < number_of_pages; ) {
	        		copy.addPage(copy.getImportedPage(ReadInputPDF, ++page));
	        	}
	        }
        } catch (Throwable ex) {
			log.error( ex, ex);
		} finally {
        	if(PDFCombineUsingJava != null) {
        		if(PDFCombineUsingJava.isOpen()) {
        			PDFCombineUsingJava.close();
        		}
        	}
        }
    }
    
    public static boolean  mergePDFVal(String carpetaBase, String[] pdfs, String pdfCombinado)  {
    	Document PDFCombineUsingJava = null;
    	PdfCopy copy = null;
    	PdfReader ReadInputPDF = null; 
        int number_of_pages = 0;

        boolean rpta = false;
        try {
	        PDFCombineUsingJava = new Document();
	        copy = new PdfCopy(PDFCombineUsingJava, new FileOutputStream(carpetaBase + pdfCombinado));
	        PDFCombineUsingJava.open();
	      
	        for(int i = 0; i < pdfs.length; i++) {
	        	ReadInputPDF = new PdfReader(carpetaBase + pdfs[i]);
	        	number_of_pages = ReadInputPDF.getNumberOfPages();
	          
	        	for(int page = 0; page < number_of_pages; ) {
	        		copy.addPage(copy.getImportedPage(ReadInputPDF, ++page));
	        	}
	        }
	        rpta = true;
        } catch (Throwable ex) {
        	rpta = false;
			log.error( ex, ex);
		}finally {
        	if(PDFCombineUsingJava != null) {
        		if(PDFCombineUsingJava.isOpen()) {
        			PDFCombineUsingJava.close();
        		}
        	}
        }
        log.debug("final rpta ***"+rpta);
        return rpta;
    }
    public static void  convertFotoYDatosRepresLegalAPDF(String archivoImagen, String archivoPDF, String[] listaDatos) throws Exception {
    	Document document = null;
    	Image image = null;
    	PdfWriter writer = null;
    	FileOutputStream fos = null;
    	float widthPDF = 250;//PageSize.A4.getWidth() - 66f;
    	float heightPDF = 300;//PageSize.A4.getHeight() - 66f;
        try {
        	document = new Document();
        	fos = new FileOutputStream(archivoPDF);
        	writer = PdfWriter.getInstance(document, fos);
        	writer.open();
            
            document.open();
            image = Image.getInstance(archivoImagen);
            
            if((image.getHeight() > widthPDF) || (image.getWidth() > heightPDF)) {
            	image.scaleToFit(widthPDF, heightPDF);
            }
            
            document.add(image);
            
          //CREAMOS EL TEXTO CON LOS DATOS ADICIONALES
			String[] listaTitulosDatos = {"DNI ", "Apellidos y Nombres", "Domicilio ", "Relación con el contribuyente "};

			Font fontNegrita = new Font(Font.HELVETICA, 22, Font.ITALIC, Color.BLACK);
			Font fontNormal = new Font(Font.HELVETICA, 22, Font.ITALIC, Color.BLACK);
			fontNegrita.setStyle(Font.BOLD);
			
			PdfPTable tablaDatos = new PdfPTable(2);
			tablaDatos.setTotalWidth(500);
			tablaDatos.setWidths(new int[]{170,330});

			PdfPCell celda;
			//INICIO ESPACIO EN BLANCO
			celda = new PdfPCell(new Phrase("   ", fontNegrita));
			celda.setBorder(Rectangle.NO_BORDER);
			celda.setPaddingTop(10);
			celda.setPaddingBottom(5);
			tablaDatos.addCell(celda);
			
			celda = new PdfPCell(new Phrase("  ", fontNormal));
			celda.setBorder(Rectangle.NO_BORDER);
			celda.setPaddingTop(10);
			celda.setPaddingBottom(5);
			tablaDatos.addCell(celda);
			//FIN ESPACIO EN BLANCO
			for (int i = 0; i < listaTitulosDatos.length; i++) {
				String itemTitulo = listaTitulosDatos[i];
				String itemDato = listaDatos[i];
				
				celda = new PdfPCell(new Phrase(itemTitulo, fontNegrita));
				celda.setBorder(Rectangle.NO_BORDER);
				celda.setPaddingTop(10);
				celda.setPaddingBottom(5);
				tablaDatos.addCell(celda);
				
				celda = new PdfPCell(new Phrase(itemDato, fontNormal));
				celda.setBorder(Rectangle.NO_BORDER);
				celda.setPaddingTop(10);
				celda.setPaddingBottom(5);
				tablaDatos.addCell(celda);
            }
			PdfContentByte cb = writer.getDirectContent();

			//Se agrega datos del representante legal
			tablaDatos.writeSelectedRows(0,-1,59,521,cb);

//			document.add(tablaDatos);//Descomentar para presentar
        } catch(Exception ex) {
        	throw ex;
        } finally {
        	if(document != null) {
        		if(document.isOpen()) {
        			document.close();
        		}
        	}
        	
        	if(writer != null) {
       			writer.close();
        	}
        }
    }
    
    public static void convertImagenAPDF(String archivoImagen, String archivoPDF) throws Exception {
    	Document document = null;
    	Image image = null;
    	PdfWriter writer = null;
    	FileOutputStream fos = null;
    	float widthPDF = PageSize.A4.getWidth() - 66f;
    	float heightPDF = PageSize.A4.getHeight() - 66f;
    	
        try {
        	document = new Document();
        	fos = new FileOutputStream(archivoPDF);
        	writer = PdfWriter.getInstance(document, fos);
        	writer.open();
            
            document.open();
            image = Image.getInstance(archivoImagen);
            
            if((image.getHeight() > widthPDF) || (image.getWidth() > heightPDF)) {
            	image.scaleToFit(widthPDF, heightPDF);
            }
            
            document.add(image);
        } catch(Exception ex) {
        	throw ex;
        } finally {
        	if(document != null) {
        		if(document.isOpen()) {
        			document.close();
        		}
        	}
        	
        	if(writer != null) {
       			writer.close();
        	}
        }
    }
    
    public static void convertWordExcelAPDF(String archivoWordExcel, String archivoPDF, 
    		String extension, String rutaTemp) throws Exception {
    	File wordFile = null; 
    	File target = null;
    	IConverter converter = null;
    	DocumentType documentType = null;
    	
    	wordFile = new File(archivoWordExcel);
    	target = new File(archivoPDF);
    	
		converter = LocalConverter.builder()
                .baseFolder(new File(rutaTemp))
                .workerPool(20, 25, 2, TimeUnit.SECONDS)
                .processTimeout(5, TimeUnit.SECONDS)
                .build();		
    	
		if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_DOC)) {
			documentType = DocumentType.DOC; 
		} else {
			if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_DOCX)) {
				documentType = DocumentType.DOCX; 
			} else {
				if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_XLS)) {
					documentType = DocumentType.XLS; 
				} else {
					if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_XLSX)) {
						documentType = DocumentType.XLSX; 
					}
				}
			}
		}
		
		converter.convert(wordFile).as(documentType)
                 .to(target).as(DocumentType.PDF)
                 .execute();
                 //.prioritizeWith(1000) // optional
                 //.schedule();
    }
    
    public static void convertPPTAPDF(String archivoPPT, String archivoPDF, 
    		String extension, String rutaImageTemp) throws Exception {
    	FileInputStream is = null;
    	SlideShow ppt = null;
		XMLSlideShow pptx = null;
        Dimension pgsize = null;  
        XSLFSlide[] slideXSLF = null;
        BufferedImage bufferImage = null;  
        Graphics2D graphics = null;
        FileOutputStream out = null;
        Document document = null;
        Image image = null;
        int numSlides = 0;
        
        is = new FileInputStream(archivoPPT);
        
        // PPTX
        if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PPTX)) { 
	        pptx = new XMLSlideShow(is);  
	        is.close();  
	        pgsize = pptx.getPageSize();  
	        slideXSLF= pptx.getSlides();
	        
	        numSlides = slideXSLF.length;
	        for (int i = 0; i < numSlides; i++) {              	           
	            bufferImage = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);  
	            graphics = bufferImage.createGraphics();  
	            graphics.setPaint(Color.white);  
	            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));  
	            slideXSLF[i].draw(graphics);  
	              
	            out = new FileOutputStream(rutaImageTemp + (i + 1) + ".jpeg");  
	            javax.imageio.ImageIO.write(bufferImage, "jpeg", out);  
	            out.close();
	        }
        } else {
        	if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PPT)) { 
	        	ppt = new SlideShow(is);  
	            is.close();  
	            pgsize = ppt.getPageSize();  
	            org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
	            
	            numSlides = slide.length;
	            for (int i = 0; i < numSlides; i++) {                 
	                bufferImage = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);  
	                graphics = bufferImage.createGraphics();  
	                graphics.setPaint(Color.white);  
	                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));  
	                slide[i].draw(graphics);  
	                  
	                out = new FileOutputStream(rutaImageTemp + (i + 1) + ".jpeg");  
	                javax.imageio.ImageIO.write(bufferImage, "jpeg", out);  
	                out.close();  
	            }
        	}
        }   
        
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(archivoPDF));  
        document.open();  
            
        for (int i = 1; i <= numSlides; i++) {             
          image = Image.getInstance(rutaImageTemp + i + ".jpeg");  
          image.scalePercent(50);  
          document.add(image);  
        } 
        
        document.close();
    }
    
	public static List<Map<String, Object>> consultarWS(String urlWS) throws Exception {
		List<Map<String, Object>> listaMap = null;
		URL url = null;
		HttpURLConnection httpConnection = null;
		BufferedReader responseBuffer = null;
		String output = null;
		String error = null;
		
		url = new URL(urlWS);
		
		httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Accept", "application/json");
		
		if (httpConnection.getResponseCode() != 200) {
			responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getErrorStream()),"UTF-8"));
			
			while ((output = responseBuffer.readLine()) != null) {
				error = output;
			}
			throw new RuntimeException(error);
		}
		
		responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream()),"UTF-8"));
		
		while ((output = responseBuffer.readLine()) != null) {
			listaMap  = (List<Map<String, Object>>) new JsonSerializer().deserialize(output, java.util.Map.class);
		}
		
		httpConnection.disconnect();
		
		return listaMap;
	}
	
	public static String escapeHTML(String s) {
	    StringBuilder out = new StringBuilder(Math.max(16, s.length()));
	    for (int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
	            out.append("&#");
	            out.append((int) c);
	            out.append(';');
	        } else {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}
	
	public static String formatDateString(String fechaInicial) {
		String fechaFinal = "";
		
		if(fechaInicial != null) {
			fechaInicial = fechaInicial.trim();
			if(fechaInicial.equals("-")) {
				fechaFinal = fechaInicial;
			} else {
				fechaFinal = fechaInicial.substring(8, 10) + "-" +
							 fechaInicial.substring(5, 7) + "-" +
							 fechaInicial.substring(0, 4);
			}	
		}
		
		return fechaFinal;
	}
	
	//lestrada 31012017 Inicio
	public static String convertirUnicode(String s){
		if (Utils.isEmpty(s)) return ""; 
		s = s.replace("U0021", "!");
		s = s.replace("U0022", "\"");
		s = s.replace("U0023", "#");
		s = s.replace("U0024", "$");
		s = s.replace("U0025", "%");
		s = s.replace("U0026", "&");
		s = s.replace("U0027", "'");
		s = s.replace("U0028", "(");
		s = s.replace("U0029", ")");
		s = s.replace("U002A", "*");
		s = s.replace("U002B", "+");
		s = s.replace("U002C", ",");
		s = s.replace("U002D", "-");
		s = s.replace("U002E", ".");
		s = s.replace("U002F", "/");
		s = s.replace("U0030", "0");
		s = s.replace("U0031", "1");
		s = s.replace("U0032", "2");
		s = s.replace("U0033", "3");
		s = s.replace("U0034", "4");
		s = s.replace("U0035", "5");
		s = s.replace("U0036", "6");
		s = s.replace("U0037", "7");
		s = s.replace("U0038", "8");
		s = s.replace("U0039", "9");
		s = s.replace("U003A", ":");
		s = s.replace("U003B", ";");
		s = s.replace("U003C", "<");
		s = s.replace("U003D", "=");
		s = s.replace("U003E", ">");
		s = s.replace("U003F", "?");
		s = s.replace("U0040", "@");
		s = s.replace("U0041", "A");
		s = s.replace("U0042", "B");
		s = s.replace("U0043", "C");
		s = s.replace("U0044", "D");
		s = s.replace("U0045", "E");
		s = s.replace("U0046", "F");
		s = s.replace("U0047", "G");
		s = s.replace("U0048", "H");
		s = s.replace("U0049", "I");
		s = s.replace("U004A", "J");
		s = s.replace("U004B", "K");
		s = s.replace("U004C", "L");
		s = s.replace("U004D", "M");
		s = s.replace("U004E", "N");
		s = s.replace("U004F", "O");
		s = s.replace("U0050", "P");
		s = s.replace("U0051", "Q");
		s = s.replace("U0052", "R");
		s = s.replace("U0053", "S");
		s = s.replace("U0054", "T");
		s = s.replace("U0055", "U");
		s = s.replace("U0056", "V");
		s = s.replace("U0057", "W");
		s = s.replace("U0058", "X");
		s = s.replace("U0059", "Y");
		s = s.replace("U005A", "Z");
		s = s.replace("U005B", "[");
		s = s.replace("U005C", "\"");
		s = s.replace("U005D", "]");
		s = s.replace("U005E", "^");
		s = s.replace("U005F", "_");
		s = s.replace("U0060", "`");
		s = s.replace("U0061", "a");
		s = s.replace("U0062", "b");
		s = s.replace("U0063", "c");
		s = s.replace("U0064", "d");
		s = s.replace("U0065", "e");
		s = s.replace("U0066", "f");
		s = s.replace("U0067", "g");
		s = s.replace("U0068", "h");
		s = s.replace("U0069", "i");
		s = s.replace("U006A", "j");
		s = s.replace("U006B", "k");
		s = s.replace("U006C", "l");
		s = s.replace("U006D", "m");
		s = s.replace("U006E", "n");
		s = s.replace("U006F", "o");
		s = s.replace("U0070", "p");
		s = s.replace("U0071", "q");
		s = s.replace("U0072", "r");
		s = s.replace("U0073", "s");
		s = s.replace("U0074", "t");
		s = s.replace("U0075", "u");
		s = s.replace("U0076", "v");
		s = s.replace("U0077", "w");
		s = s.replace("U0078", "x");
		s = s.replace("U0079", "y");
		s = s.replace("U007A", "z");
		s = s.replace("U007B", "{");
		s = s.replace("U007C", "|");
		s = s.replace("U007D", "}");
		s = s.replace("U007E", "~");
		s = s.replace("U00A1", "¡");
		s = s.replace("U00A2", "¢");
		s = s.replace("U00A3", "£");
		s = s.replace("U00A4", "¤");
		s = s.replace("U00A5", "¥");
		s = s.replace("U00A6", "¦");
		s = s.replace("U00A7", "§");
		s = s.replace("U00A8", "¨");
		s = s.replace("U00A9", "©");
		s = s.replace("U00AA", "ª");
		s = s.replace("U00AB", "«");
		s = s.replace("U00AC", "¬");
		s = s.replace("U00AD", " ");
		s = s.replace("U00AE", "®");
		s = s.replace("U00AF", "¯");
		s = s.replace("U00B0", "°");
		s = s.replace("U00B1", "±");
		s = s.replace("U00B2", "²");
		s = s.replace("U00B3", "³");
		s = s.replace("U00B4", "´");
		s = s.replace("U00B5", "µ");
		s = s.replace("U00B6", "¶");
		s = s.replace("U00B7", "·");
		s = s.replace("U00B8", "¸");
		s = s.replace("U00B9", "¹");
		s = s.replace("U00BA", "º");
		s = s.replace("U00BB", "»");
		s = s.replace("U00BC", "¼");
		s = s.replace("U00BD", "½");
		s = s.replace("U00BE", "¾");
		s = s.replace("U00BF", "¿");
		s = s.replace("U00C0", "À");
		s = s.replace("U00C1", "Á");
		s = s.replace("U00C2", "Â");
		s = s.replace("U00C3", "Ã");
		s = s.replace("U00C4", "Ä");
		s = s.replace("U00C5", "Å");
		s = s.replace("U00C6", "Æ");
		s = s.replace("U00C7", "Ç");
		s = s.replace("U00C8", "È");
		s = s.replace("U00C9", "É");
		s = s.replace("U00CA", "Ê");
		s = s.replace("U00CB", "Ë");
		s = s.replace("U00CC", "Ì");
		s = s.replace("U00CD", "Í");
		s = s.replace("U00CE", "Î");
		s = s.replace("U00CF", "Ï");
		s = s.replace("U00D0", "Ð");
		s = s.replace("U00D1", "Ñ");
		s = s.replace("U00D2", "Ò");
		s = s.replace("U00D3", "Ó");
		s = s.replace("U00D4", "Ô");
		s = s.replace("U00D5", "Õ");
		s = s.replace("U00D6", "Ö");
		s = s.replace("U00D7", "×");
		s = s.replace("U00D8", "Ø");
		s = s.replace("U00D9", "Ù");
		s = s.replace("U00DA", "Ú");
		s = s.replace("U00DB", "Û");
		s = s.replace("U00DC", "Ü");
		s = s.replace("U00DD", "Ý");
		s = s.replace("U00DE", "Þ");
		s = s.replace("U00DF", "ß");
		s = s.replace("U00E0", "à");
		s = s.replace("U00E1", "á");
		s = s.replace("U00E2", "â");
		s = s.replace("U00E3", "ã");
		s = s.replace("U00E4", "ä");
		s = s.replace("U00E5", "å");
		s = s.replace("U00E6", "æ");
		s = s.replace("U00E7", "ç");
		s = s.replace("U00E8", "è");
		s = s.replace("U00E9", "é");
		s = s.replace("U00EA", "ê");
		s = s.replace("U00EB", "ë");
		s = s.replace("U00EC", "ì");
		s = s.replace("U00ED", "í");
		s = s.replace("U00EE", "î");
		s = s.replace("U00EF", "ï");
		s = s.replace("U00F0", "ð");
		s = s.replace("U00F1", "ñ");
		s = s.replace("U00F2", "ò");
		s = s.replace("U00F3", "ó");
		s = s.replace("U00F4", "ô");
		s = s.replace("U00F5", "õ");
		s = s.replace("U00F6", "ö");
		s = s.replace("U00F7", "÷");
		s = s.replace("U00F8", "ø");
		s = s.replace("U00F9", "ù");
		s = s.replace("U00FA", "ú");
		s = s.replace("U00FB", "û");
		s = s.replace("U00FC", "ü");
		s = s.replace("U00FD", "ý");
		s = s.replace("U00FE", "þ");
		s = s.replace("U00FF", "ÿ");
		s = s.replace("u0021", "!");
		s = s.replace("u0022", "\"");
		s = s.replace("u0023", "#");
		s = s.replace("u0024", "$");
		s = s.replace("u0025", "%");
		s = s.replace("u0026", "&");
		s = s.replace("u0027", "'");
		s = s.replace("u0028", "(");
		s = s.replace("u0029", ")");
		s = s.replace("u002a", "*");
		s = s.replace("u002b", "+");
		s = s.replace("u002c", ",");
		s = s.replace("u002d", "-");
		s = s.replace("u002e", ".");
		s = s.replace("u002f", "/");
		s = s.replace("u0030", "0");
		s = s.replace("u0031", "1");
		s = s.replace("u0032", "2");
		s = s.replace("u0033", "3");
		s = s.replace("u0034", "4");
		s = s.replace("u0035", "5");
		s = s.replace("u0036", "6");
		s = s.replace("u0037", "7");
		s = s.replace("u0038", "8");
		s = s.replace("u0039", "9");
		s = s.replace("u003a", ":");
		s = s.replace("u003b", ";");
		s = s.replace("u003c", "<");
		s = s.replace("u003d", "=");
		s = s.replace("u003e", ">");
		s = s.replace("u003f", "?");
		s = s.replace("u0040", "@");
		s = s.replace("u0041", "A");
		s = s.replace("u0042", "B");
		s = s.replace("u0043", "C");
		s = s.replace("u0044", "D");
		s = s.replace("u0045", "E");
		s = s.replace("u0046", "F");
		s = s.replace("u0047", "G");
		s = s.replace("u0048", "H");
		s = s.replace("u0049", "I");
		s = s.replace("u004a", "J");
		s = s.replace("u004b", "K");
		s = s.replace("u004c", "L");
		s = s.replace("u004d", "M");
		s = s.replace("u004e", "N");
		s = s.replace("u004f", "O");
		s = s.replace("u0050", "P");
		s = s.replace("u0051", "Q");
		s = s.replace("u0052", "R");
		s = s.replace("u0053", "S");
		s = s.replace("u0054", "T");
		s = s.replace("u0055", "U");
		s = s.replace("u0056", "V");
		s = s.replace("u0057", "W");
		s = s.replace("u0058", "X");
		s = s.replace("u0059", "Y");
		s = s.replace("u005a", "Z");
		s = s.replace("u005b", "[");
		s = s.replace("u005c", "\"");
		s = s.replace("u005d", "]");
		s = s.replace("u005e", "^");
		s = s.replace("u005f", "_");
		s = s.replace("u0060", "`");
		s = s.replace("u0061", "a");
		s = s.replace("u0062", "b");
		s = s.replace("u0063", "c");
		s = s.replace("u0064", "d");
		s = s.replace("u0065", "e");
		s = s.replace("u0066", "f");
		s = s.replace("u0067", "g");
		s = s.replace("u0068", "h");
		s = s.replace("u0069", "i");
		s = s.replace("u006a", "j");
		s = s.replace("u006b", "k");
		s = s.replace("u006c", "l");
		s = s.replace("u006d", "m");
		s = s.replace("u006e", "n");
		s = s.replace("u006f", "o");
		s = s.replace("u0070", "p");
		s = s.replace("u0071", "q");
		s = s.replace("u0072", "r");
		s = s.replace("u0073", "s");
		s = s.replace("u0074", "t");
		s = s.replace("u0075", "u");
		s = s.replace("u0076", "v");
		s = s.replace("u0077", "w");
		s = s.replace("u0078", "x");
		s = s.replace("u0079", "y");
		s = s.replace("u007a", "z");
		s = s.replace("u007b", "{");
		s = s.replace("u007c", "|");
		s = s.replace("u007d", "}");
		s = s.replace("u007e", "~");
		s = s.replace("u00a1", "¡");
		s = s.replace("u00a2", "¢");
		s = s.replace("u00a3", "£");
		s = s.replace("u00a4", "¤");
		s = s.replace("u00a5", "¥");
		s = s.replace("u00a6", "¦");
		s = s.replace("u00a7", "§");
		s = s.replace("u00a8", "¨");
		s = s.replace("u00a9", "©");
		s = s.replace("u00aa", "ª");
		s = s.replace("u00ab", "«");
		s = s.replace("u00ac", "¬");
		s = s.replace("u00ad", " ");
		s = s.replace("u00ae", "®");
		s = s.replace("u00af", "¯");
		s = s.replace("u00b0", "°");
		s = s.replace("u00b1", "±");
		s = s.replace("u00b2", "²");
		s = s.replace("u00b3", "³");
		s = s.replace("u00b4", "´");
		s = s.replace("u00b5", "µ");
		s = s.replace("u00b6", "¶");
		s = s.replace("u00b7", "·");
		s = s.replace("u00b8", "¸");
		s = s.replace("u00b9", "¹");
		s = s.replace("u00ba", "º");
		s = s.replace("u00bb", "»");
		s = s.replace("u00bc", "¼");
		s = s.replace("u00bd", "½");
		s = s.replace("u00be", "¾");
		s = s.replace("u00bf", "¿");
		s = s.replace("u00c0", "À");
		s = s.replace("u00c1", "Á");
		s = s.replace("u00c2", "Â");
		s = s.replace("u00c3", "Ã");
		s = s.replace("u00c4", "Ä");
		s = s.replace("u00c5", "Å");
		s = s.replace("u00c6", "Æ");
		s = s.replace("u00c7", "Ç");
		s = s.replace("u00c8", "È");
		s = s.replace("u00c9", "É");
		s = s.replace("u00ca", "Ê");
		s = s.replace("u00cb", "Ë");
		s = s.replace("u00cc", "Ì");
		s = s.replace("u00cd", "Í");
		s = s.replace("u00ce", "Î");
		s = s.replace("u00cf", "Ï");
		s = s.replace("u00d0", "Ð");
		s = s.replace("u00d1", "Ñ");
		s = s.replace("u00d2", "Ò");
		s = s.replace("u00d3", "Ó");
		s = s.replace("u00d4", "Ô");
		s = s.replace("u00d5", "Õ");
		s = s.replace("u00d6", "Ö");
		s = s.replace("u00d7", "×");
		s = s.replace("u00d8", "Ø");
		s = s.replace("u00d9", "Ù");
		s = s.replace("u00da", "Ú");
		s = s.replace("u00db", "Û");
		s = s.replace("u00dc", "Ü");
		s = s.replace("u00dd", "Ý");
		s = s.replace("u00de", "Þ");
		s = s.replace("u00df", "ß");
		s = s.replace("u00e0", "à");
		s = s.replace("u00e1", "á");
		s = s.replace("u00e2", "â");
		s = s.replace("u00e3", "ã");
		s = s.replace("u00e4", "ä");
		s = s.replace("u00e5", "å");
		s = s.replace("u00e6", "æ");
		s = s.replace("u00e7", "ç");
		s = s.replace("u00e8", "è");
		s = s.replace("u00e9", "é");
		s = s.replace("u00ea", "ê");
		s = s.replace("u00eb", "ë");
		s = s.replace("u00ec", "ì");
		s = s.replace("u00ed", "í");
		s = s.replace("u00ee", "î");
		s = s.replace("u00ef", "ï");
		s = s.replace("u00f0", "ð");
		s = s.replace("u00f1", "ñ");
		s = s.replace("u00f2", "ò");
		s = s.replace("u00f3", "ó");
		s = s.replace("u00f4", "ô");
		s = s.replace("u00f5", "õ");
		s = s.replace("u00f6", "ö");
		s = s.replace("u00f7", "÷");
		s = s.replace("u00f8", "ø");
		s = s.replace("u00f9", "ù");
		s = s.replace("u00fa", "ú");
		s = s.replace("u00fb", "û");
		s = s.replace("u00fc", "ü");
		s = s.replace("u00fd", "ý");
		s = s.replace("u00fe", "þ");
		s = s.replace("u00ff", "ÿ");
		//caso especial signo grados (°) ("" + (char) 186) :
		s = s.replace("°", "\u00b0");
		s = s.replace("u00b0", "\u00b0");
		s = s.replace("U00B0", "\u00b0");
		//AQ-FSW
		s = s.replace("&lt;", "<");
		s = s.replace("&gt;", ">");
		return s;
	}
	//lestrada 31012017 Fin
	String campoOrden;
	
	public String getCampoOrden() {
		return campoOrden;
	}

	public void setCampoOrden(String campoOrden) {
		this.campoOrden = campoOrden;
	}

	@Override
    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		return ((String) o1.get(campoOrden)).compareTo((String) o2.get(campoOrden));
    }	

	
	
	/**
	 * @author: nchavez
	 * @date :  09/06/2016
	 * @time :  11:38:47
	 */
	public static class ExcelUtil {

		
		public static enum ALIGN{LEFT,CENTER,RIGHT}
		/**
		 * 
		* @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
		* @author: nchavez
		* @date 09/06/2016
		* @time 11:22:06
		* @param title    		  : Titulo del excel
		* @param sheetname		  : Nombre del la Hoja
		* @param columnsProperties: Mapa de objetos ColumOption para definir propiedades de las columnas
		* @param data 		      : Lista de Mapa de Objects con la data
		 */
		public static  HSSFWorkbook buildWorkbookXLS(String title,String sheetname,Map<String,ColumOption> columnsProperties,List<Map<String, Object>> data){
			HSSFWorkbook libro = null;
			//Inicio [gangles 19/08/2016]-Se agrega fecha del reporte  
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			 Date fechaActual = new Date();
			 String fecImpresion = sdf.format(fechaActual);
			//Fin [gangles 19/08/2016]
			try {
				libro = new HSSFWorkbook();
				HSSFSheet hoja = libro.createSheet(sheetname);
				
				HSSFRow fila = hoja.createRow(1);
				HSSFCell tituloCelda = fila.createCell(0);
				tituloCelda.setCellValue(title);
				hoja.addMergedRegion(new CellRangeAddress(1, 1, 0, columnsProperties.size()));
				
				HSSFFont fuente = libro.createFont();
				fuente.setFontHeightInPoints((short) 11);
				fuente.setFontName(HSSFFont.FONT_ARIAL);
				
				HSSFFont fuenteBold = libro.createFont();
				fuenteBold.setFontHeightInPoints((short) 11);
				fuenteBold.setFontName(HSSFFont.FONT_ARIAL);
				fuenteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				
			    HSSFCellStyle estiloCelda = libro.createCellStyle();
			       
			    estiloCelda.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			    estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			    estiloCelda.setFont(fuente);
			    estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    estiloCelda.setBottomBorderColor((short) 8);
			    estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    estiloCelda.setLeftBorderColor((short) 8);
			    estiloCelda.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    estiloCelda.setRightBorderColor((short) 8);
			    estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THIN);
			    estiloCelda.setTopBorderColor((short) 8);
			    estiloCelda.setFillForegroundColor((short) 22);
			    estiloCelda.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	   
				   
			    HSSFCellStyle estiloTitulo = libro.createCellStyle();
			    estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			    estiloTitulo.setFont(fuenteBold);
			    tituloCelda.setCellStyle(estiloTitulo);
				   
				Sheet ssheet = libro.getSheetAt(0);

				fila = hoja.createRow(2);
				//Inicio [jtejada 26/08/2016]-Se agrega ruc del contribuyente
				boolean agregarRuc =  data.get(0).containsKey("agregarRuc") ? Boolean.parseBoolean(Utils.toStr(data.get(0).get("agregarRuc"))) : false;
				if(agregarRuc){
					String numRuc = ValidaConstantes.CADENA_VACIA;
					String desRazonSocial = ValidaConstantes.CADENA_VACIA;
					if(data != null && !data.isEmpty()){
						numRuc = Utils.toStr(data.get(0).get("numRuc"));
						desRazonSocial = Utils.toStr(data.get(0).get("desRazonSocial"));
					}
					HSSFCell celdaRuc = fila.createCell(1);
					celdaRuc.setCellValue("RUC: ");
					ssheet.autoSizeColumn(1);
					
					HSSFCell celdaValRuc = fila.createCell(2);
					celdaValRuc.setCellValue(numRuc + " - " + (desRazonSocial != null ? desRazonSocial.trim() : ValidaConstantes.CADENA_VACIA));
					ssheet.autoSizeColumn(2);
				}
				//Fin [jtejada 26/08/2016]-Se agrega ruc del contribuyente
				
				//Inicio [gangles 19/08/2016]-Se agrega fecha del reporte  
				HSSFCell celdaFecha = fila.createCell(agregarRuc ? 4 : 1);
				celdaFecha.setCellValue("Fecha del Reporte: ");
				ssheet.autoSizeColumn(agregarRuc ? 4 : 1);

				HSSFCell celdaValFecha = fila.createCell(agregarRuc ? 5 : 2);
				celdaValFecha.setCellValue(fecImpresion);
				ssheet.autoSizeColumn(agregarRuc ? 4 : 1);
				//Fin [gangles 19/08/2016]
				
				fila = hoja.createRow(4);
				HSSFCell celda = fila.createCell(0);
				celda.setCellValue("N°");
				ssheet.autoSizeColumn(0);
				celda.setCellStyle(estiloCelda);
		
				int i=1;
				
				for (Entry<String,ColumOption> columnPropertie: columnsProperties.entrySet()) {
					HSSFCell cell= fila.createCell(i);
					cell.setCellValue(String.valueOf(columnPropertie.getValue().getHeaderName()));
					cell.setCellStyle(estiloCelda);
					ssheet.autoSizeColumn(i);
					i++;
				}
			       
			   HSSFRichTextString texto;
			   HSSFFont fuenteContenido = libro.createFont();
			   HSSFCellStyle estiloCeldaContenido = libro.createCellStyle();
			   estiloCeldaContenido.setFont(fuenteContenido);
			
			   HSSFCellStyle estiloRecorrido = libro.createCellStyle();
			   estiloRecorrido.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			
			   estiloRecorrido.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			   estiloRecorrido.setBottomBorderColor((short) 8);
			   estiloRecorrido.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			   estiloRecorrido.setLeftBorderColor((short) 8);
			   estiloRecorrido.setBorderRight(HSSFCellStyle.BORDER_THIN);
			   estiloRecorrido.setRightBorderColor((short) 8);
			   estiloRecorrido.setBorderTop(HSSFCellStyle.BORDER_THIN);
			   estiloRecorrido.setTopBorderColor((short) 8);
			   
			   HSSFCellStyle estiloRecorridoRight = libro.createCellStyle();
			   estiloRecorridoRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			
			   estiloRecorridoRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoRight.setBottomBorderColor((short) 8);
			   estiloRecorridoRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoRight.setLeftBorderColor((short) 8);
			   estiloRecorridoRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoRight.setRightBorderColor((short) 8);
			   estiloRecorridoRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoRight.setTopBorderColor((short) 8);
			   
			   HSSFCellStyle estiloRecorridoCenter = libro.createCellStyle();
			   estiloRecorridoCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			   estiloRecorridoCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoCenter.setBottomBorderColor((short) 8);
			   estiloRecorridoCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoCenter.setLeftBorderColor((short) 8);
			   estiloRecorridoCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoCenter.setRightBorderColor((short) 8);
			   estiloRecorridoCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
			   estiloRecorridoCenter.setTopBorderColor((short) 8);
			   
			   int j=0;
				
			   for (Map<String, Object> map : data) {
				   
				   fila = hoja.createRow(j + 5);
				   
				   celda = fila.createCell(0);
				   texto = new HSSFRichTextString(Utils.toStr(j+1));
				   celda.setCellValue(texto.toString());
				   hoja.autoSizeColumn(0);
				   celda.setCellStyle(estiloRecorridoRight);
				   
				   int k=1;
				   for (Entry<String,ColumOption> columnPropertie: columnsProperties.entrySet()) {
					   HSSFCell cell = fila.createCell(k);
					   ALIGN alignStyle=columnPropertie.getValue().getAlignStyle();
					   
					   //inicio lestrada Inicio Gangles 06/01/2017
					   cell.setCellValue((map.get(columnPropertie.getKey())!=null)? ((columnPropertie.getKey().equals("desProceso")|| columnPropertie.getKey().equals("desTipoExpediente") || columnPropertie.getKey().equals("plazoVigenciaDias") || columnPropertie.getKey().equals("desRazonSocial"))? Utils.convertirUnicode(String.valueOf(map.get(columnPropertie.getKey())).trim()): String.valueOf(map.get(columnPropertie.getKey())).trim()):ValidaConstantes.CADENA_VACIA);
					   //fin lestrada -Inicio Gangles 06/01/2017
					    hoja.autoSizeColumn(k);
					   cell.setCellStyle(alignStyle.equals(ALIGN.LEFT)?
							   estiloRecorrido:alignStyle.equals(ALIGN.CENTER)?estiloRecorridoCenter:estiloRecorridoRight);
					   k++;
				   }
			  	 j++;
			   }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return libro;
		}
		
		/**
		 * @author nchavez
		 * @date :  09/06/2016
		 * @time :  11:38:47
		 */
		public static class ColumOption{

			private String headerName;
			private ALIGN alignStyle;
			private int columnWidth;
			
			public ColumOption() {
				super();
			}
			public ColumOption(String headerName, ALIGN alignStyle) {
				super();
				this.headerName = headerName;
				this.alignStyle = alignStyle;
			}
			public ColumOption(String headerName, ALIGN alignStyle, int columnWidth) {
				super();
				this.headerName = headerName;
				this.alignStyle = alignStyle;
				this.columnWidth = columnWidth;
			}
			
			public ALIGN getAlignStyle() {
				return alignStyle;
			}
			public void setAlignStyle(ALIGN alignStyle) {
				this.alignStyle = alignStyle;
			}
			public String getHeaderName() {
				return headerName;
			}
			public void setHeaderName(String headerName) {
				this.headerName = headerName;
			}
			public int getColumnWidth() {
				return columnWidth;
			}
			public void setColumnWidth(int columnWidth) {
				this.columnWidth = columnWidth;
			}
			
			
		}
		
	}
	
	// Inicio [jquispe 05/07/2016] Agrega el estado del expediente que se obtiene del ultimo seguimiento vacio al modelo.
	public static void agregarEstadoExpedienteModelo(List<T6622SeguimBean> listTrazabilidad, ModelAndView modelo){		
		String descripcionEstado = "";
		
		if(!Utils.isEmpty(listTrazabilidad)){			
			for(int i=listTrazabilidad.size()-1; i>=0 ; i--){
				T6622SeguimBean seguimientoBean = listTrazabilidad.get(i);
				String estado = seguimientoBean.getDesEstado();
				if(!Utils.isEmpty(estado) && !ValidaConstantes.SEPARADOR_GUION.equals(estado)){
					descripcionEstado = estado;
					break;
				}
			}		
		}
		
		modelo.addObject("descripcionEstado",  new JsonSerializer().serialize(descripcionEstado));		
	}
	// Fin [jquispe 05/07/2016]	
	
	// Inicio [jjurado 17/08/2016] Generar Representación Impresa
	public static Map<String, Object> generarRepImpresa(Map<String, Object> mapDatosDocumento, ResponseEntity<byte[]> responseDoc, Map<String, Object> mapUsuarioBean) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - Utils.generarRepImpresa");
		String numRuc = "";
		String numAsign = "";
		PdfReader oPdfReader = null;
		int totalPags = 0;
		Image docImg = null;
		T6625repimpexpvirtBean t6625repimpexpvirtBean = null;
		String correlativo = null;
		Map<String, Object> respuesta = null;

		try {
			if (log.isDebugEnabled()) log.debug("Procesa - Utils.generarRepImpresa");

			// DATOS CONTRIBUYENTE y REP IMPRESA
			correlativo = Utils.toStr(mapDatosDocumento.get("numCorrelativo")); // //crear secuenciador a 7 digitos (SEQ) --------------------- FALTA
			numAsign = "" + Calendar.getInstance().get(Calendar.YEAR) + mapDatosDocumento.get("codTipDoc").toString().substring(0, 3) + correlativo; // - Número de la Representación Impresa FORMATO:
																																					 // ANO + CODTIPDOC__3dig + SEQ_7dig (ej: 2016 006
																																					 // 0000001)
			String fechaGen = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA_HORA).format(new Date()); // - Fecha y hora de generación de la Representación Impresa <<DD/MM/AAAA – HH:MM:SS>>

			// CODIGO QR (3.7cm x 3.7cm a 75DPI)
			StringBuilder sbUrlConsulta = new StringBuilder();
			sbUrlConsulta.append(ValidaConstantes.URL_SUNAT + "ol-ti-itexpvirtual-consvaliriev/ConsValiRiev.htm?action=consultarReprImpresaView");
			sbUrlConsulta.append("&ri=" + numAsign);
			sbUrlConsulta.append("&ruc=" + Utils.toStr(mapDatosDocumento.get("ruc")).trim());

			BitMatrix bitMatrix = new QRCodeWriter().encode(sbUrlConsulta.toString(), BarcodeFormat.QR_CODE, 100, 100);
			String rutaImagen = CatalogoConstantes.CARPETA_TEMPORAL + numAsign + ".png";
			File img = new File(rutaImagen);
			FileOutputStream fos = new FileOutputStream(img);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);
			fos.close();

			// CREAMOS EL DOCUMENTO PRINCIPAL
			Document docPDF = new Document(PageSize.A4, 0, 0, 0, 0);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(docPDF, baos); // solo bytes
			docPDF.open();

			// de acuerdo al tipo de documento generamos la imagen o las paginas del PDF
			byte[] pdfIn = responseDoc.getBody(); // arreglo de bytes PDF
			MediaType contentType = responseDoc.getHeaders().getContentType();

			PdfContentByte cb = writer.getDirectContent();

			// CREAMOS LA MARCA DE AGUA
			Font fontLeyenda = new Font(Font.HELVETICA, 24, Font.ITALIC, Color.GRAY);
			String textLeyenda = "Representación Impresa del Expediente Electrónico  - SUNAT";
			Paragraph marcaAgua = new Paragraph(new Chunk(textLeyenda, fontLeyenda));
			PdfContentByte canvas = writer.getDirectContent();
			PdfTemplate textTemplate = canvas.createTemplate(800, 600);
			ColumnText columnText = new ColumnText(textTemplate);
			columnText.setSimpleColumn(0, 0, 800, 600);
			columnText.addElement(marcaAgua);
			columnText.go();

			// CREAMOS EL TEXTO CON LOS DATOS ADICIONALES
			String[] listaFooter = { ("Número de RUC                                                                  : " + Utils.toStr(mapDatosDocumento.get("ruc")).trim()),
			        ("Número de Representación Impresa                                : " + numAsign), ("Fecha y hora de generación Representación Impresa   : " + fechaGen), };
			/*
			 * String[] listaFooter = {("Número de RUC                                                 : " +"20132094218" ), ("Número de Representación Impresa               : " + numAsign),
			 * ("Fecha y hora de generación Representación Impresa   : " + fechaGen),};
			 */
			com.lowagie.text.List listaVinetas = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED, 10); // parte 1
			String vineta = "\u2022";// viñeta
			listaVinetas.setListSymbol(new Chunk(vineta));
			Font negrita = new Font(Font.HELVETICA, 9, Font.ITALIC, Color.BLACK);
			negrita.setStyle(Font.BOLD);
			for (String item : listaFooter) {
				listaVinetas.add(new ListItem(new Chunk(item, negrita))); // texto
			}

			PdfPTable tablaDatosAdicionales = new PdfPTable(1);
			tablaDatosAdicionales.setTotalWidth(370);
			PdfPCell celdaListaVinetas = new PdfPCell();
			celdaListaVinetas.addElement(listaVinetas);
			celdaListaVinetas.setBorder(Rectangle.NO_BORDER);
			celdaListaVinetas.setPaddingTop(18);
			tablaDatosAdicionales.addCell(celdaListaVinetas); // lista a la derecha

			// CREAMOS EL TEXTO PARA EL PIE DE PAGINA
			StringBuilder sbLeyenda = new StringBuilder();
			sbLeyenda.append("Esta es una representación impresa del documento ");
			sbLeyenda.append("que se encuentra en el Expediente Electrónico ");
			sbLeyenda.append("de la SUNAT, conforme la Resolución de Superintendencia N°084-2016/SUNAT y normas modificatorias. ");
			sbLeyenda.append("Se puede verificar su autenticidad en ");
			sbLeyenda.append("http://www.sunat.gob.pe");

			Font cursivaAzul = new Font(Font.HELVETICA, 8, Font.ITALIC, new Color(43, 145, 175));
			Paragraph leyendaParrafo = new Paragraph(new Chunk(Utils.toStr(sbLeyenda), cursivaAzul));
			leyendaParrafo.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPTable tablaLeyenda = new PdfPTable(1);
			tablaLeyenda.setTotalWidth(370);
			PdfPCell celdaLeyenda = new PdfPCell();
			celdaLeyenda.addElement(leyendaParrafo);
			celdaLeyenda.setBorder(Rectangle.NO_BORDER);
			celdaLeyenda.setPaddingTop(20);
			tablaLeyenda.addCell(celdaLeyenda);
			writer.setStrictImageSequence(true);

			if (Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF)) {

				oPdfReader = new PdfReader(pdfIn);
				totalPags = oPdfReader.getNumberOfPages();
				
				for (int pagina = 1; pagina <= totalPags; pagina++) {
					// agregamos las páginas
					PdfImportedPage page = writer.getImportedPage(oPdfReader, pagina);
					Rectangle pageW = oPdfReader.getPageSize(pagina);
					float tamaño = tamanoPDF(pageW);
					
					docPDF.newPage();
					//Inicio lestrada Correccion bug
					Image oImage = Image.getInstance(rutaImagen);
					cb.addImage(oImage, 150, 0, 0, 150, 400, 35);
					//Fin lestrada Correccion bug
					cb.addTemplate(page, tamaño, 0, 0, tamaño, 55, 155);

					// agregamos la marca de Agua
					ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, marcaAgua, 310, 480, 45f);

					// agregamos el código QR
					//Image oImage = Image.getInstance(rutaImagen);
					// oImage.setAlignment(Image.TOP| Image.TEXTWRAP);
					
					/* oImage.setAbsolutePosition(400, 55);
					oImage.scaleAbsolute(150, 150);
					cb.addImage(oImage);*/

					// Agregamos datos adicionales a la izquierda del código QR
					tablaDatosAdicionales.writeSelectedRows(0, 1, 58, 170, cb);

					// Agregamos pie de pagina
					tablaLeyenda.writeSelectedRows(0, 1, 59, 121, cb);
				}
			}
			// else if(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG)
			// || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG)
			// || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPEG)){
			//
			// docImg = Image.getInstance(pdfIn);
			// docImg.setInterpolation(true);
			// docImg.setAbsolutePosition(0, 690);
			//
			// docPDF.newPage();
			//
			// //agregamos la Imagen importada+
			// PdfPTable tablaImgPrincipal = new PdfPTable(1);
			// tablaImgPrincipal.setTotalWidth(448);
			// PdfPCell celdaImgPrincipal = new PdfPCell();
			// celdaImgPrincipal.addElement(docImg);
			// celdaImgPrincipal.setBorder(Rectangle.NO_BORDER);
			// tablaImgPrincipal.addCell(celdaImgPrincipal);
			// tablaImgPrincipal.writeSelectedRows(0,1,69,800,cb);
			//
			// //agregamos la marca de Agua
			// ColumnText.showTextAligned(cb,Element.ALIGN_CENTER, marcaAgua, 300, 510, 45f);
			//
			// //agregamos el código QR
			// Image oImage = Image.getInstance(rutaImagen);
			// oImage.setAlignment(Image.TOP| Image.TEXTWRAP);
			// oImage.setAbsolutePosition(444,55);
			// cb.addImage(oImage);
			//
			// //Agregamos datos adicionales a la derecha del código QR
			// tablaDatosAdicionales.writeSelectedRows(0,1,58,170,cb);
			//
			// //Agregamos pie de pagina
			// tablaLeyenda.writeSelectedRows(0,1,59,121,cb);
			//
			// }

			docPDF.close();

			if (log.isDebugEnabled()) log.debug("Creando bean t6625...");
			// GUARDAR DETALLE DE CADA REPRESENTACIÓN IMPRESA GENERADA EN UNA ENTIDAD ADICIONAL: (CREAR TABLA, MAPEO, CLASES, ETC) --------------------- FALTA
			t6625repimpexpvirtBean = new T6625repimpexpvirtBean();
			t6625repimpexpvirtBean.setNumRepImp(numAsign);
			t6625repimpexpvirtBean.setNumRuc(Utils.toStr(mapDatosDocumento.get("ruc")));
			t6625repimpexpvirtBean.setNumExpedo(Utils.toStr(mapDatosDocumento.get("numExpedo")));
			t6625repimpexpvirtBean.setCodTipExp(mapDatosDocumento.get("codTipExp").toString());
			t6625repimpexpvirtBean.setCodTipDoc(mapDatosDocumento.get("codTipDoc").toString());
			t6625repimpexpvirtBean.setNumCorDoc(mapDatosDocumento.get("numCorDoc").toString());
			t6625repimpexpvirtBean.setNumDoc(mapDatosDocumento.get("numDoc").toString());
			t6625repimpexpvirtBean.setCodOriDes(mapDatosDocumento.get("codOriDes").toString());
			t6625repimpexpvirtBean.setCodUsu(mapUsuarioBean.get("usuarioRegistro").toString());
			t6625repimpexpvirtBean.setFecDoc(new Date());
			t6625repimpexpvirtBean.setCodUsuRegis(mapUsuarioBean.get("usuarioRegistro").toString());
			t6625repimpexpvirtBean.setFecRegis(new Date());
			t6625repimpexpvirtBean.setCodUsuMod(ValidaConstantes.SEPARADOR_GUION);
			t6625repimpexpvirtBean.setFecMod(Utils.stringToDate(ValidaConstantes.FECHA_VACIA, CatalogoConstantes.INT_FOUR));

			respuesta = new HashMap<String, Object>();
			respuesta.put("t6625repimpexpvirtBean", t6625repimpexpvirtBean);
			respuesta.put("baos", baos);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - Utils.generarRepImpresa");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - Utils.generarRepImpresa");
			NDC.pop();
			NDC.remove();
		}

		return respuesta;
	}
	
	
        // Inicio [jtejada 19/08/2016] Generar Documento de Representación Impresa
	public static T6625repimpexpvirtBean generarDocumentoDeRepImpresa(Map<String,Object> mapDatosDocumento,ResponseEntity<byte[]> responseDoc, Map<String,Object> mapUsuarioBean, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - Utils.generarDocumentoDeRepImpresa");
		
		ModelAndView modelo = null;
		PdfReader oPdfReader = null;
		int totalPags = 0;
		Image docImg = null;
		T6625repimpexpvirtBean t6625repimpexpvirtBean =  null;
		
		try {
			if (log.isDebugEnabled()) log.debug("Procesa - Utils.generarDocumentoDeRepImpresa");

			
		    //CREAMOS EL DOCUMENTO PRINCIPAL
		    Document docPDF = new Document(PageSize.A4, 0, 0, 0, 0);

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        PdfWriter writer = PdfWriter.getInstance(docPDF, baos); //solo bytes
	        docPDF.open();
		    
	        //de acuerdo al tipo de documento generamos la imagen o las paginas del PDF
		    byte[] pdfIn = responseDoc.getBody(); //arreglo de bytes PDF
			MediaType contentType = responseDoc.getHeaders().getContentType();
						
			PdfContentByte cb = writer.getDirectContent();
				
			//CREAMOS LA MARCA DE AGUA
			Font fontLeyenda = new Font(Font.HELVETICA, 27, Font.ITALIC, Color.GRAY); 
			String textLeyenda = "Copia del Documento Original - No válido para trámites";
			Paragraph marcaAgua = new Paragraph(new Chunk(textLeyenda, fontLeyenda));
			PdfContentByte canvas = writer.getDirectContent();
			PdfTemplate textTemplate = canvas.createTemplate(800, 600);
			ColumnText columnText = new ColumnText(textTemplate);
			columnText.setSimpleColumn(0, 0, 800, 600);
			columnText.addElement(marcaAgua);
			columnText.go();
				
			if (Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF)){
				
				oPdfReader = new PdfReader(pdfIn);
				totalPags = oPdfReader.getNumberOfPages();
				
				for (int pagina = 1; pagina <= totalPags; pagina++) {
					//agregamos las páginas
					PdfImportedPage page = writer.getImportedPage(oPdfReader, pagina);
					docPDF.newPage();
					cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
					
					ColumnText.showTextAligned(cb,Element.ALIGN_CENTER, marcaAgua, 310, 400, 45f);
				}
			}/*else if(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG) 
					|| Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG)
					|| Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPEG)){
				
				docImg = Image.getInstance(pdfIn);
				docImg.setInterpolation(true);
				docImg.setAbsolutePosition(0, 690);
				
				docPDF.newPage();
				
				//agregamos la Imagen importada+
				PdfPTable tablaImgPrincipal = new PdfPTable(1);
				tablaImgPrincipal.setTotalWidth(448);
				PdfPCell celdaImgPrincipal = new PdfPCell();
				celdaImgPrincipal.addElement(docImg);
				celdaImgPrincipal.setBorder(Rectangle.NO_BORDER);
				tablaImgPrincipal.addCell(celdaImgPrincipal); 
				tablaImgPrincipal.writeSelectedRows(0,1,69,800,cb);
				
				//agregamos la marca de Agua
				ColumnText.showTextAligned(cb,Element.ALIGN_CENTER, marcaAgua, 300, 510, 45f);
					
			}*/
				
			docPDF.close();
			
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
			
			
			t6625repimpexpvirtBean = new T6625repimpexpvirtBean();
				
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - Utils.generarDocumentoDeRepImpresa");
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - Utils.generarDocumentoDeRepImpresa");
			NDC.pop();
			NDC.remove();
		}
		
		return t6625repimpexpvirtBean;
	}
	//Fin [jtejada 19/08/2016] Generar Documento de Representación Impresa
	
	//Inicio[Luis Estrada] Validar Extension con descripcion ECM 
	public static String ValidarExtensionECM(String descExtension){
		if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_DOC)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_DOC;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_DOCX)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_DOCX;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_XLS)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_XLS;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_XLSX)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_XLSX;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_PPT)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_PPT;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_PPTX)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_PPTX;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_JPG_JPEG)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_JPG;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_TIF_TIFF)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_TIFF;
		}else if(Utils.equiv(descExtension, ValidaConstantes.TIPO_ARCHIVO_ECM_PNG)){
			descExtension=ValidaConstantes.TIPO_ARCHIVO_PNG;
		}
		
	return descExtension;
	}
	//eaguilar 11 AGO
    public void onStartPage(PdfWriter writer, Document document) {
    }

    public int nroPartesHeader = 0;
	public Phrase fraseHeader = null;
	public float posRefXHeader = 0;
	public float posRefYHeader = 0;
	public float anguloRotaAntihorHeader = 0;
	public boolean crearHeader = false;
    
	public int nroPartesFooter = 0;
	public Phrase fraseFooter = null;
	public float posRefXFooter = 0;
	public float posRefYFooter = 0;
	public float anguloRotaAntihorFooter = 0;
	public boolean crearFooter = false;
	
	/**
	 * Al iniciar la pagina, se crea un header compuesto por columnas de texto.
	 * El texto de la columna es un objeto Phrase <b>fraseHeader</b>
	 * Tiene una referencia de posicion X (<b>posRefXHeader</b>) y Y (<b>posRefXHeader</b>),
	 * ademas de una angulo de rotacion antihorario en grados (<b>anguloRotaAntihorHeader</b>).
     * 
     * Utils evento = new Utils();
     * PdfWriter.setPageEvent(evento);
     * 
	 * @author eaguilar
	 * @date : 11/08/2016
	 */
    public void onEndPage(PdfWriter writer, Document document) {
    	if (crearHeader) ColumnText.showTextAligned(writer.getDirectContent(), 
    												Element.ALIGN_CENTER, 
    												fraseHeader, 
    												//posRefXHeader, 
    												//posRefYHeader,
    												(document.right() - document.left()) / 2 + document.leftMargin(),
    								                document.top() + 10,
    												anguloRotaAntihorHeader);
    	if (crearFooter) ColumnText.showTextAligned(writer.getDirectContent(), 
									    			Element.ALIGN_CENTER, 
									    			fraseFooter, 
									    			//posRefXFooter, 
									    			//posRefYFooter,
									    			(document.right() - document.left()) / 2 + document.leftMargin(),
									    			document.bottom() - 10,
									    			anguloRotaAntihorFooter);
    }

    //Inicio [oachahuic][PAS20165E210400270]
	public static Map<String, Object> consultarWSv2(String urlWS) throws Exception {
		Map<String, Object> mapRpta = null;
		URL url = null;
		HttpURLConnection httpConnection = null;
		BufferedReader responseBuffer = null;
		String output = null;
		String error = null;
		
		url = new URL(urlWS);
		
		httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("GET");
		httpConnection.setRequestProperty("Accept", "application/json");
		log.debug("httpConnection.getResponseCode(): " + httpConnection.getResponseCode());
		
		if (httpConnection.getResponseCode() != 200) {
			responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getErrorStream()),"UTF-8"));
			
			while ((output = responseBuffer.readLine()) != null) {
				error = output;
			}
			throw new RuntimeException(error);
		}
		
		responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream()),"UTF-8"));

		while ((output = responseBuffer.readLine()) != null) {
			log.debug("output: " + output);
			mapRpta  = (Map<String, Object>) new JsonSerializer().deserialize(output, java.util.Map.class);
		}
		log.debug("mapRpta: " + mapRpta);
		httpConnection.disconnect();
		
		return mapRpta;
	}
	//Fin [oachahuic][PAS20165E210400270]
	//Inicio [oachahuic][PAS20171U210400030]
	public static float tamanoPDF(Rectangle pageW){
		float tpage ;
		if (pageW.getWidth() > pageW.getHeight()){
			if(pageW.getWidth() > 3340.0 && pageW.getHeight() > 2350.0){
				tpage = 0.07f;
			}else if(pageW.getWidth() > 2350.0 && pageW.getHeight() > 1500.0 ){
				tpage = 0.12f;
			}else if (pageW.getWidth() > 1500.0 && pageW.getHeight() > 1100.0){
				tpage = 0.20f;
			}else if (pageW.getWidth() > 1100.0 && pageW.getHeight() > 834.0 ){
				tpage = 0.35f;
			}else {
				tpage = 0.63f;
			}
		}else{
			if(pageW.getWidth() > 2350.0 && pageW.getHeight() > 3340.0){
				tpage = 0.19f;
			}else if(pageW.getWidth() > 1500.0 && pageW.getHeight() > 2350.0){
				tpage = 0.25f;
			}else if (pageW.getWidth() > 1100.0 && pageW.getHeight() > 1500.0){
				tpage = 0.37f;
			}else if (pageW.getWidth() > 834.0 && pageW.getHeight() > 1100.0){
				tpage = 0.52f;
			}else {
				tpage = 0.80f;
			}
		}
		return tpage;
	}
    
	public static boolean exonerarValidacion(String codTipDoc){
		boolean rpta = false;
		if ("954000".equals(codTipDoc)) {
			rpta = true;
		} else if ("953102".equals(codTipDoc)) {
			rpta = true;
		} else if ("953202".equals(codTipDoc)) {
			rpta = true;
		} else if ("007007".equals(codTipDoc)) {
			rpta = true;
		} else if ("007607".equals(codTipDoc)) {
			rpta = true;
		} else if ("007702".equals(codTipDoc)) {
			rpta = true;
		} else if ("007703".equals(codTipDoc)) {
			rpta = true;
		} else if ("007314".equals(codTipDoc)) {
			rpta = true;
		} else if ("007013".equals(codTipDoc)) {
			rpta = true;
		} else if ("007613".equals(codTipDoc)) {
			rpta = true;
		} else if ("007017".equals(codTipDoc)) {
			rpta = true;
		} else if ("007611".equals(codTipDoc)) {
			rpta = true;
		} else if ("007612".equals(codTipDoc)) {
			rpta = true;
		} else if ("007039".equals(codTipDoc)) {
			rpta = true;
		} else if ("007065".equals(codTipDoc)) {
			rpta = true;
		} else if ("007020".equals(codTipDoc)) {
			rpta = true;
		} else if ("007015".equals(codTipDoc)) {
			rpta = true;
		} else if ("007081".equals(codTipDoc)) {
			rpta = true;
		} else if ("007016".equals(codTipDoc)) {
			rpta = true;
		} else if ("007082".equals(codTipDoc)) {
			rpta = true;
		} else if ("007014".equals(codTipDoc)) {
			rpta = true;
		} else if ("007012".equals(codTipDoc)) {
			rpta = true;
		} else if ("007011".equals(codTipDoc)) {
			rpta = true;
		} else if ("007339".equals(codTipDoc)) {
			rpta = true;
		} else if ("953104".equals(codTipDoc)) {
			rpta = true;
		} else if ("953204".equals(codTipDoc)) {
			rpta = true;
		} else {
			rpta = false;
		}
		return rpta;
	}
	
	public int getNroPartesHeader() {
		return nroPartesHeader;
	}
	public void setNroPartesHeader(int nroPartesHeader) {
		this.nroPartesHeader = nroPartesHeader;
	}
	public Phrase getFraseHeader() {
		return fraseHeader;
	}
	public void setFraseHeader(Phrase fraseHeader) {
		this.fraseHeader = fraseHeader;
	}
	public float getPosRefXHeader() {
		return posRefXHeader;
	}
	public void setPosRefXHeader(float posRefXHeader) {
		this.posRefXHeader = posRefXHeader;
	}
	public float getPosRefYHeaderer() {
		return posRefYHeader;
	}
	public void setPosRefYHeaderer(float posRefYHeaderer) {
		this.posRefYHeader = posRefYHeaderer;
	}
	public float getAnguloRotaAntihorHeader() {
		return anguloRotaAntihorHeader;
	}
	public void setAnguloRotaAntihorHeader(float anguloRotaAntihorHeader) {
		this.anguloRotaAntihorHeader = anguloRotaAntihorHeader;
	}
	public int getNroPartesFooter() {
		return nroPartesFooter;
	}
	public void setNroPartesFooter(int nroPartesFooter) {
		this.nroPartesFooter = nroPartesFooter;
	}
	public Phrase getFraseFooter() {
		return fraseFooter;
	}
	public void setFraseFooter(Phrase fraseFooter) {
		this.fraseFooter = fraseFooter;
	}
	public float getPosRefXFooter() {
		return posRefXFooter;
	}
	public void setPosRefXFooter(float posRefXFooter) {
		this.posRefXFooter = posRefXFooter;
	}
	public float getPosRefYFooter() {
		return posRefYFooter;
	}
	public void setPosRefYFooter(float posRefYFooter) {
		this.posRefYFooter = posRefYFooter;
	}
	public float getAnguloRotaAntihorFooter() {
		return anguloRotaAntihorFooter;
	}
	public void setAnguloRotaAntihorFooter(float anguloRotaAntihorFooter) {
		this.anguloRotaAntihorFooter = anguloRotaAntihorFooter;
	}

	public boolean isCrearHeader() {
		return crearHeader;
	}

	public void setCrearHeader(boolean crearHeader) {
		this.crearHeader = crearHeader;
	}

	public boolean isCrearFooter() {
		return crearFooter;
	}

	public void setCrearFooter(boolean crearFooter) {
		this.crearFooter = crearFooter;
	}
	//LLRB
	public static Map<String, Object> generarRepImpresaArchivo(Map<String, Object> mapDatosDocumento, ResponseEntity<byte[]> responseDoc, Map<String, Object> mapUsuarioBean) throws Exception {

		if (log.isDebugEnabled()) log.debug("Inicio - Utils.generarRepImpresaArchivo");
		String numRuc = "";
		String numAsign = "";
		PdfReader oPdfReader = null;
		int totalPags = 0;
		Image docImg = null;
		T6625repimpexpvirtBean t6625repimpexpvirtBean = null;
		String correlativo = null;
		Map<String, Object> respuesta = null;

		try {
			if (log.isDebugEnabled()) log.debug("Procesa - Utils.generarRepImpresaArchivo");

			// DATOS CONTRIBUYENTE y REP IMPRESA
			correlativo = Utils.toStr(mapDatosDocumento.get("numCorrelativo")); // //crear secuenciador a 7 digitos (SEQ) --------------------- FALTA
			numAsign = "" + Calendar.getInstance().get(Calendar.YEAR) + mapDatosDocumento.get("codTipDoc").toString().substring(0, 3) + correlativo; // - Número de la Representación Impresa FORMATO:
																																					 // ANO + CODTIPDOC__3dig + SEQ_7dig (ej: 2016 006
																																					 // 0000001)
			String fechaGen = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA_HORA).format(new Date()); // - Fecha y hora de generación de la Representación Impresa <<DD/MM/AAAA – HH:MM:SS>>

			// CODIGO QR (3.7cm x 3.7cm a 75DPI)
			StringBuilder sbUrlConsulta = new StringBuilder();
			sbUrlConsulta.append(ValidaConstantes.URL_SUNAT + "ol-ti-itexpvirtual-consvaliriev/ConsValiRiev.htm?action=consultarReprImpresaView");
			sbUrlConsulta.append("&ri=" + numAsign);
			sbUrlConsulta.append("&ruc=" + Utils.toStr(mapDatosDocumento.get("ruc")).trim());

			BitMatrix bitMatrix = new QRCodeWriter().encode(sbUrlConsulta.toString(), BarcodeFormat.QR_CODE, 100, 100);
			String rutaImagen = CatalogoConstantes.CARPETA_TEMPORAL + numAsign + ".png";
			File img = new File(rutaImagen);
			FileOutputStream fos = new FileOutputStream(img);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);
			fos.close();

			// CREAMOS EL DOCUMENTO PRINCIPAL
			Document docPDF = new Document(PageSize.A4, 0, 0, 0, 0);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(docPDF, baos); // solo bytes
			docPDF.open();

			// de acuerdo al tipo de documento generamos la imagen o las paginas del PDF
			byte[] pdfIn = responseDoc.getBody(); // arreglo de bytes PDF
			MediaType contentType = responseDoc.getHeaders().getContentType();

			PdfContentByte cb = writer.getDirectContent();

			// CREAMOS LA MARCA DE AGUA
			Font fontLeyenda = new Font(Font.HELVETICA, 24, Font.ITALIC, Color.GRAY);
			String textLeyenda = "Representación Impresa del Expediente Electrónico  - SUNAT";
			Paragraph marcaAgua = new Paragraph(new Chunk(textLeyenda, fontLeyenda));
			PdfContentByte canvas = writer.getDirectContent();
			PdfTemplate textTemplate = canvas.createTemplate(800, 600);
			ColumnText columnText = new ColumnText(textTemplate);
			columnText.setSimpleColumn(0, 0, 800, 600);
			columnText.addElement(marcaAgua);
			columnText.go();

			// CREAMOS EL TEXTO CON LOS DATOS ADICIONALES
			String[] listaFooter = { ("Número de RUC                                                                  : " + Utils.toStr(mapDatosDocumento.get("ruc")).trim()),
			        ("Número de Representación Impresa                                : " + numAsign), ("Fecha y hora de generación Representación Impresa   : " + fechaGen), };
			/*
			 * String[] listaFooter = {("Número de RUC                                                 : " +"20132094218" ), ("Número de Representación Impresa               : " + numAsign),
			 * ("Fecha y hora de generación Representación Impresa   : " + fechaGen),};
			 */
			com.lowagie.text.List listaVinetas = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED, 10); // parte 1
			String vineta = "\u2022";// viñeta
			listaVinetas.setListSymbol(new Chunk(vineta));
			Font negrita = new Font(Font.HELVETICA, 9, Font.ITALIC, Color.BLACK);
			negrita.setStyle(Font.BOLD);
			for (String item : listaFooter) {
				listaVinetas.add(new ListItem(new Chunk(item, negrita))); // texto
			}

			PdfPTable tablaDatosAdicionales = new PdfPTable(1);
			tablaDatosAdicionales.setTotalWidth(370);
			PdfPCell celdaListaVinetas = new PdfPCell();
			celdaListaVinetas.addElement(listaVinetas);
			celdaListaVinetas.setBorder(Rectangle.NO_BORDER);
			celdaListaVinetas.setPaddingTop(18);
			tablaDatosAdicionales.addCell(celdaListaVinetas); // lista a la derecha

			// CREAMOS EL TEXTO PARA EL PIE DE PAGINA
			StringBuilder sbLeyenda = new StringBuilder();
			sbLeyenda.append("Esta es una representación impresa del documento ");
			sbLeyenda.append("que se encuentra en el Expediente Electrónico ");
			sbLeyenda.append("de la SUNAT, conforme la Resolución de Superintendencia N°084-2016/SUNAT y normas modificatorias. ");
			sbLeyenda.append("Se puede verificar su autenticidad en ");
			sbLeyenda.append("http://www.sunat.gob.pe");

			Font cursivaAzul = new Font(Font.HELVETICA, 8, Font.ITALIC, new Color(43, 145, 175));
			Paragraph leyendaParrafo = new Paragraph(new Chunk(Utils.toStr(sbLeyenda), cursivaAzul));
			leyendaParrafo.setAlignment(Element.ALIGN_JUSTIFIED);

			PdfPTable tablaLeyenda = new PdfPTable(1);
			tablaLeyenda.setTotalWidth(370);
			PdfPCell celdaLeyenda = new PdfPCell();
			celdaLeyenda.addElement(leyendaParrafo);
			celdaLeyenda.setBorder(Rectangle.NO_BORDER);
			celdaLeyenda.setPaddingTop(20);
			tablaLeyenda.addCell(celdaLeyenda);
			writer.setStrictImageSequence(true);

			if (Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PDF)) {

				oPdfReader = new PdfReader(pdfIn);
				totalPags = oPdfReader.getNumberOfPages();
				
				for (int pagina = 1; pagina <= totalPags; pagina++) {
					// agregamos las páginas
					PdfImportedPage page = writer.getImportedPage(oPdfReader, pagina);
					Rectangle pageW = oPdfReader.getPageSize(pagina);
					float tamaño = tamanoPDF(pageW);
					
					docPDF.newPage();
					//Inicio lestrada Correccion bug
					Image oImage = Image.getInstance(rutaImagen);
					cb.addImage(oImage, 150, 0, 0, 150, 400, 35);
					//Fin lestrada Correccion bug
					cb.addTemplate(page, tamaño, 0, 0, tamaño, 55, 155);

					// agregamos la marca de Agua
					ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, marcaAgua, 310, 480, 45f);

					// agregamos el código QR
					//Image oImage = Image.getInstance(rutaImagen);
					// oImage.setAlignment(Image.TOP| Image.TEXTWRAP);
					
					/* oImage.setAbsolutePosition(400, 55);
					oImage.scaleAbsolute(150, 150);
					cb.addImage(oImage);*/

					// Agregamos datos adicionales a la izquierda del código QR
					tablaDatosAdicionales.writeSelectedRows(0, 1, 58, 170, cb);

					// Agregamos pie de pagina
					tablaLeyenda.writeSelectedRows(0, 1, 59, 121, cb);
				}
			}
			// else if(Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPG)
			// || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_PNG)
			// || Utils.equiv(contentType.getSubtype().toUpperCase(), ValidaConstantes.TIPO_ARCHIVO_JPEG)){
			//
			// docImg = Image.getInstance(pdfIn);
			// docImg.setInterpolation(true);
			// docImg.setAbsolutePosition(0, 690);
			//
			// docPDF.newPage();
			//
			// //agregamos la Imagen importada+
			// PdfPTable tablaImgPrincipal = new PdfPTable(1);
			// tablaImgPrincipal.setTotalWidth(448);
			// PdfPCell celdaImgPrincipal = new PdfPCell();
			// celdaImgPrincipal.addElement(docImg);
			// celdaImgPrincipal.setBorder(Rectangle.NO_BORDER);
			// tablaImgPrincipal.addCell(celdaImgPrincipal);
			// tablaImgPrincipal.writeSelectedRows(0,1,69,800,cb);
			//
			// //agregamos la marca de Agua
			// ColumnText.showTextAligned(cb,Element.ALIGN_CENTER, marcaAgua, 300, 510, 45f);
			//
			// //agregamos el código QR
			// Image oImage = Image.getInstance(rutaImagen);
			// oImage.setAlignment(Image.TOP| Image.TEXTWRAP);
			// oImage.setAbsolutePosition(444,55);
			// cb.addImage(oImage);
			//
			// //Agregamos datos adicionales a la derecha del código QR
			// tablaDatosAdicionales.writeSelectedRows(0,1,58,170,cb);
			//
			// //Agregamos pie de pagina
			// tablaLeyenda.writeSelectedRows(0,1,59,121,cb);
			//
			// }

			docPDF.close();

			if (log.isDebugEnabled()) log.debug("Creando bean t6625...");
			// GUARDAR DETALLE DE CADA REPRESENTACIÓN IMPRESA GENERADA EN UNA ENTIDAD ADICIONAL: (CREAR TABLA, MAPEO, CLASES, ETC) --------------------- FALTA
			t6625repimpexpvirtBean = new T6625repimpexpvirtBean();
			t6625repimpexpvirtBean.setNumRepImp(numAsign);
			t6625repimpexpvirtBean.setNumRuc(Utils.toStr(mapDatosDocumento.get("ruc")));
			t6625repimpexpvirtBean.setCodTipExp(mapDatosDocumento.get("codTipExp").toString());
			t6625repimpexpvirtBean.setCodTipDoc(mapDatosDocumento.get("codTipDoc").toString());
			t6625repimpexpvirtBean.setNumCorDoc(mapDatosDocumento.get("numCorDoc").toString());
			t6625repimpexpvirtBean.setNumDoc(mapDatosDocumento.get("numDoc").toString());
			t6625repimpexpvirtBean.setCodOriDes(mapDatosDocumento.get("codOriDes").toString());
			t6625repimpexpvirtBean.setNumExpedo(mapDatosDocumento.get("numExpedO").toString());
			t6625repimpexpvirtBean.setCodUsu(mapUsuarioBean.get("usuarioRegistro").toString());
			t6625repimpexpvirtBean.setFecDoc(new Date());
			t6625repimpexpvirtBean.setCodUsuRegis(mapUsuarioBean.get("usuarioRegistro").toString());
			t6625repimpexpvirtBean.setFecRegis(new Date());
			t6625repimpexpvirtBean.setCodUsuMod(ValidaConstantes.SEPARADOR_GUION);
			t6625repimpexpvirtBean.setFecMod(Utils.stringToDate(ValidaConstantes.FECHA_VACIA, CatalogoConstantes.INT_FOUR));

			respuesta = new HashMap<String, Object>();
			respuesta.put("t6625repimpexpvirtBean", t6625repimpexpvirtBean);
			respuesta.put("baos", baos);

		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - Utils.generarRepImpresa");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - Utils.generarRepImpresa");
			NDC.pop();
			NDC.remove();
		}

		return respuesta;
	}
	//HHC1 - INI
	public static boolean validarPDFA(byte metadata[]){
        String pdfa = "pdfaid:conformance";
        boolean rpta = false;
        if (metadata != null) {
              String xmlMetadata = new String(metadata);
              int resultado = xmlMetadata.indexOf("xmlns:pdfaid");
              if(resultado != -1) {
                     rpta = true;
              } else {
            	  log.debug("Estándar diferente a PDF/A");
              }
        } else {
        	log.debug("Sin estándar");
        }
        log.debug("PDF/A: " + rpta);
        return rpta;
	}
	public static List<String> unZipFiles(String fileName, String destination){		
		List<String> listFileNames = new ArrayList<String>();
		File filedest = new File(destination);
		int BUFFER_SIZE = 2048;
		try{			
		if (!filedest.exists()) {
			filedest.mkdirs();
		}
		// Create a ZipInputStream to read the zip file
		BufferedOutputStream dest = null;
		FileInputStream fis = new FileInputStream(fileName);
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		// Loop over all of the entries in the zip file
		int count;
		byte data[] = new byte[BUFFER_SIZE];
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			String entryName = prepareEntryName(":", entry.getName());
			prepareFileDirectories(destination, entryName);
			String destFN = destination + entryName;
			// Write the file to the file system
			FileOutputStream fos = new FileOutputStream(destFN);
			dest = new BufferedOutputStream(fos, BUFFER_SIZE);
			while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
				dest.write(data, 0, count);
			}
			
			listFileNames.add(prepareEntryName("/", destFN.replace(destination, "")));				
			dest.flush();
			dest.close();
		}
		zis.close();
		}catch(Exception ex){ex.printStackTrace();}
		return listFileNames;
			
	}
	private static String prepareEntryName(String Character,  String entryName) {
		int index = entryName.indexOf(Character); //If it's a Windows environment
		if (index != -1)
			entryName = entryName.substring(index + 1, entryName.length());
		return entryName;
	}
	
	private static void prepareFileDirectories(String destination, String entryName) {
		File f = new File(destination + entryName);
		if (!f.getParentFile().exists())
			f.getParentFile().mkdir();
		f = null;
	}
	public static byte[] loadFile(File file) throws IOException {
	       InputStream is = new FileInputStream(file);

	        long length = file.length();
	        if (length > Integer.MAX_VALUE) {
	            // File is too large
	        }
	        byte[] bytes = new byte[(int)length];
	        
	        int offset = 0;
	        int numRead = 0;
	        while (offset < bytes.length
	               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	            offset += numRead;
	        }

	        if (offset < bytes.length) {
	            throw new IOException("Could not completely read file "+file.getName());
	        }

	        is.close();
	        return bytes;
	    }
	
	public static String dateUtilToStringOnlyDDMMYYYY(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH) + 1;
		String mesStr="";
		int dia = cal.get(Calendar.DATE);
		String diaStr="";
		if(mes<10){
			mesStr = "0"+mes;
		}else{
			mesStr=""+ mes;
		}
		if(dia<10){
			diaStr = "0"+dia;
		}else{
			diaStr = ""+dia;
		} 
		String formatedDate = diaStr  + (mesStr) +  cal.get(Calendar.YEAR);
		return formatedDate;
	}

	//HHC1 - FIN
}