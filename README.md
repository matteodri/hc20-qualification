# Google Hash Code 2020 Qualification

[![jdk11](https://img.shields.io/badge/java-11-blue.svg)](http://jdk.java.net/11)
![Java CI](https://github.com/matteodri/hc20-qualification/workflows/Java%20CI/badge.svg)

## Purpose
Application that processes a text file and produces a text file as output.

## Build
Build JAR and a ZIP archive with only the source code.

`mvn clean package`

## Run
Run hc20-qualification Spring Boot application with Java command:

`java -jar target/hc20-qualification-<version>.jar [inputfile] [outputfile] `

Example:

`java -jar target/hc20-qualification-0.0.1-SNAPSHOT.jar ~/input.txt ~/output.txt `

## Use as template
### Modify banner.txt
Generate banner with: https://devops.datenkollektiv.de/banner.txt/index.html

Font: _smslant_