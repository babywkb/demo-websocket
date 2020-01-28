
How to run

```
cd <project_root>
./mvnw clean spring-boot:run
```  

```
cd <project_root>/front
npm install
npm start
```

↓見て写経＆読み込み(徐々に自分で書き換え)
https://www.baeldung.com/websockets-spring
https://github.com/lahsivjar/spring-websocket-template

AbstractWebSocketMessageBrokerConfigurer (deplicated) -> WebSocketMessageBrokerConfigurer
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/socket/config/annotation/AbstractWebSocketMessageBrokerConfigurer.html

WebMvcConfigurerAdapter (deplicated) -> WebMvcConfigurer
https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurerAdapter.html