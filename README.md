# 📦 kelari-sample


Exemplo de uso real do **[Kelari – API Test Generator](https://github.com/agsn10/kelari-spring-api-test-generator)**, uma ferramenta para geração automática de testes de APIs REST em projetos Java com Spring Boot e JUnit 5.
Este projeto demonstra um endpoint com upload de arquivos, autenticação JWT e múltiplas respostas HTTP documentadas via anotação `@ApiTestSpec`.

---

## 🚀 Funcionalidades demonstradas

✅ Upload de arquivos com `@ModelAttribute`  
✅ Suporte a `@RequestPart` (multipart/form-data)  
✅ Testes com diferentes status (200, 400, 401, 404, 500)  
✅ Geração automática de testes com `mvn compile`  
✅ Integração com `WebTestClient` e JUnit 5  
✅ Suporte a autenticação via Bearer Token (simulado)  
✅ Organização por cenários via anotação `@ApiTestCase`

---

## 📁 Estrutura do projeto

```
kelari-sample/
├── src/
│   ├── main/
│   │   └── java/com/example/demo/ExampleResource.java
│   └── test/
│       └── java/com/example/demo/ExampleResourceGeneratedTest.java  ← gerado automaticamente
├── pom.xml
```

---

## ⚙️ Como executar

### 1. Clonar o projeto

```bash
git clone https://github.com/agsn10/kelari-spring-api-test-generator-sample.git
cd kelari-sample
```

### 2. Gerar os testes automaticamente com o Kelari

> Isso irá ler as anotações `@ApiTestSpec` e gerar os testes na pasta `src/test/java`.

```bash
mvn compile
```

### 3. Executar os testes

```bash
mvn test
```

---

## 🔐 Simulação de autenticação

Os testes simulam autenticação JWT usando um valor fictício de `Authorization: Bearer <token>` inserido automaticamente nos cenários que exigem autenticação.

---

## 📦 Dependências

Este projeto usa:

- Java 17+
- Spring Boot 2.7.x ou 3.x
- JUnit 5
- WebTestClient
- Kelari (fornecido via repositório GitHub Packages)

---

## 📄 Licença

Este exemplo está sob a [Licença MIT](LICENSE).

---

## 💡 Sobre o Kelari

> Geração automática de testes de API REST com suporte nativo ao ecossistema Spring Boot.

Desenvolvido por [Antonio Neto](https://www.linkedin.com/in/...), com apoio da comunidade e inspiração em projetos reais.
