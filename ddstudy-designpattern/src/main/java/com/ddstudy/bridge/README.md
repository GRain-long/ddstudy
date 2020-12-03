## 桥接模式（Bridge Pattern）

![桥接模式](https://github.com/GRain-long/ddstudy/blob/dev/ddstudy-designpattern/src/main/resources/image/bridge.png)

### 定义

​将抽象和实现解耦，使得两者可以独立地变化。

### 说明

桥接模式属于结构型模式的一种，该模式主要包含4个角色：

- Abstraction-----抽象化角色：一般为抽象类，主要定义该角色的行为，同时保存对一个实现化角色的引用。
- Implementor-----实现化角色：为接口或抽象类，定义角色必须的行为和属性。
- RefinedAbstraction-----修正抽象化角色：引用实现化角色对抽象话角色进行修正。
- ConcreteImplementor-----具体实现化角色：实现接口或抽象类定义的方法和属性。


### 优点

- 抽象和实现分离，在该模式下，实现可以不受抽象的约束，不用再绑定在一个固定的抽象层次上。

- 优秀的扩展能力，因抽象与实现分离，非常易于扩展。


### 缺点

- 桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。 

### 使用场景

- 接口或抽象类不稳定的场景。

- 适用于有两个独立变化的维度。




