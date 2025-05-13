### **Core Java 基础** — 总结、详细描述及示例

Core Java 基础涵盖了 Java 编程语言的核心部分，涉及到许多重要的概念和技术。这些是作为 Java 开发的基础，掌握了这些技术和概念，才能有效编写和优化 Java 应用。

以下是 **Core Java** 基础的几个主要知识点：

---

#### 1. **面向对象编程 (OOP)**

面向对象编程（OOP）是 Java 的核心编程范式。OOP 强调封装、继承、多态和抽象四大基本特性。

###### * **封装 (Encapsulation)**：将数据和操作数据的方法捆绑在一起，限制外部访问，提供控制访问权限的接口。

    * **例子**：通过 `private` 限制成员变量的访问，使用 `public` 的 getter 和 setter 方法来访问和修改私有变量。

      ```java
      class Car {
          private String model;
          
          public String getModel() {
              return model;
          }
          
          public void setModel(String model) {
              this.model = model;
          }
      }
      ```

###### * **继承 (Inheritance)**：通过继承，可以创建一个子类，继承父类的属性和方法，减少代码重复。

    * **例子**：`Dog` 类继承了 `Animal` 类，能够使用父类的方法和属性。

      ```java
      class Animal {
          void sound() {
              System.out.println("Animal makes a sound");
          }
      }
  
      class Dog extends Animal {
          void sound() {
              System.out.println("Dog barks");
          }
      }
      ```

###### * **多态 (Polymorphism)**：允许对象的不同实例以相同的方式响应相同的消息。

    * **例子**：子类可以重写父类的方法，根据实际对象类型来调用不同的实现。

      ```java
      Animal animal = new Dog();
      animal.sound();  // 输出 "Dog barks"
      ```
有！而且**非常实用**，几乎所有**大型项目**和**面试题**都会考多态。
我帮你总结成**一句话+表格+例子**，一口气让你彻底懂：

---

## ✅ **一句话总结**

> **多态** = 「写一次调用代码，适配N个不同实现」
> 👉 **统一调用**，**方便扩展**，**减少修改**！

---

## ✅ **多态的核心好处**

| 🚩 **好处**  | 🚀 **说明**                  |
| ---------- | -------------------------- |
| **统一调用**   | 父类/接口引用 ➡️ 子类自己决定怎么做       |
| **方便扩展**   | 新增子类时，**旧代码不动**            |
| **减少代码重复** | 调用代码**一套搞定**，不用每个子类写一遍     |
| **实现开闭原则** | **对扩展开放**，**对修改关闭**（OCP原则） |
| **逻辑更清晰**  | 把**调用**和**具体实现**分开，结构更干净   |

---


比如：

> 你说：「给我支付100元」
>
> * 用**微信**：扫码
> * 用**支付宝**：扣余额
> * 用**Apple Pay**：刷FaceID

**你只管下命令**（pay），**执行细节**不同人（子类）各自搞定。
👉 这就叫**多态**！

---

```java
abstract class Payment {
    abstract void pay(double amount);
}

class WeChatPay extends Payment {
    void pay(double amount) {
        System.out.println("微信支付：" + amount + "元");
    }
}

class Alipay extends Payment {
    void pay(double amount) {
        System.out.println("支付宝支付：" + amount + "元");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment p1 = new WeChatPay();
        Payment p2 = new Alipay();

        p1.pay(100);  // 微信支付：100元
        p2.pay(200);  // 支付宝支付：200元
    }
}
```

👉 **同一个 `pay()` 调用**，**结果因对象不同而变化**。这就是**多态**。

---

## ✅ **多态特别适合哪些场景？**

| 场景                   | 多态帮你做啥                            |
| -------------------- | --------------------------------- |
| 支付、消息推送、登录这种**多种渠道** | 用统一方法 `send()` / `pay()`，底下适配不同子类 |
| 游戏里**不同怪物/角色**行为     | 调用 `attack()`，各怪物表现不一样            |
| UI里**不同按钮/控件**点击效果   | 点击事件 `onClick()`，每个控件自己实现         |

---

### 🔥 口诀帮你记

> **多态** = 「统一调用，子类各显神通」
> 👉 写一次，跑遍全场，方便、扩展、干净！

---


###### * **抽象 (Abstraction)**：隐藏实现细节，只暴露接口，使得用户可以通过接口与类进行交互。

    * **例子**：通过抽象类或接口定义通用的行为，具体的实现留给子类。

      ```java
      abstract class Animal {
          abstract void sound();
      }
  
      class Dog extends Animal {
          void sound() {
              System.out.println("Dog barks");
          }
      }
      ```
抽象类 + 抽象方法 =
“我（父类）规定了你（子类）必须实现某个功能，但具体怎么做，你自己决定。”
    

 ### 为什么用abstract
    好问题！👍
