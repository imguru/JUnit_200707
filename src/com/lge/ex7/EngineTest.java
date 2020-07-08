package com.lge.ex7;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Test Specific Subclass Pattern(테스트 전용 하위 클래스)
// 의도: SUT의 검증하고자 하는 상태가 존재하지 않을 때, 어떻게 하면 테스트 가능하게 할 수 있는가?
// 방법: SUT를 상속 받은 클래스에 테스트에 필요한 상태나 동작을 드러내는 메소드를 추가한다.

class TestEngine extends Engine {
    private boolean isStart;

    TestEngine() {
        isStart = false;
    }

    @Override
    public void start() {
        super.start();
        isStart = true;
    }

    public boolean isStart() {
        return isStart;
    }
}

public class EngineTest {
    @Test
    public void engineTest() {
        TestEngine engine = new TestEngine();
        Car car = new Car(engine);

        car.go();

        // 테스트 전용 하위 클래스에 추가된 기능을 통해 검증을 수행한다.
        assertTrue(engine.isStart(), "Car가 go했을 때");
    }

    // Car의 go가 호출되었을 때, engine의 start가 제대로 호출되었는지 여부를 검증하고 싶다.
    @Test
    public void engineTest_bad() {
        Engine engine = new Engine();
        Car car = new Car(engine);

        car.go();

        // Engine에 대해서 assert 할만한 기능이 제공되고 있지 않다.
    }
}


//--------------
class Engine {
    public void start() {
        System.out.println("Engine start");
    }
}

class Car {
    private Engine engine;
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void go() {
        // ...
        // engine.start();
    }
}