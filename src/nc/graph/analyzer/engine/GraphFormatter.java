package nc.graph.analyzer.engine;

import nc.graph.analyzer.data.GraphWrapper;
import nc.graph.analyzer.data.RulesMap;

import java.util.*;

/**
 * Created by LightRain.
 */
public class GraphFormatter {
    public String getConditionVertexesWithRules(Set<Integer> vertexes, RulesMap rulesMap){
        StringBuilder sb = new StringBuilder();
        for(Integer i: vertexes){
            sb.append("\"");
            sb.append(rulesMap.getName(i));
            sb.append("\" из-за правил: ");
            appendRules(sb, rulesMap.getRuleIdsWhereConditions(i), rulesMap);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getFactVertexesWithRules(Set<Integer> vertexes, RulesMap rulesMap){
        StringBuilder sb = new StringBuilder();
        for(Integer i: vertexes){
            sb.append("\"");
            sb.append(rulesMap.getName(i));
            sb.append("\" из-за правил: ");
            appendRules(sb, rulesMap.getRuleIdsWhereFacts(i), rulesMap);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getDuplicateRulesSets(Set<Set<String>> rules, RulesMap rulesMap){
        StringBuilder sb = new StringBuilder();
        for(Set<String> ruleSet : rules){
            appendRules(sb, ruleSet, rulesMap);
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getCycles(List<List<Integer>> cycles, RulesMap rulesMap){
        StringBuilder sb = new StringBuilder();
        for(List<Integer> cycle : cycles){
            appendCycle(sb, cycle, rulesMap);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendCycle(StringBuilder sb, List<Integer> cycle, RulesMap rulesMap){
        for(int i = 1; i< cycle.size(); i++){
            appendRule(sb, cycle.get(i-1), cycle.get(i), rulesMap);
            sb.append(" -> ");
        }
        appendRule(sb, cycle.get(cycle.size()-1), cycle.get(0), rulesMap);
    }

    private void appendRules(StringBuilder sb, Collection<String> ruleIds, RulesMap rulesMap){
        int i=1;
        for(String ruleId : ruleIds){
            appendRule(sb, ruleId, rulesMap);
            if(i < ruleIds.size()){
                sb.append(", ");
            }
            i++;
        }
    }

    private void appendRule(StringBuilder sb, String ruleId, RulesMap rulesMap){
        sb.append(ruleId);
        sb.append(" (");
        sb.append(rulesMap.getName(rulesMap.getConditionIdByRuleId(ruleId)));
        sb.append("->");
        sb.append(rulesMap.getName(rulesMap.getFactIdByRuleId(ruleId)));
        sb.append(")");
    }


    private void appendRule(StringBuilder sb, int cId, int fId, RulesMap rulesMap){
        sb.append(rulesMap.getRuleId(cId, fId));
        sb.append(" (");
        sb.append(rulesMap.getName(cId));
        sb.append("->");
        sb.append(rulesMap.getName(fId));
        sb.append(")");
    }
}
