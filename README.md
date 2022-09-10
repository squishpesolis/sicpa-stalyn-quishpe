# Despliegue del sistema

# CASO PRACTICO JAVA
## _Stalyn Quishpe, Ever_



[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://github.com/squishpesolis/sicpa-stalyn-quishpe.git)

Este proyecto fue desarrollado en Spring Boot, hibernate y docker,

## Url del proyecto
- https://github.com/squishpesolis/sicpa-stalyn-quishpe.git

## Compilacion y Despliegue con Docker


## Instalacion

Este microservicio requiere [docker](https://www.docker.com/products/docker-desktop/)  para ejecutar.

Compilacion y despliegue del microservicio.
Ubicarse en la raiz del proyecto, donde este el archivo Dockerfile
```sh
Para construir el sistema se debe ejecutar el siguiente comando
docker-compose up --build -d

Para comporbar que esta arriba los servicios se debe ejecutar el siguiente comando;
docker-compose ps

Para detener los servicios se debe ejecuar el siguiente comando
sudo docker-compose stop
```

Para verificar el despliegue podemos ingresar a la siguiente URL.

swagger

```sh
http://127.0.0.1:8080/api/swagger-ui/index.html
```







