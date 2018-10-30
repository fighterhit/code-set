package design_pattern.Mediator;

/**
 * @author Fighter.
 */
public class Finacial implements Department {

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
