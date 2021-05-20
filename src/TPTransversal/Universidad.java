/**
 * Barrionuevo Pablo: edito lo ultimo subido, para adaptarse al TP
 */

package TPTransversal; //Así terminó llamandose mi paquete (Universidad). Pueden editarlo para usar las otras clases.
//En este caso como todos usamos la misma estructura, no sirve que cada uno tenga estructuras distintas.

/*
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//Todos ésos imports están en uso.
*/
public class Universidad {

    /**
     * Metodo main
     * @param args
     */
    public static void main(String[] args) {
        /*
        Barrionuevo Pablo: Edito para usar Recursos.Materia
        Por ahora, es necesario instanciar objetos en el main.
        A la hora de crear la aplicación gráfica, éso se corregirá.
        */
        Recursos.Materia lab2 = new Recursos.Materia("Laboratorio II", 2, true);
        System.out.println(lab2.toString());
        //nuevaMateria (lab2); -> lo hacemos con conexion establecida
//=======<<<<<<< Upstream, based on origin/master
        
        //Prueba de Alumno nuevo
        Recursos.Alumno nuevo = new Recursos.Alumno();
        nuevo.setNombre("Jhon Smith"); nuevo.setLegajo(10000); nuevo.setFechaNacimiento(java.time.LocalDate.of(1988, java.time.Month.JULY, 1));
        System.out.println(nuevo.toString());
        
        //Prueba de Materia nueva
        Recursos.Materia nuevaM = new Recursos.Materia();
        nuevaM.setNombreMateria("Matemática 1");nuevaM.setAnio(1);
        System.out.println(nuevaM.toString());
        
        //Prueba de Inscripcion
        Recursos.Inscripcion cursada = new Recursos.Inscripcion(nuevo, nuevaM, java.time.LocalDate.now(), 8);
        System.out.println(cursada.toString());
        
        
        //Prueba de Conexion
        BD.Conexion c = new BD.Conexion();
        if(c.getConexion() != null){
            /*Comentar este bloque para realizar las pruebas                    //Pruebas de MateriaData
            BD.MateriaData md = new BD.MateriaData(c);
            
            System.out.println("Guardar Materias");                             //.....Guardar Materia
            int idNuevo = md.guardarMateria(nuevaM);
            md.guardarMateria(lab2);//esta no la voy a modificar
            nuevaM.setIdMateria(idNuevo);
            
            System.out.println("Buscar la Materia con id: "+idNuevo);           //.....Buscar Materia
            System.out.println(md.buscarMateria(idNuevo).toString());
            
            System.out.println("Actualizar una Materia");                       //.....Actualizar Materia
            nuevaM.setAnio(2); nuevaM.setNombreMateria("Matemática 2");
            md.actualizarMateria(nuevaM);
            System.out.println(md.buscarMateria(idNuevo).toString());
            
            System.out.println("Desactivar una Materia");                       //.....Desactivar Materia
            md.desactivarMateria(idNuevo);
            
            //.....Mostrar todas las materias en la tabla materias de la BD     //.....Obtener Materias
            System.out.println("Todas las Materias:\n"
                +md.obtenerMaterias().toString());
            */                                                                  //Final de Pruebas de MateriaData
            
//            /*Comentar este bloque para realizar las pruebas                    //Pruebas de AlumnoData
            
//            */                                                                  //Final de Pruebas de AlumnoData
            
//            /*Comentar este bloque para realizar las pruebas                    //Pruebas de InscripcionData
            
//            */                                                                  //Final de Pruebas de InscripcionData
        }else{
            System.out.println("Error en conexion...");
        }
        //Vistas de la interfaz de usuario
        //Vistas.Principal.iniciar();
//>>>>>>> 64e5c14 Modificaciones segun pruebas
    }
    /*
    Barrionuevo Pablo: edito para usar el paquete BD, que tiene todo esto en conexion, y en las clases data
    
    
    
    Creo que aquí podrían ir todos los métodos estáticos para inscribir y borrar alumnos de las materias.

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
            String sqlQuery = "INSERT INTO Universidad.Alumno (Nombre, Aprobado, FechaNacimiento, FechaInscripción) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement (sqlQuery);
            ps.setString (1, unAlumno.getNombre());
            //Borrada parte del método porque el ID es auto-incremental.
            ps.setInt (2, unAlumno.getEstado());
            ps.setDate (3, Date.valueOf(unAlumno.getFechaNacimiento()));
            ps.setDate (4, Date.valueOf(LocalDate.now ()));//Por defecto la fecha es hoy. Arreglar más tarde.
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
            String sqlQuery = "INSERT INTO Universidad.Materia (Nombre, Año) VALUES (?, ?)"; //Como el ID de la materia es auto-incremental, borré la parte de insertar el ID.
            ps = conn.prepareStatement (sqlQuery);
            ps.setString (1, unaMateria.getNombre());
            ps.setInt (2, unaMateria.getAño());
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
    */
}

// [Suspiro] 180 líneas, y éstos son métodos que van a invocarse desde la aplicación gráfica... y sobrecargué demasiado el constructor de Alumno. Opiniones?
//En realidad sólo escribí manualmente unas 40 líneas, pero bueno, el poder de CTRL-C y CTRL-V.