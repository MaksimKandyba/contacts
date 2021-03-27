chmod +x build-service-jar.sh
./build-service-jar.sh

echo "building main service image"
cd ..
docker build -f Dockerfile-main -t contacts-main .

echo "running main service with main database"
cd docker
docker-compose -f contacts-main.yml -f contacts-main-db.yml up -d