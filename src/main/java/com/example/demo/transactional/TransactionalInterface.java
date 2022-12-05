package com.example.demo.transactional;

import java.sql.SQLException;

public interface TransactionalInterface {
    void logic() throws SQLException;
    void noLogic() throws SQLException;
}
