all:
	javac *.java
jar:
	jar -cmf manifest npcworld.jar *.class img/ docs/
