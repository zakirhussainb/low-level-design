package main.java.design_patterns.creational.abstract_factory_pattern;

import main.java.design_patterns.creational.abstract_factory_pattern.buttons.Button;
import main.java.design_patterns.creational.abstract_factory_pattern.checkboxes.Checkbox;
import main.java.design_patterns.creational.abstract_factory_pattern.factory.GUIFactory;

/**
 * Factory users don't care which concrete factory they use since they work with
 * factories and products through abstract interfaces.
 */
public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory guiFactory) {
        button = guiFactory.createButton();
        checkbox = guiFactory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
