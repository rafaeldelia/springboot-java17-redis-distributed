### 1. Tecnologias Utilizadas

   - Java JDK 17
   - Spring Boot Framework 3.2.2
   - Caching Abstraction
   - Redis
   - Maven
   - Spring Data JPA - JPA is an interface and Hibernate is the implementation
   - Project Lombok
   - H2 Database Engine ou PostgreSQL
   - Spring Actuator: monitoramento e gerenciamento de aplicações
   - Mapped Diagnostic Context (MDC)
   - SonarLint by Sonar
   
### 2. Configuração

##### Instale

* [Java JDK 17](https://openjdk.java.net/projects/jdk/17/)
* [Git](https://git-scm.com/downloads)
* [Postman Agent](https://www.postman.com/downloads/);
* [Sonar Lint](https://www.sonarsource.com/products/sonarlint/)

### 3. Build e Deploy 

#### 3.1 Ambiente de desenvolvimento (LocalHost)

##### Spring Boot 

   1. Importe o projeto como Maven project
   2. Realize o Maven Build...
   3. Digite mvn clean install
   
##### SonarLint

   1. Adicione o plugin sonarLint na IDE
   2. Selecione a pasta raiz do projeto
   3. Clique com o botão direito do mouse
   4. Escolha SonarLint -> Analize  
   5. Vizualize os erros indicados pelo sonarLint
   6. Corrija-os
   7. Repita o passo 4 até zerar os codes smells

##### Docker

   1. Acesse a pasta \ms-cache\target onde o JAR foi compilado
   2. Crie a imagem no docker
   		2.1 docker build -t <seu repo>/ms-cache:01 . 
   3. Suba para o Docker Hub - se desejar
        3.1 docker push <seu repo>/ms-cache:01
   4. Obs.: Subir com o docker exige que você tenha subido o container do Redis também.
   
   
##### Docker-Compose

   1. Acesse a pasta \ms-cache\target onde o JAR foi compilado
   2. Crie a imagem no docker
   		2.1 docker build -t <seu repo>/ms-cache:01 . 
   3. Suba para o Docker Hub - se desejar
        3.1 docker push <seu repo>/ms-cache:01
   4. Obs.: Subir com o docker exige que você tenha subido o container do Redis também.
   ms-cache\src\docker\docker-compose-redis.yml
        
##### Logback

##### CURL

curl --location 'http://172.210.89.150:8080/ms-cache/v1/area/list-all'


http://localhost:8080/ms-cache/actuator/metrics/hikaricp.connections

http://localhost:8080/ms-cache/actuator/health

http://localhost:9090/graph