# Trabalho Final Sistemas Distribuidos
- Um sistema de um jogo parecido com jokenpô com 4 jogadores.
- Para a comunicação entre os usuários e servidor foi utilizado o protocolo HTTP para as chamadas ao servidor, trocando mensagens por JSON, e TCP para as mensagens enviadas por socket.
- Para gerenciar os dados, foi utilizado o PostgreSQL.

# Arquitetura e funcionamento do sistema
<p align="center">
    <img src="docs/arquitetura.png" />
</p>


## Tecnologias utilizadas
- Angular
- Java Spring Boot
- PostgreSQL

## Funcionalidades 
- O jogador se inscreve em vários canais de socket:
  - Principal: ele se inscreve ao entrar no sistema, para receber informações das salas criadas.
  - Sala do jogo: ele se inscreve neste canal para trocar informações com os outros jogadores.
- Vários jogadores podem jogar em salas simultâneas.
- Um jogador poderá criar uma sala que comporta 4 jogadores simultâneos.
- Caso algum usuário fique offline, o sistema deverá abortar a partida.

# Execução do sistema

Primeiro deve-se clonar o repositório

```bash
git clone https://github.com/JuanFSR/TrabalhoSD
```

## Cliente

Para rodar o cliente, deve-se primeiro instalar todas as dependências.
```bash
cd frontend/
```
```bash
npm install
```

Após isso, basta inicializar o cliente com o comando:
```bash
ng serve --open
```

## Servidor

Para rodar o servidor, deve-se primeiro instalar todas as dependências.
```bash
cd backend/
```
```bash
mvn compile
```
Após isso, basta inicializar o servidor com o comando:
```bash
mvn spring-boot:run
```
