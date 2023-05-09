:: Cr�ation du dossier temporaire temp
mkdir temp
cd temp


:: Cr�ation de la structure du fichier
mkdir WEB-INF
mkdir src
cd WEB-INF
mkdir classes
mkdir lib
cd ../../


:: Compilation du modele
copy fw.jar .\temp\WEB-INF\lib
for /r .\Test-Framework\ %%f in (*.java) do copy %%f .\temp\src
javac -cp .\temp\WEB-INF\lib\fw.jar -d .\temp\WEB-INF\classes .\temp\src\*.java
rmdir /S /Q .\temp\src

copy fw.jar .\Test_framework\build\web\WEB-INF\lib\

:: Copie des Autre fichiers
copy .\Test-Framework\web\*.jsp .\temp\
copy .\Test-Framework\web\WEB-INF\web.xml .\temp\WEB-INF\


:: D�ploiement vers tomcat
jar -cvf test_fw.war -C .\temp\ .
copy test_fw.war C:\MyWebServer\apache-tomcat-10.0.20\apache-tomcat-10.0.20\webapps\

:: Supprimer le dossier temp
rmdir /S /Q .\temp