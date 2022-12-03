public class TransactionManager {
    public static TransactionManager getInstance(){
        return new TransactionManager();
    }
    public void begin(){
        TransactionSynchronizationManager.initSynchronization(); // 동기화 초기화
        Connection conn = DataSourceUtils.getConnection(dataSource); // DB 커넥션 생성 및 트랜잭션 시작
        conn.setAutoCommit(false);

        try{
            // 작업

            conn.commit();
        } catch(Exception e) {
            conn.rollback();
        } finally {
            DataSourceUtils.releaseConnection(conn, dataSource); // DB 커넥션 닫기
            TransactionSynchronizationManager.unbindResource(this.dataSource); // 리소스 해제
            TransactionSynchronizationManager.clearSynchronization();
        }
    }
}
