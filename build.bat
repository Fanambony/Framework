set fw_root=D:\Documents\MrNaina\S4\Framework(1)\Framework
set project_lib=.\TestFramework\build\web\WEB-INF\lib\

mkdir src_jar
mkdir class_jar

for /r %fw_root%\ %%f in (*.java) do copy %%f .\src_jar
javac -Xlint -cp gson-2.8.2.jar;servlet-api.jar -parameters -d .\class_jar .\src_jar\*.java
jar -cf fw.jar -C .\Framework\build\web\WEB-INF\classes\ etu2023
::copy fw.jar %project_lib%

rmdir /S /Q src_jar
rmdir /S /Q class_jar