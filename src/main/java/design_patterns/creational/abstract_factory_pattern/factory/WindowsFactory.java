package main.java.design_patterns.creational.abstract_factory_pattern.factory;

import main.java.design_patterns.creational.abstract_factory_pattern.buttons.Button;
import main.java.design_patterns.creational.abstract_factory_pattern.buttons.MacOSButton;
import main.java.design_patterns.creational.abstract_factory_pattern.buttons.WindowsButton;
import main.java.design_patterns.creational.abstract_factory_pattern.checkboxes.Checkbox;
import main.java.design_patterns.creational.abstract_factory_pattern.checkboxes.MacOSCheckbox;
import main.java.design_patterns.creational.abstract_factory_pattern.checkboxes.WindowsCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class WindowsFactory implements GUIFactory {
    
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
