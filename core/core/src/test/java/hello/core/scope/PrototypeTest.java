package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void PrototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean PrototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean PrototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("singletonBean1 = " + PrototypeBean1);
        System.out.println("singletonBean2 = " + PrototypeBean2);

        Assertions.assertThat(PrototypeBean1).isNotSameAs(PrototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        }
    }
}
