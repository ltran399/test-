
public class Place {
    private final int x;
    private final int y;
    private String service;

    public Place(int x, int y, String service) {
        this.x = x;
        this.y = y;
        this.service = service;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return x == place.x && y == place.y;
    }

    @Override
    public int hashCode() {
        // Using a basic hashing formula: 31 * x + y
        return 31 * x + y;
    }
}
