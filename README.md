# 🖨️ Integração Java com Impressora Elgin i9
### *(Sistema de Impressão Java com DLL — Impressoras Elgin (JNA).)*
---

## 📘 Sobre o Projeto

Este projeto foi desenvolvido por alunos com o objetivo de criar uma aplicação Java capaz de se comunicar com a impressora **Elgin i9**, utilizando comandos **ESC/POS**, testes de impressão e interação via porta de comunicação.  
O trabalho faz parte da disciplina de **Análise e desenvolvimento de sistemas**.

---

## 🧰 Tecnologias Utilizadas

- **Java 17**
- **IntelliJ IDEA**
- **Biblioteca ESC/POS**
- **Driver/SDK da Elgin i9**


---

## 📁 Estrutura do Projeto

/src → Código-fonte principal
/lib → Bibliotecas e dependências externas
/docs → Materiais adicionais e documentação
/examples → Testes e arquivos de demonstração


---

## ⚙️ Instalação e Execução

### 🔽 1. Baixar o Projeto do GitHub
1. Acesse o repositório.
2. Clique no botão **Code**.
3. Selecione **Download ZIP**.
4. Aguarde o download.

### 📦 2. Extrair o Arquivo ZIP
1. Vá até o local onde o arquivo foi salvo.
2. Clique com o botão direito → **Extrair aqui** / **Extract All**.
3. Será criada uma nova pasta com os arquivos.

### 🧭 3. Importar no IntelliJ IDEA
1. Abra o **IntelliJ IDEA**.
2. Clique em **Open**.
3. Escolha a pasta extraída.
4. Aguarde o carregamento.
5. Caso apareça, clique em **Import Project** (Maven/Gradle).
6. Verifique o SDK em:
   - *File → Project Structure → Project SDK → Java 17*
7. Abra o arquivo `Main.java` e clique em ▶️ **Run**.

---

## 🧪 Testes

- Teste de conexão com a impressora
- Impressão de texto simples
- Envio de comandos **ESC/POS**
- Verificação das portas de comunicação
- Teste com várias mensagens

---

## 📚 Documentação Adicional

- Manual da Impressora **Elgin i9**
- Documentação do **SDK Elgin**
- Referência **ESC/POS**

INSTRUÇÕES DOS PRÓPRIOS AUTORES FORA DO MODELO: 
# Sistema de Impressão Java com DLL — Impressoras Elgin (JNA).

Este projeto demonstra a integração entre Java e impressoras Elgin por meio da DLL E1_Impressora01.dll, utilizando a biblioteca JNA (Java Native Access).
Com ele, é possível realizar impressões de texto, QR Code, código de barras, XML SAT, XML de cancelamento, além de acionar gavetas e emitir sinais sonoros.

🚀 Recursos Disponíveis

O menu interativo permite realizar:

🔌 Conexão

Configurar conexão (modelo, tipo e interface)

Abrir conexão

Fechar conexão

🖨️ Impressões

Impressão de texto

Impressão de QR Code

Impressão de código de barras

Impressão de XML SAT

Impressão de XML de cancelamento SAT

📦 Acessórios

Abrir gaveta Elgin

Abrir gaveta padrão

Avanço de papel

Corte de papel

Sinal sonoro (beep)

📁 Estrutura do Código
Main.java
|
|-- Interface ImpressoraDLL
|      - Mapeia funções da DLL via JNA
|
|-- Métodos de controle
|      - abrirConexao()
|      - fecharConexao()
|      - impressaoTexto()
|      - impressaoQRCode()
|      - impressaoCodBarra()
|      - impressaoXMLSAT()
|      - impressaoXMLcancSAT()
|      - abrirGaveta()
|      - abrirGavetaElgin()
|      - sinalSonoro()
|      - corte()
|      - avancaPapel()
|
|-- Menu principal de interação

📦 Requisitos
✔️ Java 11 ou superior
✔️ Biblioteca JNA

Se estiver usando Maven:

<dependency>
    <groupId>net.java.dev.jna</groupId>
    <artifactId>jna</artifactId>
    <version>5.13.0</version>
</dependency>


Ou baixe o JAR:
https://mvnrepository.com/artifact/net.java.dev.jna/jna

✔️ DLL da impressora Elgin

A DLL usada é carregada nesta linha:

Native.load("C:\\Users\\...\\E1_Impressora01.dll", ImpressoraDLL.class);


➡️ Ajuste o caminho para o local correto no seu PC.

⚙️ Como executar

Instale o JDK 11+

Baixe ou configure o JNA no classpath

Garanta que a DLL esteja acessível

Compile:

javac Main.java


Execute:

java Main

🧭 Menu do Sistema

Ao abrir o programa, você verá:

1  - Configurar Conexao
2  - Abrir Conexao
3  - Impressao Texto
4  - Impressao QRCode
5  - Impressao Cod Barras
6  - Impressao XML SAT
7  - Impressao XML Canc SAT
8  - Abrir Gaveta Elgin
9  - Abrir Gaveta
10 - Sinal Sonoro
0  - Fechar Conexao e Sair

⚠️ Observações

Sempre abra a conexão antes de tentar imprimir.

O programa automaticamente avança papel e corta após cada impressão.

A função de XML de cancelamento requer:

XML válido

Assinatura QR Code válida (string Base64)

O menu fecha o scanner ao sair, então o programa termina por completo.

🛠️ Pontos Melhoráveis

Externalizar configurações em um arquivo JSON/YAML

Criar uma interface gráfica em JavaFX

Validar XMLs antes de enviar para a DLL

Criar logs de execução

Implementar melhor tratamento de erros

📄 Licença

Este projeto é aberto para uso educacional e integração com equipamentos Elgin.

Autores:
Eduardo Silva
Lucca de Toledo Trentini
Pedro Joaquim


---

## 👥 Autores  
*(em ordem alfabética)*

- Ana Beatriz Souza
- Carlos Henrique Pereira
- Marcos Vinicius Almeida
