package design_pattern.State;

/**
 * 状态接口
 */
public interface State {
    void handle();
}

/**
 * 空闲状态
 */
class FreeState implements State {
    @Override
    public void handle() {
        System.out.println("目前是空闲状态!");
    }
}

/**
 * 已预订状态
 */
class BookedState implements State {
    @Override
    public void handle() {
        System.out.println("目前是已预订状态!");
    }
}

/**
 * 已入住状态
 */
class CheckedInState implements State {
    @Override
    public void handle() {
        System.out.println("目前是已入住状态");
    }
}



