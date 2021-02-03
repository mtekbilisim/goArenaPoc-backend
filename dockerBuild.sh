cd dashboard_service
./gradlew bootBuildImage
cd ..
cd employee_service
./gradlew bootBuildImage
cd ..
cd  feed_service
./gradlew bootBuildImage
cd ..
cd  file_service
./gradlew bootBuildImage
cd ..
 cd  gateway 
 ./gradlew bootBuildImage
 cd ..
 cd  service_discovery
 ./gradlew bootBuildImage
 cd ..
 cd  auth_service
 ./gradlew bootBuildImage
 cd ..

docker-compose up -d --remove-orphans
