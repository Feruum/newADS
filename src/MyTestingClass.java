import java.util.Random;

// This is the class we use as KEY in our hash table
public class MyTestingClass {

    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Custom hashCode - we are NOT allowed to use Objects.hash()
    // We manually combine the fields to create a hash code
    @Override
    public int hashCode() {
        int result = 1;

        // Mix in the id
        result = result * 31 + id;

        // Mix in each character of the name manually
        if (name != null) {
            for (int i = 0; i < name.length(); i++) {
                result = result * 31 + name.charAt(i);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof MyTestingClass)) return false;

        MyTestingClass other = (MyTestingClass) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name=" + name + "}";
    }
}


// Simple Student class used as VALUE
class Student {
    private String studentName;
    private int grade;

    public Student(String studentName, int grade) {
        this.studentName = studentName;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" + studentName + ", grade=" + grade + "}";
    }
}


// Main test class
class TestMain {
    public static void main(String[] args) {

        // Create hash table with MyTestingClass as key and Student as value
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random random = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                "Frank", "Grace", "Henry", "Isla", "Jack"};

        // Add 10000 random elements
        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(100000);
            String randomName = names[random.nextInt(names.length)];
            MyTestingClass key = new MyTestingClass(randomId, randomName);

            int randomGrade = random.nextInt(100);
            Student value = new Student("Student" + i, randomGrade);

            table.put(key, value);
        }

        System.out.println("Total elements stored: " + table.size());
        System.out.println("\n--- Elements per bucket ---");
        table.printBucketSizes();
    }
}