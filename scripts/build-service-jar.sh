echo "building main database image"
cd ../docker
docker build -f Dockerfile-main-db -t contacts-main-db .

echo "building loaded database image"
docker build -f Dockerfile-loaded-db -t contacts-loaded-db .

echo "running databases"
docker-compose -f contacts-main-db.yml -f contacts-loaded-db.yml up -d

echo "sleeping till databases are up"
sleep 60
echo "waking up"

echo "packaging service jar"
cd ..
mvn clean package

echo "building loaded service image"
docker build -f Dockerfile-loaded -t contacts-loaded .

echo "running loaded service"
cd docker
docker-compose -f contacts-loaded.yml up -d

echo "sleeping till service is up"
sleep 60
echo "waking up"

echo "running performance tests"
cd ..
mvn verify -Dskip.surefire.tests=true

echo "stopping loaded service with databases"
cd docker
docker-compose -f contacts-loaded.yml -f contacts-main-db.yml -f contacts-loaded-db.yml down
