package me.maximumpower55.mecha.api.component;

public interface ComponentProvider {
    Component getComponent(ComponentKey component);

    default boolean hasComponent(ComponentKey componentKey) {
        return getComponent(componentKey) != null;
    }
}
