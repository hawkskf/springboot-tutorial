# Building a RESTful Web Service

# チュートリアル実行メモ

Springを使用して、RESTAPIを返すWebサービスを作ります。

基本的に↓のチュートリアルに従って作成します。

[Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

[Spring MVC チュートリアル実行メモ](https://github.com/hawkskf/springboot-tutorial/blob/master/serving-web-content.md)のプロジェクトを流用します。

# リソースクラスを作る

フィールドとgetterから成るデータモデルを作ります。

```java
package com.example.demo;

public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
```

finalをつける、そもそもsetterがなくコンストラクタ以外で代入できないのでより明示的。

## APIコントローラー作成

```java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingAPIController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greetingapi")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World")String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
```

greetingapiへGetリクエストがあると、Greetingクラスをnewし、URLパラメータのname とidはAtomicLongクラスによってアクセスするたびに増える値が入る。

> [AtomicLong (Java Platform SE 8 )](https://docs.oracle.com/javase/jp/8/docs/api/java/util/concurrent/atomic/AtomicLong.html)

ブラウザから`http://localhost:8080/greetingapi?name=hogehoge`でURLアクセス。

![image-20220413151138294](https://github.com/hawkskf/springboot-tutorial/blob/master/images/image-20220413151138294.png)

