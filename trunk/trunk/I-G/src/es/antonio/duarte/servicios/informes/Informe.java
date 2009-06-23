package es.antonio.duarte.servicios.informes;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Clase que representa al informe que queremos generar.
 * Podemos configurar la mayoria de los parametros que necesita un informe.
 * El resto se configuraran en el fichero jasper.
 *
 * @author Antonio Duarte
 */
public class Informe {

    /**
     * Nombre del informe y del fichero resultante.
     */
    private String name;

    /**
     * Path de los ficheros jasper compilados. Directorio relativo a
     * la carpeta classes de la aplicación.
     */
    private String jasperPath;

    /**
     * Path donde los ficheros resultantes seran colocados.
     * Directorio absoluto.
     */
    private String resultPath;

    /**
     * Nombre del fichero jasper master(en el caso de que halla subinformes).
     */
    private String jasperName;

    /**
     * Si hay que pasarle parametros al ficheros jasper.
     */
    private Map<String, Object> params;

    /**
     * Para parametros de configuracion del exporter.
     */
    private Map<Object, Object> exportParams;

    /**
     * Nombre de los Subreports (deben de estar en el jasperPath).
     */
    private List<String> subReportNames;

    /**
     * Nombre del fichero Message Resource. Si se quiere cargar dinamicamente.
     * Debe estar en el path o poner la estructura de paquetes donde este.
     * Esta opcion se recomienda poniendo el paquete resources en este paquete.
     */
    private String msgResourceName;

    /**
     * El Locale del fichero (para el multiidioma).
     */
    private Locale locale;

    /**
     * Si al generar el informe hace falta la virtualizacion.
     */
    private boolean virtualizerNeeded;

    /**
     * Para seleccionar el exporter (PDFExporter por defecto).
     * Tambien determina la extension del fichero final.
     */
    private ExporterType exporterType;

    /**
     * Instancia del generador de informes.
     */
    private GeneradorInformes generadorInformes;

    /**
     * Data Source (JREmptyDataSource por defecto).
     */
    private JRDataSource jrDataSource;

    /**
     * Constructor vacio.
     */
    public Informe() {
        name = "";
        jasperName = "";
        jasperPath = "./";
        resultPath = "./";
        params = new HashMap<String, Object>();
        exportParams = new HashMap<Object, Object>();
        subReportNames = new ArrayList<String>();
        msgResourceName = "";
        locale = Locale.getDefault();
        virtualizerNeeded = false;
        exporterType = ExporterType.PDF;
        jrDataSource = new JREmptyDataSource();
    }

    /**
     * Obtiene el nombre del informe.
     * @return nombre del informe.
     */
    public final String getName() {
        return name;
    }

    /**
     * Modifica el nombre del informe.
     * @param newName nuevo nombre del informe.
     */
    public final void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Modifica el path de los ficheros del informe.
     * @return path de los ficheros del informe.
     */
    public final String getJasperPath() {
        return jasperPath;
    }

    /**
     * Modifica el path de los ficheros del informe.
     * Si se mete cadena vacia se pone como './'.
     * Añade una / al final.
     * @param newJasperPath nuevo path de los ficheros del informe.
     */
    public final void setJasperPath(final String newJasperPath) {
        if (newJasperPath.equals("")) {
            this.jasperPath = "./";
        }
        this.jasperPath = newJasperPath + "/";
    }

    /**
     * Obtiene el path de los ficheros generados.
     * @return path de los ficheros generados.
     */
    public final String getResultPath() {
        return resultPath;
    }

    /**
     * modifica el path de los ficheros generados.
     * Si se mete cadena vacia se pone como './'.
     * Añade una / al final.
     * @param newResultPath nuevo path de los ficheros generados.
     */
    public final void setResultPath(final String newResultPath) {
        if (newResultPath.equals("")) {
            this.resultPath = "./";
        }
        this.resultPath = newResultPath + "/";
    }

    /**
     * Obtiene el nombre del fichero jasper master.
     * @return nombre del fichero jasper master.
     */
    public final String getJasperName() {
        return jasperName;
    }

    /**
     * Modifica el nombre del fichero jasper master.
     * @param newJasperName nuevo nombre del fichero jasper master.
     */
    public final void setJasperName(final String newJasperName) {
        this.jasperName = newJasperName;
    }


    /**
     * Obtiene los parametros para el informe.
     * @return parametros para el informe.
     */
    public final Map<String, Object> getParams() {
        return params;
    }

    /**
     * Modifica los parametros para el informe.
     * @param newParams nuevos parametros para el informe.
     */
    public final void setParams(final Map<String, Object> newParams) {
        this.params = newParams;
    }

    /**
     * Añadir parametros.
     * @param key nombre del parametro.
     * @param value valor del parametro.
     */
    public final void addParam(final String key, final Object value) {
        this.params.put(key, value);
    }


    /**
     * Obtiene los parametros del exporter.
     * @return parametros del exporter.
     */
    public final Map<Object, Object> getExportParams() {
        return exportParams;
    }

