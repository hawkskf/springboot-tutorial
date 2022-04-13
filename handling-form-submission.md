# Building a RESTful Web Service

# チュートリアル実行メモ

Springを使用して、RESTAPIを返すWebサービスを作ります。

基本的に↓のチュートリアルに従って作成します。

[Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

[Spring MVC チュートリアル実行メモ](https://github.com/hawkskf/springboot-tutorial/blob/master/serving-web-content.md)のプロジェクトを流用します。

# リソースクラスを作る

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

別のチュートリアルで作ったものをそのまま利用します。

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

入力画面

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: handling Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>Form</h1>
<form action="#" th:action="@{/greeting}" th:object="${greeting}" method="post">
    <p>Id: <input type="text" th:field="*{id}"></p>
    <p>Message: <input type="text" th:field="*{content}"></p>
    <p><input type="submit" value="Submit"> <input type="reset" value="Reset"></p>
</form>
</body>
</html>
```

result画面

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: handling Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h1>Result</h1>
<p th:text="'id: ' + ${greeting.id}}"/>
<p th:text="'content: ' + ${greeting.content}"/>
    <a href="/greeting">Submit another message</a>
</body>
</html>
```

サーバを起動してブラウザでアクセスします。

`http://localhost:8080/greeting`

適当にフォーム入力します。

![image-20220413170440218](https://github.com/hawkskf/springboot-tutorial/blob/master/images/image-20220413170440218.png)



実行するとエラー。

```
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Wed Apr 13 16:56:55 JST 2022
There was an unexpected error (type=Internal Server Error, status=500).
An error happened during template parsing (template: "class path resource [templates/result.html]")
org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "class path resource [templates/result.html]")
	at org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser.parse(AbstractMarkupTemplateParser.java:241)
	at org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser.parseStandalone(AbstractMarkupTemplateParser.java:100)
	at org.thymeleaf.engine.TemplateManager.parseAndProcess(TemplateManager.java:666)
	at org.thymeleaf.TemplateEngine.process(TemplateEngine.java:1098)
	at org.thymeleaf.TemplateEngine.process(TemplateEngine.java:1072)
	at org.thymeleaf.spring5.view.ThymeleafView.renderFragment(ThymeleafView.java:366)
	at org.thymeleaf.spring5.view.ThymeleafView.render(ThymeleafView.java:190)
	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1401)
	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1145)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1084)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:360)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:399)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:889)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: org.attoparser.ParseException: Could not parse as expression: "'id: ' + ${greeting.id}}" (template: "result" - line 9, col 4)
	at org.attoparser.MarkupParser.parseDocument(MarkupParser.java:393)
	at org.attoparser.MarkupParser.parse(MarkupParser.java:257)
	at org.thymeleaf.templateparser.markup.AbstractMarkupTemplateParser.parse(AbstractMarkupTemplateParser.java:230)
	... 48 more
Caused by: org.thymeleaf.exceptions.TemplateProcessingException: Could not parse as expression: "'id: ' + ${greeting.id}}" (template: "result" - line 9, col 4)
	at org.thymeleaf.standard.expression.StandardExpressionParser.parseExpression(StandardExpressionParser.java:131)
	at org.thymeleaf.standard.expression.StandardExpressionParser.parseExpression(StandardExpressionParser.java:62)
	at org.thymeleaf.standard.expression.StandardExpressionParser.parseExpression(StandardExpressionParser.java:44)
	at org.thymeleaf.engine.EngineEventUtils.parseAttributeExpression(EngineEventUtils.java:220)
	at org.thymeleaf.engine.EngineEventUtils.computeAttributeExpression(EngineEventUtils.java:207)
	at org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor.doProcess(AbstractStandardExpressionAttributeTagProcessor.java:125)
	at org.thymeleaf.processor.element.AbstractAttributeTagProcessor.doProcess(AbstractAttributeTagProcessor.java:74)
	at org.thymeleaf.processor.element.AbstractElementTagProcessor.process(AbstractElementTagProcessor.java:95)
	at org.thymeleaf.util.ProcessorConfigurationUtils$ElementTagProcessorWrapper.process(ProcessorConfigurationUtils.java:633)
	at org.thymeleaf.engine.ProcessorTemplateHandler.handleStandaloneElement(ProcessorTemplateHandler.java:918)
	at org.thymeleaf.engine.TemplateHandlerAdapterMarkupHandler.handleStandaloneElementEnd(TemplateHandlerAdapterMarkupHandler.java:260)
	at org.thymeleaf.templateparser.markup.InlinedOutputExpressionMarkupHandler$InlineMarkupAdapterPreProcessorHandler.handleStandaloneElementEnd(InlinedOutputExpressionMarkupHandler.java:256)
	at org.thymeleaf.standard.inline.OutputExpressionInlinePreProcessorHandler.handleStandaloneElementEnd(OutputExpressionInlinePreProcessorHandler.java:169)
	at org.thymeleaf.templateparser.markup.InlinedOutputExpressionMarkupHandler.handleStandaloneElementEnd(InlinedOutputExpressionMarkupHandler.java:104)
	at org.attoparser.HtmlElement.handleStandaloneElementEnd(HtmlElement.java:79)
	at org.attoparser.HtmlMarkupHandler.handleStandaloneElementEnd(HtmlMarkupHandler.java:241)
	at org.attoparser.MarkupEventProcessorHandler.handleStandaloneElementEnd(MarkupEventProcessorHandler.java:327)
	at org.attoparser.ParsingElementMarkupUtil.parseStandaloneElement(ParsingElementMarkupUtil.java:96)
	at org.attoparser.MarkupParser.parseBuffer(MarkupParser.java:706)
	at org.attoparser.MarkupParser.parseDocument(MarkupParser.java:301)
	... 50 more
```



> Caused by: org.thymeleaf.exceptions.TemplateProcessingException: Could not parse as expression: "'id: ' + ${greeting.id}}" (template: "result" - line 9, col 4)

より、thymeleafのパースに失敗したことと、該当行が書いてあります。

閉じの } が一つ多く }} になっているようですね。}を1つ消して正しくします。

`<p th:text="'id: ' + ${greeting.id}"/>`



再度実行して入力して送信。

![image-20220413170756108](https://github.com/hawkskf/springboot-tutorial/blob/master/images/image-20220413170756108.png)
