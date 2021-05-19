package universidad;
import java.util.ArrayList;

public class Materia {
    //Borrado el ID porque éste es auto-incremental.
    private String nombreMateria;
    private int año;
    private ArrayList <Integer> alumnos = new ArrayList <> (); //No sé si ésto sea necesario.

    public Materia (String nombre, int año) {
        this.nombreMateria = nombre;
        this.año = año;
    }
    
    public int getID () {
        return this.idMateria;
    }
    
    public String getNombre () {
        return this.nombreMateria;
    }
    
    public int getAño () {
        return this.año;
    }
}
