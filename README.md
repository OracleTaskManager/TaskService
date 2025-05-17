## 🔐 Task Service - Oracle Task Manager

Este repositorio contiene el microservicio de gestión de tareas para el sistema Oracle Task Manager. Proporciona funcionalidades para la creación, asignación y seguimiento de tareas, sprints y relaciones entre usuarios y tareas.

### Requisitos previos

* Java JDK 23
* Maven
* Oracle Wallet (proporcionado separadamente)
* Git

### Configuración del entorno local

#### 1. Clonar el repositorio

```bash
git clone https://github.com/tuOrganizacion/TaskService.git
cd TaskService
```

#### 2. Configurar Oracle Wallet

##### 2.1 Configurar `sqlnet.ora`

```ora
WALLET_LOCATION = (SOURCE = (METHOD = file) (METHOD_DATA = (DIRECTORY="C:\Users\cesar\Wallet_TelegramBotDatabase")))
SSL_SERVER_DN_MATCH=yes
```

##### 2.2 Configurar `application.properties`

```properties
spring.datasource.url=jdbc:oracle:thin:@TelegramBotDatabase_medium?TNS_ADMIN=C:/Users/your-username/Wallet_TelegramBotDatabase
spring.datasource.username=ADMIN
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
jwt.secret.oracle=${JWT_SECRET_ORACLE}
server.port=8081

spring.datasource.hikari.maximum-pool-size=3
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.max-lifetime=600000

# Swagger config local
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html

# Comentar estas líneas en desarrollo local
springdoc.swagger-ui.configUrl=/swagger-task/v3/api-docs/swagger-config
springdoc.swagger-ui.url=/swagger-task/v3/api-docs
springdoc.swagger-ui.oauth2RedirectUrl=/swagger-task/swagger-ui/oauth2-redirect.html
springdoc.swagger-ui.disable-swagger-default-url=true
```

Variables de entorno:

* `DB_PASSWORD`
* `JWT_SECRET_ORACLE`

#### 3. Ejecutar el servicio

```bash
mvn clean package
java -jar target/TaskService-0.0.1-SNAPSHOT.jar
# O directamente:
mvn spring-boot:run
```

#### 4. Verificación

* API: [http://localhost:8081](http://localhost:8081)
* Swagger UI: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

---

### Diferencias entre local y producción

#### Configuración del Wallet

| Local                                               | Producción                        |
| --------------------------------------------------- | --------------------------------- |
| `C:/Users/your-username/Wallet_TelegramBotDatabase` | `/wallet` (montado en contenedor) |

#### Configuración Swagger

| Local                 | Producción                                          |
| --------------------- | --------------------------------------------------- |
| `/swagger-ui.html`    | `http://140.84.189.81/swagger-tasks/swagger-ui/index.html` |
| Sin prefijo           | Prefijo `/api/tasks`                                 |
| `configUrl` comentado | Requiere configUrl para el entorno                  |

#### Endpoints

| Local                             | Producción                                |
| --------------------------------- | ----------------------------------------- |
| `http://localhost:8081/tasks/...` | `http://140.84.189.81/api/tasks/tasks/...` |

#### GitHub Actions

Pipeline `.github/workflows/build-push-task.yml` automatiza:

1. Modificación de `application.properties` para producción
2. Compilación y empaquetado
3. Construcción de imagen Docker
4. Push al Oracle Container Registry
5. Montaje del wallet desde secretos
6. Actualización en Kubernetes

---

### Ejemplo de petición

```http
POST http://localhost:8081/tasks
Content-Type: application/json

{
  "name": "Nueva tarea",
  "description": "Descripción de ejemplo"
}
```

---

### Documentación API

* **Local**: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
* **Producción**: [http://140.84.189.81/swagger-tasks/swagger-ui/index.html](http://140.84.189.81/swagger-tasks/swagger-ui/index.html)

---
