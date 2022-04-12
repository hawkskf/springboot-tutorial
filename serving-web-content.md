# Serving Web Content with Spring MVC

# チュートリアル実行メモ

Spring MVCを使用して、ThymeleafのHTMLにJavaコードから情報を埋め込む一般的なMVCのWebフレームワークのHelloWorldをします。

基本的に↓のチュートリアルに従って作成します。

[Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)

# 1.spring initializr

[spring initializr](https://start.spring.io/)を使用します。

Webページ上で言語やアプリに必要な依存を選択するだけで初期環境が作成され、Zipでダウンロードできます。

![image-20220412172411253](C:\Users\future\Documents\GitHub\springboot-tutorial\images\image-20220412171907905.png)

Maven、Java11で作成することにします。

WebアプリケーションのSpringWebとHTMLテンプレートに使用するThmeleafを依存に追加します。

開発者ツールのSpring Boot DevToolsも入れます。

<4/12 TODO>
