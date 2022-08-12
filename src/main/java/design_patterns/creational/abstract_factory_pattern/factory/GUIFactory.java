package main.java.design_patterns.creational.abstract_factory_pattern.factory;

import main.java.design_patterns.creational.abstract_factory_pattern.buttons.Button;
import main.java.design_patterns.creational.abstract_factory_pattern.checkboxes.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
