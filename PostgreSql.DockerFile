FROM postgres
ENV POSTGRES_PASSWORD Qa1q2w3e4r5t!
COPY psql_dump.sql /docker-entrypoint-initdb.d/

