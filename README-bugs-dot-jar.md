To rebuild ACS.jar, open the repository in Intellij and Build artifact...

If you encounter a "Exception in thread "main" java.lang.SecurityException: Invalid signature file digest for Manifest main attributes" then run the following.

zip -d ACS.jar 'META-INF/*.SF' 'META-INF/*.RSA' 'META-INF/*SF'
