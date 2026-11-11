import java.time.LocalDate;
import java.util.Scanner;

public class lista {
    Scanner sc = new Scanner(System.in);
    Nodo p = null;
    Nodo f = null;
    Nodo u1 = null; //ultimo nodo de la prioridad 1

    static final LocalDate min = LocalDate.of(1980, 1, 1);
    static final LocalDate max = LocalDate.of(2030, 12, 31);
    static final LocalDate divisionNodos = LocalDate.of(2005, 1, 1);


    public Nodo buscar(LocalDate v, int prio) {

        if (prio == 1) {
            Nodo n = p;
            while (n != null && n.prioridad == 1) {
                if (n.info.equals(v))
                    return n;
                n = n.sig;
            }
            return null;
        }

        if (v.isBefore(divisionNodos)) {
            Nodo n = (u1 == null) ? p : u1.sig;
            while (n != null && n.prioridad == 2) {
                if (n.info.equals(v))
                    return n;
                if (n.info.isAfter(v))
                    return null;
                n = n.sig;
            }
        } else {
            Nodo n = f;
            while (n != null && n.prioridad == 2) {
                if (n.info.equals(v))
                    return n;
                if (n.info.isBefore(v))
                    return null;
                n = n.ant;
            }
        }
        return null;
    }
    public void InsertarInicio (LocalDate v, int prio) {
        Nodo q= new Nodo(v,prio);
        q.sig=p;
        if (p==null){
            f=q;
        } else{
            p.ant=q;
        }
        p=q;
    }
    public void InsertarFinal (LocalDate v, int prio) {
        Nodo q= new Nodo(v,prio);
        q.ant=f;
        if (f==null){
            p=q;
        } else {
            f.sig=q;
        }
        f=q;
    }

}
