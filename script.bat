mkdir temp
cd temp

mkdir WEB-INF
mkdir src
cd WEB-INF
mkdir lib
cd ../../

jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ etu2023
jar -cvf test_fw.war -C .\temp\

copy fw.jar .\temp\WEB-INF\lib
for /r .\Test-Framework\ %%f in (*.java) do copy %%f .\temp\src
javac -cp .\temp\WEB-INF\lib\fw.jar -d .\temp\WEB-INF\classes .\temp\src\*.java
::rmdir /S /Q .\temp\src

copy fw.jar echo .\Test_framework\build\web\WEB-INF\lib\
copy test_fw.war echo C:\MyWebServer\apache-tomcat-10.0.20\apache-tomcat-10.0.20\webapps\

copy .\Test_Framework\web\*.jsp .\temp\
copy .\Test_Framework\web\WEB-INF\web.xml .\temp\WEB-INF\


::rmdir /S /Q .\temp