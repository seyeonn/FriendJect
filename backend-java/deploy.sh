echo "hello"
cd /home/ubuntu/app/backend_friendject
pwd

docker build -t friendject/backend:latest .

docker container stop friendject

docker rm friendject

docker run -d --name friendject -p 8081:8081 friendject/backend
