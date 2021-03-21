#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    do '
    begin
        for r in 1..1000000 loop
            insert into public.contacts(name) values(upper(substr(md5(random()::text), 0, 10)));
        end loop;
    end;
    ';

EOSQL