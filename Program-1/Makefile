# CMPS 12B 
# 1/18/2018
# Makefile
# A simple makefile

Recursion: Recursion.class
	echo Main-Class: Recursion > Manifest
	jar cvfm Recursion Manifest Recursion.class
	rm Manifest
	chmod +x Recursion
Recursion.class: Recursion.java
	javac -Xlint Recursion.java
clean:
	rm -f Recursion Recursion.class
