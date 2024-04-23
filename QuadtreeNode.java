public class QuadtreeNode {
    private static final int MAX_CAPACITY = 4;
    private boolean isDivided;
    private int x, y, width, height;
    private SimplePlaceSet places;
    private QuadtreeNode[] children;

    public QuadtreeNode(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        places = new SimplePlaceSet();
        isDivided = false;
    }

    public void subdivide() {
        int subWidth = width / 2;
        int subHeight = height / 2;
        children = new QuadtreeNode[4];
        children[0] = new QuadtreeNode(x, y, subWidth, subHeight);
        children[1] = new QuadtreeNode(x + subWidth, y, subWidth, subHeight);
        children[2] = new QuadtreeNode(x, y + subHeight, subWidth, subHeight);
        children[3] = new QuadtreeNode(x + subWidth, y + subHeight, subWidth, subHeight);
        isDivided = true;

        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            int idx = (place.getX() >= x + subWidth ? 1 : 0) + (place.getY() >= y + subHeight ? 2 : 0);
            children[idx].insert(place);
        }
        places = new SimplePlaceSet(); // Clear the original set
    }

    public void insert(Place place) {
        if (isDivided) {
            int idx = (place.getX() >= x + width / 2 ? 1 : 0) + (place.getY() >= y + height / 2 ? 2 : 0);
            children[idx].insert(place);
        } else {
            places.add(place);
            if (places.size() > MAX_CAPACITY) {
                subdivide();
            }
        }
    }

    public void search(int x, int y, int width, int height, String serviceType, SimplePlaceSet results) {
        if (!intersects(x, y, width, height)) {
            return;
        }
        if (isDivided) {
            for (int i = 0; i < children.length; i++) {
                children[i].search(x, y, width, height, serviceType, results);
            }
        } else {
            for (int i = 0; i < places.size(); i++) {
                Place place = places.get(i);
                if (place.getX() >= x && place.getX() <= x + width &&
                    place.getY() >= y && place.getY() <= y + height &&
                    place.getService().equals(serviceType)) {
                    results.add(place);
                }
            }
        }
    }

    public boolean edit(Place target, String newService) {
        if (isDivided) {
            int idx = (target.getX() >= x + width / 2 ? 1 : 0) + (target.getY() >= y + height / 2 ? 2 : 0);
            return children[idx].edit(target, newService);
        } else {
            for (int i = 0; i < places.size(); i++) {
                Place place = places.get(i);
                if (place.equals(target)) {
                    place.setService(newService);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(Place target) {
        if (isDivided) {
            int idx = (target.getX() >= x + width / 2 ? 1 : 0) + (target.getY() >= y + height / 2 ? 2 : 0);
            return children[idx].remove(target);
        } else {
            return places.remove(target);
        }
    }

    private boolean intersects(int x, int y, int width, int height) {
        return !(this.x + this.width < x || this.x > x + width ||
                 this.y + this.height < y || this.y > y + height);
    }
}
