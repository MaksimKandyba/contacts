chmod +x build-service-jar.sh
./build-service-jar.sh

echo "stopping databases"
cd ../docker
docker-compose -f contacts-main-db.yml -f contacts-loaded-db.yml down