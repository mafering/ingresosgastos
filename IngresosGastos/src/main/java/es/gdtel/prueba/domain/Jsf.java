package es.gdtel.prueba.domain;

// default package
// Generated 22-feb-2008 9:48:42 by Hibernate Tools 3.2.0.b11


/**
 * Jsf generated by hbm2java
 */
public class Jsf  implements java.io.Serializable {


     

	private static final long serialVersionUID = -3550102346127041878L;
	private Integer pkIdent;
     private String nombre;
     private String apellidos;
     private String usuario;
     private String clave;

    public Jsf() {
    }

	
    public Jsf(Integer pkIdent, String nombre) {
        this.pkIdent = pkIdent;
        this.nombre = nombre;
    }
    public Jsf(Integer pkIdent, String nombre, String apellidos, String usuario, String clave) {
       this.pkIdent = pkIdent;
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.usuario = usuario;
       this.clave = clave;
    }
   
    public Integer getPkIdent() {
        return this.pkIdent;
    }
    
    public void setPkIdent(Integer pkIdent) {
        this.pkIdent = pkIdent;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }

}

