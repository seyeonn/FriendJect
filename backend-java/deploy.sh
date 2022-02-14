echo "hello"
cd /home/ubuntu/app/backend_friendject
pwd

docker build -t friendject/backend:latest .

docker container stop friendject

docker rm friendject

docker run -d --name friendject -p 80:80 friendject/backend
