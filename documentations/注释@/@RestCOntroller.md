# 当我写了@GetMapping，但没写@RestController -- 为什么404


---

## ✅ `@RestController` 是什么？

这是 Spring Boot 里的一个注解，用来标记一个类是\*\*"控制器（Controller）"**，而且是用来返回**数据（通常是 JSON）\*\*的，而不是返回网页。

它是下面两个注解的组合：

```java
@RestController = @Controller + @ResponseBody
```

---

## 🧠 用类比来理解：

你可以把一个 `@RestController` 理解成是一个“**服务窗口**”，比如：

* 你访问 `/hello`，它就“回应”你一个字符串或者 JSON 数据；
* 它不会返回网页，也不会跳转页面，而是直接把数据甩给你。

---

## ✅ 一个最简单的例子：

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Fia!";
    }
}
```

### 上面这个例子做了什么？

* 这个类是个控制器（`@RestController`）
* 里面有个接口：当你访问 `http://localhost:8080/hello`
* 它就会在浏览器或 Postman 中显示：

  ```
  Hello, Fia!
  ```

---

## ✅ 如果你返回的是一个对象，它会自动变成 JSON：

```java
@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        return new User("Fia", 20);
    }
}

class User {
    private String name;
    private int age;

    // 构造函数、getter、setter（不能少！）

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}
```

### 返回结果是 JSON：

```json
{
  "name": "Fia",
  "age": 20
}
```

---

## ✅ 总结一句话：

| 注解                | 用途                          |
| ----------------- | --------------------------- |
| `@Controller`     | 用于返回网页（配合 HTML 页面）          |
| `@RestController` | 用于返回数据（通常是 JSON），写 API 接口时用 |
| `@ResponseBody`   | 把返回值直接写到 HTTP 响应体中          |

> 如果你要写前后端分离、提供 API 接口，就用 `@RestController`。

---

如果你告诉我你项目是在写网页、写接口，还是只是练习，我可以给你定制个小模板。
