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
                System.out.print("Fecha a modificar: ");
                LocalDate vieja = LocalDate.parse(sc.nextLine());

                System.out.print("Nueva fecha: ");
                LocalDate nueva = LocalDate.parse(sc.nextLine());

                System.out.print("Prioridad (1 o 2): ");
                int prioridad = sc.nextInt();
                sc.nextLine();

                lista.modificar(vieja, nueva, prioridad);
                break;
            case 7:
                break;
        }
    } while (op != 0);
}
