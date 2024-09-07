# Tech Challenge


Trabalho desenvolvido como forma de avaliação no curso de pós-graduação em Software Architecture na FIAP.

## Objetivo
Solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades

## Arquitetura Kubernetes (K8S) 
<details>
  <summary>Arquitetura Kubernetes</summary>
 <img src="https://github.com/igusvs/shogun-lanches-challenge/blob/main/docs/arquitetura_k8s-Infra%20K8s.jpg" style="max-width: 100%;">
</details>

## Arquitetura 
<details>
  <summary>Arquitetura</summary>
 <img src="https://github.com/igusvs/shogun-lanches-challenge/blob/main/docs/arquitetura_k8s-Negocio.jpg" style="max-width: 100%;">
</details>

## Detalhes do projeto

[Diagrama da arquitetura](docs/Hexagonal_Architecture.jpg)

## Como executar o projeto
<details>
  <summary>Setup aplicação</summary>

- Atualize a configuracao do kubeconfig

  - Execucao na aws
    > aws eks update-kubeconfig --region {REGIAO} --name {NOME_CLUSTER}
  - Execucao local
    > kubectl config use-context docker-desktop

- Clone o repositório:

  	> git clone https://github.com/igusvs/shogun-lanches-challenge.git

  	> cd shogun-lanches-challenge/infra

- Execute o script para iniciar os recursos k8s

  >  chmod +x ./start.sh
  
  > ./start.sh
</details>

## Link collection Postman
[Collection Postman](https://github.com/igusvs/shogun-lanches-challenge/blob/main/docs/OpenAPI%20definition%20-%20Shogun%20AWS.postman_collection.json)

## Link de apresentação
[Apresentação youtube](https://www.youtube.com/watch?v=xQ0PRRjv3oA) 

## Swagger 
 Link de acesso a documentacao do swagger
> http://localhost:30000/swagger-ui/index.html

## Event Storming
- Link da documentação do event storming do grupo: [[Miro](https://miro.com/app/board/uXjVKYtyiY8=/?share_link_id=475227793071)] 

## Integrantes - Grupo 74

- Igor Alves da Silva - RM 354365

- Rodrigo Seiji Campos Imai - RM 353766
