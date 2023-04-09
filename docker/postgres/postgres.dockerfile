FROM postgres:15.1-alpine

LABEL author="Georges DEFO"
LABEL description="Postgres Image for PDL database"
LABEL version="1.0"

COPY *.sql /docker-entrypoint-initdb.d/