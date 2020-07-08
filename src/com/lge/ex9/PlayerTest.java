package com.lge.ex9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Test Stub Pattern
// 의도: 다른 컴포넌트로부터의 간접 입력에 의존하는 로직을 독립적으로 검증하고 싶다.
// 방법: 실제 의존하는 객체를 테스트용 객체로 교체해서, SUT가 테스트하는데 필요한 결과를 보내도록 설계한다.
// => 특수한 상황을 시뮬레이션 한다.
//  : 네트워크, 시간, 환경 등

class StubConnection implements Connection {
    @Override
    public void move(int x, int y) throws IOException {
        throw new IOException("Network error");
    }
}

public class PlayerTest {
    // when - stubbing 하고자하는 메소드의 결과가 void가 아닐 때 사용할 수 있다.

    @DisplayName("move에서 IOException 발생하였을 때, NetworkException이 발생하는지 여부를 검증하고 싶다.")
    @Test
    public void moveTest_mockito() throws Exception {
        Connection stub = mock(Connection.class);
        // when(stub.move(anyInt(), anyInt())).thenThrow(new IOException("Network error"));
        doThrow(new IOException("Network error")).when(stub).move(anyInt(), anyInt());
        Player player = new Player(stub);

        assertThrows(NetworkException.class, () -> player.move(10, 20));
    }

    @DisplayName("move에서 IOException 발생하였을 때, NetworkException이 발생하는지 여부를 검증하고 싶다.")
    @Test
    public void moveTest() throws Exception {
        StubConnection stub = new StubConnection();
        Player player = new Player(stub);

        assertThrows(NetworkException.class, () -> player.move(10, 20));
    }

    @DisplayName("move에서 IOException 발생하였을 때, NetworkException이 발생하는지 여부를 검증하고 싶다.")
    @Test
    public void moveTest_bad() throws Exception {
        TCPConnection connection = new TCPConnection();
        Player player = new Player(connection);

        assertThrows(NetworkException.class, () -> player.move(10, 20));
    }
}

// SUT

interface Connection {
    void move(int x, int y) throws IOException;
}

class TCPConnection implements Connection {
    @Override
    public void move(int x, int y) throws IOException {
        // TCP
    }
}

class NetworkException extends Exception {
    public NetworkException(String message) {
        super(message);
    }
}

class Player {
    private Connection connection;

    public Player(Connection connection) {
        this.connection = connection;
    }

    public void move(int x, int y) throws NetworkException {
        try {
            connection.move(x, y);
        } catch (IOException e) {
            throw new NetworkException(e.getLocalizedMessage());
        }
    }
}