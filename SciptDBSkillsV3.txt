-- Scrip de Creacion de Base de Datos. Tablas Table: UserTbl

-- Scrip de Creacion de Base de Datos. Tablas Table: RolesTbl


CREATE TABLE public.rolTipo
(
   idRol SERIAL NOT NULL, 
   name character varying, 
   isCreater character(1),
   isReader character(1),
   isUpdater character(1),
   isDeleter character(1),
   isSeacher character(1),
  CONSTRAINT PK_rol PRIMARY KEY (idRol),
  CONSTRAINT ch_isCreater CHECK (isCreater = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
  CONSTRAINT ch_isReader CHECK (isReader = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
  CONSTRAINT ch_isUpdater CHECK (isUpdater = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
  CONSTRAINT ch_isDeleter CHECK (isDeleter  = ANY (ARRAY['T'::bpchar, 'F'::bpchar])),
  CONSTRAINT ch_isSeacher CHECK (isSeacher  = ANY (ARRAY['T'::bpchar, 'F'::bpchar]))
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
  CONSTRAINT PK_SkillPeople PRIMARY KEY (idPeople, idSkill),
  CONSTRAINT ch_levelSkill CHECK (level = ANY (ARRAY['0'::bpchar, '1'::bpchar, '2'::bpchar]))

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