
public class Map2D {
    private QuadtreeNode root;

    public Map2D(int width, int height) {
        root = new QuadtreeNode(0, 0, width, height);
    }

    public void add(int x, int y, String service) {
        root.insert(new Place(x, y, service));
    }

    public void search(int x, int y, int width, int height, String serviceType, SimplePlaceSet results) {
        root.search(x, y, width, height, serviceType, results);
    }
    public boolean editPlace(int x, int y, String newService) {
        Place target = new Place(x, y, "");  // Temporary service value for search purposes
        return root.edit(target, newService);
    }

    public boolean removePlace(int x, int y) {
        Place target = new Place(x, y, "");  // Temporary service value for removal purposes
        return root.remove(target);
    }
    public static void main(String[] args) {
        Map2D map = new Map2D(10000000, 10000000);
        map.add(500, 500, "Coffee Shop");
        map.add(1500, 1500, "Gym");
        map.add(200, 500, "Coffee Shop");
        map.add(2000,2000,"Gym");
        map.editPlace(500, 500, "Restaurant");
        map.removePlace(200, 500);
        SimplePlaceSet foundPlaces = new SimplePlaceSet();
        map.search(0, 0, 2000, 2000, "Restaurant", foundPlaces);

        for (int i = 0; i < foundPlaces.size(); i++) {
            Place place = foundPlaces.get(i);
            if (place != null) {
                System.out.println("Found " + place.getService() + " at (" + place.getX() + "," + place.getY() + ")");
            }
        }
    }
}



