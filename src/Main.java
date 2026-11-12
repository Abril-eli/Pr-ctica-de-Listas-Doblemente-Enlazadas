import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

void main() {
    Scanner sc = new Scanner(System.in);
    lista lista = new lista();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int op;
    String texto;
    LocalDate v;
    int prio;
    Nodo r;

    do {
        System.out.println("1. Inicializar / Borrar Lista:");
        System.out.println("2. Mostrar Lista: ");
        System.out.println("3. Buscar:");
        System.out.println("4. Insertar:");
        System.out.println("5. Eliminar:");
        System.out.println("6. Modificar:");
        System.out.println("7. Créditos:");
        System.out.println("0. Salir:");
        op = sc.nextInt();

        switch (op) {
            case 1:
                lista.inicializar();
                System.out.println("Lista inicializada ");
                break;
            case 2:
                sc.nextLine();
                System.out.print("¿Mostrar de inicio a final? (si/no): ");
                String res = sc.nextLine();

                lista.mostrarLista(res);
                break;
            case 3:
                System.out.print("Fecha a buscar (dd/MM/yyyy): ");
                texto = sc.next();
                System.out.print("Prioridad (1 o 2): ");
                prio = sc.nextInt();

                if (prio != 1 && prio != 2) {
                    System.out.println("Prioridad invalida");
                    break;
                }
                v = LocalDate.parse(texto, formato);
                r = lista.buscar(v, prio);
                if (r != null) {
                    System.out.println("Fecha encontrada");
                } else {
                    System.out.println("Fecha no encontrada");
                }
                break;
            case 4:
                System.out.print("Fecha a insertar (dd/MM/yyyy): ");
                texto = sc.next();
                System.out.print("Prioridad (1 o 2): ");
                prio = sc.nextInt();

                if (prio != 1 && prio != 2) {
                    System.out.println("Prioridad invalida");
                    break;
                }
                v = LocalDate.parse(texto, formato);
                if (lista.insertar(v, prio)) {
                    System.out.println("Fecha insertada");
                }
                break;
            case 5:
                System.out.print("Prioridad a eliminar (1 o 2) ");
                prio = sc.nextInt();

                if (prio == 1) {
                    if (lista.eliminar(null, prio)) {
                        System.out.println(" fecha de prioridad 1 eliminada (Cola)");
                    } else {
                        System.out.println("No hay elementos de prioridad 1 para eliminar.");
                    }
                }// PRIORIDAD 2
                else if (prio == 2) {
                    System.out.print("Fecha a eliminar (dd/MM/yyyy): ");
                    texto = sc.next();
                    v = LocalDate.parse(texto, formato);

                    if (lista.eliminar(v, prio)) {
                        System.out.println("fecha de prioridad 2 eliminada correctamente.");
                    } else {
                        System.out.println("fecha no encontrada, no procede la operacion.");
                    }
                } else {
                    System.out.println("prioridad invalida");
                }
                break;
            case 6:
                sc.nextLine();
                System.out.print("Fecha a modificar (dd/MM/yyyy): ");
                LocalDate fechaVieja =
                        LocalDate.parse(sc.nextLine(), formato);
                System.out.print("Nueva fecha (dd/MM/yyyy): ");
                LocalDate fechaNueva =
                        LocalDate.parse(sc.nextLine(), formato);
                System.out.print("Prioridad (1 o 2): ");
                int prioridad = sc.nextInt();
                sc.nextLine();
                lista.modificar(fechaVieja, fechaNueva, prioridad);
                break;
            case 7:
                System.out.println("---CREDITOS---");
                System.out.println("Nombre: Sebastian Chavarria Gomez");
                System.out.println("Materia: Estructura de Datos");
                System.out.println("Matricula: 24170144");
                System.out.println("---------------------------");
                System.out.println("Nombre: Saul Abimael Ochoa Gonzalez");
                System.out.println("Materia: Estructura de Datos");
                System.out.println("Matricula: ");
                System.out.println("---------------------------");
                System.out.println("Nombre: Abril Elizabeth Guillen Vargas");
                System.out.println("Materia: Estructura de Datos");
                System.out.println("Matricula: ");

                break;
        }
    } while (op != 0);
}
