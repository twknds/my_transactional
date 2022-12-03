public class TransactionalProxy implements TransactionalInterface{
    TransactionalInterface transactionalInterface = new TransactionalImpl();
    @Override
    public void logic(){
        // 호출전 처리기능 ex) commit 수동

        transactionalInterface.logic();

        // commit
    }
}
