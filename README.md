# Projeto Teste JavaDesktop


Este projeto foi criado apenas para substituir um mini sisteminha, escrito em outra linguagem de programacao.


- Geralmente nao e a forma que costumo utilizar, mas como era um simples projeto
  com o objetivo de resolver um pequeno problema com relatorios, resolvi criar assim mesmo (simples).

- Os relacionamentos entre as classes foram replicadas do outro projeto (por isso o relacionamento nao existe rsrs).

- As tabelas do banco tambem foi replicada do outro projeto, para
  evitar uma eventual dor de cabeca de relacionamento com o banco de dados.
  
  
O objetivo aqui era criar um relatorio simples, que o proprio cliente solicitou.
  Todo o sistema esta da forma que o cliente solicitou.
  
  
Topicos do sisteminha (simples, muito simples)
Utilizando o Padrao MVC (projeto desktop):

	CONTROL:
	- classe Util com alguns comandos e utilidades
	- classe simples de conexao com banco de dados
	- classe repository com comandos em SQL utilizando PreparedStatement
	- classe service onde fica o grosso das regras (nesse caso nem tanto rsrs), processar requisicoes
	
	- classe Listener para separar Requisicoes e Acoes dos botoes da tela (aqui que ta o grosso das regras rsrs)
	
	MODEL: 
	- classe Atendimento
	- classe Convenio
	- classe Hospital
	- classe Medico
	- Enum EnumNatureza
	- Enum UF
	- classe de filtro pra abstrair alguns campos
	
	VISAO:
	- classes baseadas em JFrame e Dialog que sao as
	telas (somente a parte de visao porque a parte de acoes e requisicoes estao na classe Listener)
	J

==============================================================================================================================
	Utilizei apenas 1 padrao de projeto, para separar a jframe com os desenhos das telas,
	dos listeners que contem as acoes dos botoes e requisicoes
	
	
	
	
bem simples, nao e do jeito que gosto, mas resolve o problema sem precisar trocar no cliente











