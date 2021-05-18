package TPTransversal;
public class Universidad {

    /**
     * main
     * @param args 
     */
    public static void main(String[] args) {
        
        //Prueba de clases
        Recursos.Alumno a1 = new Recursos.Alumno();
        Recursos.Materia m1 = new Recursos.Materia();
        Recursos.Materia m2 = new Recursos.Materia();
        
        //Prueba de conexión
        BD.Conexion con = new BD.Conexion(); //una unica conexion para todo el trabajo.
        BD.MateriaData materias = new BD.MateriaData(con); //para probar lista de trabajo.
        BD.AlumnoData alumnos = new BD.AlumnoData(con);
        
        //lista de tareas para materias
        //materias
            //.altaMateria(m1)//inserto una materia en la bd, ................agrego la tarea para ejecutar despues
            //.altaMateria(m2)//inserto otra materia en la bd
            //.modificarMateria(22,m2)//modifico una materia con los datos de otra materia en la bd
            //.bajaMateria(21)//cambio el estado de la materia a 0
            //;
                    //Al usar este tipo de anidado de mensajes, 
                    //podriamos en las vistas hacer una especie de cola de trabajo
                    //para ahorrar conexiones a la bd.
        //lista de tareas para alumnos
        alumnos
            .altaAlumno(a1);
        //Mandar a trabajar la conexion
        con.conectar().trabajar();
        System.out.println(
                materias
                .listarMaterias()//devuelvo un ArrayList de todas las materias en la bd, requiere que conexion este conectada a la bd
                .toString()
        );
        con.desconectar();
        //Por cambiar, desarrollar mejor o implementar:
        //
        //
            //Prueba de Vistas
        //Vistas.Principal.iniciar();
    }
    
}
