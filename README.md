# user-registration-microservices

Api sencilla desarrollada en Java para la generacion de tokens usando JWT.





## Deployment

Para deployar el proyecto es necesario contar con jdk 21 en adelante.
Se recomienda usar Intellij Idea 2024.3.5 en adelante.
En el application.properties se dejo configurada la base de datos h2 y una clave que se puede modificar para fines de usar como llave para la generacion de tokens.

El primer endpoint al que se accede es:

```bash
  users/sign-up
```

- Se le debe proveer un json en el body que contenga el siguiente formato:

```bash
{
  "name": "John Doe",
  "email": "johndoe@email.com",
  "password": "a2asfGdfdf4",
  "phones": [
    {
      "number": 113693568,
      "citycode": 1,
      "countrycode": "54"
    }
    ]
}
```
La respuesta tendra un token que debe ajuntarse en el parametro Authorization en la llamada al endpoint:
```bash
  users/login
```
que dara como respuesta los datos iniciales mas el token generado.
