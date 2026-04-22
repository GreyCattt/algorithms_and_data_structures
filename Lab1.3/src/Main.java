class Student {
    String lastName;
    String firstName;
    int course;
    long ticketNumber;
    String gender;
    String maritalStatus;

    public Student(String lastName, String firstName, int course, long ticketNumber, String gender, String maritalStatus) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.course = course;
        this.ticketNumber = ticketNumber;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }
}

class TreeNode {
    Student data;
    TreeNode left;
    TreeNode right;

    public TreeNode(Student data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(Student student) {
        if (student.ticketNumber <= 0) {
            System.out.println("Помилка: Номер квитка має бути додатнiм числом.");
            return;
        }
        
        root = insertRecursive(root, student);
    }

    private TreeNode insertRecursive(TreeNode current, Student student) {
        if (current == null) {
            return new TreeNode(student);
        }

        if (student.ticketNumber < current.data.ticketNumber) {
            current.left = insertRecursive(current.left, student);
        } else if (student.ticketNumber > current.data.ticketNumber) {
            current.right = insertRecursive(current.right, student);
        } else {
            System.out.println("Студент з квитком №" + student.ticketNumber + " вже iснує в деревi!");
        }

        return current;
    }

    public void printTable() {
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.printf("| %-15s | %-12s | %-4s | %-15s | %-5s | %-16s |\n", 
                          "Прiзвище", "Iм'я", "Курс", "№ Квитка", "Стать", "Сiмейний статус");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        printInOrder(root);
        
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private void printInOrder(TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            
            System.out.printf("| %-15s | %-12s | %-4d | %-15d | %-5s | %-16s |\n", 
                              node.data.lastName, 
                              node.data.firstName, 
                              node.data.course, 
                              node.data.ticketNumber, 
                              node.data.gender, 
                              node.data.maritalStatus);
            
            printInOrder(node.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        System.out.println("Створення дерева та додавання студентiв...");

        addAndShow(tree, new Student("Коваленко", "Олег", 2, 10500, "Чоловiча", "Неодружений"));
        addAndShow(tree, new Student("Шевченко", "Марiя", 3, 10200, "Жiноча", "Замiжня"));
        addAndShow(tree, new Student("Бойко", "Iван", 1, 10800, "Чоловiча", "Неодружений"));
        addAndShow(tree, new Student("Ткаченко", "Анна", 4, 10100, "Жiноча", "Неодружена"));
        addAndShow(tree, new Student("Мельник", "Петро", 2, 10400, "Чоловiча", "Одружений"));
        addAndShow(tree, new Student("Дублiкат", "Iван", 1, 10500, "Чоловiча", "Неодружений"));
    }

    private static void addAndShow(BinaryTree tree, Student student) {
        tree.insert(student);
        tree.printTable();
    }
}