package BD;
/**
 * Clase Data para la tabla materias de la BD
 * @author Pablo
 */
public class MateriaData {
    
    //configuraciones para tener a mano
    private final String 
        TABLAMATERIAS = "materias",                                             //Nombre de la tabla materias
        CAMPOS[] = {"id_materia","estado","nombre","año","docente","aula"};     //Campos o encabezados de la tabla [0] es id, [1] es estado
    
    //atributos
    private Conexion conexion = null;
    
    //Constructores
    /**
     * 
     * @param conexion 
     */
    public MateriaData(Conexion conexion){
        this.conexion = conexion;
    }
    
    //getters y setters
    /*
    public Recursos.Materia getMateria(){
        return materia;
    }
    */
    
    //Metodos para resolver el TP
    /**
     * Inserta una instrucción SQL a la lista de trabajo de la conexión con
     * los datos de una materia para luego al usar el metodo trabajar() de la conexión
     * insertar estos datos en la tabla Materias de la BD.
     * @param materia con los datos a insertar
     * @return devuelve un objeto tipo MateriaData.
     */
    public MateriaData altaMateria(Recursos.Materia materia){//.................Modificar cuando tengamos la clase Materia
        String 
            query = "INSERT INTO `"+ TABLAMATERIAS +"` VALUES (VALORES);",
            valores = "NULL, NULL,'Nombre de la materia', '1', NULL, NULL"; //reemplazar por los valores de Materia
        query = query.replace("VALORES",valores);
        conexion.agregarInstruccion(query);
        conexion.setMensaje(query +"\nAlta de Materia en Desarrollo...");
        System.out.println(conexion.getMensaje());
        return this;
    }
    /**
     * Inserta una instrucción SQL a la lista de trabajo de la conexión con
     * lo necesario para cambia el estado de una materia a deshabilitada (0).
     * Luego al usar el metodo trabajar() de la conexión se realizarán los cambios a la BD.
     * @param id de la materia
     * @return devuelve un objeto tipo MateriaData
     */
    public MateriaData bajaMateria(int id){
        String query = "UPDATE `"+ TABLAMATERIAS +"` SET `"+ CAMPOS[1] +"` = '0' WHERE `"+ TABLAMATERIAS +"`.`"+ CAMPOS[0] +"` = "+ id +";";
        conexion.agregarInstruccion(query);
        conexion.setMensaje("Modificar estado de la materia de id: "+ id +" a 0.");
        System.out.println(conexion.getMensaje());
        return this;
    }
    /**
     * Inserta una instrucción SQL a la lista de trabajo de la conexión con
     * lo necesario para cambiar los datos del id de la materia.
     * Luego al usar el metodo trabajar() de la conexión se realizarán los cambios a la BD.
     * @param id a modificar
     * @param materia con nuevos datos
     * @return devuelve un objeto tipo MateriaData
     */
    public MateriaData modificarMateria(int id, Recursos.Materia materia){
        String 
            query = "UPDATE `"+ TABLAMATERIAS +"` SET DATOS WHERE `"+ TABLAMATERIAS +"`.`"+ CAMPOS[0] +"` = "+ id +";",
            datos = "..."; //modificar teniendo la clase materia
        //try catch ...
        conexion.setMensaje("Modificar Materia en Desarrollo...");
        System.out.println(conexion.getMensaje());
        return this;
    }
    /**
     * Lista todas las materias de la universidad.
     * Requiere una conexión ya establecida a la bd.
     * No usa la lista de trabajo de la conexión.
     * @return Listado java.util.ArrayList de las materias
     */
    public java.util.List listarMaterias(){ //..................................agregar tipo a la lista cuando tengamos la clase Materia
        String query = "SELECT * FROM `"+ TABLAMATERIAS +"`";
        java.util.List<String> respuesta = new java.util.ArrayList<>();//.......para probar con String, deberia ser lista de Materia
        try{
            java.sql.PreparedStatement declaracion = conexion.getConexion().prepareStatement(query);
            java.sql.ResultSet resultado = declaracion.executeQuery();
            java.util.List<String> temp;//......................................DESDE ACA - modificar al tener la clase Materia
            while(resultado.next()){
                temp = new java.util.ArrayList<>();
                for(int i = 0, j = CAMPOS.length ; i < j; i++){
                    temp.add(resultado.getString(CAMPOS[i]));
                }
                respuesta.add(temp.toString()+"\n");
            }//.................................................................HASTA ACA
            conexion.setMensaje("Listar Materias finalizado.");
        }catch(java.sql.SQLException ex){
            conexion.setMensaje("Error al Listar Materias: "+ ex);
        }
        System.out.println(conexion.getMensaje());
        return respuesta;
    }
    /**
     * Obtiene la Materia que se encuentra en la BD en el indice del id pasado.
     * Requiere una conexión ya establecida a la bd.
     * No usa la lista de trabajo de la conexión.
     * @param id de la materia a devolver.
     * @return una Materia
     */
    public Recursos.Materia getMateria(int id){
        Recursos.Materia m = null; //ver si usar null por errores y luego controlar
        String query = "SELECT * FROM `"+ TABLAMATERIAS +"` WHERE `"+ CAMPOS[0] +"` = "+ id;
        try{
            java.sql.PreparedStatement declaracion = conexion.getConexion().prepareStatement(query);
            java.sql.ResultSet resultado = declaracion.executeQuery();
            if(resultado.next()){
                //datos de la materia
            }
            m = new Recursos.Materia(/*Datos*/);
            conexion.setMensaje("Obtener Materia finalizado.");
        }catch(java.sql.SQLException ex){
            conexion.setMensaje("Error al obtener datos de la materia id: "+ id +".\nError: "+ ex +".");
        }
        return m;
    }
    /**
     * Obtiene el id de la Materia cuyos datos coincidan con los que se encuentran en la BD.
     * Requiere una conexión ya establecida a la bd.
     * No usa la lista de trabajo de la conexión.
     * @param materia
     * @return 
     */
    public int getId(Recursos.Materia materia){
        int id = 0;
        String 
            query = "SELECT `"+ CAMPOS[0] +"` FROM `"+ TABLAMATERIAS +"` WHERE (CONDICION)",
            condicion = "";//modificar al tener la clase Materia
        query.replace("CONDICION", condicion);
        //try catch ...
        //desarrollar
        return id;
    }
}
