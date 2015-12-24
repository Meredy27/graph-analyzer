package nc.graph.analyzer.data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by LightRain on 24.12.2015.
 */
public class Graph {
    private Map<Integer, List<Integer>> incList;
    private RulesMap rulesMap;
    private Set<Integer> input;
    private Set<Integer> output;

    public List<Integer[]> getDuplicateRules() {
        return duplicateRules;
    }

    public void setDuplicateRules(List<Integer[]> duplicateRules) {
        this.duplicateRules = duplicateRules;
    }

    private List<Integer[]> duplicateRules;

    public Graph(Map<Integer, List<Integer>> incList, RulesMap rulesMap,
                 Set<Integer> input, Set<Integer> output) {
        this.incList = incList;
        this.rulesMap = rulesMap;
        this.input = input;
        this.output = output;
    }

    public Set<Integer> getVertexes(){
        return incList.keySet();
    }

    public Map<Integer, List<Integer>> getIncList() {
        return incList;
    }

    public RulesMap getRulesMap(){
        return rulesMap;
    }

    public Set<Integer> getInput(){
        return input;
    }

    public Set<Integer> getOutput(){
        return output;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "incList=" + incList +
                ", rulesMap=" + rulesMap +
                ", input=" + input +
                ", output=" + output +
                '}';
    }
}
