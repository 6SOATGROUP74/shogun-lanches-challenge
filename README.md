# Tech Challenge

Trabalho desenvolvido como forma de avaliação no curso de pós-graduação em Software Architecture na FIAP.

## Objetivo
Solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades

## Funcionalidades

- Cadastro do Cliente

- Identificação do cliente via CPF

- Criar, editar e remover produtos

- Buscar produtos por categoria

- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.

- Listar os pedidos


## Event Storming
- Link da documentação do event storming do grupo: [[Miro](https://miro.com/app/board/uXjVKYtyiY8=/?share_link_id=475227793071)] 

## Detalhes do projeto

[Diagrama da arquitetura](docs/Hexagonal_Architecture.jpg)

## Setup aplicação

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

## Swagger 
 Link de acesso a documentacao do swagger
> http://localhost:30000/swagger-ui/index.html

## Integrantes - Grupo 44

- Igor Alves da Silva - RM 354365

- Rodrigo Seiji Campos Imai - RM 353766
