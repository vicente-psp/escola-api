-- criar tabela para lançamentos das faltas do aluno

CREATE SEQUENCE IF NOT EXISTS public.avaliacao_seq START 1;

CREATE TABLE public.avaliacao (
	id INT4 NOT NULL DEFAULT NEXTVAL('avaliacao_seq'),
	avaliacao_peso_id INT4 NOT NULL,
	aluno_id INT4 NOT NULL,
	nota NUMERIC(5,2) NOT NULL,
	peso NUMERIC(5,2) NOT NULL,
	ano_letivo CHAR(20) NOT NULL,
	CONSTRAINT fk_avaliacao_avaliacao_peso FOREIGN KEY (avaliacao_peso_id) REFERENCES avaliacao_peso(id),
	CONSTRAINT fk_avaliacao_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
	CONSTRAINT pk_avaliacao PRIMARY KEY (id)
);