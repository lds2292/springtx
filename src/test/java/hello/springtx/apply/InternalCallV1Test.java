package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV1Test {

    @Autowired CallService callService;

    @TestConfiguration
    static class InternalCallV1TestConfig {

        @Bean
        CallService callService(){
            return new CallService();
        }

    }

    @Test
    void printProxy() {
        log.info("callService class={}", callService.getClass());
    }

    @Test
    void internalCall(){
        callService.internal();
    }

    @Test
    void externalCall(){
        callService.external();
    }


    @Slf4j
    static class CallService {

        // 클라이언트에서 external을 호출한다
        public void external(){
            log.info("call external");
            printTxInfo();
            // 다시 내부의 메서드를 호출한다
            internal();
        }

        @Transactional
        public void internal(){
            log.info("call internal");
            printTxInfo();
        }


        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
            log.info("tx name={}", TransactionSynchronizationManager.getCurrentTransactionName());
        }

    }

}
