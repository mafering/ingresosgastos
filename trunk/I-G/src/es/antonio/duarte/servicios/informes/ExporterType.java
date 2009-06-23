package es.antonio.duarte.servicios.informes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que proporciona un metodo de definir tipos y a la vez constantes.
 * Permite definirlas a partir de strings.
 * @author Antonio Duarte
 */
public final class ExporterType {
    /**
     * PDF.
     */
    public static final ExporterType PDF = new ExporterType("pdf");

    /**
     * POI Excel.
     */
    public static final ExporterType POI_XLS = new ExporterType("poi.xls");

    /**
     * JXL Excel.
     */
    public static final ExporterType JXL_XLS = new ExporterType("jxl.xls");

    /**
     * XML.
     */
    public static final ExporterType XML = new ExporterType("xml");

    /**
     * Html.
     */
    public static final ExporterType HTML = new ExporterType("html");

    /**
     * Csv.
     */
    public static final ExporterType CSV = new ExporterType("csv");

    /**
     * Txt.
     */
    public static final ExporterType TXT = new ExporterType("txt");

    /**
     * Rtf.
     */
    public static final ExporterType RTF = new ExporterType("rtf");

    /**
     * Odt.
     */
    public static final ExporterType ODT = new ExporterType("odt");
    //<---------------- añadir aqui ----
    // public static final ExporterType *** = new ExporterType("***");

    /**
     * Numero de tipos.
     */
    private static final int SIZE = 9;

    /**
     * Tipo del exporter como cadena.
     */
    private String value;

    /**
     * @param newValue tipo del exporter como cadena
     */
    private ExporterType(final String newValue) {
        this.value = newValue;
    }

    /**
     * Constructor por defecto.
     */
    protected ExporterType() {
        // Empty
    }

    /**
     * Obtiene el valor del objeto.
     * @return tipo del exporter
     */
    public String toString() {
        return value;
    }

    /**
     * Crea una instancia de ExporterType a partir de una cadena.
     * @param value tipo del exporter
     * @return un ExporterType
     */
    public static ExporterType fromString(final String value) {
        ExporterType typeValue = (ExporterType) values.get(value);
        if (typeValue == null) {
            throw new IllegalArgumentException("Valor invalido '" + value
                + "', los valores posibles son: " + literals);
        }
        return typeValue;
    }

    /**
     * Especifica el tipo del objeto.
     * @return El tipo del objeto.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Compara el objeto con otro de la misma clase.
     * @param that elemento a comparar
     * @return >0, 0 o <0 si this es mayor, igual o menor que that
     */
    public int compareTo(final Object that) {
        int res = 0;
        if (this != that) {
            res = this.getValue().compareTo(((ExporterType) that).getValue());
        }
        return  res;
    }

    /**
     * Devuelve una lista inmodificable con los literales posible.
     * @return todos los tipos posibles
     */
    public static List<String> getLiterals() {
        return literals;
    }

    /**
     * Devuelve una lista inmodificable con los nombre de los literales posible.
     * @return todos los tipos posibles
     */
    public static List<String> getNames() {
        return names;
    }

    /**
     * Compara el objeto con otro del mismo tipo.
     * @param object ExporterType a comparar
     * @return true si son iguales o false si no lo son
     */
    public boolean equals(final Object object) {
        return (this == object) || (object instanceof ExporterType
                && ((ExporterType) object).getValue().equals(this.getValue()));
    }

    /**
     * Obtiene el hashcode del objeto en funcion del valor que tiene.
     * @return hashcode del valor
     */
    public int hashCode() {
        return this.getValue().hashCode();
    }

    /**
     * Map con todos los valores.
     */
    private static final Map<String, ExporterType> values =
                            new HashMap<String, ExporterType>(SIZE, 1);

    /**
     * Lista de tipos.
     */
    private static List<String> literals = new ArrayList<String>(SIZE);

    /**
     * Lista de tipos.
     */
    private static List<String> names = new ArrayList<String>(SIZE);

    /**
     * Inicializa los valores.
     */
    static
    {
        values.put(PDF.value, PDF);
        literals.add(PDF.value);
        names.add("PDF");
        values.put(XML.value, XML);
        literals.add(XML.value);
        names.add("XML");
        values.put(POI_XLS.value, POI_XLS);
        literals.add(POI_XLS.value);
        names.add("POI_XLS");
        values.put(JXL_XLS.value, JXL_XLS);
        literals.add(JXL_XLS.value);
        names.add("JXL_XLS");
        values.put(CSV.value, CSV);
        literals.add(CSV.value);
        names.add("CSV");
        values.put(HTML.value, HTML);
        literals.add(HTML.value);
        names.add("HTML");
        values.put(TXT.value, TXT);
        literals.add(TXT.value);
        names.add("TXT");
        values.put(RTF.value, RTF);
        literals.add(RTF.value);
        names.add("RTF");
        values.put(ODT.value, ODT);
        literals.add(ODT.value);
        names.add("ODT");
        // ------------ añadir aqui ---------------
        //    values.put(***.value, ***);
        //    literals.add(***.value);
        //    names.add("***");

        literals = Collections.unmodifiableList(literals);
        names = Collections.unmodifiableList(names);
    }
}
