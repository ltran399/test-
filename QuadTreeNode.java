

class QuadtreeNode {
    private static final int MAX_CAPACITY = 4;
    boolean isDivided;
    int x, y, width, height;
    Set<Place> places;
    QuadtreeNode[] children;

    public QuadtreeNode(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.places = new HashSet<>();
        this.isDivided = false;
    }

    void subdivide() {
        int subWidth = width / 2;
        int subHeight = height / 2;
        children = new QuadtreeNode[4];
        children[0] = new QuadtreeNode(x, y, subWidth, subHeight);
        children[1] = new QuadtreeNode(x + subWidth, y, subWidth, subHeight);
        children[2] = new QuadtreeNode(x, y + subHeight, subWidth, subHeight);
        children[3] = new QuadtreeNode(x + subWidth, y + subHeight, subWidth, subHeight);
        isDivided = true;

        for (Place place : places) {
            int i = (place.getX() >= x + subWidth ? 1 : 0) + (place.getY() >= y + subHeight ? 2 : 0);
            children[i].insert(place);
        }
        places.clear();
    }

    void insert(Place place) {
        if (isDivided) {
            int i = (place.getX() >= x + width / 2 ? 1 : 0) + (place.getY() >= y + height / 2 ? 2 : 0);
            children[i].insert(place);
        } else {
            places.add(place);
            if (places.size() > MAX_CAPACITY) {
                subdivide();
            }
        }
    }

    void search(int searchX, int searchY, int searchWidth, int searchHeight, String serviceType, Set<Place> results) {
        if (!intersects(searchX, searchY, searchWidth, searchHeight)) {
            return;
        }
        if (isDivided) {
            for (QuadtreeNode child : children) {
                child.search(searchX, searchY, searchWidth, searchHeight, serviceType, results);
            }
        } else {
            for (Place place : places) {
                if (place.getX() >= searchX && place.getX() <= searchX + searchWidth &&
                    place.getY() >= searchY && place.getY() <= searchY + searchHeight &&
                    place.getServices().contains(serviceType)) {
                    results.add(place);
                }
            }
        }
    }

    boolean intersects(int searchX, int searchY, int searchWidth, int searchHeight) {
        return !(x + width < searchX || x > searchX + searchWidth ||
                 y + height < searchY || y > searchY + searchHeight);
    }
}
