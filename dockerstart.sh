# Stop and Remove container if exists
sudo docker stop asmr-app-container
sudo docker rm asmr-app-container

# Pull docker image
sudo docker pull devindev/asmr:dev

# Run docker image
sudo docker run -it --name asmr-app-container -d -p 8080:8080 devindev/asmr:dev
sudo docker ps