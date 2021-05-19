package universidad; //Así terminó llamandose mi paquete. Pueden editarlo para usar las otras clases.
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//Todos ésos imports están en uso.
public class Universidad {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
        Por ahora, es necesario instanciar objetos en el main.
        A la hora de crear la aplicación gráfica, éso se corregirá.
        */
        Materia lab2 = new Materia (219, "Laboratorio II", 2);
        System.out.println (lab2);
        nuevaMateria (lab2);
    }
    /*
    Creo que aquí podrían ir todos los métodos estáticos para inscribir y borrar alumnos de las materias.
    */
    public static DateTimeFormatter globalDTF = DateTimeFormatter.ofPattern ("M/d/yyyy"); //Dejé así el formateador porque así suelo escribir las fechas (i.e. hoy es 5/19/2021).
    //No tengo problemas al cambiar el orden. Pero recomendaría omitir los ceros innecesarios ("M" en lugar de "MM").
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    
    public static void conectar () {
        try {
            Class.forName ("org.mariadb.jdbc.Driver").getDeclaredConstructor ().newInstance ();
            conn = DriverManager.getConnection ("jdbc:mysql://localhost/Universidad", "root", "");
            String sqlQuery = "SELECT * FROM Universidad.Alumno";
            ps = conn.prepareStatement (sqlQuery);
            ps.execute ();
            rs = ps.executeQuery();
            System.out.println ("Conexión exitosa. \n Listado de estudiantes:");
            
            if (!rs.next()) {
                System.out.println("No hay más registros.");
            }
            else do {
                System.out.println("#" + rs.getInt("Legajo")+", "+rs.getString("Nombre") + ", nacido el " +rs.getDate ("FechaNacimiento") +";");
            } while (rs.next());
        } catch (SQLException ex) { //NetBeans me sugirió múltiples cláusulas catch.
            System.err.println ("Excepción SQL encontrada: " + ex.getMessage()); //Usemos más el System.err.println.
        } catch (ClassNotFoundException ex) {
            System.err.println ("Clase no encontrada: " + ex.getMessage());
        } catch (NoSuchMethodException ex) {
            System.err.println ("Método no encontrado: " + ex.getMessage());
        } catch (SecurityException ex) {
            System.err.println ("Excepción de seguridad encontrada: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println ("Excepción de instanciación encontrada: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println ("Acceso ilegal: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println ("Argumento ilegal: " + ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.err.println ("Excepción de objetivo de invocamiento encontrada: " + ex.getMessage());
        }
        //Éste es el mismo método, sólo que para mostrar las materias junto con los estudiantes.
        try {
            String sqlQuery = "SELECT * FROM Universidad.Materia";
            ps = conn.prepareStatement (sqlQuery);
            ps.execute ();
            rs = ps.executeQuery();
            System.out.println ("Listado de materias:");
            
            if (!rs.next()) {
                System.out.println("No hay más registros.");
            }
            else do {
                System.out.println("#" + rs.getInt("ID_Materia")+", "+rs.getString("Nombre") + ", año " +rs.getInt ("Año") +";");
            } while (rs.next());
        } catch (SQLException ex) {
            System.err.println ("Excepción SQL encontrada: " + ex.getMessage());
        }
    }
    //Más voids estáticos para trabajar con la base de datos. También podríamos hacer que retornen un int diferente por cada catch y 0 cuando funciona bien.
    public static void inscribir (int alumno, int materia) {
        try {
            Class.forName ("org.mariadb.jdbc.Driver").getDeclaredConstructor ().newInstance ();
            conn = DriverManager.getConnection ("jdbc:mysql://localhost/Universidad", "root", "");
            String sqlQuery = "INSERT INTO Universidad.Inscripción (Legajo, ID_Materia, FechaInscripción) VALUES (?, ?, ?)";
            ps = conn.prepareStatement (sqlQuery);
            ps.setInt (1, alumno);
            ps.setInt (2, materia);
            ps.setDate (3, Date.valueOf(LocalDate.now())); //Una sobrecarga con la fecha cambiará éso.
            ps.execute ();
            rs = ps.executeQuery();
            System.out.println ("Conexión exitosa.");
            System.out.println("Alumno #" + rs.getInt ("Legajo") + " inscrito a la materia #" + rs.getInt ("ID_Materia") + " exitosamente");
        } catch (SQLException ex) {
            System.err.println ("Excepción SQL encontrada: " + ex.getMessage()); //Usemos más el System.err.println.
        } catch (ClassNotFoundException ex) {
            System.err.println ("Clase no encontrada: " + ex.getMessage());
        } catch (NoSuchMethodException ex) {
            System.err.println ("Método no encontrado: " + ex.getMessage());
        } catch (SecurityException ex) {
            System.err.println ("Excepción de seguridad encontrada: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println ("Excepción de instanciación encontrada: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println ("Acceso ilegal: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println ("Argumento ilegal: " + ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.err.println ("Excepción de objetivo de invocamiento encontrada: " + ex.getMessage());
        }
    }
    
    public static void nuevoAlumno (Alumno unAlumno) { //Hay que colocar el constructor de Alumno aquí, pero lo sobrecargué mucho.
        try {
            Class.forName ("org.mariadb.jdbc.Driver").getDeclaredConstructor ().newInstance ();
            conn = DriverManager.getConnection ("jdbc:mysql://localhost/Universidad", "root", ""); //"localhost" puede reemplazarse por "127.0.0.1" ó "::1".
            String sqlQuery = "INSERT INTO Universidad.Alumno (Nombre, Legajo, Aprobado, FechaNacimiento, FechaInscripción) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement (sqlQuery);
            ps.setString (1, unAlumno.getNombre());
            ps.setInt (2, unAlumno.getID());
            ps.setInt (3, unAlumno.getEstado());
            ps.setDate (4, Date.valueOf(unAlumno.getFechaNacimiento()));
            ps.setDate (5, Date.valueOf(LocalDate.now ()));
            ps.execute ();
            rs = ps.executeQuery();
            System.out.println ("Conexión exitosa.");
            System.out.println("Bienvenido " + rs.getString("Nombre") + ", legajo #" + rs.getInt ("Legajo"));
        } catch (SQLException ex) {
            System.err.println ("Excepción SQL encontrada: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println ("Clase no encontrada: " + ex.getMessage());
        } catch (NoSuchMethodException ex) {
            System.err.println ("Método no encontrado: " + ex.getMessage());
        } catch (SecurityException ex) {
            System.err.println ("Excepción de seguridad encontrada: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println ("Excepción de instanciación encontrada: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println ("Acceso ilegal: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println ("Argumento ilegal: " + ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.err.println ("Excepción de objetivo de invocamiento encontrada: " + ex.getMessage());
        }
    }
    
    public static void nuevaMateria (Materia unaMateria) {
        try {
            Class.forName ("org.mariadb.jdbc.Driver").getDeclaredConstructor ().newInstance ();
            conn = DriverManager.getConnection ("jdbc:mysql://localhost/Universidad", "root", "");
            String sqlQuery = "INSERT INTO Universidad.Materia (ID_Materia, Nombre, Año) VALUES (?, ?, ?)";
            ps = conn.prepareStatement (sqlQuery);
            ps.setInt (1, unaMateria.getID());
            ps.setString (2, unaMateria.getNombre());
            ps.setInt (3, unaMateria.getAño());
            ps.execute ();
            rs = ps.executeQuery();
            System.out.println ("Conexión exitosa.");
            System.out.println("Creada nueva materia #" + rs.getInt("ID_Materia") + ", año" + rs.getInt ("Año"));
        } catch (SQLException ex) {
            System.err.println ("Excepción SQL encontrada: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println ("Clase no encontrada: " + ex.getMessage());
        } catch (NoSuchMethodException ex) {
            System.err.println ("Método no encontrado: " + ex.getMessage());
        } catch (SecurityException ex) {
            System.err.println ("Excepción de seguridad encontrada: " + ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println ("Excepción de instanciación encontrada: " + ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println ("Acceso ilegal: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println ("Argumento ilegal: " + ex.getMessage());
        } catch (InvocationTargetException ex) {
            System.err.println ("Excepción de objetivo de invocamiento encontrada: " + ex.getMessage());
        }
    }
}
// [Suspiro] 181 líneas, y éstos son métodos que van a invocarse desde la aplicación gráfica... y sobrecargué demasiado el constructor de Alumno. Opiniones?
//En realidad sólo escribí manualmente unas 40 líneas, pero bueno, el poder de CTRL-C y CTRL-V.