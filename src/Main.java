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
                break;
            case 2:
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
                break;
            case 6:
                break;
            case 7:
                break;
        }
    } while (op != 0);
}
