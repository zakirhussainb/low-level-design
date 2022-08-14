package com.zakcorp.design_patterns.creational.abstract_factory_pattern.factory;


import com.zakcorp.design_patterns.creational.abstract_factory_pattern.buttons.Button;
import com.zakcorp.design_patterns.creational.abstract_factory_pattern.checkboxes.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
