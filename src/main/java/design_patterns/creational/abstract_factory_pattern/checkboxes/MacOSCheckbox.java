package main.java.design_patterns.creational.abstract_factory_pattern.checkboxes;

/**
 * All products families have the same varieties (MacOS/Windows).
 *
 * This is a MacOS variant of a checkbox.
 */
public class MacOSCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("You have created MacOSCheckbox.");
    }
}
