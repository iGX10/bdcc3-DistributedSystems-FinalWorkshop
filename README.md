# bdcc3-DistributedSystems-FinalWorkshop

## Backend & Frontend
Keycloak server version 12.0.1 is needed

## Kafka 
Broker Kafka version 2.13-2.7.0 is needed
And then creating the topic "FACTURATION" with the following command :
>$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic FACTURATION
