# Projeto Cardap.io

O Projeto Cardap.io é uma ferramenta de gerenciamento de pedidos para restaurantes que adotam o modelo tradicional de comanda, com ele é possível monitorar grande parte do processo operacional de maneira fácil e rápida sendo possível armazenar e consultar todos os dados de todas as diárias em um servidor na nuvem.

## Sistema

O grande sistema é dividido em duas partes, um **servidor global** que pode armazenar os dados e retornar consultas dos dados de diárias de diversos estabelecimentos e os clientes desse servidor que são os **estabelecimentos** (usaremos “estabelecimento” para se referir aos restaurantes).

<picture>
    <source media="(prefers-color-scheme: light)" srcset="./docs/static/big_system_representation_light.svg" />
    <source media="(prefers-color-scheme: dark)" srcset="./docs/static/big_system_representation_dark.svg" />
    <img alt="representação do grande sistema" />
</picture>

## Gerenciamento

Os estabelecimentos são um subsistema dividido em três partes, **salão**, **cozinha** e **caixa**, o salão recebe os clientes, abre/fecha mesas e atende os pedidos, a cozinha recebe os pedidos e notifica quando estiverem prontos, o caixa monitora a interação entre o salão e a cozinha, processa todos os pedidos para enviar para a nuvem (servidor global), recebe notificações de imprevistos do salão e da cozinha e indica como cada um deverá tratar o problema, ou seja, será o administrador.

A conexão entre o salão, cozinha e caixa ocorrerá de forma local por conexão LAN do estabelecimento, essa alternativa foi escolhida para o sistema não necessitar de conexão com a internet 24/7 sendo assim, os dados processados podem ser armazenados localmente no caixa e enviados para o servidor global a qualquer momento.

<picture>
    <source media="(prefers-color-scheme: light)" srcset="./docs/static/client_system_representation_light.svg" />
    <source media="(prefers-color-scheme: dark)" srcset="./docs/static/client_system_representation_dark.svg" />
    <img alt="representação do cliente" />
</picture>

## Pastas

Para melhor organização do projeto em geral, foi escolhida a seguinte sistema de árvore de arquivos.

```q
📁src             // Pasta com códigos java
 |_ 📁server       
 | |_ 📁protocol  // Protocolos
 | |_ 📁database  // Banco de dados
 |_ 📁client       
 | |_ 📁cashier   // Caixa
 | |_ 📁kitchen   // Cozinha
 | |_ 📁hall      // Salão
 |_ 📁resources   // Arquivos estáticos (imagens etc.)
 |_ 📁gui         // UI do cliente
 |
📁out             // Saída de arquivos compilados (configurado na IDE)
 |_ 📁production  // Saída de arquivos compilados (produção) 
 | |_📁...        // Mesma organização da pasta src
 |_ 📁...         // Mesma organização da pasta src
 |
📁docs            // Documentação do código em geral em .md
 |_ 📁static      // Possíveis imagens da documentação
 |
📁lib             // Para bibliotecas externas
```
