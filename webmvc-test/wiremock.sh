#!/usr/bin/env bash

docker run -it --rm -p 8080:8080 -v $(PWD)/webmvc-test/src/test/resources/contracts:/wiremock/mappings hellgate75/wiremock-server:2.7.1
