# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table program (
  id                        bigint not null,
  name                      varchar(255),
  type                      varchar(255),
  owner_email               varchar(255),
  constraint pk_program primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (email))
;


create table program_user (
  program_id                     bigint not null,
  user_email                     varchar(255) not null,
  constraint pk_program_user primary key (program_id, user_email))
;
create sequence program_seq;

create sequence user_seq;

alter table program add constraint fk_program_owner_1 foreign key (owner_email) references user (email) on delete restrict on update restrict;
create index ix_program_owner_1 on program (owner_email);



alter table program_user add constraint fk_program_user_program_01 foreign key (program_id) references program (id) on delete restrict on update restrict;

alter table program_user add constraint fk_program_user_user_02 foreign key (user_email) references user (email) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists program;

drop table if exists program_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists program_seq;

drop sequence if exists user_seq;

