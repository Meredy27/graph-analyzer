package nc.graph.analyzer.engine;

import nc.graph.analyzer.data.GraphWrapper;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by LightRain.
 */
public class ErrorDetectingEngine {

    public Set<Integer> getUnnecessarySources(GraphWrapper graph){
        Set<Integer> vertexes = new HashSet<>();
        for(Map.Entry<Integer, List<Integer>> entry : graph.getRevIncList().entrySet()) {
            if(entry.getValue().isEmpty() && !graph.getInput().contains(entry.getKey())){
                vertexes.add(entry.getKey());
            }
        }
        return vertexes;
    }

    public Set<Integer> getUnnecessaryPurposes(GraphWrapper graph){
        Set<Integer> vertexes = new HashSet<>();
        for(Map.Entry<Integer, List<Integer>> entry : graph.getIncList().entrySet()) {
            if(entry.getValue().isEmpty() && !graph.getOutput().contains(entry.getKey())){
                vertexes.add(entry.getKey());
            }
        }
        return vertexes;
    }

    public Set<Set<String>> getDuplicateRules(GraphWrapper graph){
        Set<Set<String>> rules = new HashSet<>();
        for(Integer[] pair : graph.getDuplicateRules()) {
            rules.add(graph.getRulesMap().getRuleIdsByConditionAndFact(pair[0], pair[1]));
        }
        return rules;
    }

    public List<List<Integer>> findCycles(GraphWrapper graph){
        ElementaryCyclesSearch ecs = new ElementaryCyclesSearch(graph.getAdgMatrix(),
                graph.getVertexes().toArray(new Integer[0]));
        return ecs.getElementaryCycles();
    }
}
