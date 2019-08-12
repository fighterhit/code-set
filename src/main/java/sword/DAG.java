package sword;

import java.util.*;

public class DAG {
    enum Mode {
        SEQUENTIAL,
        CONCURRENT
    }

    static class Edge {

        Vertex l, r;
        Mode mode;

        public Vertex getL() {
            return l;
        }

        public void setL(Vertex l) {
            this.l = l;
        }

        public Vertex getR() {
            return r;
        }

        public void setR(Vertex r) {
            this.r = r;
        }

        public void setMode(Mode mode) {
            this.mode = mode;
        }

        Vertex getSource() {
            return l;
        }

        Vertex getTarget() {
            return r;
        }

        Mode getMode() {
            return null;
        }
    }

    static class Vertex {
        char c;

        public Vertex(char c) {
            this.c = c;
        }

        List<Edge> inputs = new ArrayList<>();
        List<Edge> outputs = new ArrayList<>();

        List<Edge> getInputs() {
            return inputs;
        }

        List<Edge> getOutputs() {
            return outputs;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    static Map<Vertex, Boolean> visited;
    static Collection<Collection<Vertex>> res = new HashSet<>();

    static Collection<Collection<Vertex>> getAllConcurrentSets2(List<Vertex> vertices) {
        if (vertices == null || vertices.size() == 0) {
            return res;
        }

        visited = new HashMap<>();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = vertices.get(i);
            if (visited.containsKey(v)) {
                continue;
            }
            visited.put(v, true);
            Queue<Vertex> queue = new LinkedList<>();
            ((LinkedList<Vertex>) queue).add(v);
            Set<Vertex> s = new HashSet<>();
            s.add(v);

            while (!queue.isEmpty()) {
                Vertex tmp = queue.poll();
                for (Edge input : tmp.getInputs()) {
                    if (input.mode == Mode.CONCURRENT) {
                        Vertex src = input.getSource();
                        s.add(src);
                        visited.put(src, true);
                        if (!visited.containsKey(src)) {
                            ((LinkedList<Vertex>) queue).add(src);
                        }
                    }
                }
                for (Edge output : tmp.getOutputs()) {
                    if (output.mode == Mode.CONCURRENT) {
                        Vertex dst = output.getTarget();
                        s.add(dst);
                        ((LinkedList<Vertex>) queue).add(dst);
                        visited.put(dst, true);
                        if (!visited.containsKey(dst)) {
                            ((LinkedList<Vertex>) queue).add(dst);
                        }
                    }
                }
            }
            res.add(s);
        }
        /*for (Collection<Vertex> re : res) {
            for (Vertex vertex : re) {
                System.out.print(vertex.c);
            }
            System.out.println();
        }*/
        return res;
    }


    static Collection<Collection<Vertex>> getAllConcurrentSets(List<Vertex> vertices) {
        Collection<Collection<Vertex>> res = new ArrayList<>();
        if (vertices == null || vertices.size() == 0) {
            return res;
        }

        //值为 set
        Map<Vertex, Set> v2map = new HashMap<>();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertices.get(i);
            Set<Vertex> conSetTmp = new HashSet<>();
            //seq
            conSetTmp.add(vertex);

            List<Edge> inputEdges = vertex.getInputs();
            List<Edge> outputEdges = vertex.getOutputs();

            for (Edge edge : inputEdges) {
                if (edge.mode == Mode.CONCURRENT) {
                    Vertex src = edge.getSource();
                    if (v2map.containsKey(vertex)) {
                        conSetTmp = v2map.get(vertex);
                    } else if (v2map.containsKey(src)) {
                        conSetTmp = v2map.get(src);
                    }
                    conSetTmp.add(vertex);
                    conSetTmp.add(src);
                    v2map.put(vertex, conSetTmp);
                    v2map.put(src, conSetTmp);
                }
            }

            for (Edge edge : outputEdges) {
                if (edge.mode == Mode.CONCURRENT) {
                    Vertex dst = edge.getTarget();
                    if (v2map.containsKey(vertex)) {
                        conSetTmp = v2map.get(vertex);
                    } else if (v2map.containsKey(dst)) {
                        conSetTmp = v2map.get(dst);
                    }
                    conSetTmp.add(vertex);
                    conSetTmp.add(dst);
                    v2map.put(vertex, conSetTmp);
                    v2map.put(dst, conSetTmp);
                }
            }

            if (!res.contains(conSetTmp)) {
                res.add(conSetTmp);
            }
        }

       /* for (Collection<Vertex> re : res) {
            for (Vertex vertex : re) {
                System.out.print(vertex.c);
            }
            System.out.println();
        }*/
        return res;
    }

