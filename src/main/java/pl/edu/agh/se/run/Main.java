package pl.edu.agh.se.run;

import pl.edu.agh.se.run.license.JsmileLicense;
import smile.Network;

public class Main {


    public static void main(String[] args) {
        JsmileLicense.addLicense();
        checkLibraryPath();
        
        Network net = new Network();
        net.readFile("computer.xdsl");
        net.updateBeliefs();

        System.out.println("Hello World");
    }

    private static void checkLibraryPath() {
        String libraryPath = "java.library.path";
        String correctPath = "libs/smile";
        String path = System.getProperty(libraryPath);
        if (!path.equals(correctPath)) {
            System.out.println(String.format("Warning! %s should be set to %s", libraryPath, correctPath));
            System.out.println(String.format("%s=%s", libraryPath, path));
        }
    }

}
