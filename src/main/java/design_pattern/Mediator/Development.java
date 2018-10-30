package design_pattern.Mediator;

/**
 * @author Fighter.
 */
public class Development implements Department {

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
