import java.util.HashSet;
import java.util.Set;

public class Place {
    private final int x;
    private final int y;
    private Set<String> services;

    public Place(int x, int y, Set<String> services) {
        this.x = x;
        this.y = y;
        this.services = new HashSet<>(services);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Set<String> getServices() {
        return new HashSet<>(services);
    }

    public void setServices(Set<String> services) {
        this.services = new HashSet<>(services);
    }
}
