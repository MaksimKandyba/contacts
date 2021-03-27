#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL

    insert into public.contacts(name) values('Dr. Henry Jekyll');
    insert into public.contacts(name) values('Mr. Edward Hyde');
    insert into public.contacts(name) values('Gabriel John Utterson');
    insert into public.contacts(name) values('Richard Enfield');
    insert into public.contacts(name) values('Dr. Hastie Lanyon');
    insert into public.contacts(name) values('Mr. Poole');
    insert into public.contacts(name) values('Inspector Newcomen');
    insert into public.contacts(name) values('Sir Danvers Carew, MP');
    insert into public.contacts(name) values('Maid');

EOSQL