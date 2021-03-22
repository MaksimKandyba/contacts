echo "building main database image"
cd ../docker/contacts-db
docker build -f Dockerfile-main -t contacts-main-db .

echo "building loaded database image"
docker build -f Dockerfile-loaded -t contacts-loaded-db .

echo "running databases"
cd ..
docker-compose -f contacts-main-db.yml -f contacts-loaded-db.yml up -d

echo "building service jar"
cd ..
mvn clean package