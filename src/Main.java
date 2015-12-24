import jdk.internal.org.xml.sax.ErrorHandler;
import nc.graph.analyzer.data.Graph;
import nc.graph.analyzer.data.GraphWrapper;
import nc.graph.analyzer.data.extractor.GraphExtractor;
import nc.graph.analyzer.data.extractor.impl.FileExtractor;
import nc.graph.analyzer.engine.ErrorDetectingEngine;
import nc.graph.analyzer.engine.GraphFormatter;

import java.io.File;

/**
 * Created by LightRain.
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("resources/graph.gr");
        GraphExtractor extractor = new FileExtractor(file);
        Graph graph = extractor.getGraph();
        GraphWrapper graphWrapper = new GraphWrapper(graph);
        GraphFormatter formatter = new GraphFormatter();
        ErrorDetectingEngine errorDetectingEngine = new ErrorDetectingEngine();
        System.out.println("Висячие предпосылки: ");
        System.out.println(formatter.getConditionVertexesWithRules(
                errorDetectingEngine.getUnnecessarySources(graphWrapper),
                graphWrapper.getRulesMap()
        ));
        System.out.println("Висячие заключения: ");
        System.out.println(formatter.getFactVertexesWithRules(
                errorDetectingEngine.getUnnecessaryPurposes(graphWrapper),
                graphWrapper.getRulesMap()
        ));
        System.out.println("Дублирующиеся правила: ");
        System.out.println(formatter.getDuplicateRulesSets(
                errorDetectingEngine.getDuplicateRules(graphWrapper),
                graphWrapper.getRulesMap()
        ));
        System.out.println("Циклы: ");
        System.out.println(formatter.getCycles(
                errorDetectingEngine.findCycles(graphWrapper),
                graphWrapper.getRulesMap()
        ));
    }
}
