package com.zakcorp.design_patterns.creational.abstract_factory_pattern.factory;

import com.zakcorp.design_patterns.creational.abstract_factory_pattern.buttons.Button;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.buttons.MacOSButton;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.checkboxes.Checkbox;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.checkboxes.MacOSCheckbox;

/**
 * Each concrete factory extends basic factory and responsible for creating
 * products of a single variety.
 */
public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
