#!/bin/bash

kubectl apply -f ./database/mysql-configmap.yaml
kubectl apply -f ./database/mysql-opaque.yaml
kubectl apply -f ./database/mysql-pod.yaml
kubectl apply -f ./database/mysql-pvc.yaml
kubectl apply -f ./database/mysql-svc.yaml

sleep 1

kubectl apply -f ./service-account.yaml
kubectl apply -f ./app/app-nlb.yaml
kubectl apply -f ./app/app-svc.yaml
kubectl apply -f ./app/app-configmap.yaml
kubectl apply -f ./app/app-deployment.yaml
kubectl apply -f ./app/app-hpa.yaml
kubectl apply -f ./app/app-opaque.yaml