    public static void main(String[] args) {
        HashSet<HashSet<Vertex>> set = new HashSet<>();


        Vertex a = new Vertex('a');
        Vertex aa = new Vertex('a');

        HashSet<Vertex> s1 = new HashSet<>();
        s1.add(a);
        s1.add(aa);

        int ah = a.hashCode(), aah = aa.hashCode();
        System.out.println(ah);
        System.out.println(aah);
        System.out.println(ah + aah);
        System.out.println(s1.hashCode());

        set.add(s1);

        Iterator<HashSet<Vertex>> i = set.iterator();
        HashSet s2 = i.next();
        System.out.println();
        System.out.println(s2.hashCode());
        Vertex aaa = new Vertex('a');
        System.out.println(aa.hashCode());
        System.out.println(s1.hashCode() + aaa.hashCode());
        s2.add(aaa);
        System.out.println(s2.hashCode());
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);

        System.out.println(i1 == i2);

    }
/*
    public static void main(String[] args) {
        Vertex a = new Vertex('a');
        Vertex b = new Vertex('b');

        Vertex c = new Vertex('c');

        Vertex d = new Vertex('d');
        Vertex e = new Vertex('e');

        Vertex f = new Vertex('f');
        Vertex g = new Vertex('g');

        Vertex h = new Vertex('h');
        Vertex i = new Vertex('i');

        Vertex j = new Vertex('j');
        Vertex k = new Vertex('k');

        Vertex l = new Vertex('l');
        Vertex m = new Vertex('m');

        Edge c1 = new Edge();
        c1.l = a;
        c1.r = c;
        c1.mode = Mode.CONCURRENT;

        Edge c2 = new Edge();
        c2.l = b;
        c2.r = c;
        c2.mode = Mode.CONCURRENT;

        Edge s1 = new Edge();
        s1.l = c;
        s1.r = d;
        s1.mode = Mode.SEQUENTIAL;

        Edge s2 = new Edge();
        s2.l = c;
        s2.r = e;
        s2.mode = Mode.SEQUENTIAL;

        Edge c3 = new Edge();
        c3.l = d;
        c3.r = f;
        c3.mode = Mode.CONCURRENT;

        Edge c4 = new Edge();
        c4.l = e;
        c4.r = g;
        c4.mode = Mode.CONCURRENT;

        Edge s3 = new Edge();
        s3.l = f;
        s3.r = h;
        s3.mode = Mode.SEQUENTIAL;

        Edge s4 = new Edge();
        s4.l = g;
        s4.r = i;
        s4.mode = Mode.SEQUENTIAL;

        Edge s5 = new Edge();
        s5.l = j;
        s5.r = a;
        s5.mode = Mode.SEQUENTIAL;


        Edge s6 = new Edge();
        s6.l = k;
        s6.r = b;
        s6.mode = Mode.SEQUENTIAL;

        Edge c5 = new Edge();
        c5.l = f;
        c5.r = l;
        c5.mode = Mode.CONCURRENT;

        Edge c6 = new Edge();
        c6.l = g;
        c6.r = m;
        c6.mode = Mode.CONCURRENT;


        Edge c7 = new Edge();
        c7.l = h;
        c7.r = j;
        c7.mode = Mode.CONCURRENT;

        Edge c8 = new Edge();
        c8.l = k;
        c8.r = i;
        c8.mode = Mode.CONCURRENT;

        a.outputs.add(c1);
        a.inputs.add(s5);

        b.outputs.add(c2);
        b.inputs.add(s6);

        c.inputs.add(c1);
        c.inputs.add(c2);
        c.outputs.add(s1);
        c.outputs.add(s2);

        d.inputs.add(s1);
        d.outputs.add(c3);

        e.inputs.add(s2);
        e.outputs.add(c4);

        f.inputs.add(c3);
        f.outputs.add(c5);

        g.inputs.add(c4);
        g.outputs.add(c6);

        h.inputs.add(s3);
        h.outputs.add(c7);

        i.inputs.add(s4);
        i.inputs.add(c8);

        j.outputs.add(s5);
        j.inputs.add(c7);

        k.outputs.add(s6);
        k.outputs.add(c8);

        l.inputs.add(c5);
        m.inputs.add(c6);

        List<Vertex> ls = new ArrayList<>();
        ls.add(a);
        ls.add(b);
        ls.add(c);
        ls.add(d);
        ls.add(e);
        ls.add(f);
        ls.add(g);
        ls.add(h);
        ls.add(i);
        ls.add(j);
        ls.add(k);
        ls.add(l);
        ls.add(m);

        long start = System.currentTimeMillis();
        for (int n = 0; n < 10000000; n++) {
            getAllConcurrentSets(ls);
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);

        start = System.currentTimeMillis();
        for (int n = 0; n < 10000000; n++) {
            getAllConcurrentSets2(ls);
        }
        end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);

    }
*/
}
