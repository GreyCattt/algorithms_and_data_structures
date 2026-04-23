class Student {
    String lastName;
    String gender;
    String studyForm;
    boolean hasScholarship;

    public Student(String lastName, String gender, String studyForm, boolean hasScholarship) {
        this.lastName = lastName;
        this.gender = gender;
        this.studyForm = studyForm;
        this.hasScholarship = hasScholarship;
    }

    @Override
    public String toString() {
        String scholarshipStatus = hasScholarship ? "Отримує" : "Не отримує";
        return String.format("| %-15s | %-10s | %-10s | %-12s |", 
                             lastName, gender, studyForm, scholarshipStatus);
    }
}

public class Main {

    public static int countFemaleBudgetScholars(Student[] students) {
        int count = 0;
        
        for (Student student : students) {
            if (student.gender.equalsIgnoreCase("Жiноча") && 
                student.studyForm.equalsIgnoreCase("Бюджет") && 
                student.hasScholarship) {
                count++;
            }
        }
        
        return count;
    }

    public static void printStudents(Student[] students) {
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-15s | %-10s | %-10s | %-12s |\n", "Прiзвище", "Стать", "Форма", "Стипендiя");
        System.out.println("---------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {
        Student[] students = new Student[20];

        students[0] = new Student("Шевченко", "Жiноча", "Бюджет", true);
        students[1] = new Student("Коваленко", "Чоловiча", "Бюджет", true);
        students[2] = new Student("Бойко", "Жiноча", "Контракт", false);
        students[3] = new Student("Ткаченко", "Жiноча", "Бюджет", false);
        students[4] = new Student("Мельник", "Чоловiча", "Контракт", false);
        students[5] = new Student("Зайцева", "Жiноча", "Бюджет", true);
        students[6] = new Student("Кравченко", "Чоловiча", "Бюджет", false);
        students[7] = new Student("Олiйник", "Жiноча", "Бюджет", true);
        students[8] = new Student("Полiщук", "Жiноча", "Контракт", true);
        students[9] = new Student("Лисенко", "Чоловiча", "Бюджет", true);
        students[10] = new Student("Марченко", "Жiноча", "Бюджет", false);
        students[11] = new Student("Гриценко", "Чоловiча", "Контракт", false);
        students[12] = new Student("Савченко", "Жiноча", "Бюджет", true);
        students[13] = new Student("Мороз", "Чоловiча", "Бюджет", true);
        students[14] = new Student("Романенко", "Жiноча", "Контракт", false);
        students[15] = new Student("Павленко", "Чоловiча", "Бюджет", false);
        students[16] = new Student("Василенко", "Жiноча", "Бюджет", true);
        students[17] = new Student("Петренко", "Чоловiча", "Контракт", false);
        students[18] = new Student("Гончар", "Жiноча", "Бюджет", false);
        students[19] = new Student("Сидоренко", "Чоловiча", "Бюджет", true);

        System.out.println("=== БАЗА ДАНИХ СТУДЕНТIВ ===");
        printStudents(students);

        int result = countFemaleBudgetScholars(students);

        System.out.println("\n=== РЕЗУЛЬТАТ ПОШУКУ ===");
        System.out.println("Завдання: Визначити кiлькiсть студенток бюджетноi форми навчання, що отримують стипендiю.");
        System.out.println("Знайдено студенток, якi вiдповiдають критерiю: " + result);
    }
}