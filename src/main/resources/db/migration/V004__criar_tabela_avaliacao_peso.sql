-- criar tabela para peso da avaliação

CREATE SEQUENCE IF NOT EXISTS public.avaliacao_peso_seq START 1;

CREATE TABLE public.avaliacao_peso (
	id INT4 NOT NULL DEFAULT NEXTVAL('avaliacao_peso_seq'),
	descricao VARCHAR(50) NOT NULL,
	valor NUMERIC(5,2) NOT NULL,
	CONSTRAINT pk_avaliacao_peso PRIMARY KEY (id)
);


-- insere alunos que serão utilizados no teste
INSERT INTO public.avaliacao_peso
		(descricao, valor)
	VALUES
		('Participação em sala de aula', 1.5),
		('Entrega das tarefas', 2.5),
		('Trabalho bimestral', 3.0),
		('Prova bimestral', 3.0);