package oakberg.dk.BallBoiV4.entity;

/**
 * Created by Oakberg on 13/03/2018.
 */

public class User extends Throwable {

    private String name;
    private int age;

    public User(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
