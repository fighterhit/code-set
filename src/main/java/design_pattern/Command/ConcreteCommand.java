package design_pattern.Command;

/**
 * @author Fighter.
 */
public class ConcreteCommand implements Command {
    //命令真正执行者
    Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //命令执行前后进行相关处理
        receiver.action();
    }
}
