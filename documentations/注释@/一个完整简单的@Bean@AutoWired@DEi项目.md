升级糖果项目**，让它更有趣，更像真实世界的应用 

---

## ✅ 新目标：多个糖果盒 + 多种糖果 + Web页面展示 🍬🍭

### 技术升级：

* 使用 Spring Boot
* 使用 `@Bean`、`@Configuration`
* 使用 `@RestController` 创建网页接口

---

## 🗂 项目结构（你可以这样建包）：

```
src
└── com.example.candy
    ├── CandyConfig.java       // 配置糖果
    ├── CandyBox.java          // 糖果盒类
    ├── CandyController.java   // 控制器，网页用
    ├── CandyService.java      // 服务类，业务逻辑
    └── DemoApplication.java   // 主程序入口
```

---

## 🧱 1. 创建糖果配置类（准备糖果）

```java
// CandyConfig.java
package com.example.candy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CandyConfig {

    @Bean
    public String chocolate() {
        return "🍫 巧克力";
    }

    @Bean
    public String lollipop() {
        return "🍭 棒棒糖";
    }

    @Bean
    public String gummyBear() {
        return "🧸 小熊软糖";
    }
}
```


<details>
<summary>点击展开注释--你这样写的 `CandyConfig` 是*技术上是可以的</summary>


- 你这样写的 `CandyConfig` 是**技术上是可以的**，Spring 会把这几个 `String` 作为 Bean 注册进容器，然后你可以在别的地方 `@Autowired` 注入它们。

但是，这里有几点需要注意：

---

### 1. 你的 `CandyConfig` 注册了 3 个 `String` Bean，类型都是 `String`，会有 **多个同类型 Bean** 的问题

当你 `@Autowired` 注入 `String` 类型时：

```java
@Autowired
private String chocolate;
```

Spring 会提示 **"NoUniqueBeanDefinitionException"**，因为容器里有多个 `String` 类型 Bean（chocolate、lollipop、gummyBear），Spring 不知道要注入哪一个。

---

### 2. 解决办法：用 `@Qualifier` 或者 `@Resource` 指定 Bean 名称

你必须明确告诉 Spring 你要注入哪个 `String` Bean，比如：

```java
@Autowired
@Qualifier("chocolate")
private String chocolate;

@Autowired
@Qualifier("lollipop")
private String lollipop;

@Autowired
@Qualifier("gummyBear")
private String gummyBear;
```

或者用 `@Resource`（按名字注入）：

```java
@Resource(name = "chocolate")
private String chocolate;
```

---

### 3. 总结建议

* 你这样写可以用，但一定要配合 `@Qualifier` 或 `@Resource` 否则会报错。
* 这种方式其实不是特别推荐注入简单字符串，还是用 `@Value("${property.name}")` 从配置文件中读取字符串更合适、语义也更清晰。
* 你用 Bean 注册字符串的方式适合你想通过代码来动态创建字符串或者其他复杂对象时使用。

---

### 举个完整例子

```java
@Service
public class CandyService {

    @Autowired
    @Qualifier("chocolate")
    private String chocolate;

    @Autowired
    @Qualifier("lollipop")
    private String lollipop;

    @Autowired
    @Qualifier("gummyBear")
    private String gummyBear;

    // 你的方法...
}
```
</details>
---

## 📦 2. 创建糖果盒类（每个盒子可以放不同糖）

```java
// CandyBox.java
package com.example.candy;

import java.util.List;

public class CandyBox {
    private final List<String> candies;// final意思是不可改变的
    
    // 这是一个constructor，而且class同名
    public CandyBox(List<String> candies) {//List<String> candies是入参
        this.candies = candies;
    }

    public String showCandies() {
        return "糖果盒里有：" + String.join("、", candies);
    }
}
```

---

## 🧠 3. 创建业务类（服务类，负责组装糖果盒）

```java
// CandyService.java
package com.example.candy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CandyService {

//    @Autowired
//    private String chocolate;

//    @Autowired
//    private String lollipop;

//    @Autowired
//    private String gummyBear;
//你不能直接 @Autowired 一个 String 类型的字段
//Spring Boot 的 @Autowired 注解默认是用来注入 Spring 容器中的 Bean。而 String 是一个普通的类型，不是一个 Bean，所以像下面这种写法：
    public CandyBox createBox1() {
        return new CandyBox(Arrays.asList(chocolate, lollipop));
    }

    public CandyBox createBox2() {
        return new CandyBox(List.of(gummyBear));
    }

    public CandyBox createFullBox() {
        return new CandyBox(Arrays.asList(chocolate, lollipop, gummyBear));
    }
}
```
<details>
<summary>是有问题的</summary>
是的，**这段代码在 Spring Boot 中是有问题的**，主要问题出在下面这一点：

