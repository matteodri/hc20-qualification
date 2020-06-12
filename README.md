#     Google Hash Code 2020 Qualification

[![jdk11](https://img.shields.io/badge/java-11-blue.svg)](http://jdk.java.net/11)
![Java CI](https://github.com/matteodri/hc20-qualification/workflows/Java%20CI/badge.svg)

## Overview
This is our solution to the [Google Hash Code 2020](https://codingcompetitions.withgoogle.com/hashcode/archive) qualification round.

This is a refined solution developed from the one submitted for the qualification round. 
Code has been refactored and made more readable while also improving the algorithm.
Solution wouldn't rank in the first positions as it produces, using the problem example files, 
a score of 23,039,706; where top scorer teams totalled around 27,200,000.

## The Codeardi Team
* [abegaj](https://github.com/abegaj)
* [matteodri](https://github.com/matteodri)
* [zupermanzupereroe](https://github.com/zupermanzupereroe)

## Problem Statement
Given a description of libraries and books available, plan which books to scan from
which library to maximize the total score of all scanned books, taking into account that
each library needs to be signed up before it can ship books.

[Full problem statement (pdf)](problem/hashcode_2020_online_qualification_round.pdf)

## Build
Build JAR plus a ZIP archive that includes only the source code.

`mvn clean package`

## Run
Run _hc20-qualification_ Spring Boot application with Java command:

`java -jar target/hc20-qualification-<version>.jar [inputfile] [outputfile] `

Example:

`java -jar target/hc20-qualification-0.0.1-SNAPSHOT.jar ~/input.txt ~/output.txt `
