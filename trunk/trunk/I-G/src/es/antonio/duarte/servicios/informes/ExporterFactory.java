package es.antonio.duarte.servicios.informes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

/**
 * Clase Factoria Singleton que proporciona el exporter para la generacion en
 * el formato que necesitemos en ese momento.
 * @author Antonio Duarte
 */
public final class ExporterFactory {

    /**
     * Logger.
     */
    private static final Log logger =
        LogFactory.getLog(ExporterFactory.class);

    /**
     * Instancia unica.
     */
    private static ExporterFactory instance = null;

    /**
     * Tipo del exporter.
     */
    private ExporterType type;

    /**
     * Constructor. Pone por defecto el exporter a PDF.
     */
    private ExporterFactory() {
        // tipo por defecto
        this.type = ExporterType.PDF;
    }

    /**
     * Crea el ExporterFactory.
     * @return instancia unica.
     */
    public static ExporterFactory getExporterFactory() {
        synchronized (ExporterFactory.class) {
            if (instance == null) {
                instance = new ExporterFactory();
            }
        }
        return instance;
    }

    /**
     * Crea el exporter que queremos usar.
     * @return Un exporter especifico.
     */
    public JRAbstractExporter getExporter() {
        JRAbstractExporter exporter;

        if (ExporterType.PDF.equals(type)) {
            exporter = new JRPdfExporter();
        } else if (ExporterType.XML.equals(type)) {
            exporter = new JRXmlExporter();
        } else if (ExporterType.HTML.equals(type)) {
            exporter = new JRHtmlExporter();
        } else if (ExporterType.POI_XLS.equals(type)) {  // uso de POI
            exporter = new JRXlsExporter();
        } else if (ExporterType.JXL_XLS.equals(type)) {  // uso de jxl
            exporter = new JExcelApiExporter();
        } else if (ExporterType.CSV.equals(type)) {
            exporter = new JRCsvExporter();
        } else if (ExporterType.TXT.equals(type)) {
            exporter = new JRTextExporter();
        } else if (ExporterType.RTF.equals(type)) {
            exporter = new JRRtfExporter();
        } else if (ExporterType.ODT.equals(type)) {
            exporter = new JROdtExporter();
        } else {
            exporter = new JRPdfExporter();
        }
        logger.debug("Generado exporter de tipo " + type.toString());
        return exporter;
    }

    /**
     * Obtiene el tipo de exporter que tiene configurado para generar.
     * @return tipo del exporter.
     */
    public ExporterType getType() {
        return type;
    }

    /**
     * Modifica el tipo de exporter que queremos.
     * @param newType tipo para el exporter.
     */
    public void setType(final ExporterType newType) {
        this.type = newType;
    }

    /**
     * Modifica el tipo de exporter que queremos.
     * Si el tipo que le pasamos no existe pone por defecto PDF.
     * @param newType tipo para el exporter en formato cadena.
     */
    public void setType(final String newType) {
        try {
            this.type = ExporterType.fromString(newType);
        } catch (IllegalArgumentException e) {
            logger.error(
                "Tipo de exporter incorrecto. Se pone por defecto a PDF", e);
            this.type = ExporterType.PDF;
        }
    }
}
