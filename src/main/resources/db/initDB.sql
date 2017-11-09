
-- Таблица ролей
drop table if exists adm_user_roles;
drop table if exists adm_roles;
drop table if exists adm_users;
drop sequence if exists ids_generator;
create sequence ids_generator start 1000000; -- генерируем инкрементные ключи
create table adm_roles (
  id bigint primary key default nextval('ids_generator'),
  name varchar(64) not null,  -- наименование роли
  description varchar(255) null,  -- описание роли
  comment varchar(255) null -- комментарии
);

-- Собственно роли в порядке возрастания полномочий
insert into adm_roles (name, description)
values ('ROLE_ADMIN', 'Admin role - user/roles operation are available');

insert into adm_roles (name, description)
values ('ROLE_POWER', 'Power role - all (except non user/roles) operations are available');

insert into adm_roles (name, description)
values ('ROLE_USER', 'General standard user');

insert into adm_roles (name, description)
values ('ROLE_ANONYMOUS', 'Anonymous or guest access');

-- Таблица пользователей
create table adm_users(
  id bigint primary key default nextval('ids_generator'),
  name varchar(255) not null, -- login
  password varchar(60) not null, -- хеш пароля
  fio varchar(255) null,  -- ФИО пользователея
  enabled boolean not null default false, -- вкл/выкл пользователя
  activated boolean not null default false, -- активирован или еще нет
  email varchar(255) not null, -- адрес электронной почты
  date_create timestamp not null default current_timestamp, -- дата создания пользователя
  date_activate timestamp null, -- дата активации пользователя
  date_modify timestamp not null default current_timestamp, -- дата модификации записи пользователя в таблице
  last_activity timestamp not null default current_timestamp, -- дата последнего входа пользователя
  date_reset timestamp null, -- дата запроса сброса пароля пользователем
  avatar_img varchar(255) null, -- имя аватар файла
  activation_key varchar(32) null, -- активационный ключ, высылаемый на эл почту
  reset_key varchar(32) null, -- ключ сброса пароля, высылаемый на почту
  comment varchar(255) null, -- комментарии
  language varchar(32) not null default 'ru' -- язык интерфейса пользователя
);

create table adm_user_roles (
  user_id bigint not null, -- какому пользователю
  role_id bigint not null, -- какая роль соответствует
  constraint fk_authorities_users foreign key(user_id) references adm_users(id),
  constraint fk_authorities_roles foreign key(role_id) references adm_roles(id)
);


-- Создадим администратора системы с логином = admin и паролем 123456
insert into adm_users( name, password, fio, email, enabled, activated,date_activate, language)
values('admin','$2a$10$gC.2Zz0uBED2I/jPet6aWu1mAz2FhV8mt2xnzV9/iApuMnf8/0o7i', 'admin@admin.ru',
       'Administrator', true, true, current_timestamp, 'ru');

-- Присвоим пользователю admin роль = ADMIN
insert into adm_user_roles(user_id, role_id)
  select id, 1000000 from adm_users where name='admin';

