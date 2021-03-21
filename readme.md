[![Build Status](https://travis-ci.org/MaksimKandyba/contacts.svg?branch=master)](https://travis-ci.org/MaksimKandyba/contacts)

to build and run\
1 ensure ports 3333, 4444, and 4445 are free to be used\
2 if they aren't free the rest service must be reconfigured\
3 else continue by running build-and-run.sh\
4 wait a few seconds till the service is available by http://localhost:3333/hello/contacts?nameFilter=val\

to stop\
1 change directory to docker/\
2 run docker-compose -f contacts-main-db.yml -f contacts.yml down\

to run already built\
1 change directory to docker/\
2 run docker-compose -f contacts-main-db.yml -f contacts.yml up -d\