# Postgis docker container

To set up local container you need:

1. Run build script to build postgis docker image:

```bash
./build.sh
```

2. Run daemon script to run docker container:

```bash
./run-daemon.sh
```

3. Be sure these plugins were installed:

```
* Required
CREATE EXTENSION postgis;
CREATE EXTENSION postgis_topology;
* Optional
CREATE EXTENSION postgis_sfcgal;
CREATE EXTENSION fuzzystrmatch;
CREATE EXTENSION address_standardizer;
CREATE EXTENSION address_standardizer_data_us;
CREATE EXTENSION postgis_tiger_geocoder;
```

NOTE: default username=`playground-admin` and password=`changeme`.
Don't forget to change schema name `-e POSTGRES_SCHEMA=schema_name` at [run script](run-daemon.sh)