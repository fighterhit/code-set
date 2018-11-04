package structure.Graph;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadGraph {
    private Scanner scanner;

    public ReadGraph(Graph graph, String filename) {
        readFile(filename);
        try {
            int V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            assert V == graph.V();
            int E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            }
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(v, w);
            }
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
    }

    private void readFile(String filename) {
        assert filename != null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                scanner = new Scanner(file, "UTF-8");
            } else {
                throw new IllegalArgumentException(filename + " doesn't exist.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not open " + filename, e);
        }
    }

    public static void main(String[] args) {
        String fileName = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG1.txt";

        SparseGraph sg1 = new SparseGraph(13, false);
        ReadGraph rg1 = new ReadGraph(sg1, fileName);
        System.out.println("test G1 in Sparse Graph:");
        sg1.show();
        System.out.println();

        DenseGraph dg1 = new DenseGraph(13, false);
        ReadGraph rg2 = new ReadGraph(dg1, fileName);
        System.out.println("test G1 in Dense Graph:");
        dg1.show();
        System.out.println();

        fileName = "/Users/fighter/IdeaProjects/code-set/src/main/java/structure/Graph/testG2.txt";
        SparseGraph sg2 = new SparseGraph(6, false);
        ReadGraph rg3 = new ReadGraph(sg2, fileName);
        System.out.println("test G2 in Sparse Graph:");
        sg2.show();
        System.out.println();

        DenseGraph dg2 = new DenseGraph(6, false);
        ReadGraph rg4 = new ReadGraph(dg2, fileName);
        System.out.println("test G1 in Dense Graph:");
        dg2.show();
        System.out.println();
    }
}
