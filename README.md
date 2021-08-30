# Trabalho Final Sistemas Distribuidos
- Um sistema do jogo jokenpô com 4 jogadores.
- Para a comunicação será usado o protocolo HTTP para as chamadas ao servidor e TCP para as mensagens enviadas por socket.

# Arquitetura e funcionamento do sistema
<p align="center">
    <img src="docs/arquitetura.png" />
</p>

- Para um usuario poder jogar, o mesmo deve inserir informações (nome e e-mail), e escolher entrar em alguma sala ou criar uma sala.
- Assim que o usuario entra na sala, ele deve aguardar até que 4 jogadores também entrem junto dele. Após terem entrado a partida começa, onde cada jogador faz seu lance e após todos finalizarem o resultado é enviado via socket.

# Execução do sistema

Primeiro deve-se clonar o repositório

```bash
git clone https://github.com/JuanFSR/TrabalhoSD
```

## Cliente

Para rodar o cliente, deve-se instalar todas as dependências.
```bash
cd frontend/
npm install
```
