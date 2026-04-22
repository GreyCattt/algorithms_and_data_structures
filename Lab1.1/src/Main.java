class HexVectorList {
    private String[] array;
    private int size;
    private int tailIndex;

    public HexVectorList(int capacity) {
        array = new String[capacity];
        size = 0;
        tailIndex = -1;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidHex(String value) {
        return value != null && value.matches("^[1-9a-fA-F][0-9a-fA-F]*$");
    }

    public boolean insert(String item) {
        if (isFull()) {
            System.out.println("Помилка: Список заповнений!");
            return false;
        }
        if (!isValidHex(item)) {
            System.out.println("Помилка: '" + item + "' не є додатним шiстнадцятковим числом.");
            return false;
        }
        array[size] = item;
        size++;
        tailIndex = size - 1;
        return true;
    }

    public String delete(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Список порожнiй, видалення неможливе.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некоректний iндекс: " + index);
        }

        String deletedItem = array[index];
        
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        tailIndex = size - 1;

        return deletedItem;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Список порожнiй.");
            return;
        }
        System.out.print("Вмiст списку: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- ЗАВДАННЯ ПЕРШОГО РIВНЯ ---");
        HexVectorList list = new HexVectorList(5);
        
        System.out.println("Додавання елементiв...");
        list.insert("1A");
        list.insert("FF");
        list.insert("10");
        list.insert("B2C");
        list.insert("9");
        list.print();
        
        System.out.println("\nВидалення елемента за iндексом 1 (FF)...");
        list.delete(1);
        list.print();

        System.out.println("\nВидалення елемента з хвоста (9)...");
        list.delete(3);
        list.print();
    }
}