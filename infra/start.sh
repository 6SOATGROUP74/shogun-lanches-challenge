#!/bin/bash
kubectl apply -f app-configmap.yaml
kubectl apply -f app-svc.yaml
kubectl apply -f