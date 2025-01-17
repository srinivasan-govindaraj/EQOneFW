package dev.eq.utills;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext = new HashMap<>();

    public static ScenarioContext getInstance() {
        return ScenarioContextManager.getContext();
    }

    public Object getObject(String key) {
        return scenarioContext.get(key);
    }

    public void putObject(String key, Object object) {
        scenarioContext.put(key, object);
    }

    public void removeObject(String key) {
        scenarioContext.remove(key);
    }

    public boolean containsObject(String key) {
        return scenarioContext.keySet().contains(key);
    }

    public void clear() {
        scenarioContext.clear();
    }
}
