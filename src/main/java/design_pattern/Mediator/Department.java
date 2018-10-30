package design_pattern.Mediator;

/**
 * Colleague
 */
public interface Department {

    //本部门做的事情
    void selfAction();

    //向总经理发出申请
    void outAction();
}
