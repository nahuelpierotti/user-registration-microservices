# user-registration-microservices

Api sencilla desarrollada en Java para la generacion de tokens usando JWT.





## Deployment

Para deployar el proyecto es necesario contar con java jdk 21 y springboot 3.4.0 en adelante.
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
      "number": 1133335555,
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
que dara como respuesta los datos iniciales mas el token generado, ej:
```bash
{
    "id": "becbea36-ba94-4389-8bce-1c0b857ff871",
    "created": "2025-05-01T00:14:45.932+00:00",
    "lastLogin": "2025-05-01T00:14:45.932+00:00",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoiTmFodWVsIiwic3ViIjoibnBpZXJAZ21haWwuY29tIiwiaWF0IjoxNzQ2MDU4NDg2LCJleHAiOjE3NDYwNzY0ODZ9.q-bzSKy-71fnNI1Lrd2x0Us6tbqeAf1GAhKNty6STSFHhUe5JuTTerREAuc27zkAhgvSyQ_Pbk6BuCPwm_XTSw",
    "email": "johndoe@email.com",
    "password": "$2a$10$bRzyVRXhqgV.zc504rVTLuVXn55XQpR0OZGNR.vmZs28ie37a3L3.",
    "active": true,
    "phone": [
        {
            "number": 1133335555,
            "citycode": 1,
            "countrycode": "54"
        }
    ]
}
```
