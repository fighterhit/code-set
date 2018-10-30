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

class Development implements Department {
    //持有中介者引用
    Mediator m;

    public Development(Mediator mediator) {
        this.m = mediator;
        m.register("dev", this);
    }

    @Override
    public void selfAction() {
        System.out.println("研发ing...");

    }

    @Override
    public void outAction() {
        System.out.println("汇报：研发资金紧缺！");
        //通过中介者调用finacial部门self方法
        m.command("finacial");
    }
}

class Finacial implements Department {
    Mediator m;

    public Finacial(Mediator mediator) {
        m = mediator;
        m.register("finacial", this);
    }

    @Override
    public void selfAction() {
        System.out.println("发钱ing...");
    }

    @Override
    public void outAction() {
        System.out.println("汇报：财务资金紧缺！");
    }
}