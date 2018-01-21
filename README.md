# eksperty-bayes

## Prerequisites
* download and paste jSMILE files into [`libs/smile`](libs) directory

## jSMILE
jSMILE tutorial: [link][jsmile_tutorial]

### java.library.path

Run the code with JVM option:
```
-Djava.library.path="libs/smile"
```

### jSMILE license
1. To run the code you need to have a license from BayesFusion. You can obtain it here: [academic license][jsmile_academic_license]
2. Paste the license to file [JsmileLicense.java][jsmile_license_file]
```
...
    public static void addLicense() {
        new smile.License(
        ...
        );
    }
...
```
3. To prevent your license from being pushed to remote repository execute command
```
git update-index --assume-unchanged src/main/java/pl/edu/agh/se/run/license/JsmileLicense.java
```

[jsmile_academic_license]:https://download.bayesfusion.com/files.html?category=Academia
[jsmile_license_file]:src/main/java/pl/edu/agh/se/run/license/JsmileLicense.java
[jsmile_tutorial]:https://dslpitt.org/genie/wiki/Java_Tutorial_2:_Performing_Inference_with_a_Bayesian_Network