package universidad;
import java.util.ArrayList;

public class Materia {
    private int idMateria;
    private String nombreMateria;
    private int año;
    private ArrayList <Integer> alumnos = new ArrayList <> (); //No sé si ésto sea necesario.

    public Materia (int id, String nombre, int año) {
        this.idMateria = id;
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