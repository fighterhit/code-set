### 解释器模式
为语言创建解释器，通常由语言的语法和语法分析来定义。

![解释器模式](解释器模式.png)  

#### 场景
![场景](场景.png)

#### UML

![UML](UML.png)
- TerminalExpression：终结符表达式，每个终结符都需要一个 TerminalExpression。
- Context：上下文，包含解释器之外的一些全局信息。