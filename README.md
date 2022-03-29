# Back-End

Back-End projet E-Commerce 

## Configuration

### Dev
Quand vous êtes en cours de développement, vous devez créer votre propre fichier `application-dev.yml` à placer dans `src/main/resources`.
#### application-dev.yml
Ceci est un exemple de configuration. Si le mot de passe de votre base de données ne correspond pas ou autre, vous pouvez le changer dans le fichier qui est de toute façon dans le `.gitignore`.

```yaml
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce
    password: root
    username: root
    driver-class-name: org.mariadb.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQL5Dialect
    defer-datasource-initialization: true
    show-sql: true
  sql:
    init:
      mode: always
      data-locations: "classpath:init_data.sql"
jwt:
  secret: waooappseckey
  expiration: 86400000
mail:
  host: smtp.gmail.com
  port: 587
  username: someone@gmail.com
  password: "mot de passe en clair"
  auth: true
  ttls: true
  commercial: commercial@waoo.fr
  admin: admin@waoo.fr
```

## Jeu de données
Un jeu de données est fourni dans le fichier `init_data.sql` avec trois utilisateurs.

```
E-mail
leclient@waoo.fr
Mot de passe
password
Rôle
Client

E-mail
levendeur@waoo.fr
Mot de passe
password
Rôle
Commercial

E-mail
ladmin@waoo.fr
Mot de passe
password
Rôle
Admin
```
