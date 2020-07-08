package com.lge.ex10;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Fake Object Pattern
// 의도: SUT가 '아직 준비되지 않은 컴포넌트'에 의해 테스트 되지 않은 요구사항이 있다.
// 방법: 아직 준비되지 않은 컴포넌트 대신하는 가벼운 테스트 대역을 만들어서, 검증을 수행한다.

class FakeDatabase implements Database {
    private Map<String, User> data = new HashMap<>();

    @Override
    public void saveUser(String name, User user) {
        data.put(name, user);
    }

    @Override
    public User loadUser(String name) {
        return data.get(name);
    }
}


public class UserManagerTest {
    @Test
    public void createTest() {
        FakeDatabase fake = new FakeDatabase();
        UserManager manager = new UserManager(fake);
        String testUserName = "TEST_USER";
        int testAge = 42;
        // User expected = new User(testUserName, testAge);
        User expected = new User("Tom", testAge);

        manager.createUser(testUserName, testAge);

        assertEquals(expected, manager.getUser(testUserName));
    }
}

//------------------------
interface Database {
    void saveUser(String name, User user);

    User loadUser(String name);
}

class UserManager {
    private Database database;

    public UserManager(Database database) {
        this.database = database;
    }

    public void createUser(String name, int age) {
        User user = new User(name, age);
        if (database.loadUser(name) == null) {
            database.saveUser(name, user);
        }
    }

    public User getUser(String name) {
        return database.loadUser(name);
    }
}

// 아래의 객체를 assertEquals 등의 메소드를 통해 검증하고자 할 때는,
// 몇가지 기능을 제공해야 합니다.
//  - equals
//  - equals를 제공하면, 반드시 hashCode도 제공해야 한다.

// Data class
//  - DTO, VO, DAO
//  - @Lombok
class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        // User의 자식 타입을 허용하지 않습니다.
        /*
        if (obj.getClass() != User.class) {
            return false;
        }
        */

        // User의 자식 타입을 허용한다.
        if (!(obj instanceof User)) {
            return false;
        }

        User u = (User) obj;
        // return name != null && name.equals(u.name) && age == u.age;
        return Objects.equals(name, u.name) && age == u.age;
    }
}








