import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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

    public void inicializar() {
        Nodo actual = p;
        while (actual != null) {
            Nodo siguiente = actual.sig;
            actual.sig = null;
            actual.ant = null;
            actual.info = null;
            actual = siguiente;
        }
        p = null;
        f = null;
        u1 = null;
    }

    public boolean eliminar(LocalDate v, int prio) {
        if (p == null) {
            return false;
        }

        // PRIORIDAD 1
        if (prio == 1) {
            if (u1 == null) {
                return false;
            }
            Nodo aBorrar = p;

            if (p == u1) {

                p = p.sig;
                if (p != null) {
                    p.ant = null;
                } else {
                    f = null;
                }
                u1 = null;
            } else {

                p = p.sig;
                p.ant = null;
            }

            aBorrar.sig = null;
            aBorrar.ant = null;
            return true;
        }

        // PRIORIDAD 2
        if (prio == 2) {

            Nodo aBorrar = buscar(v, prio);

            if (aBorrar == null) {
                return false;
            }


            if (aBorrar == p && aBorrar == f) {
                p = null;
                f = null;
            } else if (aBorrar == p) {
                p = p.sig;
                p.ant = null;
            } else if (aBorrar == f) {
                f = f.ant;
                f.sig = null;
            } else {

                aBorrar.ant.sig = aBorrar.sig;
                aBorrar.sig.ant = aBorrar.ant;
            }

            aBorrar.sig = null;
            aBorrar.ant = null;
            return true;
        }
        return false;
    }
    public void mostrarLista(String res) {

        if (p == null) {
            System.out.println("Lista vacía");
            return;
        }
        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Nodo t = res.equalsIgnoreCase("si") ? p : f;
        while (t != null) {
            System.out.println(t.info.format(formato));
            t = res.equalsIgnoreCase("si") ? t.sig : t.ant;
        }
    }
    public boolean modificar(LocalDate fechaVieja, LocalDate fechaNueva, int prio) {
        Nodo n = buscar(fechaVieja, prio);
        if (n == null) {
            System.out.println("No se pudo localizar la fecha");
            return false;
        }
        // Validar rango
        if (fechaNueva.isBefore(min) || fechaNueva.isAfter(max)) {
            System.out.println("La nueva fecha está fuera de rango");
            return false;
        }
        if (prio == 1) {
            // Conserva la posición
            n.info = fechaNueva;
        } else {
            // Mantiene el orden
            eliminar(fechaVieja, 2);
            insertar(fechaNueva, 2);
        }
        System.out.println("Fecha modificada correctamente");
        return true;
    }
    }
