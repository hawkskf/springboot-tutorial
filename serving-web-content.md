# Serving Web Content with Spring MVC

# チュートリアル実行メモ

Spring MVCを使用して、ThymeleafのHTMLにJavaコードから情報を埋め込む一般的なMVCのWebフレームワークのHelloWorldをします。

基本的に↓のチュートリアルに従って作成します。

[Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)

# spring initializr

[spring initializr](https://start.spring.io/)を使用します。

Webページ上で言語やアプリに必要な依存を選択するだけで初期環境が作成され、Zipでダウンロードできます。

![image-20220412172411253](https://github.com/hawkskf/springboot-tutorial/blob/master/images/image-20220412171907905.png)

Maven、Java11で作成することにします。

WebアプリケーションのSpringWebとHTMLテンプレートに使用するThmeleafを依存に追加します。

開発者ツールのSpring Boot DevToolsも入れます。

## プロジェクトをIDEで開く

今回は**IntelliJ IDEA Community** を使用します。

[IntelliJ IDEA](https://www.jetbrains.com/ja-jp/idea/)

![image-20220413093635804](https://github.com/hawkskf/springboot-tutorial/blob/master/images/image-20220413093635804.png)

IDE初回なので日本語プラグインを入れます。

### Controllerを作ります

src/main/java/com/example/demo/GreetingController.java

```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String Greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
```

アノテーションによるルーティング機能もあるコントローラーですね。

MVCコントローラーなので、/greeting にひっかかるURLにGetリクエストしたときに、「greeting」というファイルをビューとしてレスポンスを返します。

メソッドの内容はURLのパラメータ「name」をモデルにセットしています。

このモデルはthymeleafから参照できます。

### ビューをthymeleafのHTMLで作ります

src/main/resources/templates/greeting.html

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p th:text="'Hello, ' + ${name} + '!'" />
</body>
</html>
```

ターミナルから`./mvnw spring-boot:run`で実行。

ブラウザからhttp://localhost:8080/greeting?name=namaeにアクセス。

![image-20220413131350759](https://github.com/hawkskf/springboot-tutorial/blob/master/images/image-20220413131350759.png)

