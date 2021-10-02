run -> docker run -p 8080:8080 --network="host" api2
Bu sayede; Use --network="host" in your docker run command, then 127.0.0.1 in your docker container will point to your docker host.

Bu komut yalnızca docker linux' te çalışırmış.
