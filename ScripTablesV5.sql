﻿-- Scrip de Creacion de Base de Datos. Tablas Table: UserTbl

-- Scrip de Creacion de Base de Datos. Tablas Table: RolesTbl


CREATE TABLE public.rolTipo
(
   idRol SERIAL NOT NULL, 
   name character varying,
  CONSTRAINT PK_rol PRIMARY KEY (idRol)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE rolTipo 
  OWNER TO postgres;



-- //////////////////////////////////////////////////////////

CREATE TABLE people
(
  idPeople serial NOT NULL,
  name character varying,
  lastName character varying,
  email character varying UNIQUE,
  inDate timestamp without time zone,
  pasword character varying,
  jobTitle character varying,
  fotoLink character varying,
  foto     bytea,
  isChangePassword character(1),
  idRol  int NOT NULL,
  CONSTRAINT pk_people PRIMARY KEY (idPeople),
  CONSTRAINT ch_isChangePassword CHECK (isChangePassword = ANY (ARRAY['T'::bpchar, 'F'::bpchar]))
)
WITH (
  OIDS=FALSE
);
ALTER TABLE people
  OWNER TO postgres;

ALTER TABLE public.people
   ADD CONSTRAINT fk_idRol
   FOREIGN KEY (idRol) 
   REFERENCES rolTipo(idRol);

-- //////////////////////////////////////////////////////////
-- Scrip de Creacion de Base de Datos. Tablas Table: Skill;

CREATE TABLE public.skill
(  
  idSkill SERIAL NOT NULL, 
  name character varying,
  CONSTRAINT PK_Skill PRIMARY KEY (idSkill)

)
WITH (
  OIDS=FALSE
);
ALTER TABLE  skill
  OWNER TO postgres;

-- //////////////////////////////////////////////////////////

CREATE TABLE public.skillPeople
(
   idPeople int NOT NULL,
   idSkill int NOT NULL, 
   level character(1),
   updateDate timestamp without time zone,
  CONSTRAINT PK_SkillPeople PRIMARY KEY (idPeople, idSkill)

)
WITH (
  OIDS=FALSE
);
ALTER TABLE  skillPeople 
  OWNER TO postgres;

ALTER TABLE public.skillPeople  
   ADD CONSTRAINT fk_idPeople FOREIGN KEY (idPeople) REFERENCES people(idPeople);
ALTER TABLE public.skillPeople  
   ADD CONSTRAINT fk_idSkill FOREIGN KEY (idSkill) REFERENCES skill(idSkill);

-- //////////////////////////////////////////////////////////
-- Scrip de Creacion de Base de Datos. Tablas Table: LogTbl;


CREATE TABLE public.log
(
   idLog SERIAL NOT NULL, 
   actionLog character varying,
   dateTimeLog timestamp without time zone,
   idPeople int NOT NULL,
  CONSTRAINT PK_Log PRIMARY KEY (idLog)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE  log
  OWNER TO postgres;

ALTER TABLE public.log  
   ADD CONSTRAINT fk_idPeople FOREIGN KEY (idPeople) REFERENCES people(idPeople);