FROM yugabytedb/yugabyte:2.17.0.0-b24

RUN yum install -y chrony

ARG POSTGRES_DB
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD

ENV CQLSH_HOST=yugabytedb

RUN mkdir -p /var/ybinit
RUN echo "create database ${POSTGRES_DB}" > /var/ybinit/01-db.sql
RUN echo "create user ${POSTGRES_USER} with password '${POSTGRES_PASSWORD}'" > /var/ybinit/02-usr.sql
RUN echo "grant all privileges on database ${POSTGRES_DB} to ${POSTGRES_USER}" > /var/ybinit/03-privs.sql

CMD ["/usr/local/bin/yugabyted","start","--daemon=false","--initial_scripts_dir=/var/ybinit","--tserver_flags=ysql_enable_auth=true"]