    /**
     * Modifica los parametros del exporter.
     * @param newExportParams nuevos parametros del exporter.
     */
    public final void setExportParams(
                            final Map<Object, Object> newExportParams) {
        this.exportParams = newExportParams;
    }

    /**
     * Añadir parametros del exporter.
     * @param key nombre del parametro.
     * @param value valor del parametro.
     */
    public final void addExportParam(final Object key, final Object value) {
        this.exportParams.put(key, value);
    }


    /**
     * Obtiene los nombres de los ficheros de los subReport.
     * @return nombres de los ficheros de los subReport.
     */
    public final List<String> getSubReportNames() {
        return subReportNames;
    }

    /**
     * Modifica los nombres de los ficheros de los subReport.
     * @param newSubReportNames nuevos nombres de los ficheros de los subReport.
     */
    public final void setSubReportNames(
                                    final List<String> newSubReportNames) {
        this.subReportNames = newSubReportNames;
    }

    /**
     * Añadir subReports.
     * @param newSubReportName nuevo nombre de un fichero de un subReport.
     */
    public final void addSubReportName(final String newSubReportName) {
        this.subReportNames.add(newSubReportName);
    }

    /**
     * Obtiene el locale.
     * @return locale.
     */
    public final Locale getLocale() {
        return locale;
    }

    /**
     * Modifica el locale.
     * @param newLocale nuevo locale.
     */
    public final void setLocale(final Locale newLocale) {
        this.locale = newLocale;
    }

    /**
     * Obtiene el tipo del exportador.
     * @return exporterType.
     */
    public final ExporterType getExporterType() {
        return exporterType;
    }

    /**
     * Modifica el tipo del exportador.
     * @param newExporterType nuevo ExporterType.
     */
    public final void setExporterType(final ExporterType newExporterType) {
        this.exporterType = newExporterType;
    }

    /**
     * Modifica el tipo del exportador.
     * @param newExporterType nuevo ExporterType como cadena.
     */
    public final void setExporterType(final String newExporterType) {
        this.exporterType = ExporterType.fromString(newExporterType);
    }


    /**
     * Obtiene el nombre del fichero Message Resource.
     * @return nombre del fichero Message Resource.
     */
    public final String getMsgResourceName() {
        return msgResourceName;
    }

    /**
     * Modifica el nombre del fichero Message Resource.
     * @param newMsgRsrcName nuevo nombre del fichero Message Resource.
     */
    public final void setMsgResourceName(final String newMsgRsrcName) {
        this.msgResourceName = newMsgRsrcName;
    }


    /**
     * Obtiene la bandera que indica si hay que virtualizar.
     * @return si hay que virtualizar.
     */
    public final boolean isVirtualizerNeeded() {
        return virtualizerNeeded;
    }

    /**
     * Modifica la bandera que indica si hay que virtualizar.
     * @param useVirtualizer si se quiere virtualizar.
     */
    public final void setVirtualizerNeeded(final boolean useVirtualizer) {
        this.virtualizerNeeded = useVirtualizer;
    }

    /**
     * Obtiene el generador de informes.
     * @return generador de informes.
     */
    public final GeneradorInformes getGeneradorInformes() {
        return generadorInformes;
    }

    /**
     * Modifica el generador de informes.
     * @param newGenInformes nuevo generador de Informes.
     */
    public final void setGeneradorInformes(
                                    final GeneradorInformes newGenInformes) {
        this.generadorInformes = newGenInformes;
    }

    /**
     * Obtiene el JrDataSource..
     * @return jrDataSource datos con los que generar el informe.
     */
    public final JRDataSource getJrDataSource() {
        return jrDataSource;
    }

    /**
     * Modifica el JrDataSource.
     * @param newJrDataSource nuevos datos con los que generar el informe.
     */
    public final void setJrDataSource(final JRDataSource newJrDataSource) {
        this.jrDataSource = newJrDataSource;
    }

    /**
     * Modifica el JrDataSource. La coleccion que le pasamos debe tener al
     * menos un elemento si no falla al generar el informe.
     * @param collection datos con los que generar el informe desde una
     * coleccion de beans.
     */
    public final void setJrDataSource(final Collection<Object> collection) {
        if (collection == null || collection.isEmpty()) {
            this.jrDataSource = new JREmptyDataSource();
        } else {
            this.jrDataSource = new JRBeanCollectionDataSource(collection);
        }
    }

    /**
     * Genera el informe. El nombre y el tipo determinaran el fichero de salida.
     * Genera el informe en el directorio dado.
     */
    public final void generar() {
        this.generadorInformes.generarInforme(this);
    }

    /**
     * Genera el informe. El nombre y el tipo determinaran el fichero de salida.
     * Genera el informe en un array de bytes.
     * @return informe en forma de array de bytes
     */
    public final byte[] generarEnBytes() {
        return this.generadorInformes.generarInformeEnBytes(this);
    }

    /**
     * Genera el informe en un outputStream.
     * @param outs flujo de salida en el que se escribe el fichero generado.
     * Este parámetro se le pasa ya creado y la responsabilidad de cerrarlo es
     * del que lo ha creado.
     */
    public final void generar(OutputStream outs) {
        this.generadorInformes.generarInforme(this, outs);
    }
}
