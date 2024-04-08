import java.util.HashSet;
import java.util.Set;

public class Map2D {
    private QuadtreeNode root;

    public Map2D(int width, int height) {
        root = new QuadtreeNode(0, 0, width, height);
    }

    public void add(int x, int y, Set<String> services) {
        root.insert(new Place(x, y, services));
    }

    public void search(int x, int y, int width, int height, String serviceType, Set<Place> results) {
        root.search(x, y, width, height, serviceType, results);
    }

    public static void main(String[] args) {
        Map2D map = new Map2D(10000000, 10000000);
        Set<String> services = new HashSet<>();
        services.add("Coffee Shop");

        map.add(500, 500, services);
        map.add(1500, 1500, services);
        
        Set<Place> foundPlaces = new HashSet<>();
        map.search(0, 0, 2000, 2000, "Coffee Shop", foundPlaces);

        for (Place place : foundPlaces) {
            System.out.println("Found " + "Coffee Shop" + " at (" + place.getX() + "," + place.getY() + ")");
        }
    }
}
