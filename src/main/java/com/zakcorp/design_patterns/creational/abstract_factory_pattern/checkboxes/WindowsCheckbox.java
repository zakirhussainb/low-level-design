package com.zakcorp.design_patterns.creational.abstract_factory_pattern.checkboxes;

/**
 * All products families have the same varieties (MacOS/Windows).
 *
 * This is a Windows variant of a checkbox.
 */
public class WindowsCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created WindowsCheckbox.");
    }
}
