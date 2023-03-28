jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ etu2023
jar -cvf test_fw.war -C .\Test-Framework\build\web\ WEB-INF\
copy test_fw.war C:\MyWebServer\apache-tomcat-10.0.20\apache-tomcat-10.0.20\webapps\
xcopy fw.jar D:\Etude\L2\S4\INF209-Web_dynamique\project\Step_to_Framework\Test-Framework\build\web\WEB-INF\lib\
xcopy D:\Etude\L2\S4\INF209-Web_dynamique\project\Step_to_Framework\Test-Framework\build\web\ D:\Logiciel\Apache_Software_Foundation\Tomcat_10.0\webapps\Framework\ /E
