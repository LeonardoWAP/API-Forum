# API-Forum!

Projeto para ser desenvolvido uma API , basica de um Forum 

Implementando algumas entidades como :
* CustomerModel 
* HashtagModel
* MessageModel
* ThreadModel

com o intuito de aprender e desenvolver um forum com que as pessoas consigam enviar thread e outras pessoas consigam mandar mensagens dentro das thread a fim de perguntar duvidas. 

foi utilizado o banco de dados Postgre e a linguagem kotlin para o desenvolvimento da API.

bibliotecas utilizadas : 
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

