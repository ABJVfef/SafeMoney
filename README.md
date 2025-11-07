# 💰 SafeMoney

> **SafeMoney** é um sistema de **controle financeiro pessoal** em desenvolvimento, projetado para ajudar usuários a **organizar, planejar e acompanhar suas finanças** de forma prática e segura.


## 🧭 Visão Geral

O **SafeMoney** tem como objetivo oferecer ao usuário um **controle completo das finanças pessoais**, permitindo o gerenciamento de:

- Contas bancárias e cartões de crédito  
- Lançamentos financeiros (a pagar e a receber)  
- Pagamentos, recebimentos e transferências  
- Extratos contábeis e projetados  
- Classificação de despesas e receitas por centro de custo  
- Entidades geradoras (como lojas, empresas e pessoas físicas)  

---

## 🎯 Objetivos do Sistema

- Facilitar o **controle financeiro diário** do usuário.  
- Automatizar **movimentações bancárias** e **baixas de lançamentos**.  
- Fornecer **relatórios de fluxo de caixa** e **extratos personalizados**.  
- Garantir **segurança e rastreabilidade** em todas as operações.  
- Disponibilizar uma **API RESTful** integrada a uma interface web amigável.

---

## 🏗️ Estrutura Conceitual

O domínio do sistema é composto pelos seguintes módulos principais:

| Módulo | Descrição |
|--------|------------|
| **Usuário** | Identifica o dono dos dados financeiros |
| **Conta Bancária** | Controla saldo e movimentações |
| **Cartão de Crédito** | Gerencia limite, fechamento e pagamento de faturas |
| **Lançamento Financeiro** | Representa contas a pagar e a receber |
| **Pagamento / Recebimento** | Registra baixas de lançamentos |
| **Movimento de Conta** | Controla débitos e créditos bancários |
| **Transferência** | Realiza movimentações entre contas |
| **Centro de Custo** | Agrupa lançamentos por categoria |
| **Entidade** | Define origem ou destino financeiro |
| **Relatórios e Consultas** | Gera extratos e análises por período |

---

## ⚙️ Principais Regras de Negócio

1. **Baixas de lançamentos**  
   - Atualizam automaticamente o status e o valor baixado.  
   - Criam registros em `MovimentoConta`.

2. **Movimentações bancárias**  
   - Pagamento → débito.  
   - Recebimento → crédito.  
   - Transferência → débito + crédito (transação única).

3. **Cartões de crédito**  
   - Lançamentos entram na fatura vigente.  
   - Faturas fechadas bloqueiam novos lançamentos.  
   - Pagamento de fatura gera débito em conta bancária.

4. **Auditoria e segurança**  
   - Todos os registros terão `criadoEm` e `atualizadoEm`.  
   - Ações restritas ao usuário proprietário dos dados.  

5. **Validações de integridade**  
   - Contas inativas não podem movimentar.  
   - Proibido transferir valores entre a mesma conta.  

---

## 🧱 Estrutura do Domínio

As principais entidades do sistema incluem:

| Entidade | Atributos Principais |
|-----------|----------------------|
| **Usuario** | id, nome, email, criadoEm |
| **ContaBancaria** | id, instituição, agência, número, saldoInicial, ativa |
| **CartaoCredito** | id, bandeira, emissor, fechamentoFaturaDia, vencimentoFaturaDia, ativo |
| **FaturaCartao** | id, competencia, dataFechamento, dataVencimento, valorTotal, status |
| **Lancamento** | id, tipo, descricao, valor, dataCompetencia, dataVencimento, status |
| **Pagamento / Recebimento** | id, data, valor, contaOrigem ou contaDestino, observação |
| **MovimentoConta** | id, dataMovimento, tipo, valor, histórico |
| **Transferencia** | id, contaOrigem, contaDestino, data, valor, observação |
| **CentroCusto** | id, nome, código, ativo |
| **Entidade** | id, nome, documento, tipo |

---

## 🧩 Tecnologias Planejadas

| Categoria | Tecnologia |
|------------|-------------|
| **Linguagem** | Java |
| **Framework Backend** | Spring Boot |
| **Banco de Dados** | PostgreSQL |
| **ORM** | Spring Data JPA |
| **Segurança** | Spring Security |
| **APIs** | RESTful |
| **Front-end (futuro)** | React.js / Vue.js (a definir) |
| **Documentação** | Swagger / OpenAPI |
| **Controle de Versão** | Git e GitHub |

---




