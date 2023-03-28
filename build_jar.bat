jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ etu2023
jar -cvf test_fw.war -C .\Test_Framework\build\web\ WEB-INF\
copy test_fw.war C:\MyWebServer\apache-tomcat-10.0.20\apache-tomcat-10.0.20\webapps\
xcopy fw.jar D:\Documents\MrNaina\S4\Framework(1)\Test_framework\build\web\WEB-INF\lib\
xcopy D:\Documents\MrNaina\S4\Framework(1)\Test_framework\build\web\ C:\MyWebServer\apache-tomcat-10.0.20\apache-tomcat-10.0.20\webapps\Framework\ /E