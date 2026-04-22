public class Main {
    public static void main(String[] args) {
        System.out.println("=== Завдання першого рiвня (варiант 17) ===");
        HashTableLevel1 table1 = new HashTableLevel1(7);
        
        table1.insert(new LineSegment(0, 0, 5, 0));
        table1.insert(new LineSegment(0, 0, 5, 5));
        table1.insert(new LineSegment(0, 0, 0, 5));
        
        table1.printTable();
    }
}