# business-manager-cloud
Docker project supporting the infrastructure for Business Manager B&C company 

##Components
* SpringCloudDiscovery
* SpringCloudConfig
* SpringCloudGateway

##Guide
https://docs.google.com/document/d/1r6OzWogvqeLi_tTzmVEyBMEgAVfwb0yRR2zLcznD4zs/edit#heading=h.rnw71jfqevn5

##Build the application
```
# Clean and build the artifact and create the docker images using com.spotify.dockerfile-maven-plugin
sudo mvn clean install
```

##Start the application
```
# Create the network to connect all services of Business Manager
docker network create BM_NETWORK

# Deploy services
docker-compose up -d
```

##Stop the application
```
docker-compose down
```

##Gateway routes configuration
https://docs.google.com/document/d/1Nl-01uY2qwOkfeiMySlihSKqqlsS3DFwrofJ-WgGyGY/edit#heading=h.f4xzo7fap63

##Releases

#Release 1
Contains the Config, Discovery and Gateway servers working with docker-compose  