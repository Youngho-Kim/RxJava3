# RxJava3

[전체소스코드](https://github.com/Youngho-Kim/RxJava3/blob/master/app/src/main/java/com/fastcampus/kwave/android/rxjava3/MainActivity.java)

### PublishSubject
```java
PublishSubject는 Observable 구독 이후에 발행되는 항목들에 대해서만 Observer가 내용을 알 수 있다.
 만약 옵저버마다 구독 시점이 다르다면 각 옵저버마다 다른 내용을 갖고 있을 확률이 높다.
 Observable이 발행하는 모든 항목들의 발행을 보장해야 한다면 Create을 사용한다.
 
  
 - 공식문서 -
 PublishSubject는 구독 이후에 Observable(들)이 배출한 항목들만 옵저버에게 배출한다.
 는 (이를 막지 않는 이상) 생성 시점에서 즉시 항목들을 배출하기 시작할 것이고 
 이런 특성 때문에 주제가 생성되는 시점과 옵저버가 이 주제를 구독하기 시작하는 그 사이에 배출되는 
 항목들을 잃어 버릴 수 있다는 단점이 있다. 
 따라서, Observable이 배출하는 모든 항목들의 배출을 보장해야 한다면 Create을 사용해서 
 명시적으로 Observable(항목들을 배출하기 전에 모든 옵저버가 구독을 시작했는지 체크한다)을 생성하거나, 
 PublishSubject 대신 ReplaySubject를 사용해야 한다. 
 ```
``` java
PublishSubject<String> publishSubject = PublishSubject.create();  // PublishSubject 발행할 

```

### BehaviorSubject
```java 
BehaviorSubject는 구독을 기준으로 구독시 발행하는 시점의 데이터에 대해서도 Observer가 내용을 알 수 있다.
PublishSubject와 마찬가지로 만약 옵저버마다 구독 시점이 다르다면 각 옵저버마다 다른 내용을 갖고 있을 확률이 높다.
Observable이 발행하는 모든 항목들의 발행을 보장해야 한다면 Create을 사용한다.
    
    
 - 공식문서 -
옵저버가 BehaviorSubject를 구독하기 시작하면, 옵저버는 Observable이 가장 최근에 발행한 항목
(또는 아직 아무 값도 발행되지 않았다면 맨 처음 값이나 기본 값)의 발행을 시작하며
그 이후 Observable(들)에 의해 발행된 항목들을 계속 발행한다. 
Observable이 오류 때문에 종료되면 BehaviorSubject는 아무런 항목도 배출하지 않고 
Observable에서 발생한 오류를 그대로 전달한다. 
```


### ReplaySubject
```java 
관찰한 모든 아이템을 버퍼에 저장하고 Observable이 발행한 모든 데이터를 옵저버가 구독한 시점에 상관없이 
모든 옵저버에게 발행한다.
   
      
- 공식문서 -
ReplaySubject는 옵저버가 구독을 시작한 시점과 관계 없이 Observable(들)이 배출한 모든 항목들을 
모든 옵저버에게 배출한다. 
ReplaySubject는 몇 개의 생성자 오버로드를 제공하는데 이를 통해, 재생 버퍼의 크기가 
특정 이상으로 증가할 경우, 또는 처음 배출 이후 지정한 시간이 경과할 경우 오래된 항목들을 제거한다. 
만약, ReplaySubject을 옵저버로 사용할 경우, 멀티 스레드 환경에서는 Observable 계약 위반과 주제에서 
어느 항목 또는 알림을 먼저 재생해야 하는지 알 수 없는 모호함이 동시에 발생할 수 있기 때문에 (비순차적) 
호출을 유발시키는 onNext(또는 그 외 on) 메서드를 사용하지 않도록 주의해야 한다. 
```


### AsyncSubject
```java 
옵저버가 구독한 시점에 상관없이 옵저버블이 발행하는 가장 마지막 데이터를 옵저버에게 발행한다.
  
        
 - 공식문서 -    
Observable로부터 배출된 마지막 값(만)을 배출하고 소스 Observalbe의 동작이 완료된 후에야 동작한다. 
만약, Observable이 아무 값도 배출하지 않으면 AsyncSubject 역시 아무 값도 배출하지 않는다.
또한 AsyncSubject는 맨 마지막 값을 뒤 이어 오는 옵저버에 전달하는데, 
만약 Observable이 오류로 인해 종료될 경우 AsyncSubject는 아무 항목도 배출하지 않고 발생한 오류를 그대로 전달한다. 
```