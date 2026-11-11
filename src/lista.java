import java.time.LocalDate;

public class lista {
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

    public void InsertarInicio(LocalDate v, int prio) {
        Nodo q = new Nodo(v, prio);
        q.sig = p;
        if (p == null) {
            f = q;
        } else {
            p.ant = q;
        }
        p = q;
    }

    public void InsertarFinal(LocalDate v, int prio) {
        Nodo q = new Nodo(v, prio);
        q.ant = f;
        if (f == null) {
            p = q;
        } else {
            f.sig = q;
        }
        f = q;
    }
    public boolean insertar(LocalDate v, int prio) {

        if (v.isBefore(LocalDate.of(1980, 1, 1)) || v.isAfter(LocalDate.of(2030, 12, 31))) {
            System.out.println("Fecha fuera de rango");
            return false;
        }
        if (prio == 1) {
            if (u1 == null) {
                InsertarInicio(v, 1);
                u1 = p;
            } else if (u1 == f) {
                InsertarFinal(v, 1);
                u1 = f;
            } else {
                Nodo q = new Nodo(v, 1);
                q.ant = u1;
                q.sig = u1.sig;
                u1.sig.ant = q;
                u1.sig = q;
                u1 = q;
            }
            return true;
        }
        Nodo ini2 = (u1 == null) ? p : u1.sig;
        if (ini2 == null) {
            InsertarFinal(v, 2);
            return true;
        }
        if (v.isBefore(divisionNodos)) {
            Nodo r = ini2;
            while (r != null && r.info.isBefore(v)) {
                r = r.sig;
            }
            if (r == null) {
                InsertarFinal(v, 2);
            } else if (r == p) {
                InsertarInicio(v, 2);
            } else {                      // insertar ANTES de r
                Nodo q = new Nodo(v, 2);
                q.sig = r;
                q.ant = r.ant;
                r.ant.sig = q;
                r.ant = q;
            }
        }
        else {
            Nodo r = f;
            while (r != null && r.prioridad == 2 && r.info.isAfter(v)) {
                r = r.ant;
            }
            if (r == null) {
                InsertarInicio(v, 2);
            } else if (r == f) {
                InsertarFinal(v, 2);
            } else {                      // insertar DESPUÉS de r
                Nodo q = new Nodo(v, 2);
                q.ant = r;
                q.sig = r.sig;
                r.sig.ant = q;
                r.sig = q;
            }
        }
        return true;
    }


}
