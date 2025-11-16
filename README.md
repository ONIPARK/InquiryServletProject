# InquiryServletProject
## 概要
Jakarta Servlet/JSP で構築したお問い合わせページを、今後 **Spring Boot 3.0 へ段階的にリファクタリングします。

## 現状（Current）
- Jakarta Servlet 5 + JSP（JSTL 2.0 / Jakarta）
- アプリサーバ: Tomcat 10+
- DB: PostgreSQL（JDBC）
- 設定: `web.xml` / `@WebServlet`

## リファクタリング計画（Plan）
- Spring Boot 3.0 導入（Gradle へ移行）
- Web 層: `web.xml` / `@WebServlet` → **Spring MVC `@Controller`**
- DB 層: **JDBC → Spring JDBC / JPA**（検討中）
- View: **JSP/JSTL 維持** → **Thymeleaf** へ移行

