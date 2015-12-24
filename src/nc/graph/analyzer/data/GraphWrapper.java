package nc.graph.analyzer.data;

import java.util.*;

/**
 * Created by LightRain.
 */
public class GraphWrapper {
    private Map<Integer, List<Integer>> revIncList;
    private boolean[][] adgMatrix;
    private Graph graph;

    public GraphWrapper(Graph graph){
        this.graph = graph;
        buildRevIncList();
        buildAdgMatrix();
    }

    private void buildAdgMatrix() {
        adgMatrix = new boolean[graph.getVertexes().size()][graph.getVertexes().size()];
        for(Map.Entry<Integer, List<Integer>> entry : graph.getIncList().entrySet()){
            for(Integer vertex: entry.getValue()) {
                adgMatrix[entry.getKey()][vertex]=true;
            }
        }
    }

    public boolean[][] getAdgMatrix(){
        return adgMatrix;
    }

    private void buildRevIncList() {
        revIncList = new HashMap<>();
        for(Integer vertex : graph.getVertexes()){
            revIncList.put(vertex, new ArrayList<>());
        }
        for(Map.Entry<Integer, List<Integer>> entry : graph.getIncList().entrySet()){
            for(Integer vertex: entry.getValue()) {
                revIncList.get(vertex).add(entry.getKey());
            }
        }
    }

    public Map<Integer, List<Integer>> getIncList() {
        return graph.getIncList();
    }

    public RulesMap getRulesMap(){
        return graph.getRulesMap();
    }

    public Set<Integer> getInput(){
        return graph.getInput();
    }

    public Set<Integer> getOutput(){
        return graph.getOutput();
    }

    public Map<Integer, List<Integer>> getRevIncList(){
        return revIncList;
    }

    public List<Integer[]> getDuplicateRules() {
        return graph.getDuplicateRules();
    }

    public Set<Integer> getVertexes(){
        return graph.getVertexes();
    }

    @Override
    public String toString() {
        return "GraphWrapper{" +
                "revIncList=" + revIncList +
                ", graph=" + graph +
                '}';
    }
}
