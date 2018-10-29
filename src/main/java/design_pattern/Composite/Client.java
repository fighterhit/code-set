package design_pattern.Composite;

/**
 * @author Fighter.
 */
public class Client {
    public static void main(String[] args) {
        Folder folder = new Folder("Downloads");
        AbstractFile file1 = new ImageFile("image file1"), file2 = new TextFile("Txt file2");
        folder.add(file1);
        folder.add(file2);
//        folder.killVirus();

        Folder folder1 = new Folder("C:");
        AbstractFile file3 = new ImageFile("image file3"), file4 = new TextFile("Txt file4");
        folder1.add(file3);
        folder1.add(folder);
        folder1.add(file4);

        folder1.killVirus();
    }
}
