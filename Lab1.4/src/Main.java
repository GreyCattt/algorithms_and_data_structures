class Student {
    String lastName;
    String firstName;
    String elective;
    double grade;

    public Student(String lastName, String firstName, String elective, double grade) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.elective = elective;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("[Бал: %5.1f] %-15s %-10s | Факультатив: %s", 
                             grade, lastName, firstName, elective);
    }
}

public class Main {
    
    public static void bubbleSortDescending(Student[] arr) {
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                
                if (arr[j].grade < arr[j + 1].grade) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) {
                break;
            }
        }
    }

    public static void printArray(Student[] arr) {
        for (Student s : arr) {
            System.out.println(s.toString());
        }
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {
        Student[] students = {
            new Student("Шевченко", "Марiя", "Веб-дизайн", 88.5),
            new Student("Коваленко", "Олег", "Кiбербезпека", 95.0),
            new Student("Бойко", "Iван", "Бази даних", 74.0),
            new Student("Ткаченко", "Анна", "Штучний iнтелект", 91.5),
            new Student("Мельник", "Петро", "Веб-дизайн", 68.0),
            new Student("Зайцева", "Олена", "Кiбербезпека", 88.5)
        };

        System.out.println("--- ВМIСТ МАСИВУ ДО СОРТУВАННЯ ---");
        printArray(students);

        bubbleSortDescending(students);

        System.out.println("--- ВМIСТ МАСИВУ ПIСЛЯ СОРТУВАННЯ (За спаданням бала) ---");
        printArray(students);
    }
}