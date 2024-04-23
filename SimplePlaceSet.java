public class SimplePlaceSet {
    private Place[] places;
    private int size;
    private int capacity;

    public SimplePlaceSet() {
        capacity = 4; // Initial capacity
        places = new Place[capacity];
        size = 0;
    }

    private void ensureCapacity() {
        if (size >= capacity) {
            capacity = capacity * 2; // Double the capacity
            Place[] newPlaces = new Place[capacity];
            System.arraycopy(places, 0, newPlaces, 0, size);
            places = newPlaces;
        }
    }

    public void add(Place place) {
        if (!contains(place)) {
            ensureCapacity();
            places[size++] = place;
        }
    }

    public boolean contains(Place place) {
        for (int i = 0; i < size; i++) {
            if (places[i].equals(place)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Place place) {
        for (int i = 0; i < size; i++) {
            if (places[i].equals(place)) {
                places[i] = places[size - 1]; // Replace with last element
                places[size - 1] = null; // Nullify last element
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Place get(int index) {
        if (index < size) {
            return places[index];
        }
        return null;
    }
}
