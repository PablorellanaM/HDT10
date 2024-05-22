package uvg.edu.gt;

import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Graph graph = new Graph(100);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("guategrafo.txt"));
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

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Ruta más corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Ingrese la ciudad origen:");
                    String origin = scanner.nextLine();
                    System.out.println("Ingrese la ciudad destino:");
                    String destination = scanner.nextLine();
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
                    int modChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (modChoice == 1) {
                        System.out.println("Ingrese la ciudad origen:");
                        String from = scanner.nextLine();
                        System.out.println("Ingrese la ciudad destino:");
                        String to = scanner.nextLine();
                        graph.removeEdge(from, to);
                    } else if (modChoice == 2) {
                        System.out.println("Ingrese la ciudad origen:");
                        String from = scanner.nextLine();
                        System.out.println("Ingrese la ciudad destino:");
                        String to = scanner.nextLine();
                        System.out.println("Ingrese la distancia:");
                        int dist = scanner.nextInt();
                        graph.addEdge(from, to, dist);
                    }
                    floydWarshall = new FloydWarshall(graph);
                    floydWarshall.runFloydWarshall();
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
