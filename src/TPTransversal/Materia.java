package TPTransversal;
//import java.util.ArrayList;

public class Materia {
    //Borrado el ID porque éste es auto-incremental. igualmente si hay que usar id en la clase
    private String nombreMateria;
    private int año;
    //private ArrayList <Integer> alumnos = new ArrayList <> (); //No sé si ésto sea necesario.

    public Materia (String nombre, int año) {
        this.nombreMateria = nombre;
        this.año = año;
    }
    
    /*public int getID () {
        return idMateria;
    }*/
    
    public String getNombre () {
        return this.nombreMateria;
    }
    
    public int getAño () {
        return this.año;
    }
}
