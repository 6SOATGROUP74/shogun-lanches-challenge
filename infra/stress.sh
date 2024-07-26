#!/bin/bash
for i in {1..10000}; do
  curl http://ac811e7179f834f63ba25b7fd130ce96-1761275612.us-east-1.elb.amazonaws.com/v1/pedidos
  sleep $1
done