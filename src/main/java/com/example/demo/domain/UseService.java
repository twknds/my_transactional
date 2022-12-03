package com.example.demo.domain;

public class UseService {
    TransactionalInterface transactionalInterface = new TransactionalProxy();

    public void userLogic() {
        transactionalInterface.logic();
    }
}
