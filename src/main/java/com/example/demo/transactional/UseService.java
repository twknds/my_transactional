package com.example.demo.transactional;

import java.sql.SQLException;

public class UseService {
    TransactionalInterface transactionalInterface = new TransactionalProxy();

    public void userLogic() throws SQLException {
        transactionalInterface.logic();
    }
}