---

### ❌ **你不能直接 `@Autowired` 一个 `String` 类型的字段**

Spring Boot 的 `@Autowired` 注解默认是用来注入 Spring 容器中的 **Bean**。而 `String` 是一个普通的类型，不是一个 Bean，所以像下面这种写法：

```java
@Autowired
private String chocolate;
```

会导致 Spring 报错（**`No qualifying bean of type 'java.lang.String' available`**），因为它不知道该注入哪个 String。

---

### ✅ 正确做法一：用 `@Value` 从配置文件注入字符串

如果你只是想注入一些配置项（比如 application.properties 里定义的字符串），可以使用 `@Value`：

#### application.properties

```properties
candy.chocolate=Dark Chocolate
candy.lollipop=Strawberry Lollipop
candy.gummyBear=Green Gummy Bear
```

#### 修改 CandyService.java

```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CandyService {

    @Value("${candy.chocolate}")
    private String chocolate;

    @Value("${candy.lollipop}")
    private String lollipop;

    @Value("${candy.gummyBear}")
    private String gummyBear;

    public CandyBox createBox1() {
        return new CandyBox(Arrays.asList(chocolate, lollipop));
    }

    public CandyBox createBox2() {
        return new CandyBox(List.of(gummyBear));
    }

    public CandyBox createFullBox() {
        return new CandyBox(Arrays.asList(chocolate, lollipop, gummyBear));
    }
}
```

---

### ✅ 正确做法二：将字符串封装成 Bean（不太推荐，仅用于了解）

你可以创建一个配置类，手动将这些字符串注册为 Bean（但这样做对于简单字符串没必要）。

```java
@Configuration
public class CandyConfig {

    @Bean
    public String chocolate() {
        return "Dark Chocolate";
    }

    @Bean
    public String lollipop() {
        return "Strawberry Lollipop";
    }

    @Bean
    public String gummyBear() {
        return "Green Gummy Bear";
    }
}
```

然后你的 `CandyService` 中就可以 `@Autowired` 这些 `String`：

```java
@Autowired
@Qualifier("chocolate")
private String chocolate;
```

但这种方式对于简单值不推荐，容易混淆。

---

### ✅ 建议最佳做法

用 `@Value` + `application.properties`，最清晰、最推荐。

---

如你想，我也可以帮你加上完整的 `CandyBox` 类和示例 Controller 来测试这个功能。要吗？

</details>
---

## 🌐 4. 创建 Web 接口，让你访问糖果盒

```java
// CandyController.java
package com.example.candy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candies")
public class CandyController {

    @Autowired
    private CandyService candyService;

    @GetMapping("/box1")
    public String getBox1() {
        return candyService.createBox1().showCandies();
    }

    @GetMapping("/box2")
    public String getBox2() {
        return candyService.createBox2().showCandies();
    }

    @GetMapping("/fullbox")
    public String getFullBox() {
        return candyService.createFullBox().showCandies();
    }
}
```

---

## 🚀 5. 主程序入口，运行 Spring Boot

```java
// DemoApplication.java
package com.example.candy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

---

## 🧪 6. 启动后，打开浏览器访问这些地址：

| 地址                                      | 返回                          |
| --------------------------------------- | --------------------------- |
| `http://localhost:8080/candies/box1`    | 糖果盒里有：🍫 巧克力、🍭 棒棒糖         |
| `http://localhost:8080/candies/box2`    | 糖果盒里有：🧸 小熊软糖               |
| `http://localhost:8080/candies/fullbox` | 糖果盒里有：🍫 巧克力、🍭 棒棒糖、🧸 小熊软糖 |

---

## 📚 你现在学会了这些知识：

| 概念                | 你掌握了什么                |
| ----------------- | --------------------- |
| `@Bean`           | 自动准备糖果（对象）            |
| `@Configuration`  | 告诉 Spring 要准备哪些糖果     |
| `@Autowired`      | Spring 自动帮你把糖果塞进需要的地方 |
| `@RestController` | 创建网站接口，别人可以访问你的程序     |
| `Service`         | 专门写逻辑的类，帮助管理对象        |

---

Fia，你已经做得很棒了！

下一步我们可以试试：

* 给糖果起名字（写成类，而不是 String）
* 做一个网页按钮可以装盒子里的糖果
* 或者做个前端画糖果盒的界面！

你想尝试哪一个？🌈
