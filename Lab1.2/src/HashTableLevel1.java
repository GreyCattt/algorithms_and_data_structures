public class HashTableLevel1 {
    private LineSegment[] table;
    private int size;
    private int count;

    public HashTableLevel1(int size) {
        this.size = size;
        this.table = new LineSegment[size];
        this.count = 0;
    }

    private int hashFunction(int key) {
        return key % size;
    }

    public void insert(LineSegment segment) {
        if (count >= size) {
            System.out.println("Помилка: хеш-таблиця заповнена.");
            return;
        }

        int key = segment.getAngleKey();
        int hashIndex = hashFunction(key);
        int startIndex = hashIndex;

        while (table[hashIndex] != null) {
            hashIndex = (hashIndex + 1) % size;
            if (hashIndex == startIndex) {
                System.out.println("Помилка: не знайдено вiльну комiрку.");
                return;
            }
        }

        table[hashIndex] = segment;
        count++;
        System.out.println("Вставлено в iндекс " + hashIndex + " (ключ: " + key + ")");
    }

    public void printTable() {
        System.out.println("\n--- Вмiст хеш-таблицi (вiдкрита адресацiя, метод дiлення) ---");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.println("Index " + i + ": " + table[i].toString());
            } else {
                System.out.println("Index " + i + ": [Empty]");
            }
        }
    }
}
