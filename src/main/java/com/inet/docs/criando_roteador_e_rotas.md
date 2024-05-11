# Criando Roteador e Rotas

- [Ir para o Menu](./README.md)

## Summry

- [Criando Roteador e Rotas](#criando-roteador-e-rotas)
  - [Summry](#summry)
    - [Infos](#infos)
    - [Funcionalidades](#funcionalidades)
    - [Resumo](#resumo)
    - [Criar Estrutora Base do Roteador](#criar-estrutora-base-do-roteador)
    - [Criar uma Novas Rotas](#criar-uma-novas-rotas)
    - [Registrar Middleware](#registrar-middleware)
    - [Veja Também](#veja-também)

### Infos
>
> **Diretório:** `src/main/java/com/inet/routes`
>
> **Prefixo:** `Public, Private, Protected, V2_Public etc`
>
> **Sufixo:** `Router.java`
>
> **Class deve extends de:** `com.inet.framework.servers.Router`
>
> **Registrar novo roteador no Kenel:** `routersList`

### Funcionalidades

- roteador
- rotas
- middlewares

### Resumo

O roteador e as rotas são usada para manipulção de requisições *HTTP*, gerenciando tanto a responsta para o cliente tanto a criação requisição.

### Criar Estrutora Base do Roteador

```java
package com.inet.routes;

import com.inet.framework.servers.Router;

public class PublicRouter extends Router {

    public void registers() {
        // LOCAL PARA REGISTRA AS ROTAS
    }
}
```

### Criar uma Novas Rotas

```java
package com.inet.routes;

import com.inet.framework.servers.Router;

public class PublicRouter extends Router {

    public void registers() {
        route("GET", "/", (request, response) -> {
            return response.json("Hello World");
        });

        route("POST", "/users", (request, response) -> {
            return response.setStatus(201).json("Hello World - user created");
        });

        route("GET", "/no/content", (request, response) -> {
            return true;
        });
    }
}
```

| ⚠ Obrigatoriamente deve ser criado as rotas dentro do método `registers`.

Para criar uma nova rota é usado o método route.

Quando é criado uma nova rota perceba que usado dois parametro `request`, `response` e também é retornado um `Bollean` que indica que foi finalizado a requisição.

Mas caso não sejá feito nenhuma responsta é feito automaticamente uma responta com status `204` (no content), isso ocorre no exemplo da rota `/no/content`.

### Registrar Middleware

```java
package com.inet.routes;

import com.inet.framework.servers.Router;
import com.inet.middlewares.AuthMiddleware;

public class PublicRouter extends Router {

    public void registers() {
        route("GET", "/", (request, response) -> {
            return response.json("Hello World");
        });

        route("POST", "/users", (request, response) -> {
            return response.setStatus(201).json("Hello World - user created");
        });
    }

    public void middlewares_registers() {
        middleware(AuthMiddleware.class);
    }
}
```

| ⚠ Obrigatoriamente deve ser adicionado novas middlewares dentro do método `middlewares_registers`.

Para adiciona uma nova middleware é usado o metodo `middleware` passando a middleware não instanciada.

### Veja Também

- [Criando Middlewares](./criando_middlewares.md)
