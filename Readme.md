# Multithreading com Spring transactional

Objetivo: Controlar prioridades em envios de arquivos via isolamento transacional. Entender os principais isolamentos do Spring Transactional.

Spring transactional não trabalha com Threads, para simular um ambiente onde múltiplos clientes estão fazendo uploads de arquivos, foi usado Testng para criar a concorrência e Selenium para fazer os uploads via tela html.

  - Spring Transactional com isolamento e propagação
  - Testng com Pool de Threads
  - Selenium com webdriver do Chrome
  - Spring boot
  - JPA
  - Banco de dados Oracle

# Execute!

  - 1º: Instale as dependências POM, Oracle repository (caso usando branch Oracle); TestNG test; Configure seu .properties com informações do seu banco de dados
  - 2º: Inicie a main (spring boot)
  - 3º Rode o teste com TestNG (src/test/Java/UploadConcurrentTest)


