完整的 Spring Boot 示例项目结构，展示如何使用 `@ConfigurationProperties` 来绑定配置文件中的属性。

---

## 🧱 项目结构

```
myapp/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/myapp/
│       │       ├── MyAppApplication.java
│       │       ├── config/
│       │       │   └── MyAppProperties.java
│       │       └── service/
│       │           └── MyService.java
│       └── resources/
│           └── application.yml
├── pom.xml
```

---

## 1️⃣ `application.yml`

```yaml
myapp:
  name: MySpringBootApp
  timeout: 3000
  servers:
    - 127.0.0.1
    - 192.168.0.1
  credentials:
    username: admin
    password: secret
```

---

## 2️⃣ `MyAppProperties.java`

```java
package com.example.myapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "myapp")
public class MyAppProperties {
    private String name;
    private int timeout;
    private List<String> servers;
    private Credentials credentials;

    // Getters and Setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTimeout() { return timeout; }
    public void setTimeout(int timeout) { this.timeout = timeout; }

    public List<String> getServers() { return servers; }
    public void setServers(List<String> servers) { this.servers = servers; }

    public Credentials getCredentials() { return credentials; }
    public void setCredentials(Credentials credentials) { this.credentials = credentials; }

    public static class Credentials {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
```

---

## 3️⃣ `MyService.java`

```java
package com.example.myapp.service;

import com.example.myapp.config.MyAppProperties;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private final MyAppProperties props;

    public MyService(MyAppProperties props) {
        this.props = props;
    }

    public void printConfig() {
        System.out.println("App name: " + props.getName());
        System.out.println("Timeout: " + props.getTimeout());
        System.out.println("Servers: " + props.getServers());
        System.out.println("Username: " + props.getCredentials().getUsername());
        System.out.println("Password: " + props.getCredentials().getPassword());
    }
}
```

---

## 4️⃣ `MyAppApplication.java`

```java
package com.example.myapp;

import com.example.myapp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyAppApplication implements CommandLineRunner {

    @Autowired
    private MyService myService;

    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myService.printConfig();
    }
}
```

---

## 5️⃣ `pom.xml`（只列出关键部分）

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>myapp</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>MyApp</name>
    <description>Demo for @ConfigurationProperties</description>
    <properties>
        <java.version>17</java.version>
        <spring.boot.version>3.1.0</spring.boot.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

---

## ✅ 启动效果

运行后，你会在控制台看到：

```
App name: MySpringBootApp
Timeout: 3000
Servers: [127.0.0.1, 192.168.0.1]
Username: admin
Password: secret
```

---
