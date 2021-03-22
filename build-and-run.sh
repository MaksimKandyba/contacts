echo "building main database image"
cd docker/contacts-db
docker build -f Dockerfile-main -t contacts-main-db .

echo "building loaded database image"
docker build -f Dockerfile-loaded -t contacts-loaded-db .

echo "running main database"
docker --version
docker-compose --version
cd ..
docker-compose -f contacts-main-db.yml up -d

echo "running loaded database"
docker-compose -f contacts-loaded-db.yml up -d

echo "building service jar"
cd ..
mvn clean package

echo "stopping loaded database"
cd docker
docker stop contacts-loaded-db
docker rm contacts-loaded-db

echo "building service image"
cd ..
docker build -t contacts .

echo "running service"
cd docker
docker-compose -f contacts.yml up -d