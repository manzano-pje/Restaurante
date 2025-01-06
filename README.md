
# Projeto de gerenciamento de pedidos de restaurante

Esta é uma API intuitivo e eficiente para gerenciar atendimentos em lanchonetes e restaurantes, otimizando processos e garantindo agilidade no atendimento.  

### Funcionalidades principais:
 - Cadastros inteligentes: Gestão de empresas, atendentes, mesas, grupos de produtos (bebidas, lanches, etc.), setores de atendimento (cozinha, balcão, bar) e pedidos.  
 - Validações automatizadas:
  - O cadastro de produtos verifica automaticamente a existência de um grupo relacionado.  
  - O cadastro de grupos de produtos exige um setor de atendimento previamente configurado.

   * Fluxo de pedidos e mesas:
        * Abertura de mesas: O sistema verifica se a mesa está disponível ou já está ocupada. Caso esteja disponível, um novo pedido inicial é registrado e a mesa é configurada como ocupada.
        * Gestão de pedidos: Após a realização de um pedido, cada item é direcionado automaticamente para o setor de atendimento correspondente (cozinha, balcão, bar, etc.) para produção.
        * Pedidos adicionais: Pedidos subsequentes da mesma mesa são incorporados ao pedido inicial até o fechamento da mesa.

   * Fechamento de mesas:
        * Quando a mesa é fechada, o sistema:
            - Configura a mesa como disponível novamente.
            - Soma todos os pedidos realizados.
            - Gera um relatório detalhado com:
                 - Nome da mesa.
                 - Horário de abertura e fechamento.
                 - Total geral.
                 - Lista de itens consumidos, incluindo nome, quantidade e subtotal.  

Este sistema foi projetado para otimizar a operação de lanchonetes e restaurantes, trazendo maior controle, eficiência e clareza no gerenciamento de pedidos e atendimento.

### Aprendizado
- Aprimoramento da utiliazção de relacionamento entre entidades;
- Montagem de Queries;
- Utilização do RabbitMq;
- Utilização de componentes;
- Utilização de métodos dentro das DTO´s;
- Formatação de vallores em moeda corrente (NumberFormat.getCurrencyInstance);
- Utilização do framework Spring Boot;
- Trabalho com o Docker;
- aperfeiçoamento no uso do Git Hub.

### Ferramentas e Tecnologias

 <a href="#"><img src="https://github.com/manzano-pje/imagens/blob/master/monochrome_large.png  "  align="left" height="30em"/></a>
 <a href="#"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg"  align="left" height="30em"/></a>
 <a href="#"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jetbrains/jetbrains-original.svg"  align="left" height="30em"/></a>
 <a href="#"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"  align="left" height="30em" /></a>
 <a href="#"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-plain.svg"  align="left" height="30em"/></a>
 <a href="#"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg"  align="left" height="30em"/></a>   
 
 
<br>

##


## Etiquetas

Adicione etiquetas de algum lugar, como: [shields.io](https://shields.io/)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)


## Autores

- [Paulo Manzano](https://www.github.com/manzano-pje)


## 🚀 Sobre mim
Sou uma pessoa versátil e me adapto facilmente a diferentes situações, dependendo da necessidade do projeto. Adoro desafios e explorar novas tecnologias💻e aproveita-los para construir coisas legais🛠️.


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/paulo-manzano/)


