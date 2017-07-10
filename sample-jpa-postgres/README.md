Use a docker container for backend services:

docker run -p 5432:5432 --name postgresdb -e POSTGRES_PASSWORD=rootpasswd -d postgres
