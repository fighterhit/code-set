package design_pattern.Command;

/**
 * 调用者/发起者
 */
public class Invoke {
    //也可通过容器List<Command> 容纳更多命令对象，进行处理（批处理）。数据库底层事务管理就是类似结构
    private Command command;

    public Invoke(Command command) {
        this.command = command;
    }

    //业务方法，用于调用命令类的方法
    public void call() {
        command.execute();
    }
}