知道“怎么用”只是入门，**知道“什么时候用”才是会用**。
我给你讲得又简单又实际，不绕弯子：

---

## 📚 抽象方法**具体用在什么情况下？**

### 1. 父类可以规定一套标准，但自己**不知道怎么具体实现**

> **场景**：父类知道有些事情必须做，但每个子类做法不一样。
> **例子**：动物都会叫（makeSound），但猫、狗、鸡叫声不一样！

```java
abstract class Payment {
abstract void pay(double amount);  // 抽象方法，强制子类实现
}

class WeChatPay extends Payment {
@Override
void pay(double amount) {
System.out.println("用微信支付：" + amount + "元");
}
}

class Alipay extends Payment {
@Override
void pay(double amount) {
System.out.println("用支付宝支付：" + amount + "元");
}
}

public class Main {
public static void main(String[] args) {
Payment p1 = new WeChatPay();
p1.pay(100);

        Payment p2 = new Alipay();
        p2.pay(200);
    }
}
```
结果:用微信支付：100.0元
用支付宝支付：200.0元
👉 子类必须实现 pay()，不然编译器直接报错。

✅ 这时候父类写抽象方法，让子类自己去写细节。

---

### 2. 需要**统一接口**，**规范子类的行为**/可以强制子类定义方法

> **场景**：你要设计一个系统，要求所有子类都有一套固定的功能。

比如做一个**支付系统**：

* 不管是支付宝、微信，还是银行卡，**都要有 `pay()` 方法**。
* 抽象类就是提前把`pay()`定好，***防止有人写错或者漏写。***

✅ 抽象方法保证了每种支付方式都有基本功能。

---

### 3. 需要**扩展方便**，**新增种类不动旧代码**/扩展方便，新加类型不动旧代码

> **场景**：以后可能要增加新的类型，但不想改以前写好的代码。

比如以后增加Apple Pay，只要继承抽象类，补充实现方法就行，旧代码不用动。

✅ 抽象方法配合继承，多态就可以轻松扩展。

---

### 4. 只想给子类提供**模板**，**但父类自己不想被实例化**/不希望父类被直接new出来
```java
abstract class Animal {
//👉 Animal 这个类不能new，保护了设计逻辑：动物应该是具体的（狗/猫），不能是抽象的“Animal”
    abstract void makeSound();
}

class Dog extends Animal {
@Override
void makeSound() {
System.out.println("汪汪！");
}
}

public class Main {
public static void main(String[] args) {
// Animal a = new Animal(); // ❌ 错误：抽象类不能被实例化

        Animal dog = new Dog();
        dog.makeSound();  // 输出：汪汪！
    }
}
```

> **场景**：有些类就是模板，不应该直接创建对象。

比如：

* 你不想有人直接new一个Animal出来（动物应该具体是猫还是狗）
* 那就把Animal做成抽象类，禁止直接new。

✅ 抽象类保护了系统设计合理性。

---

## 🧠 总结一句话记住：

> **抽象方法**用于\*\*"我知道需要做什么，但不知道怎么做"，让子类自己去实现；而且保证所有子类必须遵守统一标准。\*\*

---

## 🌟 小对照表（超好记）

| 想要达到什么？          | 是否用抽象方法？         |
| ---------------- | ---------------- |
| 统一规范，子类必须实现      | ✅ 用抽象方法          |
| 不希望父类被直接new出来    | ✅ 用抽象类           |
| 需要扩展新功能，不动旧代码    | ✅ 用抽象方法和多态       |
| 父类自己能完成事情，不要子类参与 | ❌ 不用抽象方法，直接写普通方法 |


---

#### 2. **集合框架 (Collections Framework)**

Java 提供了强大的集合框架来处理数据集合。主要有几种重要的接口和类，如 `List`, `Set`, `Map`, 以及它们的实现类。

* **List**：有序的元素集合，允许重复元素，常用的实现类有 `ArrayList` 和 `LinkedList`。

    * **例子**：创建一个 `ArrayList` 并添加一些元素。

      ```java
      List<String> list = new ArrayList<>();
      list.add("Java");
      list.add("Python");
      list.add("JavaScript");
      System.out.println(list);  // 输出 [Java, Python, JavaScript]
      ```

* **Set**：不允许重复元素，常用的实现类有 `HashSet` 和 `TreeSet`。

    * **例子**：创建一个 `HashSet` 并添加元素（重复的元素会被自动去除）。

      ```java
      Set<String> set = new HashSet<>();
      set.add("Java");
      set.add("Python");
      set.add("Java");
      System.out.println(set);  // 输出 [Java, Python]
      ```

