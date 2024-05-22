package uvg.edu.gt;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Graph graph = new Graph(100); // Tamaño máximo de nodos, ajustar según necesidad.
        try {
            File file = new File(
                    "C:\\Users\\Pablo\\OneDrive\\Escritorio\\Proyecto ED\\HDT10\\GrafosFloyd\\src\\main\\java\\uvg\\edu\\gt\\guategrafo.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String city1 = parts[0];
                String city2 = parts[1];
                int distance = Integer.parseInt(parts[2]);
                graph.addEdge(city1, city2, distance);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloydWarshall floydWarshall = new FloydWarshall(graph);
        floydWarshall.runFloydWarshall();

        // Imprimir la matriz de adyacencia inicial
        graph.printAdjMatrix();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Ruta más corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea.

            switch (choice) {
                case 1:
                    System.out.println("Ingrese la ciudad origen:");
                    String origin = scanner.nextLine();
                    System.out.println("Ingrese la ciudad destino:");
                    String destination = scanner.nextLine();
                    if (!graph.getNodes().containsKey(origin) || !graph.getNodes().containsKey(destination)) {
                        System.out.println("Una de las ciudades ingresadas no existe en el grafo.");
                        break;
                    }
                    int u = graph.getNodes().get(origin);
                    int v = graph.getNodes().get(destination);
                    System.out.println("Ruta más corta: " + floydWarshall.getPath(u, v));
                    System.out.println("Distancia: " + floydWarshall.getDistances()[u][v]);
                    break;
                case 2:
                    int center = floydWarshall.getGraphCenter();
                    System.out.println("El centro del grafo es: " + center);
                    break;
                case 3:
                    System.out.println("1. Interrupción de tráfico");
                    System.out.println("2. Nueva conexión");
                    int modChoice = 0;
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            modChoice = scanner.nextInt();
                            scanner.nextLine(); // Consumir la nueva línea.
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor ingrese una opción válida (1 o 2).");
                            scanner.next(); // Limpiar la entrada inválida.
                        }
                    }

                    if (modChoice == 1) {
                        System.out.println("Ingrese la ciudad origen:");
                        String from = scanner.nextLine();
                        System.out.println("Ingrese la ciudad destino:");
                        String to = scanner.nextLine();
                        if (!graph.getNodes().containsKey(from) || !graph.getNodes().containsKey(to)) {
                            System.out.println("Una de las ciudades ingresadas no existe en el grafo.");
                            break;
                        }
                        graph.removeEdge(from, to);
                    } else if (modChoice == 2) {
                        System.out.println("Ingrese la ciudad origen:");
                        String from = scanner.nextLine();
                        System.out.println("Ingrese la ciudad destino:");
                        String to = scanner.nextLine();
                        System.out.println("Ingrese la distancia:");
                        int dist = 0;
                        validInput = false;
                        while (!validInput) {
                            try {
                                dist = scanner.nextInt();
                                validInput = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor ingrese una distancia válida.");
                                scanner.next(); // Limpiar la entrada inválida.
                            }
                        }
                        graph.addEdge(from, to, dist);
                    }
                    floydWarshall = new FloydWarshall(graph);
                    floydWarshall.runFloydWarshall();
                    // Imprimir la matriz de adyacencia después de la modificación
                    graph.printAdjMatrix();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}
