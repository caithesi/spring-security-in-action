#!/bin/bash
curl -H "Request-Id:12345" \
     -H "Authorization:testkey" \
     http://localhost:8080/hello