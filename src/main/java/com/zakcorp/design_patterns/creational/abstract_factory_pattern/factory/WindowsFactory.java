package com.zakcorp.design_patterns.creational.abstract_factory_pattern.factory;

import com.zakcorp.design_patterns.creational.abstract_factory_pattern.buttons.Button;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.buttons.WindowsButton;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.checkboxes.Checkbox;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.checkboxes.WindowsCheckbox;

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
