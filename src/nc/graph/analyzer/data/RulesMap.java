package nc.graph.analyzer.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by LightRain.
 */
public class RulesMap {
    private Map<String, Integer> partId = new HashMap<>();
    private Map<Integer, String> idNames = new HashMap<>();
    private Map<String, Integer[]> rules = new HashMap<>();

    public void addPair(String part, Integer id){
        idNames.put(id, part);
        part = normalizePartName(part);
        partId.put(part, id);
    }

    public void addRule(String ruleId, int part1Id, int part2Id){
        rules.put(ruleId, new Integer[]{part1Id, part2Id});
    }

    public Set<String> getRuleIdsWhereConditions(int cId){
        Set<String> ruleIds = new HashSet<>();
        for(Map.Entry<String, Integer[]> entry : rules.entrySet()){
            if(cId == entry.getValue()[0].intValue()) {
                ruleIds.add(entry.getKey());
            }
        }
        return ruleIds;
    }

    public Set<String> getRuleIdsWhereFacts(int fId){
        Set<String> ruleIds = new HashSet<>();
        for(Map.Entry<String, Integer[]> entry : rules.entrySet()){
            if(fId == entry.getValue()[1].intValue()) {
                ruleIds.add(entry.getKey());
            }
        }
        return ruleIds;
    }

    public Set<String> getRuleIdsByConditionAndFact(int cId, int fId){
        Set<String> ruleIds = new HashSet<>();
        for(Map.Entry<String, Integer[]> entry : rules.entrySet()){
            if(cId == entry.getValue()[0] && fId == entry.getValue()[1]) {
                ruleIds.add(entry.getKey());
            }
        }
        return ruleIds;
    }

    public int getId(String name){
        name = normalizePartName(name);
        return partId.get(name);
    }

    public boolean hasPart(String part){
        part = normalizePartName(part);
        return partId.containsKey(part);
    }

    public String getName(int id){
        return idNames.get(id);
    }

    public String getRuleId(int part1Id, int part2Id){
        for(Map.Entry<String, Integer[]> entry : rules.entrySet()) {
            if(part1Id == entry.getValue()[0]
                    && part2Id == entry.getValue()[1]){
                return entry.getKey();
            }
        }
        throw new RuntimeException("no rule found");
    }

    public int getConditionIdByRuleId(String ruleId){
        return rules.get(ruleId)[0];
    }

    public int getFactIdByRuleId(String ruleId){
        return rules.get(ruleId)[1];
    }

    private String normalizePartName(String part){
        return part.toLowerCase().replaceAll(" ", "");
    }

    @Override
    public String toString() {
        return "RulesMap{" +
                "partId=" + partId +
                ", idNames=" + idNames +
                ", rules=" + rules +
                '}';
    }
}
