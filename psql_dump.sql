create user "goArena" superuser createdb createrole replication bypassrls;
alter user "goArena" PASSWORD 'Qa1q2w3e!';
create database "goArena" with owner "goArena";
\connect goArena;
create schema if not exists feeds;
create schema if not exists users;
create schema if not exists dashboard;

