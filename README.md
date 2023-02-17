## 스프링 프레임워크
+ 핵심 기술 : DI, AOP, 이벤트, 기타
+ 웹 기술 : 스프링 MVC, 스프링 WebFlux
+ 데이터 접근 기술 : 트랜잭션, JDBC, ORM, XML
+ 기술 통합 : 캐시, 이메일, 원격, 스케줄링

## 스프링 부트
+ 단독 실행 가능, 톰캣 설치 x
+ starter 종속성 제공
+ 스프링과 3rd parth 라이브러리 자동 구성
+ 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공
+ 간결한 설정
+ 스프링을 편리하게 사용할 수 있도록 지원

## 스프링의 핵심
스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크로 좋은 객체 지향 어플리케이션을 개발 할 수 있게 도와준다. 여기서 좋은 객체 지향 프로그래밍이란? 먼저 객체 지향의 특징을 알아보자

### 객체 지향 특징
+ 추상화
+ 캡슐화
+ 상속
+ 다형성

이 중 다형성에 대해 정리를 해보자 다형성은 프로그램을 유연하고 변경이 용이하게 만들 때 유용하다.

### 다형성의 본질
+ 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경한다.
+ 다형성의 본질을 이해하려면 협력이라는 객체사이의 관계에서 시작한다.
+ 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경한다.

## 좋은 객체 지향 설계의 5가지 원칙(SOLID)
+ SRP(Single Responsibility Principle) - 단일 책임원칙
+ OCP(Open Closed Principle) - 개방-폐쇄 원칙
+ LSP(Liskov Substitution Principle) - 리스코프 치환 원칙
+ ISP(Interface Segregation Principle) - 인터페이스 분리 원칙
+ DIP(Dependency Inversion Principle) - 의존관계 역전 원칙

### SRP(Single Responsibility Principle) - 단일 책임원칙
+ 한 클래스는 하나의 책임만 가져야 한다.
+ 책임이라는 것의 기준이 모호하며, 중요한 기준은 변경이다.
(변경이 일어 났을 때 파급효과가 적도록 한다.)

### OCP(Open Closed Principle) - 개방-폐쇄 원칙
+ 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.

+ **OCP 위반한 경우**

기존에는 Apple 구현 객체를 가지고 있지만

```java
	Pulbic Class FoodService {
    
		private FoodRepository foodRepository = new Apple();
        
        }
```
Banana 구현 객체로 바꿔야 하는 상황이 온다면
```java
	Pulbic Class FoodService {
    
		//private FoodRepository foodRepository = new Apple();
	    private FoodRepository foodRepository = new Banana();
        
        }
```
위 와 같이 클라이언트 코드를 변경해야 한다.

### LSP(Liskov Substitution Principle) - 리스코프 치환 원칙
+ 상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다.
+ 리스코프 치환 원칙을 지키지 않는 가장 대표적인 예가 '직사각형-정사각형' 문제이다.

### ISP(Interface Segregation Principle) - 인터페이스 분리 원칙
+ 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 좋다.
+ 인터페이스가 명확해지고, 대체 가능성이 높아진다.

### DIP(Dependency Inversion Principle) - 의존관계 역전 원칙
+ 추상화에 의존하며, 구체화에 의존하면 안된다.

+ **DIP 위반한 경우**
FoodService는 인터페이스에 의존하지만, 구현 클래스인 Apple도 동시에 의존한다.

```java
	Pulbic Class FoodService {
    
		private FoodRepository foodRepository = new Apple();
        
	}
```

## IoC(Inversion of Control) - 제어의 역전
+ 기존 프로그램은 구현 객체가 제어 흐름을 스스로 컨트롤
+ IoC는 프로그램의 제어 흐름을 직접 제어하는 것이 아닌 외부에서 관리

## DI(Dependency Injection) - 의존관계 주입
+ 의존관계는 정적/동적 의존관계로 보아야 한다.
정적 의존관계는 코드만 보고 판단이 가능
동적 의존관계는 런타임에 판단이 가능
+ 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서
클라이언트와 서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라 한다.

#### IoC 컨테이너, DI 컨테이너 : 객체를 생성하고 관리하면서 의존관계를 연결해주는 것 (어샘블러, 오브젝트 팩토리 라고도 부름

## 스프링 컨테이너

스프링 컨테이너 생성
```java
ApplicationContext applicationContext 
	= new AnnotationConfigApplicationContext(AppConfig.class);
```

+ ApplicationContext는 인터페이스 이다.
+ 스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다.(AppConfig.class)
+ 스프링 컨테이너의 생성 과정
1). 구성 정보를 활용하여 스프링이 컨테이너를 생성한다.
2). 스프링 빈을 등록한다.(빈 이름은 기본적으로 메소드명이며, 직접 설정이 가능하다.)
주의! 빈 이름은 항상 다른 이름으로 부여한다. 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을
덮어버리거나 설정에 따라 오류가 발생한다.
3). 스프링 빈 의존관계 설정 - 준비
4). 스프링 빈 의존관계 설정 - 완료

## 컨테이너에 등록된 모든 빈 이름 출력
```java
public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext ac = 
    	new AnnotationConfigApplicationContext(AppConfig.class);

	void findAllBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
 			Object bean = ac.getBean(beanDefinitionName);
 			System.out.println(beanDefinitionName + " " + bean);
        }
    }
}
```

## 애플리케이션 빈 출력하기
```java
public class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext ac = 
    	new AnnotationConfigApplicationContext(AppConfig.class);

	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition =
				ac.getBeanDefinition(beanDefinitionName);
                
 			//Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
 			//Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println(beanDefinitionName + " " + bean);
			}
		}
	}
}
```

+ ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회한다.
+ ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.
+ beanDefinition.getRole() : 해당 빈의 역할을 가져온다.

## 스프링 빈 조회 - 기본
+ ac.getBean(빈이름, 타입)
+ ac.getBean(타입)
+ 조회 대상 스프링 빈이 없으면 예외 발생
NoSuchBeanDefinitionException: No bean named 'xxxxx' available

## 스프링 빈 조회 -  동일한 타입이 둘 이상
+ 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
+ ac.getBeansOfType(type)을 사용하면 해당 타입의 모든 빈을 Map<K,V> 형식으로 조회할 수 있다.
+ beansOfType.get(key)을 사용하면 해당 키에 해당하는 값을 반환한다.

## 스프링 빈 조회 - 상속 관계
+ 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
+ 부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.(빈 이름을 지정하면 해결할 수 있다.)
+ 그래서 모든 자바 객체의 최고 부모인 Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.

## BeanFactory와 ApplicationContext
### BeanFactory
+ 스프링 컨테이너의 최상위 인터페이스다.
+ 스프링 빈을 관리하고 조회하는 역할을 담당한다.
+ getBean() 을 제공한다.
+ 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다.

### ApplicationContext
+ BeanFactory 기능을 모두 상속받아서 제공한다.
+ 그 외 MessageSource, EnvironmentCapable, ApplicationEventPublisher, ResourceLoader 기능을 상속 받는다.

## 스프링 빈 설정 메타 정보 - BeanDefinition

![](https://velog.velcdn.com/images/gcael/post/18fe525c-ecee-4d67-9894-c12b150cb7c2/image.PNG)
+ AnnotationConfigApplicationContext 는 AnnotatedBeanDefinitionReader 를 사용해서
AppConfig.class 를 읽고 BeanDefinition 을 생성한다.
+ GenericXmlApplicationContext 는 XmlBeanDefinitionReader 를 사용해서 appConfig.xml 설정 정보를 읽고 BeanDefinition 을 생성한다.
+ 새로운 형식의 설정 정보가 추가되면, XxxBeanDefinitionReader를 만들어서 BeanDefinition 을
생성하면 된다.

## 싱글톤 패턴
+ 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
+ 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.
```java
public class SingletonService {
 	//1. static 영역에 객체를 딱 1개만 생성해둔다.
 	private static final SingletonService instance = new SingletonService();
    
 	//2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
 	public static SingletonService getInstance() {
 		return instance;
 	}
    
 	//3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
 	private SingletonService() {
 	}
}
```
1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.

## 싱글톤 패턴 문제점
+ 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
+ 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
+ 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
+ 테스트하기 어렵다.내부 속성을 변경하거나 초기화 하기 어렵다.
+ private 생성자로 자식 클래스를 만들기 어렵다.
+ 결론적으로 유연성이 떨어진다.
+ 안티패턴으로 불리기도 한다.

## 싱글톤 방식의 주의점
+ 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.

### 무상태 설계
- 특정 클라이언트에 의존적인 필드가 있으면 안된다.
- 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
- 가급적 읽기만 가능하게 한다.
- 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, 스레드로컬 등을 사용해야 한다.

## @Configuration과 바이트코드 조작의 마법
+ @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
+ ![](https://velog.velcdn.com/images/gcael/post/8af4c09e-aa95-4cb1-a489-c3026fbd4134/image.PNG)
+ @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다. 스프링 설정 정보는 항상 @Configuration을 사용하자.

## @ComponentScan
+ @ComponentScan 은 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
+ 빈 이름 기본 전략: MemberServiceImpl 클래스 memberServiceImpl
+ 빈 이름 직접 지정: 만약 스프링 빈의 이름을 직접 지정하고 싶으면 @Component("beanName") 이런식으로 이름을 부여하면 된다.
+ ![](https://velog.velcdn.com/images/gcael/post/9f36310e-9c15-4d0b-93cb-de14216d7d64/image.PNG)


## @Autowired 의존관계 자동 주입
+ 생성자에 @Autowired 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다. 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다. getBean(MemberRepository.class) 와 동일하다고 이해하면 된다.
+ ![](https://velog.velcdn.com/images/gcael/post/b8167ef2-2e77-4005-94fb-75e360ac0337/image.PNG)

## 탐색 위치와 기본 스캔 대상
+ basePackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다. 복수 설정이 가능하다.
+ basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

## 컴포넌트 스캔 기본 대상
- @Component : 컴포넌트 스캔에서 사용
- @Controlller : 스프링 MVC 컨트롤러에서 사용
- @Service : 스프링 비즈니스 로직에서 사용
- @Repository : 스프링 데이터 접근 계층에서 사용
- @Configuration : 스프링 설정 정보에서 사용

## 애노테이션 스프링의 부가 기능
- @Controller : 스프링 MVC 컨트롤러로 인식
- @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
- @Configuration : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
- @Service : 사실 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.

## 컴포넌트 스캔 필터
- includeFilters : 컴포넌트 스캔 대상을 추가로 지정한다.
- excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정한다.

### 필터 타입 옵션
- ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
- ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
- ASPECTJ: AspectJ 패턴 사용
- REGEX: 정규 표현식
- CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리

## 중복 등록과 충돌
- 자동 빈 등록 vs 자동 빈 등록
컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 스프링은 오류를 발생시킨다.
- 수동 빈 등록 vs 자동 빈 등록
수동 빈 등록이 우선권을 가진다. (스프링 부트는 예외 발생)

_참고 문서 및 링크_
- 스프링 핵심 원리 - 기본편(김영한)

