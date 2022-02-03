#!/bin/bash
docker run --name psql -p 5432:5432  -e POSTGRES_PASSWORD=testPsw -e POSTGRES_USER=testUser -e POSTGRES_DB=student -d postgres

## Per accedere successivamente 
#docker exec -it psql psql student testuser




