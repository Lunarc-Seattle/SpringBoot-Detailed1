Spring Boot 启动核心的四大注解
它们各自有职责，又相互关联。我们来一个个拆解、对比，搞懂它们到底是**干什么的、有什么区别、怎么配合工作的**。

---

## 🌟 快速对比表

| 注解                         | 作用               | 是否注册 Bean | 来源               | 关系     |
| -------------------------- | ---------------- | --------- | ---------------- | ------ |
| `@SpringBootApplication`   | 启动入口组合注解         | ✅ 是       | Spring Boot      | 包含其他三个 |
| `@Configuration`           | 标记配置类（可以定义 Bean） | ✅ 是       | Spring Framework | 被包含    |
| `@ComponentScan`           | 指定扫描哪些包，注册 Bean  | ❌（控制行为）   | Spring Framework | 被包含    |
| `@EnableAutoConfiguration` | 启动自动配置机制         | ❌（控制行为）   | Spring Boot      | 被包含    |

---

## 1. `@SpringBootApplication` —— 三合一组合注解（推荐用这个）

```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```

**等价于：**

```java
@Configuration
@ComponentScan
@EnableAutoConfiguration
```

* 是 Spring Boot 的入口标志。
* 最常用的注解，在主类上标注即可自动完成配置、组件扫描等任务。

---

## 2. `@Configuration` —— 配置类（等同于 XML 配置）

* 表示当前类是一个**配置类**。
* 类中可以用 `@Bean` 方法声明 Bean。
* 是 `@Component` 的一种特殊形式，本质上是个 Bean。

示例：

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

---

## 3. `@ComponentScan` —— 包扫描器

* 作用是让 Spring 扫描指定包下的类，把带有 `@Component` / `@Service` / `@Repository` / `@Controller` 等注解的类注册为 Bean。
* 默认扫描的是当前类所在包及其子包。

示例：

```java
@ComponentScan(basePackages = {"com.example.service", "com.example.repo"})
```

---

## 4. `@EnableAutoConfiguration` —— 启用自动配置（Spring Boot 精髓）

* 是 Spring Boot 的自动配置核心。
* 会根据你当前依赖的 jar 包（如 spring-boot-starter-web），**自动配置 Web MVC、Tomcat、Jackson 等组件**。
* 内部会扫描类路径中的 `META-INF/spring.factories` 文件来找出需要自动配置的类。

比如：引入了 spring-boot-starter-web，它会自动配置：

* 内嵌 Tomcat
* DispatcherServlet
* Jackson
* 请求映射等

---

## 🔍 它们之间的关系图

```
@SpringBootApplication
   └── @Configuration         ✅ 是 Bean
   └── @ComponentScan         🔍 控制扫描 Bean
   └── @EnableAutoConfiguration  🔧 控制自动配置
```

---

## 🧠 总结一句话

> `@SpringBootApplication` 是启动入口，内部包含了注册配置类、自动扫描组件、自动配置环境的三大核心注解。其本质是简化配置和启动，让你写最少的代码做最多的事。

---

如果你还想了解：

* Spring Boot 是怎么根据你引入的依赖“猜”你需要什么配置？
* 哪些自动配置可以手动覆盖或关闭？
* 多模块项目下如何自定义 `@ComponentScan`？

我可以继续帮你深入讲解！要不要来点进阶的？
