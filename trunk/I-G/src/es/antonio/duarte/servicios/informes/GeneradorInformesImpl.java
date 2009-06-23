package es.antonio.duarte.servicios.informes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.antonio.duarte.exception.ServicioException;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Implementcion de la interfaz del generador de informes.
 * Su proposito es generar informes en funcion de la clase Informe.
 *
 * @see Informe
 *
 * @author Rmn Mdl
 *
 */
public class GeneradorInformesImpl implements GeneradorInformes {

    /**
     * Tamaño maximo (en objetos JRVirtualizable) de paginas en cache.
     */
    private static final int MAX_SIZE = 3;

    /**
     * Logger.
     */
    private static final Log logger =
        LogFactory.getLog(GeneradorInformesImpl.class);


    /**
     * Factoria que proporciona el exporter.
     */
    private static final ExporterFactory exporterFactory =
                        ExporterFactory.getExporterFactory();

    /**
     * Genera un informe con nombre y tipo proporcionados por el objeto informe.
     * Genera el informe en el directorio dado.
     * @param informe para generar.
     */
    public final void generarInforme(final Informe informe) {
        FileOutputStream fouts = null;
        String name = informe.getResultPath() + informe.getName() + "."
                        + informe.getExporterType().toString();
        try {
            fouts = new FileOutputStream(name);
            generarInforme(informe, fouts);
            fouts.flush();
        } catch (FileNotFoundException e) {
            logger.error("Error no encontrado el fichero " + name, e);
        } catch (IOException e) {
            logger.error("Error escribiendo los datos en el fichero " + name, e);
        } finally {
            if (fouts != null) {
                try {
                	fouts.close();
                } catch(IOException io) {
                	logger.error("Error cerrando el fichero " + name, io);
                }
            }
        } 
    }

    /**
     * Genera un informe con nombre y tipo proporcionados por el objeto informe.
     * Genera el informe en un array de bytes.
     * @param informe para generar.
     * @return informe en forma de array de bytes
     */
    public final byte[] generarInformeEnBytes(final Informe informe) {
        ByteArrayOutputStream fouts = null;
        try {
            fouts = new ByteArrayOutputStream();
            generarInforme(informe, fouts);
            fouts.flush();
        } catch (FileNotFoundException e) {
            logger.error("Error no encontrado el flujo ", e);
        } catch (IOException e) {
            logger.error("Error escribiendo los datos en el flujo ", e);
        } finally {
            if (fouts != null) {
                try {
                	fouts.close();
                } catch(IOException io) {
                	logger.error("Error en el flujo " + io);
                }
            }
        } 
        return fouts.toByteArray();
    }

    /**
     * Genera informes con nombre y tipo proporcionados por cada uno de los
     * objeto informe que vienen en la coleccion.
     * Genera los informes en el directorio dado.
     *
     * @param informes para generar.
     */
    public final void generarInformes(final Collection<Informe> informes) {
        for (Informe informe : informes) {
            generarInforme(informe);
        }
    }

    /**
     * Genera informes y los vuelca en el flujo de salida que le pasamos como
     * parametro.
     *
     * @param informe para generar
     * @param outs flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de cerrarlo es
     * del que lo ha creado.
     */
    public final void generarInforme(final Informe informe, OutputStream outs) {
        Map<Object, Object> parameters = new HashMap<Object, Object>();
        JRFileVirtualizer fileVirtualizer = null;
        JRAbstractExporter exporter;
        JasperPrint document;

        parameters.putAll(informe.getParams());
        logger.debug("Carga de parametros");

        parameters.put(JRParameter.REPORT_LOCALE, informe.getLocale());
        logger.debug("Carga del Locale " + informe.getLocale());

        if (!informe.getMsgResourceName().equals("")) {
            parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE,
                    ResourceBundle.getBundle(informe.getMsgResourceName(),
                            informe.getLocale()));
            logger.debug("Carga del fichero de mensajes "
                    + informe.getMsgResourceName()
                    + " con idioma: " + informe.getLocale());
        }

        if (informe.isVirtualizerNeeded()) {
            fileVirtualizer = new JRFileVirtualizer(MAX_SIZE, "cacheDir");
            parameters.put(JRParameter.REPORT_VIRTUALIZER, fileVirtualizer);
            logger.debug("Carga del virtualizer");
        }

        try {
            if (!informe.getSubReportNames().isEmpty()) {
                parameters.putAll(incluirSubinformes(informe.getJasperPath(),
                        informe.getSubReportNames()));
            }
        } catch (JRException e) {
            throw new ServicioException("Error al cargar los subinformes", e);
        }

        try {
            String cad = informe.getJasperPath() + informe.getJasperName()
                                                                + ".jasper";
            InputStream urlc =
                    this.getClass().getClassLoader().getResourceAsStream(cad);
            JasperReport masterRep = (JasperReport) JRLoader.loadObject(urlc);
            document = JasperFillManager.fillReport(masterRep, parameters,
                                                    informe.getJrDataSource());
            logger.debug("Rellenado del informe");
        } catch (JRException e) {
            throw new ServicioException("Error al rellenar el informe", e);
        }

        exporterFactory.setType(informe.getExporterType());
        exporter = exporterFactory.getExporter();
        logger.debug("Obtentcion del exporter "
                                        + informe.getExporterType().toString());
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outs);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, document);
        exporter.getParameters().putAll(informe.getExportParams());
        logger.debug("Carga de parametros del exporter");

        try {
            exporter.exportReport();
            logger.debug("Exportacion!!");
        } catch (JRException e) {
            throw new ServicioException("Error el exportar el informe", e);
        } finally {
            if (fileVirtualizer != null && informe.isVirtualizerNeeded()) {
                fileVirtualizer.cleanup();
                logger.debug("Cierre del virtualizer");
            }
        }
    }


    /**
     * Funcion auxiliar para montar los subinformes.
     *
     * @param jasperPath Path de los subreports.
     * @param subReportNames lista de los subReports.
     * @return un map con los subreports.
     * @throws JRException si no se pudo cargar alguno de ellos.
     */
    private Map<String, JasperReport> incluirSubinformes(
            final String jasperPath, final List<String> subReportNames)
                  throws JRException {
        Map<String, JasperReport> masterParams =
                                        new HashMap<String, JasperReport>();
        JasperReport subReport;
        InputStream urlc;
        for (String subReportName : subReportNames) {
            logger.debug("Carga del subinforme " + subReportName);
            String cad = jasperPath + subReportName + ".jasper";
            urlc = Thread.currentThread().getContextClassLoader()
                                                    .getResourceAsStream(cad);
            subReport = (JasperReport) JRLoader.loadObject(urlc);
            masterParams.put(subReportName, subReport);
        }
        return masterParams;
    }

}
