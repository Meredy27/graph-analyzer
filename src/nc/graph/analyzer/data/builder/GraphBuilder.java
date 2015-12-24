package nc.graph.analyzer.data.builder;

import nc.graph.analyzer.data.Graph;
import nc.graph.analyzer.data.RulesMap;

import java.util.*;

/**
 * Created by LightRain.
 */
public class GraphBuilder {
    private Map<Integer, List<Integer>> incList;
    private Set<Integer> input;
    private Set<Integer> output;
    private List<Integer[]> duplicateRules;

    public  GraphBuilder(){
        incList = new HashMap<Integer, List<Integer>>();
        input = new HashSet<>();
        output = new HashSet<>();
        duplicateRules = new ArrayList<>(0);
    }

    public void addVertex(int vertex){
        if(!incList.containsKey(vertex))
            incList.put(vertex, new ArrayList<>());
    }

    public void addEdge(int vertexFrom, int vertexTo){
        if(incList.get(vertexFrom).contains(vertexTo)){
            duplicateRules.add(new Integer[]{vertexFrom, vertexTo});
        }
        incList.get(vertexFrom).add(vertexTo);
    }

    public void addInputVertex(int vertexId){
        input.add(vertexId);
    }

    public void addOutputVertex(int vertexId){
        output.add(vertexId);
    }

    public Graph getGraph(RulesMap rulesMap){
        Graph graph = new Graph(incList, rulesMap, input, output);
        graph.setDuplicateRules(duplicateRules);
        return graph;
    }
}
