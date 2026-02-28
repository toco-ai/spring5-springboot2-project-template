#!/bin/sh

cd $(dirname $0)

./mvnw -Dstyle.color=always spring-boot:run $@
