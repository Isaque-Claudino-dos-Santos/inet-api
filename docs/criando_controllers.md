# Criando Controllers

- [Ir para o Menu](./README.md)

## Summary

- [Criando Controllers](#criando-controllers)
  - [Summary](#summary)
    - [Infos](#infos)
    - [Resumo](#resumo)
    - [Como Criar uma Nova Controller](#como-criar-uma-nova-controller)
    - [Aplicar Controller em uma Rota](#aplicar-controller-em-uma-rota)
    - [Veja Também](#veja-também)

### Infos

> **Diretório:** `src/main/java/com/inet/controllers`
>
> **Prefixo:** *A sua escolha*
>
> **Sufixo:** `Controller.java`

### Resumo

As controller é utilizada nas rotas, controller pode ter varios métodos para adicionar na rota mas o método deve ser estático, retornar boleano e ter dois parametro request e response.

### Como Criar uma Nova Controller

```java
package com.inet.controllers;

import servers.framework.src.main.java.com.ServerRequest;
import servers.framework.src.main.java.com.ServerResponse;

public class MyController {
    public static Boolean index(ServerRequest request, ServerResponse response) {
        // IMPLEMENTAÇÂO DA CONTROLLER
    }

    public static Boolean show(ServerRequest request, ServerResponse response) {
        // IMPLEMENTAÇÂO DA CONTROLLER
    }

    public static Boolean create(ServerRequest request, ServerResponse response) {
        // IMPLEMENTAÇÂO DA CONTROLLER
    }

    public static Boolean update(ServerRequest request, ServerResponse response) {
        // IMPLEMENTAÇÂO DA CONTROLLER
    }
}
```

| Uma controller controller pode ter varios métods.

### Aplicar Controller em uma Rota

```java
package com.inet.routes;

import com.inet.controllers.MyController;
import servers.framework.src.main.java.com.Router;

public class PublicRouter extends Router {

    public void registers() {
        route("GET", "/", MyController::index);
    }
}
```

### Veja Também

- [Criando Roteador e Rotas](./criando_roteador_e_rotas.md)