* **Map**：存储键值对的集合，键不可重复，值可以重复。常用的实现类有 `HashMap` 和 `TreeMap`。

    * **例子**：创建一个 `HashMap` 并使用键值对存储数据。

      ```java
      Map<String, String> map = new HashMap<>();
      map.put("Java", "A high-level programming language");
      map.put("Python", "A dynamic, interpreted language");
      System.out.println(map);  // 输出 {Java=A high-level programming language, Python=A dynamic, interpreted language}
      ```

---

#### 3. **Java 8 新特性**

Java 8 引入了许多重要的新特性，其中最具代表性的是 Lambda 表达式、Stream API 和默认方法。

* **Lambda 表达式**：允许将功能作为参数传递给方法，或者在需要时创建函数式接口的实例。

    * **例子**：使用 Lambda 表达式简化集合的迭代。

      ```java
      List<String> list = Arrays.asList("Java", "Python", "C++");
      list.forEach(item -> System.out.println(item));
      ```

* **Stream API**：用于处理集合的 API，提供了一种声明性的数据处理方式，支持链式操作。

    * **例子**：使用 Stream 对集合进行过滤和转换操作。

      ```java
      List<String> list = Arrays.asList("Java", "Python", "JavaScript");
      list.stream()
          .filter(s -> s.startsWith("J"))
          .forEach(System.out::println);  // 输出 Java, JavaScript
      ```

* **默认方法**：接口中可以有带有实现的方法，避免了需要为每个实现类都定义该方法的麻烦。

    * **例子**：定义接口时可以直接实现方法，而不必在每个实现类中重新编写。

      ```java
      interface Animal {
          default void sound() {
              System.out.println("Animal makes a sound");
          }
      }
  
      class Dog implements Animal {
          // 无需实现 sound 方法，Dog 类自动继承了接口中的默认方法
      }
      ```
### interface和abstract的区别
理解接口（`interface`）和抽象类（`abstract class`）确实有点复杂，尤其是它们的**区别**和**使用场景**。我会通过**表格对比**、**具体代码示例**和**实际应用**来帮助你更清晰地理解这两者。

---

### ✅ **接口和抽象类的对比**

| 特点            | **接口 (`interface`)**                  | **抽象类 (`abstract class`)**                    |
| ------------- | ------------------------------------- | --------------------------------------------- |
| **能否包含方法实现**  | ✅ 不能包含实现（Java 8 后，可以用 `default` 实现方法） | ✅ 可以有方法实现（即，父类实现一部分功能）                        |
| **能否有成员变量**   | ❌ 只能有 `public static final` 常量        | ✅ 可以有普通成员变量（`private`, `protected`, `public`） |
| **是否能有构造函数**  | ❌ 不能有构造函数                             | ✅ 可以有构造函数                                     |
| **是否能继承多个父类** | ✅ 可以实现多个接口                            | ❌ 只能继承一个类（Java 单继承）                           |
| **是否有默认行为**   | ✅ Java 8 后可以有 `default` 方法            | ✅ 也可以有默认方法，但通常不建议依赖于父类的默认行为                   |
| **是否适合多态**    | ✅ 适合多态                                | ✅ 适合多态，但**通常不支持混入多种实现**，通常**用于基类**            |
| **适用场景**      | 👨‍💻 **不同类之间的公共规范**                  | 🛠️ **具有共同基类的类层次结构，需要共享部分实现**                 |

---

### ✅ **代码示例：接口和抽象类的区别**

#### **接口示例：**

```java
// 定义接口
interface Payable {
    void pay(double amount);  // 没有实现，子类必须实现这个方法
}

// 实现接口的子类
class WeChatPay implements Payable {
    @Override
    public void pay(double amount) {
        System.out.println("使用微信支付：" + amount + "元");
    }
}

class Alipay implements Payable {
    @Override
    public void pay(double amount) {
        System.out.println("使用支付宝支付：" + amount + "元");
    }
}

public class Main {
    public static void main(String[] args) {
        Payable p1 = new WeChatPay();
        p1.pay(100);  // 使用微信支付：100元
        
        Payable p2 = new Alipay();
        p2.pay(200);  // 使用支付宝支付：200元
    }
}
```

**关键点：**

* 接口只规定了方法 `pay()`，没有方法实现。
* 任何类都可以实现 `Payable` 接口，但\*\*强制实现 `pay()`\*\*方法。

---

#### **抽象类示例：**

```java
// 定义抽象类
abstract class Payment {
    abstract void pay(double amount);  // 抽象方法，子类必须实现

    void logPayment(double amount) {  // 普通方法，父类可以实现
        System.out.println("支付金额：" + amount + "元");
    }
}

// 子类实现抽象类
class WeChatPay extends Payment {
    @Override
    void pay(double amount) {
        logPayment(amount);  // 调用父类的日志功能
        System.out.println("使用微信支付：" + amount + "元");
    }
}

class Alipay extends Payment {
    @Override
    void pay(double amount) {
        logPayment(amount);  // 调用父类的日志功能
        System.out.println("使用支付宝支付：" + amount + "元");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment p1 = new WeChatPay();
        p1.pay(100);  // 支付金额：100元，使用微信支付：100元
        
        Payment p2 = new Alipay();
        p2.pay(200);  // 支付金额：200元，使用支付宝支付：200元
    }
}
```

