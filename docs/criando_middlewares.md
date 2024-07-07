# Criando Middlewares

- [Ir para o Menu](./README.md)

## Summary

- [Criando Middlewares](#criando-middlewares)
  - [Summary](#summary)
    - [Infos](#infos)
    - [Resumo](#resumo)
    - [Estrutura Base de uma Middleware](#estrutura-base-de-uma-middleware)
    - [Passar para a Proxima Middleware](#passar-para-a-proxima-middleware)
    - [Veja Também](#veja-também)

### Infos
>
> **Diretório:** `src/main/java/com/inet/middlewares`
>
> **Prefixo:** *A sua escolha*
>
> **Sufixo:** `Middleware.java`
>
> **Class deve extends de:** `com.inet.framework.servers.contracts.MiddlewareInterface`

### Resumo

Middlewares são utilizadas para manuziar `requests` e `responses` da rota antés mesmo dela ser executada, muito utilizada por exemplo em autenticação de usuário.

### Estrutura Base de uma Middleware

```java
package com.inet.middlewares;

import com.framework.servers.Middleware;
import com.framework.servers.ServerRequest;
import com.framework.servers.ServerResponse;

public class AuthMiddleware extends Middleware {

    public void handle(ServerRequest request, ServerResponse response) {
        // IMPLEMENTAÇÂO DAS AÇÔES DA MIDDLEWARE
    }
}
```

### Passar para a Proxima Middleware

```java
package com.inet.middlewares;

import com.framework.servers.Middleware;
import com.framework.servers.ServerRequest;
import com.framework.servers.ServerResponse;

public class AuthMiddleware extends Middleware {

    public Boolean handle(ServerRequest request, ServerResponse response) {

        if (request.getQuerys().get('code')->equals('123')){
            return next();
        }

        response.setStatus(401).json("Usuário não autorizado");
    }
}
```

Quando ocorre a requisição e essa middleware estiver registrada na rota antés da rota ser executada vai passar por essa middleware verificado se o codigo vindo da url e igual a 123, se for o fluxo continua normalmente mas se não é interopido o fluxo e é feito uma resposta json com status 401 com a copo da resposta "Usuário não autorizado".

### Veja Também

- [Criando Roteador e Rotas](./criando_roteador_e_rotas.md)
