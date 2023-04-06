import java.util.Scanner;
import java.util.List;


public class ClientCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input file name: ");
        String fileName = scanner.nextLine();

        DeliveryRoute service = new DeliveryRoute();
        List<Coordinate> coordinates = service.readCoordinatesFromFile(fileName);
        Coordinate store = coordinates.remove(coordinates.size() - 1);
        List<Coordinate> route = service.findShortestRoute(coordinates, store);
        route.add(store);
        service.printRoute(route);
        service.plotShortestRoute(route);
        scanner.close();
    }
}
