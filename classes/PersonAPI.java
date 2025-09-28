package classes;

public class PersonAPI {
    public static Person createPerson(String name, Integer age) {
        return new Person(name, age);
    }

    public static Person createPerson(String name, Integer age, Boolean isDumb) {
        return new Person(name, age, isDumb);
    }

    public static Person createPerson() {
        return new Person();
    }
}
