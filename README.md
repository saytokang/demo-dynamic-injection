# Dynamic Bean Injection
booting property 값에 따라 Bean Injection 이 결정되는 샘플입니다. 

  테스트 내용은 실행 옵션에 따라 서로 다른 api 호출이 수행되는지를 확인하는 것입니다.
## 실행 옵션
> 테스트 케이스는 2번 실행하여 확인하는데, 첫번째는 default 실행으로 확인하고, 두번쩨는 runtime argument option 값을 제공했을 때 동작을 확인합니다.

* 아래와 같이 booting 하면 default 실행 옵션입니다.
```
mvn spring-boot:run
```

* 아래와 같이 booting argument 옵션 제공하는 방식입니다.
```
mvn spring-boot:run -Dspring-boot.run.arguments=--mode=serviceB
```

## 테스트 케이스 확인 실행

테스트 내용은 http://localhost:8080/users  라는 api 를 호출 했을 때, 결과를 확인 하는 것입니다.
* [주] 참고로 테스트 실행했을 때 console log 에 remote call response 내용이 나옵니다. 이를 확인하면 보다 직관적으로 변환된 내용을 확인 할 수 있습니다.

## 테스트 리소스
본 샘플에서 사용하는 remote fake api 가 있습니다. 
* https://reqres.in/api/users
* https://fakestoreapi.com/users

   본 예제에서는 booting 옵션으로 제공했을 때, https://fakestoreapi.com/users  호출을 하도록 되어 있습니다. 
## 

## 기능 동작 설명
@Service 레이어에서 Bean 생성 규칙이 정해집니다. 
  이를 위해 아래와 같은 설정이 필요합니다. 
```
@ConditionalOnProperty(
    name = "mode",
    havingValue = "default",
    matchIfMissing = true
)
public class UserServiceImplA implements UserService{ ... }
```
위 설정 내용을 설명하면, 
  @ConditionalOnProperty 이 어노테이션은 Property 조건에 따른 Bean 생성 기능을 제공합니다., 
    괄호 안에 있는 내용은 해당 기능의 옵션으로 
    * name ==> 여러 Property name 중에 일치하는 (맵핑되는) 값을 찾도록 합니다. 
    * havingValue ==> 이 것은 name property 에 해당하는 값하고 일치하는 조건절입니다. 위 샘플에서는 "default" 라는 값일 경우 Bean 을 생성하라.
    * matchIfMissing  ==> 이것은 만약 해당 property 값이 없으면 해당 Bean을 생성할 것이냐 라는 옵션을 true 로 설정하면 생성됩니다. 즉, property name 을 제공하지 않으면 true 로 설정되었기에 Bean이 생성됩니다.


아래 내용은 booting argument 를 제공했을 때 생성되는 Bean 입니다. 
```
@ConditionalOnProperty(
    name = "mode",
    havingValue = "serviceB"
)
public class UserServiceImplB implements UserService {...}
```

### Dynamic Injection
Dynamic Injection 이란 동작하는 Bean이 옵션값에 따라 다르게 동작하도록 설정하는 것을 말합니다. 
  아래 @Controller 클래스가 @Autowired 로 서비스 객체를 설정하고 있습니다. 
  앞서 위에서 설명한 UserServiceImplA, UserServiceImplB 클래스가 옵션 값에 따라 Inject 될 것입니다.
```
public class UserController {

	@Autowired UserService userService;

}
```

