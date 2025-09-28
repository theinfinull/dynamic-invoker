package classes;

public class Person {
    private String name;
    private int age;
    private boolean isDumb;

    {
        this.name = "Unknown";
        this.age = -1;
        this.isDumb = false;
    }

    Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this(name);
        this.age = age;
    }

    public Person(String name, int age, boolean isDumb) {
        this(name, age);
        this.isDumb = isDumb;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isDumb() {
        return isDumb;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", isDumb=" + isDumb + '}';
    }
}