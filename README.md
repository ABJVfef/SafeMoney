💰 SafeMoney

SafeMoney é um sistema robusto e intuitivo de controle financeiro pessoal, desenvolvido em Java com Spring Boot. Projetado para ser o assistente financeiro definitivo, ele permite gerenciar contas bancárias, cartões de crédito, despesas, receitas e transferências de forma centralizada e eficiente.

Este projeto foi construído seguindo as melhores práticas de desenvolvimento RESTful, com uma arquitetura limpa em camadas e foco na integridade e segurança dos dados.

🚀 Principais Funcionalidades

🏦 Controle de Contas Bancárias

Gerencie múltiplas contas (corrente, poupança, investimento) em um só lugar.

Saldo Inteligente: O sistema calcula seu saldo atual automaticamente com base em todas as suas movimentações.

Extrato Detalhado: Visualize o histórico completo de débitos e créditos por período, com cálculo de saldo anterior e final.

💳 Gestão de Cartões de Crédito

Mantenha seus gastos no crédito sob controle.

Controle de Faturas: O sistema organiza suas compras por competência (mês/ano) automaticamente.

Fechamento de Fatura: Funcionalidade para fechar a fatura do mês, somando todas as compras.

Pagamento Integrado: Pague a fatura usando o saldo da sua conta bancária com um único clique.

💸 Lançamentos Financeiros

O coração do sistema. Registre tudo o que entra e sai.

Contas a Pagar e Receber: Cadastre despesas futuras e receitas previstas.

Baixa Automática: Ao confirmar um pagamento ou recebimento, o sistema atualiza o status da conta e movimenta o saldo instantaneamente.

Categorização: Organize seus lançamentos por Centros de Custo (ex: Alimentação, Transporte) e Entidades (ex: Supermercado X, Empresa Y).

🔄 Transferências entre Contas

Mova dinheiro entre suas contas com segurança.

Transação Atômica: O sistema garante que o dinheiro só sai da origem se entrar no destino. Sem riscos de "sumir" saldo no caminho.

🛠️ Tecnologias Utilizadas

Java 17 - Linguagem moderna e robusta.

Spring Boot 3 - Framework líder para desenvolvimento ágil.

Spring Data JPA - Persistência de dados descomplicada.

H2 Database - Banco de dados em memória para testes rápidos.

PostgreSQL - Banco de dados relacional poderoso para produção.

Bean Validation - Garantia de que os dados chegam limpos e corretos.

Maven - Gerenciamento de dependências.

🏛️ Arquitetura do Projeto

O SafeMoney foi desenhado com uma arquitetura em camadas bem definidas, garantindo manutenibilidade e escalabilidade:

Resources (Controllers): A porta de entrada da API. Recebem as requisições HTTP e retornam as respostas JSON.

Services: O cérebro do sistema. Aqui residem as regras de negócio (ex: "não pode pagar uma conta com saldo insuficiente").

Repositories: A camada que fala com o banco de dados.

Domains (Entities): As classes que representam as tabelas do banco.

DTOs (Data Transfer Objects): Objetos leves para transportar dados entre o cliente e o servidor, protegendo o domínio.




🧪 Testando a API (Postman)

O sistema já vem com uma carga inicial de dados (Usuário de teste, Categorias, etc.) para você começar a brincar imediatamente!

Exemplos de Requisições:

Listar Contas: GET /api/v1/contas

Criar Lançamento: POST /api/v1/lancamentos

Ver Extrato: GET /api/v1/contas/{id}/extrato?inicio=2025-01-01&fim=2025-01-31

👥 Autores

Este projeto foi desenvolvido como parte de um estudo avançado de Spring Boot.

Desenvolvedores: Adriano Alves, Breno Rogerio, João Pedro, Leonardo Santos e Vinicius Oliveira 

Feito com ☕ e Spring Boot.
