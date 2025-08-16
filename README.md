# Projeto Integrador 6 — *Nome a definir*

> Jogo 2D estilo sandbox (Terraria-like) em **Java**, com **multiplayer autoritativo** e **sistema próprio de gerenciamento de threads**.
>
> **Objetivo**: criar um jogo 2D simples integrando conceitos de **programação paralela** e **redes de computadores**, visando **eficiência**, **baixa latência** e **jogabilidade fluida**.

---

## 👨‍💻 Integrantes do Grupo
- **Bruno Tasso Savoia** — RA: 22000354  
- **Arthur José Silva Maluf** — RA: 22005252  
- **Gustavo Kenji Mendonça Kaneko** — RA: 23002633  
- **Marcos do Amaral Miotto** — RA: 23004827  
- **Vitor Hugo Amaro Aristides** — RA: 20018040  

---

## 📝 Descrição (Resumo do Projeto)
Este projeto consiste na criação de um **jogo 2D em tiles**, com mundo particionado em **chunks**, **servidor autoritativo**, replicação de estado por **interesse** (apenas dados relevantes por jogador) e um **sistema próprio de threads** para rede, IO e geração/salvamento de chunks.  
A renderização utiliza **LibGDX (desktop/LWJGL3)**. A comunicação, **Netty (NIO)**. Serialização binária com **Kryo**.

---

## 🕹️ Funcionalidades Principais *(a definir)*

- [ ] Mundo 2D **tile-based** (chunks)  
- [ ] Player com movimento, gravidade e colisão simples  
- [ ] Colocar/remover blocos (replicação em tempo real)  
- [ ] **Servidor autoritativo** com **tick fixo** (ex.: 20–30 TPS)  
- [ ] **Cliente** com render a 60 FPS (LibGDX) e fila de rede  
- [ ] **Sistema de threads**: rede, IO e geração de chunks em pools  
- [ ] Persistência em arquivo binário por chunk  
- [ ] **Interesse**: envio de dados apenas dos chunks próximos  
- [ ] HUD básica (FPS, ping, coords)  
- [ ] Testes básicos (JUnit) e logs (SLF4J/Logback)  


---

## 🛠️ Stack Técnica

**Linguagem:** Java 17 LTS  
**Build:** Gradle (wrapper incluído)  
**Render (cliente):** LibGDX (desktop, LWJGL3)  
**Rede:** Netty (TCP inicialmente; UDP opcional depois)  
**Serialização:** Kryo  
**Logs:** SLF4J + Logback

---

## 🏗️ Build & Run (VSCode)

### Clone o Repositório

### Configurações

### 🔹 Pré-requisitos
- **Java 17**

- **Settings.json**
````
{
  "java.import.gradle.wrapper.enabled": true,
  "java.import.gradle.version": "wrapper",
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.jdt.ls.java.home": "JAVA 17 PATH"
}
````
- **Vscode Settings**
  ````CTRL+SHIFT+P -> Configure java runtime -> Java 17````


### 🔹 Execução
- CTRL + SHIFT + B -> Build & Run

---


