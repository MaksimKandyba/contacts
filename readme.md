[![Build Status](https://travis-ci.org/MaksimKandyba/contacts.svg?branch=master)](https://travis-ci.org/MaksimKandyba/contacts)

to build and run\s\s
1 ensure ports 3333, 4444, and 4445 are free to be used\s\s
2 if they aren't free the rest service must be reconfigured\s\s
3 else continue by running build-and-run.sh\s\s
4 wait a few seconds till the service is available by http://localhost:3333/hello/contacts?nameFilter=val\s\s

to stop\s\s
1 change directory to docker/\s\s
2 run docker-compose -f contacts-main-db.yml -f contacts.yml down\s\s

to run already built\s\s
1 change directory to docker/\s\s
2 run docker-compose -f contacts-main-db.yml -f contacts.yml up -d\s\s