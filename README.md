# desafio-kotlin
Desafio Kotlin

# REST API

URL PADRÃO
http://localhost:7000/

POST url_padrao/users

Esse endpoint deverá receber um usuário com os campos "nome", "email", "senha", mais uma lista de objetos "telefone", seguindo o formato abaixo:

```Json
{
  "name": "NAME_USER",
  "email": "MAIL_USER",
  "password": "PASSWORD",
  "phones": [
                {
                    "number": "NUMBER",
                    "ddd": "DDD"
                }
            ]
}
```

POST url_padrao/login

Este endpoint irá receber um objeto com e-mail e senha.

```Json
{
  "email": "MAIL_USER",
  "password": "PASSWORD",  
}
```

GET url_padrao/users/:id
Este endpoint irá receber um headers com o Authorization no formato abaixo.

Headers

Authorization : Bearer TOKEN_USUARIO
