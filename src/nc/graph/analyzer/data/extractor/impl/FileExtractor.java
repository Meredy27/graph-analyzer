package nc.graph.analyzer.data.extractor.impl;

import nc.graph.analyzer.data.Graph;
import nc.graph.analyzer.data.RulesMap;
import nc.graph.analyzer.data.builder.GraphBuilder;
import nc.graph.analyzer.data.extractor.GraphExtractor;

import java.io.*;

/**
 * Created by LightRain on 24.12.2015.
 */
public class FileExtractor implements GraphExtractor{
    private File file;
    private RulesMap rulesMap;
    private GraphBuilder builder;
    private int index;

    private void init(){
        rulesMap = new RulesMap();
        builder = new GraphBuilder();
        index = -1;
    }

    @Override
    public Graph getGraph() {
        init();
        build();
        return builder.getGraph(rulesMap);
    }

    private void build() {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader); ) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line){
        String data = line.split("( )*:( )*")[1];
        if(line.startsWith("I")) {
            parseInput(data);
        } else if (line.startsWith("O")){
            parseOutput(data);
        } else {
            parseRule(line);
        }
    }

    private void parseInput(String line){
        String[] input = line.split("( )*,( )*");
        int id;
        for (int i = 0; i < input.length; i++) {
            id = addPart(input[i]);
            builder.addInputVertex(id);
        }
    }

    private void parseOutput(String line){
        String[] input = line.split("( )*,( )*");
        int id;
        for (int i = 0; i < input.length; i++) {
            id = addPart(input[i]);
            builder.addOutputVertex(id);
        }
    }

    private void parseRule(String rule){
        String[] split = rule.split("( )*:( )*");
        String rulePart;
        String ruleId = null;
        if(split.length > 1) {
            ruleId = split[0];
            rulePart = split[1];
        } else {
            rulePart = split[0];
        }
        split = rulePart.split("( )*->( )*");
        addRule(ruleId, split[0], split[1]);
    }

    private void addRule(String ruleId, String condition, String fact) {
        int fId = addPart(fact);
        builder.addVertex(fId);
        int cId;
        String[] conditions = condition.split("( )*&( )*");
        for (int i = 0; i < conditions.length; i++) {
            cId = addPart(conditions[i]);
            rulesMap.addRule(ruleId, cId, fId);
            builder.addVertex(cId);
            builder.addEdge(cId, fId);
        }
    }

    private int addPart(String part){
        if(rulesMap.hasPart(part)) {
            return rulesMap.getId(part);
        }
        index++;
        rulesMap.addPair(part, index);
        return index;
    }

    public FileExtractor(File file){
        this.file = file;
    }
}
