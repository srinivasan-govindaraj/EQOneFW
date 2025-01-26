package dev.eq.utills;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContextManager {

    private static Map<Long, ScenarioContext> scenarioContexts = new HashMap<>();

    public static ScenarioContext getContext() {
        if (scenarioContexts.containsKey(Thread.currentThread().getId())) {
            return scenarioContexts.get(Thread.currentThread().getId());
        } else {
            ScenarioContext context = new ScenarioContext();
            scenarioContexts.put(Thread.currentThread().getId(), context);
            return context;
        }
    }
}
