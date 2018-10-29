package design_pattern.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式实现杀毒软件架构
 *
 * @author Fighter.
 */
public interface AbstractFile {
    void killVirus();
}

class ImageFile implements AbstractFile {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("正在查杀图像文件：" + name);
    }
}


class TextFile implements AbstractFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("正在查杀文本文件：" + name);
    }
}

class Folder implements AbstractFile {

    private String name;

    //存本容器下的子节点
    private List<AbstractFile> list = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    void add(AbstractFile c) {
        list.add(c);
    }

    void remove(AbstractFile c) {
        list.remove(c);
    }

    AbstractFile getChild(int index) {
        return list.get(index);
    }

    @Override
    public void killVirus() {
        for (AbstractFile file : list) {
            file.killVirus();
        }
    }
}