**关键点：**

* 抽象类可以包含已经实现的方法（如 `logPayment()`），所以子类可以复用父类的代码。
* 子类必须实现抽象方法（如 `pay()`），否则会报错。

---

### ✅ **接口和抽象类的使用场景**

| 场景             | 使用接口 (`interface`)                  | 使用抽象类 (`abstract class`)       |
| -------------- | ----------------------------------- | ------------------------------ |
| **多种实现规范**     | ✅ 适合实现「不相关类」之间的公共规范                 | ❌ 不适合，抽象类不能多继承（Java单继承）        |
| **方法实现部分共享代码** | ❌ 接口不能提供实现，**每个实现类必须自己实现方法**        | ✅ 抽象类可以提供部分方法实现，子类可以复用父类的代码    |
| **类之间有父子关系**   | ❌ 不能实现类之间的继承关系                      | ✅ 适合构建类的层次结构，如父类定义通用行为，子类进行扩展  |
| **灵活性**        | ✅ 接口可以实现多个，支持多继承                    | ❌ 只支持单继承，不适合类之间的复杂继承关系         |
| **需要强制规范行为**   | ✅ 需要保证实现类遵循一定行为规范（如支付方式必须有 `pay()`） | ✅ 适合在类之间共享行为或部分实现，尤其是在同一个层次结构下 |

---

### ✅ **简化记忆：**

| **接口**           | **抽象类**            |
| ---------------- | ------------------ |
| 只规定行为，不给实现       | 既可以给行为的规范，也可以给部分实现 |
| 不关心如何做，只规定做什么    | 关注共性，提供部分功能实现      |
| 不允许构造函数          | 可以有构造函数            |
| 允许多重实现（多个接口可以实现） | 只能单继承（只能继承一个类）     |

---

### 🎯 **总结**

* **接口**：用来**规定行为规范**，适用于那些没有直接父子关系但需要统一规范的情况，比如支付接口 `Payable`，所有支付方式都可以实现它。
* **抽象类**：用来**定义一个基础类**，多个类之间有共性时，可以共享一些方法和字段。

---

#### 4. **异常处理 (Exception Handling)**

Java 提供了异常处理机制，用于处理程序中的运行时错误。

* **常见异常类型**：

    * `Checked Exception`：编译时异常（需要处理）。
    * `Unchecked Exception`：运行时异常（可选处理）。
* **try-catch-finally 语句**：用于捕获异常并处理，确保程序在出现异常时能够继续运行。

    * **例子**：捕获并处理 `ArithmeticException`。

      ```java
      try {
          int result = 10 / 0;
      } catch (ArithmeticException e) {
      //可以这样写：catch 所有异常
      //try { 
      //int result = 10 / 0;
      //} catch (Exception e) {  // Exception 是所有异常的爸爸
      //System.out.println("发生了某种异常：" + e.getMessage());
      //}
      //Exception 可以捕获几乎所有异常（包括 ArithmeticException、NullPointerException 等）。
      // 但：更推荐具体捕获，这样代码更安全、清晰。
          System.out.println("Error: " + e.getMessage());
      } finally {
          System.out.println("Execution completed.");
      }
      ```

---

#### 5. **并发 (Concurrency)**

Java 提供了多线程编程能力，用于开发高性能的并发应用。

* **Thread 类**：用于创建和管理线程。
* **Runnable 接口**：一种实现线程的方式。
* **ExecutorService**：用于更高效地管理线程池。

    * **例子**：使用 `ExecutorService` 创建线程池。

      ```java
      ExecutorService executor = Executors.newFixedThreadPool(2);
      executor.submit(() -> {
          System.out.println("Task 1");
      });
      executor.submit(() -> {
          System.out.println("Task 2");
      });
      executor.shutdown();
      ```

---

#### 6. **输入输出 (I/O)**

Java 提供了丰富的 I/O 操作类，用于读取和写入文件、网络操作等。

* **File 类**：用于文件的创建、删除、重命名等操作。
* **InputStream 和 OutputStream**：用于字节流处理，适用于所有类型的数据文件。
* **Reader 和 Writer**：用于字符流处理，适用于文本文件。

    * **例子**：使用 `FileInputStream` 读取文件内容。

      ```java
      try (FileInputStream fis = new FileInputStream("example.txt")) {
          int content;
          while ((content = fis.read()) != -1) {
              System.out.print((char) content);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      ```

---

### Java 8+ Features

Streams API

Lambdas

Functional Interfaces

Optional
