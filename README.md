https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/basic_app_embedded_tomcat/basic_app-tomcat-embedded.html


set path=%path%;C:\apache-maven-3.5.2\bin;

mvn archetype:generate -DgroupId=com.venkat -DartifactId=resume_upload -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

create a "java" folder in main
create a package "com.venkat"

create a "test" folder at the main folder level
create a "java" folder in "test" folder

mvn package

mvn jetty:run


mvn install
