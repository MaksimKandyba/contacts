chmod +x build-service-jar.sh
./build-service-jar.sh

echo "stopping loaded database"
docker stop contacts-loaded-db
docker rm contacts-loaded-db

echo "building service image"
cd ..
docker build -t contacts .

echo "running service"
cd docker
docker-compose -f contacts.yml up -d