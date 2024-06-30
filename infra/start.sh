#!/bin/bash
kubectl apply -f mysql-configmap.yaml
kubectl apply -f mysql-opaque.yaml
kubectl apply -f mysql-pod.yaml
kubectl apply -f mysql-pvc.yaml
kubectl apply -f mysql-svc.yaml


kubectl apply -f app-configmap.yaml
kubectl apply -f app-opaque.yaml
kubectl apply -f app-pod.yaml
kubectl apply -f app-svc.yaml
