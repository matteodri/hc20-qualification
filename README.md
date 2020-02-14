# Base File Processor

[![jdk11](https://img.shields.io/badge/java-11-blue.svg)](http://jdk.java.net/11)
![Java CI](https://github.com/matteodri/base-file-processor/workflows/Java%20CI/badge.svg)

## Purpose
Application that processes a text file and produces a text file as output.

## Build
Build JAR and a ZIP archive with only the source code.

`mvn clean package`

## Run
Run base-file-processor Spring Boot application with Java command:

`java -jar target/base-file-processor-<version>.jar [inputfile] [outputfile] `

Example:

`java -jar target/base-file-processor-0.0.1-SNAPSHOT.jar ~/input.txt ~/output.txt `

## Use as template
### Modify banner.txt
Generate banner with: https://devops.datenkollektiv.de/banner.txt/index.html

Font: _smslant_