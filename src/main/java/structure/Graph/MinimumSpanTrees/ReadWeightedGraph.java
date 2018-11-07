package structure.Graph.MinimumSpanTrees;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadWeightedGraph {
    private Scanner scanner;

    public ReadWeightedGraph(WeightedGraph<Double> graph, String filename) {
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
                Double weight = scanner.nextDouble();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(new Edge<>(v, w, weight));
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
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "G:\\IdeaProjects\\code-set\\src\\main\\java\\structure\\Graph\\MinimumSpanTrees\\testG1.txt";
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<Double>(8, false);
        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename);
        System.out.println("test G1 in Sparse Weighted Graph:");
        g1.show();

        System.out.println();

        DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<Double>(8, false);
        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2, filename);
        System.out.println("test G1 in Dense Graph:");
        g2.show();

        System.out.println();
    }
}
