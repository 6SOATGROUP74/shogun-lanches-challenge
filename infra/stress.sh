#!/bin/bash
for i in {1..10000}; do
  curl a3185ec998f53407a8abe8a9514cf407-2073599187.us-east-1.elb.amazonaws.com
  sleep $1
done