@echo off
cd /d %~dp0

.\mvnw.cmd -Dstyle.color=always spring-boot:run %*
