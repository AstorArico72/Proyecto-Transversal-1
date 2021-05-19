package TPTransversal;

public class Universidad {

    /**
     * main
     * @param args 
     */
    public static void main(String[] args) {
        
        //Prueba de Alumno nuevo
        Recursos.Alumno nuevo = new Recursos.Alumno();
        nuevo.setIdAlumno(66); nuevo.setNombre("Jhon Smith"); nuevo.setLegajo(10000); nuevo.setFechaNacimiento(java.time.LocalDate.of(1988, java.time.Month.JULY, 1));
        System.out.println(nuevo.toString());
        
        //Prueba de Materia nueva
        
    }
    
}
