package main.java.design_patterns.creational.abstract_factory_pattern;

import main.java.design_patterns.creational.abstract_factory_pattern.factory.GUIAbstractFactory;
import main.java.design_patterns.creational.abstract_factory_pattern.factory.GUIFactory;
import main.java.design_patterns.creational.abstract_factory_pattern.factory.MacOSFactory;
import main.java.design_patterns.creational.abstract_factory_pattern.factory.WindowsFactory;

import java.util.Scanner;

/**
 * Demo class. Everything comes together here.
 */
public class Demo {
    /**
     * Application picks the factory type and creates it in run time (usually at
     * initialization stage), depending on the configuration or environment
     * variables.
     */
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if ( osName.contains("mac") ) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }
        app = new Application(factory);
        return app;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String osType = sc.next();
        GUIFactory factory = GUIAbstractFactory.createFactory(osType);
        Application app = configureApplication();
        app.paint();
    }
}
