/**
 * Barrionuevo Pablo: edito lo ultimo subido, para adaptarse al TP
 */

package TPTransversal; //Así terminó llamandose mi paquete (Universidad). Pueden editarlo para usar las otras clases.
//En este caso como todos usamos la misma estructura, no sirve que cada uno tenga estructuras distintas.
import BD.InscripcionData;
import java.time.LocalDate;



/*
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//Todos ésos imports están en uso.
*/
public class Universidad {
    public static BD.Conexion c = new BD.Conexion();
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
        
//        //Prueba de Alumno nuevo
//        Recursos.Alumno nuevo = new Recursos.Alumno();
//        nuevo.setNombre("Jhon Smith"); nuevo.setLegajo(10001); nuevo.setFechaNacimiento(java.time.LocalDate.of(1988, java.time.Month.JULY, 1));
//        System.out.println(nuevo.toString());
//        
//        //Prueba de Materia nueva
//        Recursos.Materia nuevaM = new Recursos.Materia();
//        nuevaM.setNombreMateria("Matemática 1");
//        nuevaM.setAnio(1);
//        nuevaM.setEstado(true);
//        System.out.println(nuevaM.toString());
//        
//        //Prueba de Inscripcion
//        Recursos.Inscripcion cursada = new Recursos.Inscripcion(nuevo, nuevaM, java.time.LocalDate.now(), 8);
//        System.out.println(cursada.toString());
        
        
        //Prueba de Conexion
        
        if(c.getConexion() != null){
            //Comentar este bloque para realizar las pruebas                    //Pruebas de MateriaData
            BD.MateriaData md = new BD.MateriaData(c);
            
//            System.out.println("Guardar Materias");                             //.....Guardar Materia
//            int idNuevo = md.guardarMateria(nuevaM);
//            md.guardarMateria(lab2);//esta no la voy a modificar
//            nuevaM.setIdMateria(idNuevo);
//            
//            System.out.println("Buscar la Materia con id: "+idNuevo);           //.....Buscar Materia
//            System.out.println(md.buscarMateria(idNuevo).toString());
//            
//            System.out.println("Actualizar una Materia");                       //.....Actualizar Materia
//            nuevaM.setAnio(2); nuevaM.setNombreMateria("Matemática 2");
//            md.actualizarMateria(nuevaM);
//            System.out.println(md.buscarMateria(idNuevo).toString());
//            
//            System.out.println("Desactivar una Materia");                       //.....Desactivar Materia
//            md.desactivarMateria(idNuevo);
//            
            //.....Mostrar todas las materias en la tabla materias de la BD     //.....Obtener Materias
            System.out.println("Todas las Materias:\n"
                +md.obtenerMaterias().toString());
                                                             //Final de Pruebas de MateriaData
            
//            Comentar este bloque para realizar las pruebas                    //Pruebas de AlumnoData
            BD.AlumnoData ad = new BD.AlumnoData(c);
            
//            System.out.println("Guardar Alumno");                               //.....Guardar Alumno
//            int idNuevoAlumno = ad.guardarAlumno(nuevo);
//            nuevo.setIdAlumno(idNuevoAlumno);
            
            System.out.println("Buscar el Alumno con id:(PRUEBA) ");      //.....Buscar Alumno
            System.out.println(ad.buscarAlumno(3).toString());
            
//            System.out.println("Actualizar un Alumno");                         //.....Actualizar Alumno
//            nuevo.setNombre("Martin Perez"); nuevo.setCorreo("mp_privado@micorreo.org");
//            ad.actualizarAlumno(nuevo);
//            System.out.println(ad.buscarAlumno(idNuevoAlumno).toString());
            
//            System.out.println("Desactivar un Alumno");                         //.....Desactivar Alumno
//            ad.desactivarAlumno(idNuevoAlumno);
            
            //.....Mostrar todos los alumnos en la tabla alumno de la BD        //.....Obtener Alumnos
//            System.out.println("Todos los Alumnos:\n"
//                +ad.obtenerAlumnos().toString());
                                                                       //Final de Pruebas de AlumnoData
            
                                                                                //Prueba InscripcionData
            InscripcionData idata=new InscripcionData(c);
            idata.inscribirAlumno(2, md.buscarMateria(1), LocalDate.now(), 10); //inscribir alumno
//            System.out.println("Guardar Inscripcion"); 
//            idata.guardarInscripcion(cursada);                                      //guardar alumno
            
//            System.out.println("Los alumnos en la materia con id 1 son:");       //lista alumnos
//            System.out.println(idata.listarAlumnos(1).toString());
//            
//            System.out.println("Las materias del alumno con id 1 son:");           //listar materias
//            System.out.println(idata.listarMaterias(1).toString());
//            
//            System.out.println ("Materias no cursadas x el alumno 1");
//            System.out.println(idata.obtenerMateriasNoCursadas(1).toString());      //listar materias no cursadas
            
            //delete
//            System.out.println("Desinscribir Alumno"); 
//            idata.desinscribirAlumno(1, 1);                                             //desincribe alumno
                //nota
//                System.out.println("Actualizo la nota: ");
//            idata.actualizarNota(1, 1, 10);                                                                            //Actualiza la nota
//            System.out.println("");
//            
//            //mostrar todo                                                                //Muestra Inscripciones
//            System.out.println("Las inscripciones son: ");
//            System.out.println(idata.obtenerInscripciones().toString());
            
        }else{
            System.out.println("Error en conexion...");
        }
        //Vistas de la interfaz de usuario
        //Vistas.PrincipalVistas.iniciar();

    }
}