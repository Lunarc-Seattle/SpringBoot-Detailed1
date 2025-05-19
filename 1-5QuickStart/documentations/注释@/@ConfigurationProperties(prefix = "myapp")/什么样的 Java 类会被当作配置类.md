# 🔚 总结一句话

你问的「是不是加了 `@Configuration` 的类就代表是配置文件？」答案是：

> ❌ **不是！**
> `@Configuration` 是“Java 配置类”
> 真正代表配置文件的，是 `application.yml` 或 `application.properties`，它们会通过 `@ConfigurationProperties` 被读取到 Java 类中。

---


「什么样的 Java 类会被当作配置类」
## ✅ 第一部分：你说的「配置文件」是 **application.yml / application.properties**

这个是 **真正的配置文件**，放在 `src/main/resources/` 下面。

比如：

```yaml
myapp:
  name: 奶茶屋
  timeout: 3000
```

这是配置文件的内容，不是 Java 类。
