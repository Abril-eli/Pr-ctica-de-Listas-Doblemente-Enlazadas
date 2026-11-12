import java.time.LocalDate;

public class Nodo {

    LocalDate info;
    int prioridad;
    Nodo sig;
    Nodo ant;

    Nodo(LocalDate v, int prioridad) {
        this.info = v;
        this.prioridad = prioridad;
        this.sig = null;
        this.ant = null;
    }
}

