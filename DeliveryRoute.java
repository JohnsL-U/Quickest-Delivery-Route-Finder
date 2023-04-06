import java.io.*;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

class Coordinate {
    int x;
    int y;
    boolean visited;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public double distanceTo(Coordinate other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

public class DeliveryRoute {

    public List<Coordinate> readCoordinatesFromFile(String fileName) {
        List<Coordinate> coordinates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int x = (int) (Double.parseDouble(parts[0]) * 10000);
                int y = (int) (Double.parseDouble(parts[1]) * 10000);
                Coordinate coord = new Coordinate(x, y);
                if (parts.length == 3 && "Store".equals(parts[2])) {
                    coordinates.add(coord); // Store is added to the end of the list
                } else {
                    coordinates.add(0, coord); // Houses are added to the beginning of the list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coordinates;
    }

    public List<Coordinate> findShortestRoute(List<Coordinate> houses, Coordinate store) {
        List<Coordinate> route = new ArrayList<>();
        route.add(store);

        while (!houses.isEmpty()) {
            Coordinate lastVisited = route.get(route.size() - 1);
            double minDistance = Double.MAX_VALUE;
            Coordinate nearestHouse = null;

            for (Coordinate house : houses) {
                double distance = lastVisited.distanceTo(house);
                if (!house.visited && distance < minDistance) {
                    minDistance = distance;
                    nearestHouse = house;
                }
            }

            nearestHouse.visited = true;
            route.add(nearestHouse);
            houses.remove(nearestHouse);
        }

        return route;
    }

    public void printRoute(List<Coordinate> route) {
        double totalDistance = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            Coordinate current = route.get(i);
            Coordinate next = route.get(i + 1);
            double distance = current.distanceTo(next);
            totalDistance += distance;
            System.out.printf("From (%.4f, %.4f) to (%.4f, %.4f) - Distance: %.2f%n", current.x / 10000.0, current.y / 10000.0, next.x / 10000.0, next.y / 10000.0, distance);
        }
        System.out.printf("Total distance: %.2f%n", totalDistance);
    }
    public void plotShortestRoute(List<Coordinate> route) {
        XYSeries series = new XYSeries("Delivery Route");

        for (Coordinate coord : route) {
            series.add(coord.x / 10000.0, coord.y / 10000.0);
        }

        // Close the loop by adding the starting point again
        Coordinate startPoint = route.get(0);
        series.add(startPoint.x / 10000.0, startPoint.y / 10000.0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Shortest Delivery Route",
                "X Coordinate",
                "Y Coordinate",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        ChartFrame frame = new ChartFrame("Shortest Delivery Route", chart);
        frame.pack();
        frame.setVisible(true);
    }
}
